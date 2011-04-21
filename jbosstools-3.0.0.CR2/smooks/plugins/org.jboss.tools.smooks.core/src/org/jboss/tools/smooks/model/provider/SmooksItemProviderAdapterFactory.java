/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.smooks.model.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.jboss.tools.smooks.model.util.SmooksAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SmooksItemProviderAdapterFactory extends SmooksAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SmooksItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.ConditionType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionTypeItemProvider conditionTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.ConditionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConditionTypeAdapter() {
		if (conditionTypeItemProvider == null) {
			conditionTypeItemProvider = new ConditionTypeItemProvider(this);
		}

		return conditionTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.DocumentRoot} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootItemProvider documentRootItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.DocumentRoot}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDocumentRootAdapter() {
		if (documentRootItemProvider == null) {
			documentRootItemProvider = new DocumentRootItemProvider(this);
		}

		return documentRootItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.ImportType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportTypeItemProvider importTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.ImportType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createImportTypeAdapter() {
		if (importTypeItemProvider == null) {
			importTypeItemProvider = new ImportTypeItemProvider(this);
		}

		return importTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.ParamType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParamTypeItemProvider paramTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.ParamType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createParamTypeAdapter() {
		if (paramTypeItemProvider == null) {
			paramTypeItemProvider = new ParamTypeItemProvider(this);
		}

		return paramTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.ProfilesType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfilesTypeItemProvider profilesTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.ProfilesType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProfilesTypeAdapter() {
		if (profilesTypeItemProvider == null) {
			profilesTypeItemProvider = new ProfilesTypeItemProvider(this);
		}

		return profilesTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.ProfileType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfileTypeItemProvider profileTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.ProfileType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProfileTypeAdapter() {
		if (profileTypeItemProvider == null) {
			profileTypeItemProvider = new ProfileTypeItemProvider(this);
		}

		return profileTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.ResourceConfigType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceConfigTypeItemProvider resourceConfigTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.ResourceConfigType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createResourceConfigTypeAdapter() {
		if (resourceConfigTypeItemProvider == null) {
			resourceConfigTypeItemProvider = new ResourceConfigTypeItemProvider(this);
		}

		return resourceConfigTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.ResourceType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceTypeItemProvider resourceTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.ResourceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createResourceTypeAdapter() {
		if (resourceTypeItemProvider == null) {
			resourceTypeItemProvider = new ResourceTypeItemProvider(this);
		}

		return resourceTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.jboss.tools.smooks.model.SmooksResourceListType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SmooksResourceListTypeItemProvider smooksResourceListTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.jboss.tools.smooks.model.SmooksResourceListType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSmooksResourceListTypeAdapter() {
		if (smooksResourceListTypeItemProvider == null) {
			smooksResourceListTypeItemProvider = new SmooksResourceListTypeItemProvider(this);
		}

		return smooksResourceListTypeItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (conditionTypeItemProvider != null) conditionTypeItemProvider.dispose();
		if (documentRootItemProvider != null) documentRootItemProvider.dispose();
		if (importTypeItemProvider != null) importTypeItemProvider.dispose();
		if (paramTypeItemProvider != null) paramTypeItemProvider.dispose();
		if (profilesTypeItemProvider != null) profilesTypeItemProvider.dispose();
		if (profileTypeItemProvider != null) profileTypeItemProvider.dispose();
		if (resourceConfigTypeItemProvider != null) resourceConfigTypeItemProvider.dispose();
		if (resourceTypeItemProvider != null) resourceTypeItemProvider.dispose();
		if (smooksResourceListTypeItemProvider != null) smooksResourceListTypeItemProvider.dispose();
	}

}
