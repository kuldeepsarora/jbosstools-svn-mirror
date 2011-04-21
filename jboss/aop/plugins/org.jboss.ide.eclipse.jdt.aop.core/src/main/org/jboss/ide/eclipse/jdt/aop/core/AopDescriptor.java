/*
 * JBoss, the OpenSource J2EE webOS
 *
 * Distributable under LGPL license.
 * See terms of license at www.gnu.org.
 */
package org.jboss.ide.eclipse.jdt.aop.core;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.jboss.aop.AspectXmlLoader;
import org.jboss.aop.advice.Scope;
import org.jboss.ide.eclipse.jdt.aop.core.jaxb.Advice;
import org.jboss.ide.eclipse.jdt.aop.core.jaxb.Aop;
import org.jboss.ide.eclipse.jdt.aop.core.jaxb.Aspect;
import org.jboss.ide.eclipse.jdt.aop.core.jaxb.Binding;
import org.jboss.ide.eclipse.jdt.aop.core.jaxb.Interceptor;
import org.jboss.ide.eclipse.jdt.aop.core.jaxb.InterceptorRef;
import org.jboss.ide.eclipse.jdt.aop.core.jaxb.Pointcut;
import org.jboss.ide.eclipse.jdt.aop.core.util.JaxbAopUtil;

/**
 * @author Marshall
 */
public class AopDescriptor {
	
	private Aop aop;
	private File file;

	/**
	 * @return Returns the aop.
	 */
	public Aop getAop() {
		return aop;
	}
	/**
	 * @param aop The aop to set.
	 */
	public void setAop(Aop aop) {
		this.aop = aop;
	}
	/**
	 * @return Returns the file.
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file The file to set.
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	public boolean equals (Object other)
	{
		if (other instanceof AopDescriptor)
		{
			AopDescriptor otherDescriptor = (AopDescriptor) other;
			return equalsFile(otherDescriptor.getFile());
		}
		else if (other instanceof File)
		{
			return equalsFile((File) other);
		}
		return false;
	}
	
	private boolean equalsFile(File other)
	{
		if (other == null || getFile() == null)
			return false;
		
		if (other.getPath() == null || getFile().getPath() == null)
			return false;
		
		return other.getPath().equals(getFile().getPath());
	}
	
	public Binding findBinding (String pointcut)
	{
		List binds = getAop().getBindings();
		Iterator bIter = binds.iterator();
		while (bIter.hasNext())
		{
			Binding binding = (Binding) bIter.next();
			if (binding.getPointcut() != null)
			{
				if (binding.getPointcut().equals(pointcut)) return binding;
			}
		}
		
		try {
			// No binding found -- create a new one and return it
			Binding binding = JaxbAopUtil.instance().getFactory().createBinding();
			binding.setPointcut(pointcut);
			getAop().getBindings().add(binding);
			
			return binding;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Aspect findAspect (String className)
	{
		return findAspect(className, "PER_VM");
	}
	
	private Aspect findAspect (String className, String scope)
	{
		List aspects = getAop().getAspects();
		Iterator aIter = aspects.iterator();
		while (aIter.hasNext())
		{
			Aspect aspect = (Aspect) aIter.next();
			if (aspect.getClazz() != null)
			{
				if (aspect.getClazz().equals(className)) {
					return aspect;
				}
			}
		}
		
		try {
			// No aspect found, create new one and return it
			Aspect aspect = JaxbAopUtil.instance().getFactory().createAspect();
			aspect.setClazz(className);
			aspect.setScope(scope);
			getAop().getAspects().add(aspect);
			
			return aspect;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Interceptor bindInterceptor (String pointcut, String className)
	{
		try {
			Binding binding = findBinding(pointcut);
			Interceptor interceptor = JaxbAopUtil.instance().getFactory().createInterceptor();
			interceptor.setClazz(className);
			
			binding.getInterceptors().add(interceptor);
			return interceptor;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void bindInterceptorRef (String pointcut, String name)
	{
		try {
			Binding binding = findBinding(pointcut);
			InterceptorRef interceptorRef = JaxbAopUtil.instance().getFactory().createInterceptorRef();
			interceptorRef.setName(name);
			
			binding.getInterceptorRefs().add(interceptorRef);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Advice bindAdvice (String pointcut, String aspectClass, String adviceName)
	{
		try {
			Binding binding = findBinding(pointcut);
			Aspect aspect = findAspect(aspectClass);
			
			Advice advice = JaxbAopUtil.instance().getFactory().createAdvice();
			advice.setAspect(aspect.getClazz());
			advice.setName(adviceName);
			
			binding.getAdvised().add(advice);
			return advice;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addAspect(String className)
	{
		addAspect(className, Scope.PER_VM);
	}
	
	public void addAspect(String className, Scope scope)
	{
		try {
			List aspects = getAop().getAspects();
			Aspect aspect = JaxbAopUtil.instance().getFactory().createAspect();
			aspect.setClazz(className);
			aspect.setScope(scope.name());
			
			aspects.add(aspect);	
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void addPointcut(String name, String expr)
	{
		try {
			List pointcuts = getAop().getPointcuts();
			Pointcut pointcut = JaxbAopUtil.instance().getFactory().createPointcut();
			pointcut.setName(name);
			pointcut.setExpr(expr);
			
			pointcuts.add(pointcut);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public List getParent (Interceptor interceptor)
	{
		if (getAop().getInterceptors().contains(interceptor)) return getAop().getInterceptors();
		else {
			Iterator bIter = getAop().getBindings().iterator();
			while (bIter.hasNext())
			{
				Binding binding = (Binding) bIter.next();
				if (binding.getInterceptors().contains(interceptor))
				{
					return binding.getInterceptors();
				}
			}
		}
		
		return null;
	}
	
	public List getParent (Advice advice)
	{
		Iterator bIter = getAop().getBindings().iterator();
		while (bIter.hasNext())
		{
			Binding binding = (Binding) bIter.next();
			if (binding.getAdvised().contains(advice))
			{
				return binding.getAdvised();
			}
		}
		return null;
	}
	
	public List getParent (InterceptorRef interceptorRef)
	{
		Iterator bIter = getAop().getBindings().iterator();
		while (bIter.hasNext())
		{
			Binding binding = (Binding) bIter.next();
			if (binding.getInterceptorRefs().contains(interceptorRef))
			{
				return binding.getInterceptorRefs();
			}
		}
		return null;
	}
	
	public void remove (Object object)
	{
		List parent = null;
		if (object instanceof Interceptor)
			parent = getParent ((Interceptor) object);
		else if (object instanceof Advice)
			parent = getParent ((Advice) object);
		else if (object instanceof InterceptorRef)
			parent = getParent ((InterceptorRef) object);
		else if (object instanceof Binding)
		{
			if (getAop().getBindings().contains(object))
			{
				parent = getAop().getBindings();
			}
		}
		
		if (parent != null)
		{
			parent.remove(object);
		}
	}
	
	public void update ()
	{
		this.aop = JaxbAopUtil.instance().unmarshal(getFile());
	}
	
	public void save ()
	{
		JaxbAopUtil.instance().marshal(aop, file);
		
		try {
			System.out.println("[aop-descriptor] re-deploying descriptor..");
			AspectXmlLoader.deployXML(getFile().toURL());
			
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IWorkspaceRoot.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
