/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reference Assignment Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction#getParameter <em>Parameter</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getReferenceAssignmentAction()
 * @model
 * @generated
 */
public interface ReferenceAssignmentAction extends Action {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(Parameter)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getReferenceAssignmentAction_Parameter()
	 * @model required="true"
	 * @generated
	 */
	Parameter getParameter();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(Parameter value);

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
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getReferenceAssignmentAction_Reference()
	 * @model required="true"
	 * @generated
	 */
	Reference getReference();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.ReferenceAssignmentAction#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(Reference value);

} // ReferenceAssignmentAction
