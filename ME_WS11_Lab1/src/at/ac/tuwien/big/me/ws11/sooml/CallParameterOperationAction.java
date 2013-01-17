/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Parameter Operation Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction#getCallObjectViaParameter <em>Call Object Via Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getCallParameterOperationAction()
 * @model
 * @generated
 */
public interface CallParameterOperationAction extends CallOperationAction {
	/**
	 * Returns the value of the '<em><b>Call Object Via Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Call Object Via Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Call Object Via Parameter</em>' reference.
	 * @see #setCallObjectViaParameter(Parameter)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getCallParameterOperationAction_CallObjectViaParameter()
	 * @model required="true"
	 * @generated
	 */
	Parameter getCallObjectViaParameter();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction#getCallObjectViaParameter <em>Call Object Via Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Call Object Via Parameter</em>' reference.
	 * @see #getCallObjectViaParameter()
	 * @generated
	 */
	void setCallObjectViaParameter(Parameter value);

} // CallParameterOperationAction
