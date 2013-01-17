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
 * A representation of the model object '<em><b>Call Operation Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.CallOperationAction#getCalledOperation <em>Called Operation</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.CallOperationAction#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getCallOperationAction()
 * @model abstract="true"
 * @generated
 */
public interface CallOperationAction extends Action {
	/**
	 * Returns the value of the '<em><b>Called Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Operation</em>' reference.
	 * @see #setCalledOperation(Operation)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getCallOperationAction_CalledOperation()
	 * @model required="true"
	 * @generated
	 */
	Operation getCalledOperation();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.CallOperationAction#getCalledOperation <em>Called Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Operation</em>' reference.
	 * @see #getCalledOperation()
	 * @generated
	 */
	void setCalledOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference list.
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getCallOperationAction_Parameter()
	 * @model containment="true"
	 * @generated
	 */
	EList<ParameterBinding> getParameter();

} // CallOperationAction
