/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.smooks.model.edi.impl;




import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.jboss.tools.smooks.model.common.CommonPackage;
import org.jboss.tools.smooks.model.common.impl.CommonPackageImpl;
import org.jboss.tools.smooks.model.edi.Component;
import org.jboss.tools.smooks.model.edi.Delimiters;
import org.jboss.tools.smooks.model.edi.Description;
import org.jboss.tools.smooks.model.edi.DocumentRoot;
import org.jboss.tools.smooks.model.edi.EdiFactory;
import org.jboss.tools.smooks.model.edi.EdiMap;
import org.jboss.tools.smooks.model.edi.EdiPackage;
import org.jboss.tools.smooks.model.edi.Field;
import org.jboss.tools.smooks.model.edi.MappingNode;
import org.jboss.tools.smooks.model.edi.Segment;
import org.jboss.tools.smooks.model.edi.Segments;
import org.jboss.tools.smooks.model.edi.SubComponent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EdiPackageImpl extends EPackageImpl implements EdiPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass delimitersEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass descriptionEClass = null;

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
	private EClass ediMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mappingNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subComponentEClass = null;

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
	 * @see org.jboss.tools.smooks.model.edi.EdiPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EdiPackageImpl() {
		super(eNS_URI, EdiFactory.eINSTANCE);
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
	public static EdiPackage init() {
		if (isInited) return (EdiPackage)EPackage.Registry.INSTANCE.getEPackage(EdiPackage.eNS_URI);

		// Obtain or create and register package
		EdiPackageImpl theEdiPackage = (EdiPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof EdiPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new EdiPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CommonPackageImpl theCommonPackage = (CommonPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) instanceof CommonPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI) : CommonPackage.eINSTANCE);

		// Create package meta-data objects
		theEdiPackage.createPackageContents();
		theCommonPackage.createPackageContents();

		// Initialize created meta-data
		theEdiPackage.initializePackageContents();
		theCommonPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEdiPackage.freeze();

		return theEdiPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponent() {
		return componentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_SubComponent() {
		return (EReference)componentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Required() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Truncatable() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDelimiters() {
		return delimitersEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelimiters_Component() {
		return (EAttribute)delimitersEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelimiters_Field() {
		return (EAttribute)delimitersEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelimiters_Segment() {
		return (EAttribute)delimitersEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelimiters_SubComponent() {
		return (EAttribute)delimitersEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDescription() {
		return descriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Name() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDescription_Version() {
		return (EAttribute)descriptionEClass.getEStructuralFeatures().get(1);
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
	public EReference getDocumentRoot_Edimap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEdiMap() {
		return ediMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdiMap_Description() {
		return (EReference)ediMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdiMap_Delimiters() {
		return (EReference)ediMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdiMap_Segments() {
		return (EReference)ediMapEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getField() {
		return fieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getField_Component() {
		return (EReference)fieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Required() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_Truncatable() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMappingNode() {
		return mappingNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMappingNode_Xmltag() {
		return (EAttribute)mappingNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSegment() {
		return segmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSegment_Field() {
		return (EReference)segmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSegment_Segment() {
		return (EReference)segmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_MaxOccurs() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_MinOccurs() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_Segcode() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_Truncatable() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSegments() {
		return segmentsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSegments_Segment() {
		return (EReference)segmentsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubComponent() {
		return subComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubComponent_Required() {
		return (EAttribute)subComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdiFactory getEdiFactory() {
		return (EdiFactory)getEFactoryInstance();
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
		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__SUB_COMPONENT);
		createEAttribute(componentEClass, COMPONENT__REQUIRED);
		createEAttribute(componentEClass, COMPONENT__TRUNCATABLE);

		delimitersEClass = createEClass(DELIMITERS);
		createEAttribute(delimitersEClass, DELIMITERS__COMPONENT);
		createEAttribute(delimitersEClass, DELIMITERS__FIELD);
		createEAttribute(delimitersEClass, DELIMITERS__SEGMENT);
		createEAttribute(delimitersEClass, DELIMITERS__SUB_COMPONENT);

		descriptionEClass = createEClass(DESCRIPTION);
		createEAttribute(descriptionEClass, DESCRIPTION__NAME);
		createEAttribute(descriptionEClass, DESCRIPTION__VERSION);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EDIMAP);

		ediMapEClass = createEClass(EDI_MAP);
		createEReference(ediMapEClass, EDI_MAP__DESCRIPTION);
		createEReference(ediMapEClass, EDI_MAP__DELIMITERS);
		createEReference(ediMapEClass, EDI_MAP__SEGMENTS);

		fieldEClass = createEClass(FIELD);
		createEReference(fieldEClass, FIELD__COMPONENT);
		createEAttribute(fieldEClass, FIELD__REQUIRED);
		createEAttribute(fieldEClass, FIELD__TRUNCATABLE);

		mappingNodeEClass = createEClass(MAPPING_NODE);
		createEAttribute(mappingNodeEClass, MAPPING_NODE__XMLTAG);

		segmentEClass = createEClass(SEGMENT);
		createEReference(segmentEClass, SEGMENT__FIELD);
		createEReference(segmentEClass, SEGMENT__SEGMENT);
		createEAttribute(segmentEClass, SEGMENT__MAX_OCCURS);
		createEAttribute(segmentEClass, SEGMENT__MIN_OCCURS);
		createEAttribute(segmentEClass, SEGMENT__SEGCODE);
		createEAttribute(segmentEClass, SEGMENT__TRUNCATABLE);

		segmentsEClass = createEClass(SEGMENTS);
		createEReference(segmentsEClass, SEGMENTS__SEGMENT);

		subComponentEClass = createEClass(SUB_COMPONENT);
		createEAttribute(subComponentEClass, SUB_COMPONENT__REQUIRED);
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
		componentEClass.getESuperTypes().add(this.getMappingNode());
		delimitersEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		descriptionEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		ediMapEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		fieldEClass.getESuperTypes().add(this.getMappingNode());
		mappingNodeEClass.getESuperTypes().add(theCommonPackage.getAbstractAnyType());
		segmentEClass.getESuperTypes().add(this.getMappingNode());
		segmentsEClass.getESuperTypes().add(this.getMappingNode());
		subComponentEClass.getESuperTypes().add(this.getMappingNode());

		// Initialize classes and features; add operations and parameters
		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_SubComponent(), this.getSubComponent(), null, "subComponent", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Required(), theXMLTypePackage.getBoolean(), "required", "false", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Truncatable(), theXMLTypePackage.getBoolean(), "truncatable", "false", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(delimitersEClass, Delimiters.class, "Delimiters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDelimiters_Component(), theXMLTypePackage.getString(), "component", null, 1, 1, Delimiters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelimiters_Field(), theXMLTypePackage.getString(), "field", null, 1, 1, Delimiters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelimiters_Segment(), theXMLTypePackage.getString(), "segment", null, 1, 1, Delimiters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelimiters_SubComponent(), theXMLTypePackage.getString(), "subComponent", null, 1, 1, Delimiters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(descriptionEClass, Description.class, "Description", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescription_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDescription_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, Description.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Edimap(), this.getEdiMap(), null, "edimap", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(ediMapEClass, EdiMap.class, "EdiMap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdiMap_Description(), this.getDescription(), null, "description", null, 1, 1, EdiMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdiMap_Delimiters(), this.getDelimiters(), null, "delimiters", null, 1, 1, EdiMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdiMap_Segments(), this.getSegments(), null, "segments", null, 1, 1, EdiMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fieldEClass, Field.class, "Field", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getField_Component(), this.getComponent(), null, "component", null, 0, -1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Required(), theXMLTypePackage.getBoolean(), "required", "false", 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getField_Truncatable(), theXMLTypePackage.getBoolean(), "truncatable", "false", 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mappingNodeEClass, MappingNode.class, "MappingNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMappingNode_Xmltag(), theXMLTypePackage.getString(), "xmltag", null, 1, 1, MappingNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(segmentEClass, Segment.class, "Segment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSegment_Field(), this.getField(), null, "field", null, 1, -1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSegment_Segment(), this.getSegment(), null, "segment", null, 0, -1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_MaxOccurs(), theXMLTypePackage.getInt(), "maxOccurs", "1", 0, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_MinOccurs(), theXMLTypePackage.getInt(), "minOccurs", "1", 0, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_Segcode(), theXMLTypePackage.getString(), "segcode", null, 1, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSegment_Truncatable(), theXMLTypePackage.getBoolean(), "truncatable", "false", 0, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(segmentsEClass, Segments.class, "Segments", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSegments_Segment(), this.getSegment(), null, "segment", null, 1, -1, Segments.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subComponentEClass, SubComponent.class, "SubComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSubComponent_Required(), theXMLTypePackage.getBoolean(), "required", "false", 0, 1, SubComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (componentEClass, 
		   source, 
		   new String[] {
			 "name", "Component",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getComponent_SubComponent(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "sub-component",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getComponent_Required(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "required"
		   });		
		addAnnotation
		  (getComponent_Truncatable(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "truncatable"
		   });		
		addAnnotation
		  (delimitersEClass, 
		   source, 
		   new String[] {
			 "name", "Delimiters",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getDelimiters_Component(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "component"
		   });		
		addAnnotation
		  (getDelimiters_Field(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "field"
		   });		
		addAnnotation
		  (getDelimiters_Segment(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "segment"
		   });			
		addAnnotation
		  (getDelimiters_SubComponent(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "sub-component"
		   });		
		addAnnotation
		  (descriptionEClass, 
		   source, 
		   new String[] {
			 "name", "Description",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getDescription_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getDescription_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
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
		  (getDocumentRoot_Edimap(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "edimap",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (ediMapEClass, 
		   source, 
		   new String[] {
			 "name", "EdiMap",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getEdiMap_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getEdiMap_Delimiters(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "delimiters",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getEdiMap_Segments(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "segments",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (fieldEClass, 
		   source, 
		   new String[] {
			 "name", "Field",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getField_Component(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "component",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getField_Required(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "required"
		   });		
		addAnnotation
		  (getField_Truncatable(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "truncatable"
		   });		
		addAnnotation
		  (mappingNodeEClass, 
		   source, 
		   new String[] {
			 "name", "MappingNode",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getMappingNode_Xmltag(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmltag"
		   });		
		addAnnotation
		  (segmentEClass, 
		   source, 
		   new String[] {
			 "name", "Segment",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSegment_Field(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "field",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getSegment_Segment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "segment",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getSegment_MaxOccurs(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "maxOccurs"
		   });			
		addAnnotation
		  (getSegment_MinOccurs(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "minOccurs"
		   });		
		addAnnotation
		  (getSegment_Segcode(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "segcode"
		   });		
		addAnnotation
		  (getSegment_Truncatable(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "truncatable"
		   });		
		addAnnotation
		  (segmentsEClass, 
		   source, 
		   new String[] {
			 "name", "Segments",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSegments_Segment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "segment",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (subComponentEClass, 
		   source, 
		   new String[] {
			 "name", "SubComponent",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getSubComponent_Required(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "required"
		   });
	}

} //EdiPackageImpl
