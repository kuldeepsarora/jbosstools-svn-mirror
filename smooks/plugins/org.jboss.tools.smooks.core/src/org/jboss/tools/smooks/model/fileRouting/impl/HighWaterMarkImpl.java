/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jboss.tools.smooks.model.fileRouting.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.jboss.tools.smooks.model.fileRouting.FileRoutingPackage;
import org.jboss.tools.smooks.model.fileRouting.HighWaterMark;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>High Water Mark</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jboss.tools.smooks.model.fileRouting.impl.HighWaterMarkImpl#getMark <em>Mark</em>}</li>
 *   <li>{@link org.jboss.tools.smooks.model.fileRouting.impl.HighWaterMarkImpl#getPollFrequency <em>Poll Frequency</em>}</li>
 *   <li>{@link org.jboss.tools.smooks.model.fileRouting.impl.HighWaterMarkImpl#getTimeout <em>Timeout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HighWaterMarkImpl extends EObjectImpl implements HighWaterMark {
	/**
	 * The default value of the '{@link #getMark() <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMark()
	 * @generated
	 * @ordered
	 */
	protected static final int MARK_EDEFAULT = 200;

	/**
	 * The cached value of the '{@link #getMark() <em>Mark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMark()
	 * @generated
	 * @ordered
	 */
	protected int mark = MARK_EDEFAULT;

	/**
	 * This is true if the Mark attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean markESet;

	/**
	 * The default value of the '{@link #getPollFrequency() <em>Poll Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPollFrequency()
	 * @generated
	 * @ordered
	 */
	protected static final int POLL_FREQUENCY_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getPollFrequency() <em>Poll Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPollFrequency()
	 * @generated
	 * @ordered
	 */
	protected int pollFrequency = POLL_FREQUENCY_EDEFAULT;

	/**
	 * This is true if the Poll Frequency attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean pollFrequencyESet;

	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMEOUT_EDEFAULT = 60000;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected int timeout = TIMEOUT_EDEFAULT;

	/**
	 * This is true if the Timeout attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean timeoutESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HighWaterMarkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FileRoutingPackage.Literals.HIGH_WATER_MARK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMark() {
		return mark;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMark(int newMark) {
		int oldMark = mark;
		mark = newMark;
		boolean oldMarkESet = markESet;
		markESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FileRoutingPackage.HIGH_WATER_MARK__MARK, oldMark, mark, !oldMarkESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMark() {
		int oldMark = mark;
		boolean oldMarkESet = markESet;
		mark = MARK_EDEFAULT;
		markESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FileRoutingPackage.HIGH_WATER_MARK__MARK, oldMark, MARK_EDEFAULT, oldMarkESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMark() {
		return markESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPollFrequency() {
		return pollFrequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPollFrequency(int newPollFrequency) {
		int oldPollFrequency = pollFrequency;
		pollFrequency = newPollFrequency;
		boolean oldPollFrequencyESet = pollFrequencyESet;
		pollFrequencyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FileRoutingPackage.HIGH_WATER_MARK__POLL_FREQUENCY, oldPollFrequency, pollFrequency, !oldPollFrequencyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPollFrequency() {
		int oldPollFrequency = pollFrequency;
		boolean oldPollFrequencyESet = pollFrequencyESet;
		pollFrequency = POLL_FREQUENCY_EDEFAULT;
		pollFrequencyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FileRoutingPackage.HIGH_WATER_MARK__POLL_FREQUENCY, oldPollFrequency, POLL_FREQUENCY_EDEFAULT, oldPollFrequencyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPollFrequency() {
		return pollFrequencyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeout(int newTimeout) {
		int oldTimeout = timeout;
		timeout = newTimeout;
		boolean oldTimeoutESet = timeoutESet;
		timeoutESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FileRoutingPackage.HIGH_WATER_MARK__TIMEOUT, oldTimeout, timeout, !oldTimeoutESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetTimeout() {
		int oldTimeout = timeout;
		boolean oldTimeoutESet = timeoutESet;
		timeout = TIMEOUT_EDEFAULT;
		timeoutESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FileRoutingPackage.HIGH_WATER_MARK__TIMEOUT, oldTimeout, TIMEOUT_EDEFAULT, oldTimeoutESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetTimeout() {
		return timeoutESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FileRoutingPackage.HIGH_WATER_MARK__MARK:
				return new Integer(getMark());
			case FileRoutingPackage.HIGH_WATER_MARK__POLL_FREQUENCY:
				return new Integer(getPollFrequency());
			case FileRoutingPackage.HIGH_WATER_MARK__TIMEOUT:
				return new Integer(getTimeout());
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
			case FileRoutingPackage.HIGH_WATER_MARK__MARK:
				setMark(((Integer)newValue).intValue());
				return;
			case FileRoutingPackage.HIGH_WATER_MARK__POLL_FREQUENCY:
				setPollFrequency(((Integer)newValue).intValue());
				return;
			case FileRoutingPackage.HIGH_WATER_MARK__TIMEOUT:
				setTimeout(((Integer)newValue).intValue());
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
			case FileRoutingPackage.HIGH_WATER_MARK__MARK:
				unsetMark();
				return;
			case FileRoutingPackage.HIGH_WATER_MARK__POLL_FREQUENCY:
				unsetPollFrequency();
				return;
			case FileRoutingPackage.HIGH_WATER_MARK__TIMEOUT:
				unsetTimeout();
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
			case FileRoutingPackage.HIGH_WATER_MARK__MARK:
				return isSetMark();
			case FileRoutingPackage.HIGH_WATER_MARK__POLL_FREQUENCY:
				return isSetPollFrequency();
			case FileRoutingPackage.HIGH_WATER_MARK__TIMEOUT:
				return isSetTimeout();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mark: "); //$NON-NLS-1$
		if (markESet) result.append(mark); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", pollFrequency: "); //$NON-NLS-1$
		if (pollFrequencyESet) result.append(pollFrequency); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", timeout: "); //$NON-NLS-1$
		if (timeoutESet) result.append(timeout); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

} //HighWaterMarkImpl
