package org.jboss.tools.deltacloud.ui.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.jboss.tools.deltacloud.core.DeltaCloud;
import org.jboss.tools.deltacloud.core.DeltaCloudException;
import org.jboss.tools.deltacloud.core.DeltaCloudInstance;

public class PerformInstanceActionThread extends Job {
	private DeltaCloud cloud;
	private DeltaCloudInstance instance;
	private String action;
	private String taskName;
	private String expectedState;
	
 	public PerformInstanceActionThread(DeltaCloud cloud, DeltaCloudInstance instance, 
 			String action, String title, String taskName, String expectedState) {
 		super(title);
 		this.cloud = cloud;
 		this.instance = instance;
 		this.action = action;
 		this.taskName = taskName;
 		this.expectedState = expectedState;
 	}
 	
	@Override
	public IStatus run(IProgressMonitor pm) {
		String id = instance.getId();
		try {
			pm.beginTask(taskName, IProgressMonitor.UNKNOWN);
			pm.worked(1);
			// To handle the user starting a new action when we haven't confirmed the last one yet,
			// cancel the previous job and then go on performing this action
			Job job = cloud.getActionJob(id);
			if (job != null) {
				job.cancel();
				try {
					job.join();
				} catch (InterruptedException e) {
					// do nothing, this is ok
				}
			}
			cloud.performInstanceAction(id, action);
			while (instance != null && !(instance.getState().equals(expectedState))
					&& !(instance.getState().equals(DeltaCloudInstance.TERMINATED))) {
				instance = cloud.refreshInstance(id);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					break;
				}
			}
		} catch (DeltaCloudException e) {
			// do nothing..action had problem executing..perhaps illegal
		} finally {
			cloud.removeActionJob(id, this);
			pm.done();
		}
		return Status.OK_STATUS;
	}
}
