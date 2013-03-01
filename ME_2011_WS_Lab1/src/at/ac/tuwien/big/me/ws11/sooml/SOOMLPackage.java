/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLFactory
 * @model kind="package"
 * @generated
 */
public interface SOOMLPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sooml";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.big.tuwien.ac.at/me/ws11/sooml";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "sooml";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SOOMLPackage eINSTANCE = at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl.init();

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.NamedElementImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.PackageImpl <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.PackageImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getPackage()
	 * @generated
	 */
	int PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__CLASSES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__PACKAGES = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__PARENT_PACKAGE = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ClassImpl <em>Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ClassImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getClass_()
	 * @generated
	 */
	int CLASS = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>State Machine</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS__STATE_MACHINE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS__OPERATIONS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS__FEATURES = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.StructuralFeatureImpl <em>Structural Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.StructuralFeatureImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getStructuralFeature()
	 * @generated
	 */
	int STRUCTURAL_FEATURE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE__UPPER_BOUND = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE__LOWER_BOUND = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Structural Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Structural Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURAL_FEATURE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.AttributeImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = STRUCTURAL_FEATURE__NAME;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__UPPER_BOUND = STRUCTURAL_FEATURE__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__LOWER_BOUND = STRUCTURAL_FEATURE__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DATA_TYPE = STRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = STRUCTURAL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = STRUCTURAL_FEATURE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__NAME = STRUCTURAL_FEATURE__NAME;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__UPPER_BOUND = STRUCTURAL_FEATURE__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__LOWER_BOUND = STRUCTURAL_FEATURE__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__TYPE = STRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Opposite</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__OPPOSITE = STRUCTURAL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = STRUCTURAL_FEATURE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_OPERATION_COUNT = STRUCTURAL_FEATURE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.OperationImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PARAMETER = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.StateMachineImpl <em>State Machine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.StateMachineImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getStateMachine()
	 * @generated
	 */
	int STATE_MACHINE = 7;

	/**
	 * The feature id for the '<em><b>Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__CLASS = 0;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__STATES = 1;

	/**
	 * The feature id for the '<em><b>Initial State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__INITIAL_STATE = 2;

	/**
	 * The number of structural features of the '<em>State Machine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>State Machine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.StateImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getState()
	 * @generated
	 */
	int STATE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__TRANSITIONS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Entry Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__ENTRY_OPERATION = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.TransitionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 9;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__ACTIONS = 0;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__GUARD = 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__EVENT = 2;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TARGET = 3;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.GuardImpl <em>Guard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.GuardImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getGuard()
	 * @generated
	 */
	int GUARD = 10;

	/**
	 * The number of structural features of the '<em>Guard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Guard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ActionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 11;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.EventImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 12;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__OPERATION = 0;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ParameterImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DATA_TYPE = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Class Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__CLASS_TYPE = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.CallOperationActionImpl <em>Call Operation Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.CallOperationActionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getCallOperationAction()
	 * @generated
	 */
	int CALL_OPERATION_ACTION = 14;

	/**
	 * The feature id for the '<em><b>Called Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__CALLED_OPERATION = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION__PARAMETER = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Call Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Call Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ParameterBindingImpl <em>Parameter Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ParameterBindingImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getParameterBinding()
	 * @generated
	 */
	int PARAMETER_BINDING = 15;

	/**
	 * The feature id for the '<em><b>Structural Feature Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_BINDING__STRUCTURAL_FEATURE_BINDING = 0;

	/**
	 * The feature id for the '<em><b>Parameter Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_BINDING__PARAMETER_BINDING = 1;

	/**
	 * The number of structural features of the '<em>Parameter Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_BINDING_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Parameter Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_BINDING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.IsInStateConditionImpl <em>Is In State Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.IsInStateConditionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getIsInStateCondition()
	 * @generated
	 */
	int IS_IN_STATE_CONDITION = 16;

	/**
	 * The feature id for the '<em><b>In State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_IN_STATE_CONDITION__IN_STATE = GUARD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Is In State Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_IN_STATE_CONDITION_FEATURE_COUNT = GUARD_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Is In State Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_IN_STATE_CONDITION_OPERATION_COUNT = GUARD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.EntryOperationImpl <em>Entry Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.EntryOperationImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getEntryOperation()
	 * @generated
	 */
	int ENTRY_OPERATION = 17;

	/**
	 * The feature id for the '<em><b>Called Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_OPERATION__CALLED_OPERATION = 0;

	/**
	 * The number of structural features of the '<em>Entry Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_OPERATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Entry Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_OPERATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceIsInStateConditionImpl <em>Reference Is In State Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceIsInStateConditionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getReferenceIsInStateCondition()
	 * @generated
	 */
	int REFERENCE_IS_IN_STATE_CONDITION = 18;

	/**
	 * The feature id for the '<em><b>In State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_IS_IN_STATE_CONDITION__IN_STATE = IS_IN_STATE_CONDITION__IN_STATE;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_IS_IN_STATE_CONDITION__REFERENCE = IS_IN_STATE_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reference Is In State Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_IS_IN_STATE_CONDITION_FEATURE_COUNT = IS_IN_STATE_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Reference Is In State Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_IS_IN_STATE_CONDITION_OPERATION_COUNT = IS_IN_STATE_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ParameterIsInStateConditionImpl <em>Parameter Is In State Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ParameterIsInStateConditionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getParameterIsInStateCondition()
	 * @generated
	 */
	int PARAMETER_IS_IN_STATE_CONDITION = 19;

	/**
	 * The feature id for the '<em><b>In State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_IS_IN_STATE_CONDITION__IN_STATE = IS_IN_STATE_CONDITION__IN_STATE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_IS_IN_STATE_CONDITION__PARAMETER = IS_IN_STATE_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Parameter Is In State Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_IS_IN_STATE_CONDITION_FEATURE_COUNT = IS_IN_STATE_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Parameter Is In State Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_IS_IN_STATE_CONDITION_OPERATION_COUNT = IS_IN_STATE_CONDITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.CallReferenceOperationActionImpl <em>Call Reference Operation Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.CallReferenceOperationActionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getCallReferenceOperationAction()
	 * @generated
	 */
	int CALL_REFERENCE_OPERATION_ACTION = 20;

	/**
	 * The feature id for the '<em><b>Called Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_REFERENCE_OPERATION_ACTION__CALLED_OPERATION = CALL_OPERATION_ACTION__CALLED_OPERATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_REFERENCE_OPERATION_ACTION__PARAMETER = CALL_OPERATION_ACTION__PARAMETER;

	/**
	 * The feature id for the '<em><b>Call Object Via Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_REFERENCE_OPERATION_ACTION__CALL_OBJECT_VIA_REFERENCE = CALL_OPERATION_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Call Reference Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_REFERENCE_OPERATION_ACTION_FEATURE_COUNT = CALL_OPERATION_ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Call Reference Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_REFERENCE_OPERATION_ACTION_OPERATION_COUNT = CALL_OPERATION_ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.CallParameterOperationActionImpl <em>Call Parameter Operation Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.CallParameterOperationActionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getCallParameterOperationAction()
	 * @generated
	 */
	int CALL_PARAMETER_OPERATION_ACTION = 21;

	/**
	 * The feature id for the '<em><b>Called Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PARAMETER_OPERATION_ACTION__CALLED_OPERATION = CALL_OPERATION_ACTION__CALLED_OPERATION;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PARAMETER_OPERATION_ACTION__PARAMETER = CALL_OPERATION_ACTION__PARAMETER;

	/**
	 * The feature id for the '<em><b>Call Object Via Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PARAMETER_OPERATION_ACTION__CALL_OBJECT_VIA_PARAMETER = CALL_OPERATION_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Call Parameter Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PARAMETER_OPERATION_ACTION_FEATURE_COUNT = CALL_OPERATION_ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Call Parameter Operation Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_PARAMETER_OPERATION_ACTION_OPERATION_COUNT = CALL_OPERATION_ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceAssignmentActionImpl <em>Reference Assignment Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceAssignmentActionImpl
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getReferenceAssignmentAction()
	 * @generated
	 */
	int REFERENCE_ASSIGNMENT_ACTION = 22;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_ASSIGNMENT_ACTION__PARAMETER = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_ASSIGNMENT_ACTION__REFERENCE = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Reference Assignment Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_ASSIGNMENT_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Reference Assignment Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_ASSIGNMENT_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link at.ac.tuwien.big.me.ws11.sooml.DataType <em>Data Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see at.ac.tuwien.big.me.ws11.sooml.DataType
	 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getDataType()
	 * @generated
	 */
	int DATA_TYPE = 23;


	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.Package#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Package#getClasses()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Classes();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.Package#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Packages</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Package#getPackages()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Packages();

	/**
	 * Returns the meta object for the container reference '{@link at.ac.tuwien.big.me.ws11.sooml.Package#getParentPackage <em>Parent Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Package</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Package#getParentPackage()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_ParentPackage();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link at.ac.tuwien.big.me.ws11.sooml.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Class <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Class
	 * @generated
	 */
	EClass getClass_();

	/**
	 * Returns the meta object for the containment reference '{@link at.ac.tuwien.big.me.ws11.sooml.Class#getStateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>State Machine</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Class#getStateMachine()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_StateMachine();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.Class#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Class#getOperations()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_Operations();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.Class#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Class#getFeatures()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_Features();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link at.ac.tuwien.big.me.ws11.sooml.Attribute#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Attribute#getDataType()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_DataType();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.Reference#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Reference#getType()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_Type();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.Reference#getOpposite <em>Opposite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Opposite</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Reference#getOpposite()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_Opposite();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.Operation#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Operation#getParameter()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Parameter();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.StructuralFeature <em>Structural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structural Feature</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StructuralFeature
	 * @generated
	 */
	EClass getStructuralFeature();

	/**
	 * Returns the meta object for the attribute '{@link at.ac.tuwien.big.me.ws11.sooml.StructuralFeature#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StructuralFeature#getUpperBound()
	 * @see #getStructuralFeature()
	 * @generated
	 */
	EAttribute getStructuralFeature_UpperBound();

	/**
	 * Returns the meta object for the attribute '{@link at.ac.tuwien.big.me.ws11.sooml.StructuralFeature#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StructuralFeature#getLowerBound()
	 * @see #getStructuralFeature()
	 * @generated
	 */
	EAttribute getStructuralFeature_LowerBound();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Machine</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StateMachine
	 * @generated
	 */
	EClass getStateMachine();

	/**
	 * Returns the meta object for the container reference '{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Class</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StateMachine#getClass_()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_Class();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StateMachine#getStates()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_States();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine#getInitialState <em>Initial State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Initial State</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StateMachine#getInitialState()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_InitialState();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.State#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transitions</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.State#getTransitions()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Transitions();

	/**
	 * Returns the meta object for the containment reference '{@link at.ac.tuwien.big.me.ws11.sooml.State#getEntryOperation <em>Entry Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Entry Operation</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.State#getEntryOperation()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_EntryOperation();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.Transition#getActions <em>Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actions</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Transition#getActions()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Actions();

	/**
	 * Returns the meta object for the containment reference '{@link at.ac.tuwien.big.me.ws11.sooml.Transition#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Guard</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Transition#getGuard()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Guard();

	/**
	 * Returns the meta object for the containment reference '{@link at.ac.tuwien.big.me.ws11.sooml.Transition#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Transition#getEvent()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Event();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Transition#getTarget()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Target();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Guard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guard</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Guard
	 * @generated
	 */
	EClass getGuard();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.Event#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Event#getOperation()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Operation();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link at.ac.tuwien.big.me.ws11.sooml.Parameter#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Parameter#getDataType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_DataType();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.Parameter#getClassType <em>Class Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Class Type</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Parameter#getClassType()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_ClassType();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.CallOperationAction <em>Call Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Operation Action</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallOperationAction
	 * @generated
	 */
	EClass getCallOperationAction();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.CallOperationAction#getCalledOperation <em>Called Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Called Operation</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallOperationAction#getCalledOperation()
	 * @see #getCallOperationAction()
	 * @generated
	 */
	EReference getCallOperationAction_CalledOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link at.ac.tuwien.big.me.ws11.sooml.CallOperationAction#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallOperationAction#getParameter()
	 * @see #getCallOperationAction()
	 * @generated
	 */
	EReference getCallOperationAction_Parameter();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding <em>Parameter Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Binding</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ParameterBinding
	 * @generated
	 */
	EClass getParameterBinding();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding#getStructuralFeatureBinding <em>Structural Feature Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Structural Feature Binding</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ParameterBinding#getStructuralFeatureBinding()
	 * @see #getParameterBinding()
	 * @generated
	 */
	EReference getParameterBinding_StructuralFeatureBinding();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding#getParameterBinding <em>Parameter Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter Binding</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ParameterBinding#getParameterBinding()
	 * @see #getParameterBinding()
	 * @generated
	 */
	EReference getParameterBinding_ParameterBinding();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition <em>Is In State Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Is In State Condition</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition
	 * @generated
	 */
	EClass getIsInStateCondition();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition#getInState <em>In State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>In State</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition#getInState()
	 * @see #getIsInStateCondition()
	 * @generated
	 */
	EReference getIsInStateCondition_InState();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.EntryOperation <em>Entry Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry Operation</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.EntryOperation
	 * @generated
	 */
	EClass getEntryOperation();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.EntryOperation#getCalledOperation <em>Called Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Called Operation</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.EntryOperation#getCalledOperation()
	 * @see #getEntryOperation()
	 * @generated
	 */
	EReference getEntryOperation_CalledOperation();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition <em>Reference Is In State Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Is In State Condition</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition
	 * @generated
	 */
	EClass getReferenceIsInStateCondition();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition#getReference()
	 * @see #getReferenceIsInStateCondition()
	 * @generated
	 */
	EReference getReferenceIsInStateCondition_Reference();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterIsInStateCondition <em>Parameter Is In State Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Is In State Condition</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ParameterIsInStateCondition
	 * @generated
	 */
	EClass getParameterIsInStateCondition();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterIsInStateCondition#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ParameterIsInStateCondition#getParameter()
	 * @see #getParameterIsInStateCondition()
	 * @generated
	 */
	EReference getParameterIsInStateCondition_Parameter();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction <em>Call Reference Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Reference Operation Action</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction
	 * @generated
	 */
	EClass getCallReferenceOperationAction();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction#getCallObjectViaReference <em>Call Object Via Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Call Object Via Reference</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction#getCallObjectViaReference()
	 * @see #getCallReferenceOperationAction()
	 * @generated
	 */
	EReference getCallReferenceOperationAction_CallObjectViaReference();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction <em>Call Parameter Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Parameter Operation Action</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction
	 * @generated
	 */
	EClass getCallParameterOperationAction();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction#getCallObjectViaParameter <em>Call Object Via Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Call Object Via Parameter</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction#getCallObjectViaParameter()
	 * @see #getCallParameterOperationAction()
	 * @generated
	 */
	EReference getCallParameterOperationAction_CallObjectViaParameter();

	/**
	 * Returns the meta object for class '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction <em>Reference Assignment Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Assignment Action</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction
	 * @generated
	 */
	EClass getReferenceAssignmentAction();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction#getParameter()
	 * @see #getReferenceAssignmentAction()
	 * @generated
	 */
	EReference getReferenceAssignmentAction_Parameter();

	/**
	 * Returns the meta object for the reference '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction#getReference()
	 * @see #getReferenceAssignmentAction()
	 * @generated
	 */
	EReference getReferenceAssignmentAction_Reference();

	/**
	 * Returns the meta object for enum '{@link at.ac.tuwien.big.me.ws11.sooml.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Data Type</em>'.
	 * @see at.ac.tuwien.big.me.ws11.sooml.DataType
	 * @generated
	 */
	EEnum getDataType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SOOMLFactory getSOOMLFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.PackageImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__CLASSES = eINSTANCE.getPackage_Classes();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__PACKAGES = eINSTANCE.getPackage_Packages();

		/**
		 * The meta object literal for the '<em><b>Parent Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__PARENT_PACKAGE = eINSTANCE.getPackage_ParentPackage();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.NamedElementImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ClassImpl <em>Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ClassImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getClass_()
		 * @generated
		 */
		EClass CLASS = eINSTANCE.getClass_();

		/**
		 * The meta object literal for the '<em><b>State Machine</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__STATE_MACHINE = eINSTANCE.getClass_StateMachine();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__OPERATIONS = eINSTANCE.getClass_Operations();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__FEATURES = eINSTANCE.getClass_Features();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.AttributeImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__DATA_TYPE = eINSTANCE.getAttribute_DataType();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__TYPE = eINSTANCE.getReference_Type();

		/**
		 * The meta object literal for the '<em><b>Opposite</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__OPPOSITE = eINSTANCE.getReference_Opposite();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.OperationImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PARAMETER = eINSTANCE.getOperation_Parameter();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.StructuralFeatureImpl <em>Structural Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.StructuralFeatureImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getStructuralFeature()
		 * @generated
		 */
		EClass STRUCTURAL_FEATURE = eINSTANCE.getStructuralFeature();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURAL_FEATURE__UPPER_BOUND = eINSTANCE.getStructuralFeature_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURAL_FEATURE__LOWER_BOUND = eINSTANCE.getStructuralFeature_LowerBound();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.StateMachineImpl <em>State Machine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.StateMachineImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getStateMachine()
		 * @generated
		 */
		EClass STATE_MACHINE = eINSTANCE.getStateMachine();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__CLASS = eINSTANCE.getStateMachine_Class();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__STATES = eINSTANCE.getStateMachine_States();

		/**
		 * The meta object literal for the '<em><b>Initial State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__INITIAL_STATE = eINSTANCE.getStateMachine_InitialState();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.StateImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__TRANSITIONS = eINSTANCE.getState_Transitions();

		/**
		 * The meta object literal for the '<em><b>Entry Operation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__ENTRY_OPERATION = eINSTANCE.getState_EntryOperation();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.TransitionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__ACTIONS = eINSTANCE.getTransition_Actions();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__GUARD = eINSTANCE.getTransition_Guard();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__EVENT = eINSTANCE.getTransition_Event();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TARGET = eINSTANCE.getTransition_Target();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.GuardImpl <em>Guard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.GuardImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getGuard()
		 * @generated
		 */
		EClass GUARD = eINSTANCE.getGuard();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ActionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.EventImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__OPERATION = eINSTANCE.getEvent_Operation();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ParameterImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__DATA_TYPE = eINSTANCE.getParameter_DataType();

		/**
		 * The meta object literal for the '<em><b>Class Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER__CLASS_TYPE = eINSTANCE.getParameter_ClassType();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.CallOperationActionImpl <em>Call Operation Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.CallOperationActionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getCallOperationAction()
		 * @generated
		 */
		EClass CALL_OPERATION_ACTION = eINSTANCE.getCallOperationAction();

		/**
		 * The meta object literal for the '<em><b>Called Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_OPERATION_ACTION__CALLED_OPERATION = eINSTANCE.getCallOperationAction_CalledOperation();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_OPERATION_ACTION__PARAMETER = eINSTANCE.getCallOperationAction_Parameter();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ParameterBindingImpl <em>Parameter Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ParameterBindingImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getParameterBinding()
		 * @generated
		 */
		EClass PARAMETER_BINDING = eINSTANCE.getParameterBinding();

		/**
		 * The meta object literal for the '<em><b>Structural Feature Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_BINDING__STRUCTURAL_FEATURE_BINDING = eINSTANCE.getParameterBinding_StructuralFeatureBinding();

		/**
		 * The meta object literal for the '<em><b>Parameter Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_BINDING__PARAMETER_BINDING = eINSTANCE.getParameterBinding_ParameterBinding();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.IsInStateConditionImpl <em>Is In State Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.IsInStateConditionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getIsInStateCondition()
		 * @generated
		 */
		EClass IS_IN_STATE_CONDITION = eINSTANCE.getIsInStateCondition();

		/**
		 * The meta object literal for the '<em><b>In State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IS_IN_STATE_CONDITION__IN_STATE = eINSTANCE.getIsInStateCondition_InState();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.EntryOperationImpl <em>Entry Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.EntryOperationImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getEntryOperation()
		 * @generated
		 */
		EClass ENTRY_OPERATION = eINSTANCE.getEntryOperation();

		/**
		 * The meta object literal for the '<em><b>Called Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTRY_OPERATION__CALLED_OPERATION = eINSTANCE.getEntryOperation_CalledOperation();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceIsInStateConditionImpl <em>Reference Is In State Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceIsInStateConditionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getReferenceIsInStateCondition()
		 * @generated
		 */
		EClass REFERENCE_IS_IN_STATE_CONDITION = eINSTANCE.getReferenceIsInStateCondition();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_IS_IN_STATE_CONDITION__REFERENCE = eINSTANCE.getReferenceIsInStateCondition_Reference();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ParameterIsInStateConditionImpl <em>Parameter Is In State Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ParameterIsInStateConditionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getParameterIsInStateCondition()
		 * @generated
		 */
		EClass PARAMETER_IS_IN_STATE_CONDITION = eINSTANCE.getParameterIsInStateCondition();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_IS_IN_STATE_CONDITION__PARAMETER = eINSTANCE.getParameterIsInStateCondition_Parameter();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.CallReferenceOperationActionImpl <em>Call Reference Operation Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.CallReferenceOperationActionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getCallReferenceOperationAction()
		 * @generated
		 */
		EClass CALL_REFERENCE_OPERATION_ACTION = eINSTANCE.getCallReferenceOperationAction();

		/**
		 * The meta object literal for the '<em><b>Call Object Via Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_REFERENCE_OPERATION_ACTION__CALL_OBJECT_VIA_REFERENCE = eINSTANCE.getCallReferenceOperationAction_CallObjectViaReference();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.CallParameterOperationActionImpl <em>Call Parameter Operation Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.CallParameterOperationActionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getCallParameterOperationAction()
		 * @generated
		 */
		EClass CALL_PARAMETER_OPERATION_ACTION = eINSTANCE.getCallParameterOperationAction();

		/**
		 * The meta object literal for the '<em><b>Call Object Via Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_PARAMETER_OPERATION_ACTION__CALL_OBJECT_VIA_PARAMETER = eINSTANCE.getCallParameterOperationAction_CallObjectViaParameter();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceAssignmentActionImpl <em>Reference Assignment Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.ReferenceAssignmentActionImpl
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getReferenceAssignmentAction()
		 * @generated
		 */
		EClass REFERENCE_ASSIGNMENT_ACTION = eINSTANCE.getReferenceAssignmentAction();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_ASSIGNMENT_ACTION__PARAMETER = eINSTANCE.getReferenceAssignmentAction_Parameter();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_ASSIGNMENT_ACTION__REFERENCE = eINSTANCE.getReferenceAssignmentAction_Reference();

		/**
		 * The meta object literal for the '{@link at.ac.tuwien.big.me.ws11.sooml.DataType <em>Data Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see at.ac.tuwien.big.me.ws11.sooml.DataType
		 * @see at.ac.tuwien.big.me.ws11.sooml.impl.SOOMLPackageImpl#getDataType()
		 * @generated
		 */
		EEnum DATA_TYPE = eINSTANCE.getDataType();

	}

} //SOOMLPackage
