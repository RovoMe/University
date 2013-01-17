/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml.impl;

import at.ac.tuwien.big.me.ws11.sooml.Attribute;
import at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction;
import at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction;
import at.ac.tuwien.big.me.ws11.sooml.DataType;
import at.ac.tuwien.big.me.ws11.sooml.EntryOperation;
import at.ac.tuwien.big.me.ws11.sooml.Event;
import at.ac.tuwien.big.me.ws11.sooml.Operation;
import at.ac.tuwien.big.me.ws11.sooml.Parameter;
import at.ac.tuwien.big.me.ws11.sooml.ParameterBinding;
import at.ac.tuwien.big.me.ws11.sooml.ParameterIsInStateCondition;
import at.ac.tuwien.big.me.ws11.sooml.Reference;
import at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction;
import at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition;
import at.ac.tuwien.big.me.ws11.sooml.SOOMLFactory;
import at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage;
import at.ac.tuwien.big.me.ws11.sooml.State;
import at.ac.tuwien.big.me.ws11.sooml.StateMachine;
import at.ac.tuwien.big.me.ws11.sooml.Transition;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SOOMLFactoryImpl extends EFactoryImpl implements SOOMLFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SOOMLFactory init() {
		try {
			SOOMLFactory theSOOMLFactory = (SOOMLFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.big.tuwien.ac.at/me/ws11/sooml"); 
			if (theSOOMLFactory != null) {
				return theSOOMLFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SOOMLFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SOOMLFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SOOMLPackage.PACKAGE: return createPackage();
			case SOOMLPackage.CLASS: return createClass();
			case SOOMLPackage.ATTRIBUTE: return createAttribute();
			case SOOMLPackage.REFERENCE: return createReference();
			case SOOMLPackage.OPERATION: return createOperation();
			case SOOMLPackage.STATE_MACHINE: return createStateMachine();
			case SOOMLPackage.STATE: return createState();
			case SOOMLPackage.TRANSITION: return createTransition();
			case SOOMLPackage.EVENT: return createEvent();
			case SOOMLPackage.PARAMETER: return createParameter();
			case SOOMLPackage.PARAMETER_BINDING: return createParameterBinding();
			case SOOMLPackage.ENTRY_OPERATION: return createEntryOperation();
			case SOOMLPackage.REFERENCE_IS_IN_STATE_CONDITION: return createReferenceIsInStateCondition();
			case SOOMLPackage.PARAMETER_IS_IN_STATE_CONDITION: return createParameterIsInStateCondition();
			case SOOMLPackage.CALL_REFERENCE_OPERATION_ACTION: return createCallReferenceOperationAction();
			case SOOMLPackage.CALL_PARAMETER_OPERATION_ACTION: return createCallParameterOperationAction();
			case SOOMLPackage.REFERENCE_ASSIGNMENT_ACTION: return createReferenceAssignmentAction();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SOOMLPackage.DATA_TYPE:
				return createDataTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SOOMLPackage.DATA_TYPE:
				return convertDataTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public at.ac.tuwien.big.me.ws11.sooml.Package createPackage() {
		PackageImpl package_ = new PackageImpl();
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public at.ac.tuwien.big.me.ws11.sooml.Class createClass() {
		ClassImpl class_ = new ClassImpl();
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference createReference() {
		ReferenceImpl reference = new ReferenceImpl();
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachine createStateMachine() {
		StateMachineImpl stateMachine = new StateMachineImpl();
		return stateMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event createEvent() {
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterBinding createParameterBinding() {
		ParameterBindingImpl parameterBinding = new ParameterBindingImpl();
		return parameterBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntryOperation createEntryOperation() {
		EntryOperationImpl entryOperation = new EntryOperationImpl();
		return entryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceIsInStateCondition createReferenceIsInStateCondition() {
		ReferenceIsInStateConditionImpl referenceIsInStateCondition = new ReferenceIsInStateConditionImpl();
		return referenceIsInStateCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterIsInStateCondition createParameterIsInStateCondition() {
		ParameterIsInStateConditionImpl parameterIsInStateCondition = new ParameterIsInStateConditionImpl();
		return parameterIsInStateCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallReferenceOperationAction createCallReferenceOperationAction() {
		CallReferenceOperationActionImpl callReferenceOperationAction = new CallReferenceOperationActionImpl();
		return callReferenceOperationAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallParameterOperationAction createCallParameterOperationAction() {
		CallParameterOperationActionImpl callParameterOperationAction = new CallParameterOperationActionImpl();
		return callParameterOperationAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceAssignmentAction createReferenceAssignmentAction() {
		ReferenceAssignmentActionImpl referenceAssignmentAction = new ReferenceAssignmentActionImpl();
		return referenceAssignmentAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType createDataTypeFromString(EDataType eDataType, String initialValue) {
		DataType result = DataType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDataTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SOOMLPackage getSOOMLPackage() {
		return (SOOMLPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SOOMLPackage getPackage() {
		return SOOMLPackage.eINSTANCE;
	}

} //SOOMLFactoryImpl
