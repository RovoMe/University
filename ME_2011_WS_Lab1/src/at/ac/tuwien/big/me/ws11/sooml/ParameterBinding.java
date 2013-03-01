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
 * A representation of the model object '<em><b>Parameter Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding#getStructuralFeatureBinding <em>Structural Feature Binding</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding#getParameterBinding <em>Parameter Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getParameterBinding()
 * @model
 * @generated
 */
public interface ParameterBinding extends EObject {
	/**
	 * Returns the value of the '<em><b>Structural Feature Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Structural Feature Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Structural Feature Binding</em>' reference.
	 * @see #setStructuralFeatureBinding(StructuralFeature)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getParameterBinding_StructuralFeatureBinding()
	 * @model
	 * @generated
	 */
	StructuralFeature getStructuralFeatureBinding();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding#getStructuralFeatureBinding <em>Structural Feature Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Structural Feature Binding</em>' reference.
	 * @see #getStructuralFeatureBinding()
	 * @generated
	 */
	void setStructuralFeatureBinding(StructuralFeature value);

	/**
	 * Returns the value of the '<em><b>Parameter Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Binding</em>' reference.
	 * @see #setParameterBinding(Parameter)
	 * @see at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage#getParameterBinding_ParameterBinding()
	 * @model required="true"
	 * @generated
	 */
	Parameter getParameterBinding();

	/**
	 * Sets the value of the '{@link at.ac.tuwien.big.me.ws11.sooml.ParameterBinding#getParameterBinding <em>Parameter Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Binding</em>' reference.
	 * @see #getParameterBinding()
	 * @generated
	 */
	void setParameterBinding(Parameter value);

} // ParameterBinding
