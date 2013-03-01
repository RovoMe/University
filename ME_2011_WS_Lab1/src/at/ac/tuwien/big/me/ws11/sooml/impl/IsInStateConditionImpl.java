/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml.impl;

import at.ac.tuwien.big.me.ws11.sooml.IsInStateCondition;
import at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage;
import at.ac.tuwien.big.me.ws11.sooml.State;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Is In State Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.IsInStateConditionImpl#getInState <em>In State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IsInStateConditionImpl extends GuardImpl implements IsInStateCondition {
	/**
	 * The cached value of the '{@link #getInState() <em>In State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInState()
	 * @generated
	 * @ordered
	 */
	protected State inState;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IsInStateConditionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SOOMLPackage.Literals.IS_IN_STATE_CONDITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State getInState() {
		if (inState != null && inState.eIsProxy()) {
			InternalEObject oldInState = (InternalEObject)inState;
			inState = (State)eResolveProxy(oldInState);
			if (inState != oldInState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SOOMLPackage.IS_IN_STATE_CONDITION__IN_STATE, oldInState, inState));
			}
		}
		return inState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetInState() {
		return inState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInState(State newInState) {
		State oldInState = inState;
		inState = newInState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SOOMLPackage.IS_IN_STATE_CONDITION__IN_STATE, oldInState, inState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SOOMLPackage.IS_IN_STATE_CONDITION__IN_STATE:
				if (resolve) return getInState();
				return basicGetInState();
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
			case SOOMLPackage.IS_IN_STATE_CONDITION__IN_STATE:
				setInState((State)newValue);
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
			case SOOMLPackage.IS_IN_STATE_CONDITION__IN_STATE:
				setInState((State)null);
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
			case SOOMLPackage.IS_IN_STATE_CONDITION__IN_STATE:
				return inState != null;
		}
		return super.eIsSet(featureID);
	}

} //IsInStateConditionImpl
