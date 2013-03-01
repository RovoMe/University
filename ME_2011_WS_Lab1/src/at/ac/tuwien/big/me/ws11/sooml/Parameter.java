/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.Parameter#getDataType <em>Data Type</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.Parameter#getClassType <em>Class Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' attribute.
	 * The literals are from the enumeration {@link at.ac.tuwien.big.me.ws11.sooml.DataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' attribute.
	 * @see at.ac.tuwien.big.me.ws11.sooml.DataType
	 * @see #setDataType(DataType)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getParameter_DataType()
	 * @model required="true"
	 * @generated
	 */
	DataType getDataType();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.Parameter#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see at.ac.tuwien.big.me.ws11.sooml.DataType
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(DataType value);

	/**
	 * Returns the value of the '<em><b>Class Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Type</em>' reference.
	 * @see #setClassType(at.ac.tuwien.big.me.ws11.sooml.Class)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getParameter_ClassType()
	 * @model
	 * @generated
	 */
	at.ac.tuwien.big.me.ws11.sooml.Class getClassType();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.Parameter#getClassType <em>Class Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Type</em>' reference.
	 * @see #getClassType()
	 * @generated
	 */
	void setClassType(at.ac.tuwien.big.me.ws11.sooml.Class value);

} // Parameter
