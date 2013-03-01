/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml.impl;

import at.ac.tuwien.big.me.ws11.sooml.CallReferenceOperationAction;
import at.ac.tuwien.big.me.ws11.sooml.Reference;
import at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Reference Operation Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.CallReferenceOperationActionImpl#getCallObjectViaReference <em>Call Object Via Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallReferenceOperationActionImpl extends CallOperationActionImpl implements CallReferenceOperationAction {
	/**
	 * The cached value of the '{@link #getCallObjectViaReference() <em>Call Object Via Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallObjectViaReference()
	 * @generated
	 * @ordered
	 */
	protected Reference callObjectViaReference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallReferenceOperationActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SOOMLPackage.Literals.CALL_REFERENCE_OPERATION_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference getCallObjectViaReference() {
		if (callObjectViaReference != null && callObjectViaReference.eIsProxy()) {
			InternalEObject oldCallObjectViaReference = (InternalEObject)callObjectViaReference;
			callObjectViaReference = (Reference)eResolveProxy(oldCallObjectViaReference);
			if (callObjectViaReference != oldCallObjectViaReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SOOMLPackage.CALL_REFERENCE_OPERATION_ACTION__CALL_OBJECT_VIA_REFERENCE, oldCallObjectViaReference, callObjectViaReference));
			}
		}
		return callObjectViaReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reference basicGetCallObjectViaReference() {
		return callObjectViaReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallObjectViaReference(Reference newCallObjectViaReference) {
		Reference oldCallObjectViaReference = callObjectViaReference;
		callObjectViaReference = newCallObjectViaReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SOOMLPackage.CALL_REFERENCE_OPERATION_ACTION__CALL_OBJECT_VIA_REFERENCE, oldCallObjectViaReference, callObjectViaReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SOOMLPackage.CALL_REFERENCE_OPERATION_ACTION__CALL_OBJECT_VIA_REFERENCE:
				if (resolve) return getCallObjectViaReference();
				return basicGetCallObjectViaReference();
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
			case SOOMLPackage.CALL_REFERENCE_OPERATION_ACTION__CALL_OBJECT_VIA_REFERENCE:
				setCallObjectViaReference((Reference)newValue);
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
			case SOOMLPackage.CALL_REFERENCE_OPERATION_ACTION__CALL_OBJECT_VIA_REFERENCE:
				setCallObjectViaReference((Reference)null);
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
			case SOOMLPackage.CALL_REFERENCE_OPERATION_ACTION__CALL_OBJECT_VIA_REFERENCE:
				return callObjectViaReference != null;
		}
		return super.eIsSet(featureID);
	}

} //CallReferenceOperationActionImpl
