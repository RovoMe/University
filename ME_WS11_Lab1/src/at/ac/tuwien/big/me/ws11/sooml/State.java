/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.State#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.State#getEntryOperation <em>Entry Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getState()
 * @model
 * @generated
 */
public interface State extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link at.ac.tuwien.big.me.ws11.sooml.Transition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference list.
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getState_Transitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Transition> getTransitions();

	/**
	 * Returns the value of the '<em><b>Entry Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry Operation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entry Operation</em>' containment reference.
	 * @see #setEntryOperation(EntryOperation)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getState_EntryOperation()
	 * @model containment="true"
	 * @generated
	 */
	EntryOperation getEntryOperation();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.State#getEntryOperation <em>Entry Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entry Operation</em>' containment reference.
	 * @see #getEntryOperation()
	 * @generated
	 */
	void setEntryOperation(EntryOperation value);

} // State
