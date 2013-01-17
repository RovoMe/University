/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Is In State Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition#getInState <em>In State</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getIsInStateCondition()
 * @model abstract="true"
 * @generated
 */
public interface IsInStateCondition extends Guard {
	/**
	 * Returns the value of the '<em><b>In State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In State</em>' reference.
	 * @see #setInState(State)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getIsInStateCondition_InState()
	 * @model required="true"
	 * @generated
	 */
	State getInState();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition#getInState <em>In State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In State</em>' reference.
	 * @see #getInState()
	 * @generated
	 */
	void setInState(State value);

} // IsInStateCondition
