/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Reference Operation Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction#getCallObjectViaReference <em>Call Object Via Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getCallReferenceOperationAction()
 * @model
 * @generated
 */
public interface CallReferenceOperationAction extends CallOperationAction {
	/**
	 * Returns the value of the '<em><b>Call Object Via Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Call Object Via Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Call Object Via Reference</em>' reference.
	 * @see #setCallObjectViaReference(Reference)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getCallReferenceOperationAction_CallObjectViaReference()
	 * @model
	 * @generated
	 */
	Reference getCallObjectViaReference();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction#getCallObjectViaReference <em>Call Object Via Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Call Object Via Reference</em>' reference.
	 * @see #getCallObjectViaReference()
	 * @generated
	 */
	void setCallObjectViaReference(Reference value);

} // CallReferenceOperationAction
