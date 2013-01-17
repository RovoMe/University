/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml.impl;

import at.ac.tuwien.big.me.ws11.sooml.Parameter;
import at.ac.tuwien.big.me.ws11.sooml.ParameterBinding;
import at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage;
import at.ac.tuwien.big.me.ws11.sooml.StructuralFeature;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.ParameterBindingImpl#getStructuralFeatureBinding <em>Structural Feature Binding</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.ParameterBindingImpl#getParameterBinding <em>Parameter Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterBindingImpl extends EObjectImpl implements ParameterBinding {
	/**
	 * The cached value of the '{@link #getStructuralFeatureBinding() <em>Structural Feature Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructuralFeatureBinding()
	 * @generated
	 * @ordered
	 */
	protected StructuralFeature structuralFeatureBinding;

	/**
	 * The cached value of the '{@link #getParameterBinding() <em>Parameter Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterBinding()
	 * @generated
	 * @ordered
	 */
	protected Parameter parameterBinding;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SOOMLPackage.Literals.PARAMETER_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeature getStructuralFeatureBinding() {
		if (structuralFeatureBinding != null && structuralFeatureBinding.eIsProxy()) {
			InternalEObject oldStructuralFeatureBinding = (InternalEObject)structuralFeatureBinding;
			structuralFeatureBinding = (StructuralFeature)eResolveProxy(oldStructuralFeatureBinding);
			if (structuralFeatureBinding != oldStructuralFeatureBinding) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SOOMLPackage.PARAMETER_BINDING__STRUCTURAL_FEATURE_BINDING, oldStructuralFeatureBinding, structuralFeatureBinding));
			}
		}
		return structuralFeatureBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeature basicGetStructuralFeatureBinding() {
		return structuralFeatureBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStructuralFeatureBinding(StructuralFeature newStructuralFeatureBinding) {
		StructuralFeature oldStructuralFeatureBinding = structuralFeatureBinding;
		structuralFeatureBinding = newStructuralFeatureBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SOOMLPackage.PARAMETER_BINDING__STRUCTURAL_FEATURE_BINDING, oldStructuralFeatureBinding, structuralFeatureBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter getParameterBinding() {
		if (parameterBinding != null && parameterBinding.eIsProxy()) {
			InternalEObject oldParameterBinding = (InternalEObject)parameterBinding;
			parameterBinding = (Parameter)eResolveProxy(oldParameterBinding);
			if (parameterBinding != oldParameterBinding) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SOOMLPackage.PARAMETER_BINDING__PARAMETER_BINDING, oldParameterBinding, parameterBinding));
			}
		}
		return parameterBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter basicGetParameterBinding() {
		return parameterBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterBinding(Parameter newParameterBinding) {
		Parameter oldParameterBinding = parameterBinding;
		parameterBinding = newParameterBinding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SOOMLPackage.PARAMETER_BINDING__PARAMETER_BINDING, oldParameterBinding, parameterBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SOOMLPackage.PARAMETER_BINDING__STRUCTURAL_FEATURE_BINDING:
				if (resolve) return getStructuralFeatureBinding();
				return basicGetStructuralFeatureBinding();
			case SOOMLPackage.PARAMETER_BINDING__PARAMETER_BINDING:
				if (resolve) return getParameterBinding();
				return basicGetParameterBinding();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SOOMLPackage.PARAMETER_BINDING__STRUCTURAL_FEATURE_BINDING:
				setStructuralFeatureBinding((StructuralFeature)newValue);
				return;
			case SOOMLPackage.PARAMETER_BINDING__PARAMETER_BINDING:
				setParameterBinding((Parameter)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SOOMLPackage.PARAMETER_BINDING__STRUCTURAL_FEATURE_BINDING:
				setStructuralFeatureBinding((StructuralFeature)null);
				return;
			case SOOMLPackage.PARAMETER_BINDING__PARAMETER_BINDING:
				setParameterBinding((Parameter)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SOOMLPackage.PARAMETER_BINDING__STRUCTURAL_FEATURE_BINDING:
				return structuralFeatureBinding != null;
			case SOOMLPackage.PARAMETER_BINDING__PARAMETER_BINDING:
				return parameterBinding != null;
		}
		return super.eIsSet(featureID);
	}

} //ParameterBindingImpl
