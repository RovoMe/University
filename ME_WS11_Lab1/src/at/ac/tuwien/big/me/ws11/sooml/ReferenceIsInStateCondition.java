/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Is In State Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getReferenceIsInStateCondition()
 * @model
 * @generated
 */
public interface ReferenceIsInStateCondition extends IsInStateCondition {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(Reference)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getReferenceIsInStateCondition_Reference()
	 * @model required="true"
	 * @generated
	 */
	Reference getReference();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceIsInStateCondition#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(Reference value);

} // ReferenceIsInStateCondition
