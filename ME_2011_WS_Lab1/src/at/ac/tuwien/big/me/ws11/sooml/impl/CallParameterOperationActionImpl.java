/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml.impl;

import at.ac.tuwien.big.me.ws11.sooml.CallParameterOperationAction;
import at.ac.tuwien.big.me.ws11.sooml.Parameter;
import at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Parameter Operation Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.CallParameterOperationActionImpl#getCallObjectViaParameter <em>Call Object Via Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallParameterOperationActionImpl extends CallOperationActionImpl implements CallParameterOperationAction {
	/**
	 * The cached value of the '{@link #getCallObjectViaParameter() <em>Call Object Via Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallObjectViaParameter()
	 * @generated
	 * @ordered
	 */
	protected Parameter callObjectViaParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallParameterOperationActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SOOMLPackage.Literals.CALL_PARAMETER_OPERATION_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter getCallObjectViaParameter() {
		if (callObjectViaParameter != null && callObjectViaParameter.eIsProxy()) {
			InternalEObject oldCallObjectViaParameter = (InternalEObject)callObjectViaParameter;
			callObjectViaParameter = (Parameter)eResolveProxy(oldCallObjectViaParameter);
			if (callObjectViaParameter != oldCallObjectViaParameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SOOMLPackage.CALL_PARAMETER_OPERATION_ACTION__CALL_OBJECT_VIA_PARAMETER, oldCallObjectViaParameter, callObjectViaParameter));
			}
		}
		return callObjectViaParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter basicGetCallObjectViaParameter() {
		return callObjectViaParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallObjectViaParameter(Parameter newCallObjectViaParameter) {
		Parameter oldCallObjectViaParameter = callObjectViaParameter;
		callObjectViaParameter = newCallObjectViaParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SOOMLPackage.CALL_PARAMETER_OPERATION_ACTION__CALL_OBJECT_VIA_PARAMETER, oldCallObjectViaParameter, callObjectViaParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SOOMLPackage.CALL_PARAMETER_OPERATION_ACTION__CALL_OBJECT_VIA_PARAMETER:
				if (resolve) return getCallObjectViaParameter();
				return basicGetCallObjectViaParameter();
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
			case SOOMLPackage.CALL_PARAMETER_OPERATION_ACTION__CALL_OBJECT_VIA_PARAMETER:
				setCallObjectViaParameter((Parameter)newValue);
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
			case SOOMLPackage.CALL_PARAMETER_OPERATION_ACTION__CALL_OBJECT_VIA_PARAMETER:
				setCallObjectViaParameter((Parameter)null);
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
			case SOOMLPackage.CALL_PARAMETER_OPERATION_ACTION__CALL_OBJECT_VIA_PARAMETER:
				return callObjectViaParameter != null;
		}
		return super.eIsSet(featureID);
	}

} //CallParameterOperationActionImpl
