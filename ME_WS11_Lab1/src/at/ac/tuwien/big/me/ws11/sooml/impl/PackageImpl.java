/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package at.ac.tuwien.big.me.ws11.sooml.impl;

import at.ac.tuwien.big.me.ws11.sooml.SOOMLPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.PackageImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.PackageImpl#getPackages <em>Packages</em>}</li>
 *   <li>{@link at.ac.tuwien.big.me.ws11.sooml.impl.PackageImpl#getParentPackage <em>Parent Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageImpl extends NamedElementImpl implements at.ac.tuwien.big.me.ws11.sooml.Package {
	/**
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<at.ac.tuwien.big.me.ws11.sooml.Class> classes;

	/**
	 * The cached value of the '{@link #getPackages() <em>Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<at.ac.tuwien.big.me.ws11.sooml.Package> packages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SOOMLPackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<at.ac.tuwien.big.me.ws11.sooml.Class> getClasses() {
		if (classes == null) {
			classes = new EObjectContainmentEList<at.ac.tuwien.big.me.ws11.sooml.Class>(at.ac.tuwien.big.me.ws11.sooml.Class.class, this, SOOMLPackage.PACKAGE__CLASSES);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<at.ac.tuwien.big.me.ws11.sooml.Package> getPackages() {
		if (packages == null) {
			packages = new EObjectContainmentWithInverseEList<at.ac.tuwien.big.me.ws11.sooml.Package>(at.ac.tuwien.big.me.ws11.sooml.Package.class, this, SOOMLPackage.PACKAGE__PACKAGES, SOOMLPackage.PACKAGE__PARENT_PACKAGE);
		}
		return packages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public at.ac.tuwien.big.me.ws11.sooml.Package getParentPackage() {
		if (eContainerFeatureID() != SOOMLPackage.PACKAGE__PARENT_PACKAGE) return null;
		return (at.ac.tuwien.big.me.ws11.sooml.Package)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SOOMLPackage.PACKAGE__PACKAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackages()).basicAdd(otherEnd, msgs);
			case SOOMLPackage.PACKAGE__PARENT_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return eBasicSetContainer(otherEnd, SOOMLPackage.PACKAGE__PARENT_PACKAGE, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SOOMLPackage.PACKAGE__CLASSES:
				return ((InternalEList<?>)getClasses()).basicRemove(otherEnd, msgs);
			case SOOMLPackage.PACKAGE__PACKAGES:
				return ((InternalEList<?>)getPackages()).basicRemove(otherEnd, msgs);
			case SOOMLPackage.PACKAGE__PARENT_PACKAGE:
				return eBasicSetContainer(null, SOOMLPackage.PACKAGE__PARENT_PACKAGE, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case SOOMLPackage.PACKAGE__PARENT_PACKAGE:
				return eInternalContainer().eInverseRemove(this, SOOMLPackage.PACKAGE__PACKAGES, at.ac.tuwien.big.me.ws11.sooml.Package.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SOOMLPackage.PACKAGE__CLASSES:
				return getClasses();
			case SOOMLPackage.PACKAGE__PACKAGES:
				return getPackages();
			case SOOMLPackage.PACKAGE__PARENT_PACKAGE:
				return getParentPackage();
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
			case SOOMLPackage.PACKAGE__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends at.ac.tuwien.big.me.ws11.sooml.Class>)newValue);
				return;
			case SOOMLPackage.PACKAGE__PACKAGES:
				getPackages().clear();
				getPackages().addAll((Collection<? extends at.ac.tuwien.big.me.ws11.sooml.Package>)newValue);
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
			case SOOMLPackage.PACKAGE__CLASSES:
				getClasses().clear();
				return;
			case SOOMLPackage.PACKAGE__PACKAGES:
				getPackages().clear();
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
			case SOOMLPackage.PACKAGE__CLASSES:
				return classes != null && !classes.isEmpty();
			case SOOMLPackage.PACKAGE__PACKAGES:
				return packages != null && !packages.isEmpty();
			case SOOMLPackage.PACKAGE__PARENT_PACKAGE:
				return getParentPackage() != null;
		}
		return super.eIsSet(featureID);
	}

} //PackageImpl
