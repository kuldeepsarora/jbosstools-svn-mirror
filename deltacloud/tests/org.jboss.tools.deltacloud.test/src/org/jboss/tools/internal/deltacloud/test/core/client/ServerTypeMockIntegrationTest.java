/*******************************************************************************
 * Copyright (c) 2010 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.internal.deltacloud.test.core.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.jboss.tools.deltacloud.core.client.DeltaCloudClientException;
import org.jboss.tools.deltacloud.core.client.DeltaCloudClientImpl;
import org.jboss.tools.deltacloud.core.client.DeltaCloudNotFoundClientException;
import org.jboss.tools.deltacloud.core.client.Image;
import org.jboss.tools.deltacloud.core.client.request.DeltaCloudRequest;
import org.jboss.tools.internal.deltacloud.test.context.MockIntegrationTestContext;
import org.jboss.tools.internal.deltacloud.test.fakes.ServerFake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Integration tests for {@link DeltaCloudClientImpl#getServerType()}.
 * 
 * @author Andre Dietisheim
 * 
 * @see DeltaCloudClientImpl#getServerType()
 */
public class ServerTypeMockIntegrationTest {

	private MockIntegrationTestContext testSetup;

	@Before
	public void setUp() throws IOException, DeltaCloudClientException {
		this.testSetup = new MockIntegrationTestContext();
		testSetup.setUp();
	}

	@After
	public void tearDown() {
		testSetup.tearDown();
	}

	@Test
	public void recognizesDeltaCloud() throws IOException {
		assertEquals(DeltaCloudClientImpl.DeltaCloudServerType.MOCK, testSetup.getClient().getServerType());
	}

/**
	 * 
	 * #getServerType reports {@link DeltaCloudClient.DeltaCloudType#UNKNOWN) if it queries a fake server that responds with a unknown answer.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void reportsUnknownUrl() throws IOException {
		ServerFake serverFake = new ServerFake(new URL(MockIntegrationTestContext.SERVERFAKE_URL).getPort(),
				"<dummy></dummy>");
		serverFake.start();
		try {
			assertEquals(DeltaCloudClientImpl.DeltaCloudServerType.UNKNOWN, new DeltaCloudClientImpl(
					MockIntegrationTestContext.SERVERFAKE_URL, MockIntegrationTestContext.DELTACLOUD_USER,
					MockIntegrationTestContext.DELTACLOUD_PASSWORD).getServerType());
		} finally {
			serverFake.stop();
		}
	}

	@Test(expected = DeltaCloudClientException.class)
	public void listImages_cannotListIfNotAuthenticated() throws MalformedURLException, DeltaCloudClientException {
		DeltaCloudClientImpl client = new DeltaCloudClientImpl(MockIntegrationTestContext.DELTACLOUD_URL, "badUser",
				"badPassword");
		client.listImages();
	}

	@Test
	public void throwsDeltaCloudClientExceptionOnUnknownResource() {
		try {
			DeltaCloudClientImpl errorClient = new DeltaCloudClientImpl(MockIntegrationTestContext.DELTACLOUD_URL) {
				@Override
				public List<Image> listImages() throws DeltaCloudClientException {
					sendRequest(new DeltaCloudRequest() {

						@Override
						public URL getUrl() throws MalformedURLException {
							return new URL(MockIntegrationTestContext.DELTACLOUD_URL + "/DUMMY");
						}

						@Override
						public HttpMethod getHttpMethod() {
							return HttpMethod.GET;
						} }
					);
					return Collections.emptyList();
				}
			};
			errorClient.listImages();
			fail("no exception catched");
		} catch (Exception e) {
			assertEquals(DeltaCloudNotFoundClientException.class, e.getClass());
		}
	}
}
