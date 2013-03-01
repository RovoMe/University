/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entry Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.EntryOperation#getCalledOperation <em>Called Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getEntryOperation()
 * @model
 * @generated
 */
public interface EntryOperation extends EObject {
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
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getEntryOperation_CalledOperation()
	 * @model required="true"
	 * @generated
	 */
	Operation getCalledOperation();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.EntryOperation#getCalledOperation <em>Called Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Operation</em>' reference.
	 * @see #getCalledOperation()
	 * @generated
	 */
	void setCalledOperation(Operation value);

} // EntryOperation
