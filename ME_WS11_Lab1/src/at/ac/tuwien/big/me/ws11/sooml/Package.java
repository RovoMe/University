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
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.Package#getClasses <em>Classes</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.Package#getPackages <em>Packages</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.Package#getParentPackage <em>Parent Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link at.ac.tuwien.big.me.ws11.sooml.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getPackage_Classes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<at.ac.tuwien.big.me.ws11.sooml.Class> getClasses();

	/**
	 * Returns the value of the '<em><b>Packages</b></em>' containment reference list.
	 * The list contents are of type {@link at.ac.tuwien.big.me.ws11.sooml.Package}.
	 * It is bidirectional and its opposite is '{@link at.ac.tuwien.big.me.ws11.sooml.Package#getParentPackage <em>Parent Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Packages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Packages</em>' containment reference list.
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getPackage_Packages()
	 * @see at.ac.tuwien.big.me.ws11.sooml.Package#getParentPackage
	 * @model opposite="parentPackage" containment="true" ordered="false"
	 * @generated
	 */
	EList<Package> getPackages();

	/**
	 * Returns the value of the '<em><b>Parent Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link at.ac.tuwien.big.me.ws11.sooml.Package#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Package</em>' container reference.
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getPackage_ParentPackage()
	 * @see at.ac.tuwien.big.me.ws11.sooml.Package#getPackages
	 * @model opposite="packages" transient="false" changeable="false" ordered="false"
	 * @generated
	 */
	Package getParentPackage();

} // Package
