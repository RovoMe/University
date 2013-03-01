/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml.impl;

import at.ac.tuwien.big.me.ws11.sooml.EntryOperation;
import at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage;
import at.ac.tuwien.big.me.ws11.sooml.State;
import at.ac.tuwien.big.me.ws11.sooml.Transition;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.StateImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.StateImpl#getEntryOperation <em>Entry Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateImpl extends NamedElementImpl implements State {
	/**
	 * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> transitions;

	/**
	 * The cached value of the '{@link #getEntryOperation() <em>Entry Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntryOperation()
	 * @generated
	 * @ordered
	 */
	protected EntryOperation entryOperation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SOOMLPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transition> getTransitions() {
		if (transitions == null) {
			transitions = new EObjectContainmentEList<Transition>(Transition.class, this, SOOMLPackage.STATE__TRANSITIONS);
		}
		return transitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntryOperation getEntryOperation() {
		return entryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEntryOperation(EntryOperation newEntryOperation, NotificationChain msgs) {
		EntryOperation oldEntryOperation = entryOperation;
		entryOperation = newEntryOperation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SOOMLPackage.STATE__ENTRY_OPERATION, oldEntryOperation, newEntryOperation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntryOperation(EntryOperation newEntryOperation) {
		if (newEntryOperation != entryOperation) {
			NotificationChain msgs = null;
			if (entryOperation != null)
				msgs = ((InternalEObject)entryOperation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SOOMLPackage.STATE__ENTRY_OPERATION, null, msgs);
			if (newEntryOperation != null)
				msgs = ((InternalEObject)newEntryOperation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SOOMLPackage.STATE__ENTRY_OPERATION, null, msgs);
			msgs = basicSetEntryOperation(newEntryOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SOOMLPackage.STATE__ENTRY_OPERATION, newEntryOperation, newEntryOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SOOMLPackage.STATE__TRANSITIONS:
				return ((InternalEList<?>)getTransitions()).basicRemove(otherEnd, msgs);
			case SOOMLPackage.STATE__ENTRY_OPERATION:
				return basicSetEntryOperation(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SOOMLPackage.STATE__TRANSITIONS:
				return getTransitions();
			case SOOMLPackage.STATE__ENTRY_OPERATION:
				return getEntryOperation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SOOMLPackage.STATE__TRANSITIONS:
				getTransitions().clear();
				getTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case SOOMLPackage.STATE__ENTRY_OPERATION:
				setEntryOperation((EntryOperation)newValue);
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
			case SOOMLPackage.STATE__TRANSITIONS:
				getTransitions().clear();
				return;
			case SOOMLPackage.STATE__ENTRY_OPERATION:
				setEntryOperation((EntryOperation)null);
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
			case SOOMLPackage.STATE__TRANSITIONS:
				return transitions != null && !transitions.isEmpty();
			case SOOMLPackage.STATE__ENTRY_OPERATION:
				return entryOperation != null;
		}
		return super.eIsSet(featureID);
	}

} //StateImpl
