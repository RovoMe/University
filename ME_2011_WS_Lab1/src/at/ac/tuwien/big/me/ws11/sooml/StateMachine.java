/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State Machine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine#getClass_ <em>Class</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine#getStates <em>States</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine#getInitialState <em>Initial State</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getStateMachine()
 * @model
 * @generated
 */
public interface StateMachine extends EObject {
	/**
	 * Returns the value of the '<em><b>Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link at.ac.tuwien.big.me.ws11.sooml.Class#getStateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' container reference.
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getStateMachine_Class()
	 * @see at.ac.tuwien.big.me.ws11.sooml.Class#getStateMachine
	 * @model opposite="stateMachine" required="true" transient="false" changeable="false" ordered="false"
	 * @generated
	 */
	at.ac.tuwien.big.me.ws11.sooml.Class getClass_();

	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link at.ac.tuwien.big.me.ws11.sooml.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getStateMachine_States()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<State> getStates();

	/**
	 * Returns the value of the '<em><b>Initial State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial State</em>' reference.
	 * @see #setInitialState(State)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getStateMachine_InitialState()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	State getInitialState();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.StateMachine#getInitialState <em>Initial State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial State</em>' reference.
	 * @see #getInitialState()
	 * @generated
	 */
	void setInitialState(State value);

} // StateMachine
