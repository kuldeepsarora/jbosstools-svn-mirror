/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.smooks.model.smooks.impl;





import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.jboss.tools.smooks.model.common.CommonPackage;
import org.jboss.tools.smooks.model.common.impl.CommonPackageImpl;
import org.jboss.tools.smooks.model.smooks.AbstractReader;
import org.jboss.tools.smooks.model.smooks.AbstractResourceConfig;
import org.jboss.tools.smooks.model.smooks.ConditionType;
import org.jboss.tools.smooks.model.smooks.ConditionsType;
import org.jboss.tools.smooks.model.smooks.DocumentRoot;
import org.jboss.tools.smooks.model.smooks.ElementVisitor;
import org.jboss.tools.smooks.model.smooks.FeaturesType;
import org.jboss.tools.smooks.model.smooks.HandlerType;
import org.jboss.tools.smooks.model.smooks.HandlersType;
import org.jboss.tools.smooks.model.smooks.ImportType;
import org.jboss.tools.smooks.model.smooks.ParamType;
import org.jboss.tools.smooks.model.smooks.ParamsType;
import org.jboss.tools.smooks.model.smooks.ProfileType;
import org.jboss.tools.smooks.model.smooks.ProfilesType;
import org.jboss.tools.smooks.model.smooks.ReaderType;
import org.jboss.tools.smooks.model.smooks.ResourceConfigType;
import org.jboss.tools.smooks.model.smooks.ResourceType;
import org.jboss.tools.smooks.model.smooks.SetOffType;
import org.jboss.tools.smooks.model.smooks.SetOnType;
import org.jboss.tools.smooks.model.smooks.SmooksFactory;
import org.jboss.tools.smooks.model.smooks.SmooksPackage;
import org.jboss.tools.smooks.model.smooks.SmooksResourceListType;
import org.jboss.tools.smooks.model.xsl.XslPackage;
import org.jboss.tools.smooks.model.xsl.impl.XslPackageImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SmooksPackageImpl extends EPackageImpl implements SmooksPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractReaderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractResourceConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementVisitorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featuresTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass handlersTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass handlerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paramsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass paramTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profilesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profileTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass readerTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceConfigTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setOffTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setOnTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass smooksResourceListTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.jboss.tools.smooks.model.smooks.SmooksPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SmooksPackageImpl() {
		super(eNS_URI, SmooksFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SmooksPackage init() {
		if (isInited) return (SmooksPackage)EPackage.Registry.INSTANCE.getEPackage(SmooksPackage.eNS_URI);

		// Obtain or create and register package
		SmooksPackageImpl theSmooksPackage = (SmooksPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SmooksPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SmooksPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		XslPackageImpl theXslPackage = (XslPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XslPackage.eNS_URI) instanceof XslPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XslPackage.eNS_URI) : XslPackage.eINSTANCE);
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);

		// Create package meta-data objects
		theSmooksPackage.createPackageContents();
		theXslPackage.createPackageContents();
		theCommonPackage.createPackageContents();

		// Initialize created meta-data
		theSmooksPackage.initializePackageContents();
		theXslPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSmooksPackage.freeze();

		return theSmooksPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractReader() {
		return abstractReaderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractReader_TargetProfile() {
		return (EAttribute)abstractReaderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractResourceConfig() {
		return abstractResourceConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionsType() {
		return conditionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionsType_Condition() {
		return (EReference)conditionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionType() {
		return conditionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Value() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Evaluator() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_Id() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionType_IdRef() {
		return (EAttribute)conditionTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_AbstractReader() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_AbstractResourceConfig() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Condition() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Conditions() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ElementVisitor() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Features() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Handler() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Handlers() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Import() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Param() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Params() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Profile() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Profiles() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Reader() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Resource() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ResourceConfig() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SetOff() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SetOn() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SmooksResourceList() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementVisitor() {
		return elementVisitorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementVisitor_Condition() {
		return (EReference)elementVisitorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElementVisitor_TargetProfile() {
		return (EAttribute)elementVisitorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeaturesType() {
		return featuresTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeaturesType_SetOn() {
		return (EReference)featuresTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeaturesType_SetOff() {
		return (EReference)featuresTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHandlersType() {
		return handlersTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHandlersType_Handler() {
		return (EReference)handlersTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHandlerType() {
		return handlerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHandlerType_Class() {
		return (EAttribute)handlerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImportType() {
		return importTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImportType_Param() {
		return (EReference)importTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportType_File() {
		return (EAttribute)importTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParamsType() {
		return paramsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParamsType_Param() {
		return (EReference)paramsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParamType() {
		return paramTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParamType_Name() {
		return (EAttribute)paramTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParamType_Type() {
		return (EAttribute)paramTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProfilesType() {
		return profilesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProfilesType_Profile() {
		return (EReference)profilesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProfileType() {
		return profileTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProfileType_Value() {
		return (EAttribute)profileTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProfileType_BaseProfile() {
		return (EAttribute)profileTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProfileType_SubProfiles() {
		return (EAttribute)profileTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReaderType() {
		return readerTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReaderType_Handlers() {
		return (EReference)readerTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReaderType_Features() {
		return (EReference)readerTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReaderType_Params() {
		return (EReference)readerTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReaderType_Class() {
		return (EAttribute)readerTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceConfigType() {
		return resourceConfigTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceConfigType_Resource() {
		return (EReference)resourceConfigTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceConfigType_Condition() {
		return (EReference)resourceConfigTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceConfigType_Param() {
		return (EReference)resourceConfigTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceConfigType_Selector() {
		return (EAttribute)resourceConfigTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceConfigType_SelectorNamespace() {
		return (EAttribute)resourceConfigTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceConfigType_TargetProfile() {
		return (EAttribute)resourceConfigTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceType() {
		return resourceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceType_Value() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceType_Type() {
		return (EAttribute)resourceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSetOffType() {
		return setOffTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSetOffType_Feature() {
		return (EAttribute)setOffTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSetOnType() {
		return setOnTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSetOnType_Feature() {
		return (EAttribute)setOnTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSmooksResourceListType() {
		return smooksResourceListTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSmooksResourceListType_Params() {
		return (EReference)smooksResourceListTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSmooksResourceListType_Conditions() {
		return (EReference)smooksResourceListTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSmooksResourceListType_Profiles() {
		return (EReference)smooksResourceListTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSmooksResourceListType_AbstractReaderGroup() {
		return (EAttribute)smooksResourceListTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSmooksResourceListType_AbstractReader() {
		return (EReference)smooksResourceListTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSmooksResourceListType_AbstractResourceConfigGroup() {
		return (EAttribute)smooksResourceListTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSmooksResourceListType_AbstractResourceConfig() {
		return (EReference)smooksResourceListTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSmooksResourceListType_DefaultConditionRef() {
		return (EAttribute)smooksResourceListTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSmooksResourceListType_DefaultSelector() {
		return (EAttribute)smooksResourceListTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSmooksResourceListType_DefaultSelectorNamespace() {
		return (EAttribute)smooksResourceListTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSmooksResourceListType_DefaultTargetProfile() {
		return (EAttribute)smooksResourceListTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SmooksFactory getSmooksFactory() {
		return (SmooksFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		abstractReaderEClass = createEClass(ABSTRACT_READER);
		createEAttribute(abstractReaderEClass, ABSTRACT_READER__TARGET_PROFILE);

		abstractResourceConfigEClass = createEClass(ABSTRACT_RESOURCE_CONFIG);

		conditionsTypeEClass = createEClass(CONDITIONS_TYPE);
		createEReference(conditionsTypeEClass, CONDITIONS_TYPE__CONDITION);

		conditionTypeEClass = createEClass(CONDITION_TYPE);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__VALUE);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__EVALUATOR);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__ID);
		createEAttribute(conditionTypeEClass, CONDITION_TYPE__ID_REF);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ABSTRACT_READER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ABSTRACT_RESOURCE_CONFIG);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONDITION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONDITIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ELEMENT_VISITOR);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FEATURES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__HANDLER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__HANDLERS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__IMPORT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PARAM);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PARAMS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROFILE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROFILES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__READER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RESOURCE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RESOURCE_CONFIG);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SET_OFF);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SET_ON);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SMOOKS_RESOURCE_LIST);

		elementVisitorEClass = createEClass(ELEMENT_VISITOR);
		createEReference(elementVisitorEClass, ELEMENT_VISITOR__CONDITION);
		createEAttribute(elementVisitorEClass, ELEMENT_VISITOR__TARGET_PROFILE);

		featuresTypeEClass = createEClass(FEATURES_TYPE);
		createEReference(featuresTypeEClass, FEATURES_TYPE__SET_ON);
		createEReference(featuresTypeEClass, FEATURES_TYPE__SET_OFF);

		handlersTypeEClass = createEClass(HANDLERS_TYPE);
		createEReference(handlersTypeEClass, HANDLERS_TYPE__HANDLER);

		handlerTypeEClass = createEClass(HANDLER_TYPE);
		createEAttribute(handlerTypeEClass, HANDLER_TYPE__CLASS);

		importTypeEClass = createEClass(IMPORT_TYPE);
		createEReference(importTypeEClass, IMPORT_TYPE__PARAM);
		createEAttribute(importTypeEClass, IMPORT_TYPE__FILE);

		paramsTypeEClass = createEClass(PARAMS_TYPE);
		createEReference(paramsTypeEClass, PARAMS_TYPE__PARAM);

		paramTypeEClass = createEClass(PARAM_TYPE);
		createEAttribute(paramTypeEClass, PARAM_TYPE__NAME);
		createEAttribute(paramTypeEClass, PARAM_TYPE__TYPE);

		profilesTypeEClass = createEClass(PROFILES_TYPE);
		createEReference(profilesTypeEClass, PROFILES_TYPE__PROFILE);

		profileTypeEClass = createEClass(PROFILE_TYPE);
		createEAttribute(profileTypeEClass, PROFILE_TYPE__VALUE);
		createEAttribute(profileTypeEClass, PROFILE_TYPE__BASE_PROFILE);
		createEAttribute(profileTypeEClass, PROFILE_TYPE__SUB_PROFILES);

		readerTypeEClass = createEClass(READER_TYPE);
		createEReference(readerTypeEClass, READER_TYPE__HANDLERS);
		createEReference(readerTypeEClass, READER_TYPE__FEATURES);
		createEReference(readerTypeEClass, READER_TYPE__PARAMS);
		createEAttribute(readerTypeEClass, READER_TYPE__CLASS);

		resourceConfigTypeEClass = createEClass(RESOURCE_CONFIG_TYPE);
		createEReference(resourceConfigTypeEClass, RESOURCE_CONFIG_TYPE__RESOURCE);
		createEReference(resourceConfigTypeEClass, RESOURCE_CONFIG_TYPE__CONDITION);
		createEReference(resourceConfigTypeEClass, RESOURCE_CONFIG_TYPE__PARAM);
		createEAttribute(resourceConfigTypeEClass, RESOURCE_CONFIG_TYPE__SELECTOR);
		createEAttribute(resourceConfigTypeEClass, RESOURCE_CONFIG_TYPE__SELECTOR_NAMESPACE);
		createEAttribute(resourceConfigTypeEClass, RESOURCE_CONFIG_TYPE__TARGET_PROFILE);

		resourceTypeEClass = createEClass(RESOURCE_TYPE);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__VALUE);
		createEAttribute(resourceTypeEClass, RESOURCE_TYPE__TYPE);

		setOffTypeEClass = createEClass(SET_OFF_TYPE);
		createEAttribute(setOffTypeEClass, SET_OFF_TYPE__FEATURE);

		setOnTypeEClass = createEClass(SET_ON_TYPE);
		createEAttribute(setOnTypeEClass, SET_ON_TYPE__FEATURE);

		smooksResourceListTypeEClass = createEClass(SMOOKS_RESOURCE_LIST_TYPE);
		createEReference(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__PARAMS);
		createEReference(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__CONDITIONS);
		createEReference(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__PROFILES);
		createEAttribute(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__ABSTRACT_READER_GROUP);
		createEReference(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__ABSTRACT_READER);
		createEAttribute(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__ABSTRACT_RESOURCE_CONFIG_GROUP);
		createEReference(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__ABSTRACT_RESOURCE_CONFIG);
		createEAttribute(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__DEFAULT_CONDITION_REF);
		createEAttribute(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__DEFAULT_SELECTOR);
		createEAttribute(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__DEFAULT_SELECTOR_NAMESPACE);
		createEAttribute(smooksResourceListTypeEClass, SMOOKS_RESOURCE_LIST_TYPE__DEFAULT_TARGET_PROFILE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractReaderEClass.getESuperTypes().add(this.getAbstractResourceConfig());
		abstractResourceConfigEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		conditionsTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		conditionTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		elementVisitorEClass.getESuperTypes().add(this.getAbstractResourceConfig());
		featuresTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		handlersTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		handlerTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		importTypeEClass.getESuperTypes().add(this.getAbstractResourceConfig());
		paramsTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		paramTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		profilesTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		profileTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		readerTypeEClass.getESuperTypes().add(this.getAbstractReader());
		resourceConfigTypeEClass.getESuperTypes().add(this.getAbstractResourceConfig());
		resourceTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		setOffTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		setOnTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		smooksResourceListTypeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractReaderEClass, AbstractReader.class, "AbstractReader", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractReader_TargetProfile(), theXMLTypePackage.getString(), "targetProfile", null, 0, 1, AbstractReader.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractResourceConfigEClass, AbstractResourceConfig.class, "AbstractResourceConfig", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conditionsTypeEClass, ConditionsType.class, "ConditionsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionsType_Condition(), this.getConditionType(), null, "condition", null, 1, -1, ConditionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionTypeEClass, ConditionType.class, "ConditionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConditionType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ConditionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionType_Evaluator(), theXMLTypePackage.getString(), "evaluator", "org.milyn.javabean.expression.BeanMapExpressionEvaluator", 0, 1, ConditionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionType_Id(), theXMLTypePackage.getString(), "id", null, 0, 1, ConditionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionType_IdRef(), theXMLTypePackage.getString(), "idRef", null, 0, 1, ConditionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_AbstractReader(), this.getAbstractReader(), null, "abstractReader", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_AbstractResourceConfig(), this.getAbstractResourceConfig(), null, "abstractResourceConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Condition(), this.getConditionType(), null, "condition", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Conditions(), this.getConditionsType(), null, "conditions", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ElementVisitor(), this.getElementVisitor(), null, "elementVisitor", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Features(), this.getFeaturesType(), null, "features", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Handler(), this.getHandlerType(), null, "handler", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Handlers(), this.getHandlersType(), null, "handlers", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Import(), this.getImportType(), null, "import", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Param(), this.getParamType(), null, "param", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Params(), this.getParamsType(), null, "params", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Profile(), this.getProfileType(), null, "profile", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Profiles(), this.getProfilesType(), null, "profiles", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Reader(), this.getReaderType(), null, "reader", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Resource(), this.getResourceType(), null, "resource", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ResourceConfig(), this.getResourceConfigType(), null, "resourceConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SetOff(), this.getSetOffType(), null, "setOff", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SetOn(), this.getSetOnType(), null, "setOn", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SmooksResourceList(), this.getSmooksResourceListType(), null, "smooksResourceList", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(elementVisitorEClass, ElementVisitor.class, "ElementVisitor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementVisitor_Condition(), this.getConditionType(), null, "condition", null, 0, 1, ElementVisitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementVisitor_TargetProfile(), theXMLTypePackage.getString(), "targetProfile", null, 0, 1, ElementVisitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(featuresTypeEClass, FeaturesType.class, "FeaturesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeaturesType_SetOn(), this.getSetOnType(), null, "setOn", null, 0, -1, FeaturesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeaturesType_SetOff(), this.getSetOffType(), null, "setOff", null, 0, -1, FeaturesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(handlersTypeEClass, HandlersType.class, "HandlersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHandlersType_Handler(), this.getHandlerType(), null, "handler", null, 1, -1, HandlersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(handlerTypeEClass, HandlerType.class, "HandlerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHandlerType_Class(), theXMLTypePackage.getString(), "class", null, 1, 1, HandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(importTypeEClass, ImportType.class, "ImportType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImportType_Param(), this.getParamType(), null, "param", null, 0, -1, ImportType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImportType_File(), theXMLTypePackage.getAnyURI(), "file", null, 1, 1, ImportType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(paramsTypeEClass, ParamsType.class, "ParamsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParamsType_Param(), this.getParamType(), null, "param", null, 1, -1, ParamsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(paramTypeEClass, ParamType.class, "ParamType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParamType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ParamType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParamType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, ParamType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(profilesTypeEClass, ProfilesType.class, "ProfilesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProfilesType_Profile(), this.getProfileType(), null, "profile", null, 1, -1, ProfilesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(profileTypeEClass, ProfileType.class, "ProfileType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProfileType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ProfileType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProfileType_BaseProfile(), theXMLTypePackage.getString(), "baseProfile", null, 1, 1, ProfileType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProfileType_SubProfiles(), theXMLTypePackage.getString(), "subProfiles", null, 0, 1, ProfileType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(readerTypeEClass, ReaderType.class, "ReaderType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReaderType_Handlers(), this.getHandlersType(), null, "handlers", null, 0, 1, ReaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReaderType_Features(), this.getFeaturesType(), null, "features", null, 0, 1, ReaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReaderType_Params(), this.getParamsType(), null, "params", null, 0, 1, ReaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReaderType_Class(), theXMLTypePackage.getString(), "class", null, 0, 1, ReaderType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceConfigTypeEClass, ResourceConfigType.class, "ResourceConfigType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceConfigType_Resource(), this.getResourceType(), null, "resource", null, 0, 1, ResourceConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceConfigType_Condition(), this.getConditionType(), null, "condition", null, 0, 1, ResourceConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceConfigType_Param(), this.getParamType(), null, "param", null, 0, -1, ResourceConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceConfigType_Selector(), theXMLTypePackage.getString(), "selector", null, 0, 1, ResourceConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceConfigType_SelectorNamespace(), theXMLTypePackage.getAnyURI(), "selectorNamespace", null, 0, 1, ResourceConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceConfigType_TargetProfile(), theXMLTypePackage.getString(), "targetProfile", null, 0, 1, ResourceConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceTypeEClass, ResourceType.class, "ResourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceType_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, ResourceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(setOffTypeEClass, SetOffType.class, "SetOffType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSetOffType_Feature(), theXMLTypePackage.getAnyURI(), "feature", null, 1, 1, SetOffType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(setOnTypeEClass, SetOnType.class, "SetOnType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSetOnType_Feature(), theXMLTypePackage.getAnyURI(), "feature", null, 1, 1, SetOnType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(smooksResourceListTypeEClass, SmooksResourceListType.class, "SmooksResourceListType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSmooksResourceListType_Params(), this.getParamsType(), null, "params", null, 0, 1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSmooksResourceListType_Conditions(), this.getConditionsType(), null, "conditions", null, 0, 1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSmooksResourceListType_Profiles(), this.getProfilesType(), null, "profiles", null, 0, 1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSmooksResourceListType_AbstractReaderGroup(), ecorePackage.getEFeatureMapEntry(), "abstractReaderGroup", null, 0, -1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSmooksResourceListType_AbstractReader(), this.getAbstractReader(), null, "abstractReader", null, 0, -1, SmooksResourceListType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSmooksResourceListType_AbstractResourceConfigGroup(), ecorePackage.getEFeatureMapEntry(), "abstractResourceConfigGroup", null, 0, -1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSmooksResourceListType_AbstractResourceConfig(), this.getAbstractResourceConfig(), null, "abstractResourceConfig", null, 0, -1, SmooksResourceListType.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getSmooksResourceListType_DefaultConditionRef(), theXMLTypePackage.getString(), "defaultConditionRef", null, 0, 1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSmooksResourceListType_DefaultSelector(), theXMLTypePackage.getString(), "defaultSelector", null, 0, 1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSmooksResourceListType_DefaultSelectorNamespace(), theXMLTypePackage.getAnyURI(), "defaultSelectorNamespace", null, 0, 1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSmooksResourceListType_DefaultTargetProfile(), theXMLTypePackage.getString(), "defaultTargetProfile", null, 0, 1, SmooksResourceListType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (abstractReaderEClass, 
		   source, 
		   new String[] {
			 "name", "abstract-reader",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getAbstractReader_TargetProfile(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "targetProfile"
		   });		
		addAnnotation
		  (abstractResourceConfigEClass, 
		   source, 
		   new String[] {
			 "name", "abstract-resource-config",
			 "kind", "empty"
		   });			
		addAnnotation
		  (conditionsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "conditions_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getConditionsType_Condition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "condition",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (conditionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "condition_._type",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getConditionType_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getConditionType_Evaluator(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "evaluator"
		   });		
		addAnnotation
		  (getConditionType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getConditionType_IdRef(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "idRef"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_AbstractReader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "abstract-reader",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_AbstractResourceConfig(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "abstract-resource-config",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Condition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "condition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Conditions(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "conditions",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ElementVisitor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "element-visitor",
			 "namespace", "##targetNamespace",
			 "affiliation", "abstract-resource-config"
		   });		
		addAnnotation
		  (getDocumentRoot_Features(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "features",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Handler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handler",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Handlers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handlers",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Import(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "import",
			 "namespace", "##targetNamespace",
			 "affiliation", "abstract-resource-config"
		   });		
		addAnnotation
		  (getDocumentRoot_Param(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "param",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Params(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "params",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Profile(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "profile",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Profiles(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "profiles",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_Reader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "reader",
			 "namespace", "##targetNamespace",
			 "affiliation", "abstract-reader"
		   });		
		addAnnotation
		  (getDocumentRoot_Resource(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resource",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_ResourceConfig(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resource-config",
			 "namespace", "##targetNamespace",
			 "affiliation", "abstract-resource-config"
		   });		
		addAnnotation
		  (getDocumentRoot_SetOff(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "setOff",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SetOn(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "setOn",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getDocumentRoot_SmooksResourceList(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "smooks-resource-list",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (elementVisitorEClass, 
		   source, 
		   new String[] {
			 "name", "element-visitor",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getElementVisitor_Condition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "condition",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getElementVisitor_TargetProfile(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "targetProfile"
		   });			
		addAnnotation
		  (featuresTypeEClass, 
		   source, 
		   new String[] {
			 "name", "features_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getFeaturesType_SetOn(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "setOn",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getFeaturesType_SetOff(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "setOff",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (handlersTypeEClass, 
		   source, 
		   new String[] {
			 "name", "handlers_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getHandlersType_Handler(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handler",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (handlerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "handler_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getHandlerType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });			
		addAnnotation
		  (importTypeEClass, 
		   source, 
		   new String[] {
			 "name", "import_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getImportType_Param(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "param",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getImportType_File(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "file"
		   });			
		addAnnotation
		  (paramsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "params_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getParamsType_Param(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "param",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (paramTypeEClass, 
		   source, 
		   new String[] {
			 "name", "param_._type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getParamType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getParamType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });			
		addAnnotation
		  (profilesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "profiles_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getProfilesType_Profile(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "profile",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (profileTypeEClass, 
		   source, 
		   new String[] {
			 "name", "profile_._type",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getProfileType_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getProfileType_BaseProfile(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "base-profile"
		   });		
		addAnnotation
		  (getProfileType_SubProfiles(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "sub-profiles"
		   });			
		addAnnotation
		  (readerTypeEClass, 
		   source, 
		   new String[] {
			 "name", "reader_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getReaderType_Handlers(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "handlers",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getReaderType_Features(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "features",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getReaderType_Params(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "params",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getReaderType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });			
		addAnnotation
		  (resourceConfigTypeEClass, 
		   source, 
		   new String[] {
			 "name", "resource-config_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getResourceConfigType_Resource(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "resource",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getResourceConfigType_Condition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "condition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getResourceConfigType_Param(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "param",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getResourceConfigType_Selector(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "selector"
		   });		
		addAnnotation
		  (getResourceConfigType_SelectorNamespace(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "selector-namespace"
		   });		
		addAnnotation
		  (getResourceConfigType_TargetProfile(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "target-profile"
		   });			
		addAnnotation
		  (resourceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "resource_._type",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getResourceType_Value(), 
		   source, 
		   new String[] {
			 "name", ":0",
			 "kind", "simple"
		   });		
		addAnnotation
		  (getResourceType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });			
		addAnnotation
		  (setOffTypeEClass, 
		   source, 
		   new String[] {
			 "name", "setOff_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getSetOffType_Feature(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "feature"
		   });			
		addAnnotation
		  (setOnTypeEClass, 
		   source, 
		   new String[] {
			 "name", "setOn_._type",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getSetOnType_Feature(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "feature"
		   });			
		addAnnotation
		  (smooksResourceListTypeEClass, 
		   source, 
		   new String[] {
			 "name", "smooks-resource-list_._type",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSmooksResourceListType_Params(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "params",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSmooksResourceListType_Conditions(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "conditions",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSmooksResourceListType_Profiles(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "profiles",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSmooksResourceListType_AbstractReaderGroup(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "abstract-reader:group",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSmooksResourceListType_AbstractReader(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "abstract-reader",
			 "namespace", "##targetNamespace",
			 "group", "abstract-reader:group"
		   });		
		addAnnotation
		  (getSmooksResourceListType_AbstractResourceConfigGroup(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "abstract-resource-config:group",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSmooksResourceListType_AbstractResourceConfig(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "abstract-resource-config",
			 "namespace", "##targetNamespace",
			 "group", "abstract-resource-config:group"
		   });		
		addAnnotation
		  (getSmooksResourceListType_DefaultConditionRef(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "default-condition-ref"
		   });		
		addAnnotation
		  (getSmooksResourceListType_DefaultSelector(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "default-selector"
		   });		
		addAnnotation
		  (getSmooksResourceListType_DefaultSelectorNamespace(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "default-selector-namespace"
		   });		
		addAnnotation
		  (getSmooksResourceListType_DefaultTargetProfile(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "default-target-profile"
		   });
	}

} //SmooksPackageImpl
