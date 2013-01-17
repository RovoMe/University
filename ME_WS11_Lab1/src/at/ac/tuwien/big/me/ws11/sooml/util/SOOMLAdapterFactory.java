/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml.util;

import at.ac.tuwien.big.me.ws11.sooml.Action;
import at.ac.tuwien.big.me.ws11.sooml.Attribute;
import at.ac.tuwien.big.me.ws11.sooml.CallOperationAction;
import at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction;
import at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction;
import at.ac.tuwien.big.me.ws11.sooml.EntryOperation;
import at.ac.tuwien.big.me.ws11.sooml.Event;
import at.ac.tuwien.big.me.ws11.sooml.Guard;
import at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition;
import at.ac.tuwien.big.me.ws11.sooml.NamedElement;
import at.ac.tuwien.big.me.ws11.sooml.Operation;
import at.ac.tuwien.big.me.ws11.sooml.Parameter;
import at.ac.tuwien.big.me.ws11.sooml.ParameterBinding;
import at.ac.tuwien.big.me.ws11.sooml.ParameterIsInStateCondition;
import at.ac.tuwien.big.me.ws11.sooml.Reference;
import at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction;
import at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition;
import at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage;
import at.ac.tuwien.big.me.ws11.sooml.State;
import at.ac.tuwien.big.me.ws11.sooml.StateMachine;
import at.ac.tuwien.big.me.ws11.sooml.StructuralFeature;
import at.ac.tuwien.big.me.ws11.sooml.Transition;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage
 * @generated
 */
public class SOOMLAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SOOMLPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SOOMLAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SOOMLPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SOOMLSwitch<Adapter> modelSwitch =
		new SOOMLSwitch<Adapter>() {
			@Override
			public Adapter casePackage(at.ac.tuwien.big.me.ws11.sooml.Package object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseClass(at.ac.tuwien.big.me.ws11.sooml.Class object) {
				return createClassAdapter();
			}
			@Override
			public Adapter caseAttribute(Attribute object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter caseReference(Reference object) {
				return createReferenceAdapter();
			}
			@Override
			public Adapter caseOperation(Operation object) {
				return createOperationAdapter();
			}
			@Override
			public Adapter caseStructuralFeature(StructuralFeature object) {
				return createStructuralFeatureAdapter();
			}
			@Override
			public Adapter caseStateMachine(StateMachine object) {
				return createStateMachineAdapter();
			}
			@Override
			public Adapter caseState(State object) {
				return createStateAdapter();
			}
			@Override
			public Adapter caseTransition(Transition object) {
				return createTransitionAdapter();
			}
			@Override
			public Adapter caseGuard(Guard object) {
				return createGuardAdapter();
			}
			@Override
			public Adapter caseAction(Action object) {
				return createActionAdapter();
			}
			@Override
			public Adapter caseEvent(Event object) {
				return createEventAdapter();
			}
			@Override
			public Adapter caseParameter(Parameter object) {
				return createParameterAdapter();
			}
			@Override
			public Adapter caseCallOperationAction(CallOperationAction object) {
				return createCallOperationActionAdapter();
			}
			@Override
			public Adapter caseParameterBinding(ParameterBinding object) {
				return createParameterBindingAdapter();
			}
			@Override
			public Adapter caseIsInStateCondition(IsInStateCondition object) {
				return createIsInStateConditionAdapter();
			}
			@Override
			public Adapter caseEntryOperation(EntryOperation object) {
				return createEntryOperationAdapter();
			}
			@Override
			public Adapter caseReferenceIsInStateCondition(ReferenceIsInStateCondition object) {
				return createReferenceIsInStateConditionAdapter();
			}
			@Override
			public Adapter caseParameterIsInStateCondition(ParameterIsInStateCondition object) {
				return createParameterIsInStateConditionAdapter();
			}
			@Override
			public Adapter caseCallReferenceOperationAction(CallReferenceOperationAction object) {
				return createCallReferenceOperationActionAdapter();
			}
			@Override
			public Adapter caseCallParameterOperationAction(CallParameterOperationAction object) {
				return createCallParameterOperationActionAdapter();
			}
			@Override
			public Adapter caseReferenceAssignmentAction(ReferenceAssignmentAction object) {
				return createReferenceAssignmentActionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Class <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Class
	 * @generated
	 */
	public Adapter createClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Reference
	 * @generated
	 */
	public Adapter createReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.StructuralFeature <em>Structural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StructuralFeature
	 * @generated
	 */
	public Adapter createStructuralFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.StateMachine
	 * @generated
	 */
	public Adapter createStateMachineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Transition
	 * @generated
	 */
	public Adapter createTransitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Guard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Guard
	 * @generated
	 */
	public Adapter createGuardAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Action
	 * @generated
	 */
	public Adapter createActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Event
	 * @generated
	 */
	public Adapter createEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.CallOperationAction <em>Call Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallOperationAction
	 * @generated
	 */
	public Adapter createCallOperationActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding <em>Parameter Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ParameterBinding
	 * @generated
	 */
	public Adapter createParameterBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition <em>Is In State Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition
	 * @generated
	 */
	public Adapter createIsInStateConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.EntryOperation <em>Entry Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.EntryOperation
	 * @generated
	 */
	public Adapter createEntryOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition <em>Reference Is In State Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition
	 * @generated
	 */
	public Adapter createReferenceIsInStateConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterIsInStateCondition <em>Parameter Is In State Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ParameterIsInStateCondition
	 * @generated
	 */
	public Adapter createParameterIsInStateConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction <em>Call Reference Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction
	 * @generated
	 */
	public Adapter createCallReferenceOperationActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction <em>Call Parameter Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction
	 * @generated
	 */
	public Adapter createCallParameterOperationActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction <em>Reference Assignment Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction
	 * @generated
	 */
	public Adapter createReferenceAssignmentActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SOOMLAdapterFactory
