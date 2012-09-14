#!/bin/sh
# This script is run here: http://hudson.qa.jboss.com/hudson/job/jbosstools-cleanup/configure
# And archived here: https://svn.jboss.org/repos/devstudio/trunk/releng/org.jboss.ide.eclipse.releng/hudson/jbosstools-cleanup.sh
# --------------------------------------------------------------------------------
# clean JBT builds from sftp://tools@filemgmt.jboss.org/downloads_htdocs/tools/builds/nightly

now=$(date +%s)
log=/tmp/${0##*/}.log.`date +%Y%m%d-%H%M`.txt

echo "Logfile: $log" | tee -a $log
echo "" | tee -a $log

getSubDirs () 
{
	getSubDirsReturn="";
	tab="";
	if [[ $1 ]]; then dir="$1"; else dir="/downloads_htdocs/tools/builds/nightly/"; fi
	if [[ $2 ]] && [[ $2 -gt 0 ]]; then
		lev=$2
		while [[ $lev -gt 0 ]]; do  
			tab=$tab"> ";
			(( lev-- ));
		done
	fi
	echo "${tab}Check $dir..." | tee -a $log
	tmp=`mktemp`
	echo "ls $dir" > $tmp
	dirs=$(sftp -b $tmp tools@filemgmt.jboss.org 2>/dev/null)
	i=0
	for c in $dirs; do
		if [[ $i -gt 2 ]] && [[ $c != "sftp>" ]] && [[ ${c##*.} != "" ]]; then # valid dir
			getSubDirsReturn=$getSubDirsReturn" "$c
		fi
		(( i++ ))
	done
	rm -f $tmp
}

# Check for $type builds more than $threshhold days old; keep minimum $numkeep builds per branch
clean () 
{
	type=$1 # nightly or release
	numkeep=$2 # number of builds to keep per branch
	threshhold=$3 # age at which a build is available for delete
	echo "Check for $type builds more than $threshhold days old; keep minimum $numkeep builds per branch" | tee -a $log 

	getSubDirs /downloads_htdocs/tools/builds/$type/ 0
	subdirs=$getSubDirsReturn
	for sd in $subdirs; do
		getSubDirs $sd 1
		subsubdirs=$getSubDirsReturn
		#echo $subsubdirs
		tmp=`mktemp`
		for ssd in $subsubdirs; do
			if [[ ${ssd##$sd/201*} == "" ]]; then # a build dir
				buildid=${ssd##*/};  
				echo $buildid >> $tmp
			fi
		done
		newest=$(cat $tmp | sort -r | head -$numkeep) # keep these
		all=$(cat $tmp | sort -r) # check these
		rm -f $tmp
		for dd in $all; do
			keep=0;
			# sec=$(date -d "$(echo $dd | perl -pe "s/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})/\1-\2-\3\ \4:\5/")" +%s) # convert buildID (folder) to timestamp, then to # seconds since epoch ## OLD FOLDER FORMAT
			sec=$(date -d "$(echo $dd | perl -pe "s/(\d{4})-(\d{2})-(\d{2})_(\d{2})-(\d{2})-(\d{2})-H(\d+)/\1-\2-\3\ \4:\5:\6/")" +%s) # convert buildID (folder) to timestamp, then to # seconds since epoch ## NEW FOLDER FORMAT
			(( day = now - sec )) 
			(( day = day / 3600 / 24 ))
			for n in $newest; do
				if [[ $dd == $n ]] || [[ $day -le $threshhold ]]; then
					keep=1
				fi
			done
			if [[ $keep -eq 0 ]]; then
				echo -n "- $sd/$dd (${day}d)... " | tee -a $log
				if [[ $USER == "hudson" ]]; then
					# can't delete the dir, but can at least purge its contents
					rm -fr /tmp/$dd; mkdir /tmp/$dd; pushd /tmp/$dd >/dev/null
					rsync -r --delete . tools@filemgmt.jboss.org:$sd/$dd 2>&1 | tee -a $log
					echo -e "rmdir $dd" | sftp tools@filemgmt.jboss.org:$sd/
					popd >/dev/null; rm -fr /tmp/$dd
				fi
				echo "" | tee -a $log
			else
				echo "+ $sd/$dd (${day}d)" | tee -a $log
			fi
		done
	done
	echo "" | tee -a $log	
}
clean nightly/core 1 2
clean nightly/coretests 1 2
clean nightly/soa-tooling 1 2
clean nightly/soatests 1 2
clean nightly/webtools 1 2
clean nightly/bottests 1 2

