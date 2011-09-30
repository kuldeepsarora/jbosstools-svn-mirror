/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.ide.eclipse.as.openshift.ui.internal.wizard;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.jboss.ide.eclipse.as.openshift.ui.internal.OpenshiftUIActivator;
import org.jboss.tools.common.ui.BrowserUtil;
import org.jboss.tools.common.ui.WizardUtils;
import org.jboss.tools.common.ui.databinding.MandatoryStringValidator;
import org.jboss.tools.common.ui.databinding.Status2BooleanConverter;

/**
 * @author André Dietisheim
 */
public class ServerWizardPage extends AbstractOpenshiftWizardPage {

	protected static final String OPENSHIFT_EXPRESS_SIGNUP_URL = "https://openshift.redhat.com/app/user/new/express"; //$NON-NLS-1$

	private Button validateButton;

	public ServerWizardPage(IWizard wizard, ServerAdapterWizardModel model) {
		super("Server connetion", "Please provide the credentails of your user account on Openshift Express", "Server Connection", wizard, model);
		this.model = model;
	}

	protected void doCreateControls(Composite container, DataBindingContext dbc) {
		GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).applyTo(container);

		Link signupLink = new Link(container, SWT.WRAP);
		signupLink.setText("If you have no user account on Openshit Express yet, please sign up <a>here</a>.");
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).span(3, 1).hint(SWT.DEFAULT, 30).applyTo(signupLink);
		signupLink.addSelectionListener(onSignupLinkClicked());

		// Label serverUrlLabel = new Label(container, SWT.NONE);
		// serverUrlLabel.setText("Server URL");
		// GridDataFactory.fillDefaults().align(SWT.LEFT,
		// SWT.CENTER).applyTo(serverUrlLabel);
		// Text serverUrlText = new Text(container, SWT.BORDER);
		// GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true,
		// false).span(2, 1).applyTo(serverUrlText);
		// dbc.bindValue(
		// WidgetProperties.text(SWT.Modify).observe(serverUrlText),
		// BeanProperties.value(
		// ServerAdapterWizardModel.class,
		// ServerAdapterWizardModel.PROPERTY_SERVER_URL).observe(model),
		// new UpdateValueStrategy()
		// .setAfterGetValidator(new
		// MandatoryStringValidator("You have to provide a value for the server url."))
		// .setBeforeSetValidator(new SimpleUrlStringValidator())
		// .setConverter(new TrimTrailingSlashConverter()),
		// null);

		Label rhLoginLabel = new Label(container, SWT.NONE);
		rhLoginLabel.setText("&Username");
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).applyTo(rhLoginLabel);
		Text rhLoginText = new Text(container, SWT.BORDER);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1).applyTo(rhLoginText);
		Binding rhLoginBining = bindMandatoryTextField(
				rhLoginText, "Username", ServerAdapterWizardModel.PROPERTY_RHLOGIN, model, dbc);

		Label passwordLabel = new Label(container, SWT.NONE);
		passwordLabel.setText("&Password");
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).applyTo(passwordLabel);
		Text passwordText = new Text(container, SWT.BORDER | SWT.PASSWORD);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1).applyTo(passwordText);
		Binding passwordBinding = bindMandatoryTextField(
				passwordText, "Password", ServerAdapterWizardModel.PROPERTY_PASSWORD, model, dbc);

		Label spacerLabel = new Label(container, SWT.None);
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).span(2, 1).applyTo(spacerLabel);

		// Label credentialsValidatyLabel = new Label(container, SWT.None);
		// GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).hint(64,
		// 64).applyTo(credentialsValidatyLabel);

		this.validateButton = new Button(container, SWT.NONE);
		validateButton.setText("&Validate");
		GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).indent(0, 10).hint(100, 34).applyTo(validateButton);
		bindValidateButtonEnablement(validateButton, dbc, toObservableCollection(rhLoginBining, passwordBinding));
		validateButton.addSelectionListener(onValidate(dbc));
		dbc.bindValue(
				new WritableValue(null, IStatus.class),
				BeanProperties.value(ServerAdapterWizardModel.PROPERTY_CREDENTIALSVALIDITY).observe(model),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				new UpdateValueStrategy().setAfterGetValidator(
						new CredentialsStatusValidator()));
	}

	protected SelectionAdapter onValidate(final DataBindingContext dbc) {
		return new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					WizardUtils.runInWizard(
							new Job("Testing user credentials") {

								@Override
								protected IStatus run(IProgressMonitor monitor) {
									model.validateCredentials();
									return Status.OK_STATUS;
								}
							}, getWizard().getContainer(), dbc);
				} catch (Exception ex) {
					// ignore
				}
			};
		};
	}

	protected SelectionAdapter onSignupLinkClicked() {
		return new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				BrowserUtil.checkedCreateInternalBrowser(OPENSHIFT_EXPRESS_SIGNUP_URL, OPENSHIFT_EXPRESS_SIGNUP_URL,
						OpenshiftUIActivator.PLUGIN_ID, OpenshiftUIActivator.getDefault().getLog());
				getWizard().getContainer().getShell().close();
			}
		};
	}

	private Binding bindMandatoryTextField(Text text, String fieldName, String modelProperty,
			ServerAdapterWizardModel model,
			DataBindingContext dbc) {
		Binding binding = dbc.bindValue(
				WidgetProperties.text(SWT.Modify).observe(text),
				BeanProperties.value(modelProperty).observe(model),
				new UpdateValueStrategy().setAfterGetValidator(
						new MandatoryStringValidator(NLS.bind("You have to provide a value for the {0}.", fieldName))),
				null);
		ControlDecorationSupport.create(binding, SWT.LEFT | SWT.TOP);
		return binding;
	}

	private void bindValidateButtonEnablement(final Button testButton, DataBindingContext dbc,
			IObservableCollection validationStatusProviders) {
		dbc.bindValue(
				WidgetProperties.enabled().observe(testButton),
				new AggregateValidationStatus(validationStatusProviders, AggregateValidationStatus.MAX_SEVERITY),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				new UpdateValueStrategy().setConverter(new Status2BooleanConverter(IStatus.ERROR)));
	}

	protected IObservableCollection toObservableCollection(ValidationStatusProvider... validationStatusProviders) {
		WritableList validationProviders = new WritableList();
		for (ValidationStatusProvider provider : validationStatusProviders) {
			validationProviders.add(provider);
		}
		return validationProviders;
	}

	private static class CredentialsStatusValidator implements IValidator {
		public IStatus validate(Object value) {
			if (value instanceof IStatus) {
				int currentSeverity = ((IStatus) value).getSeverity();
				if (currentSeverity < IStatus.ERROR) {
					return ValidationStatus.ok();
				}
			} else if (value == null) {
				return ValidationStatus.info("You have to validate your credentials");
			}
			return ValidationStatus.error("The given credentials are not valid");
		}
	}
}
