/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.compatibility.transport.ssl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.mule.compatibility.core.api.endpoint.EndpointURI;
import org.mule.compatibility.core.endpoint.MuleEndpointURI;
import org.mule.tck.junit4.AbstractMuleContextEndpointTestCase;

import org.junit.Test;

public class SslEndpointTestCase extends AbstractMuleContextEndpointTestCase {

  @Test
  public void testHostPortUrl() throws Exception {
    EndpointURI url = new MuleEndpointURI("ssl://localhost:7856", muleContext);
    url.initialise();
    assertEquals("ssl", url.getScheme());
    assertEquals("ssl://localhost:7856", url.getAddress());
    assertNull(url.getEndpointName());
    assertEquals(7856, url.getPort());
    assertEquals("localhost", url.getHost());
    assertEquals("ssl://localhost:7856", url.getAddress());
    assertEquals(0, url.getParams().size());
  }

  @Test
  public void testQueryParams1() throws Exception {
    EndpointURI url = new MuleEndpointURI("ssl://localhost:7856?param=1", muleContext);
    url.initialise();
    assertEquals("ssl", url.getScheme());
    assertEquals("ssl://localhost:7856", url.getAddress());
    assertNull(url.getEndpointName());
    assertEquals(7856, url.getPort());
    assertEquals("localhost", url.getHost());
    assertEquals("ssl://localhost:7856?param=1", url.toString());
    assertEquals(1, url.getParams().size());
    assertEquals("1", url.getParams().getProperty("param"));
  }

  @Test
  public void testQueryParams2() throws Exception {
    EndpointURI url = new MuleEndpointURI("ssl://localhost:7856?param=1&endpointName=sslProvider&blankParam=", muleContext);
    url.initialise();
    assertEquals("ssl", url.getScheme());
    assertEquals("ssl://localhost:7856", url.getAddress());
    assertNotNull(url.getEndpointName());
    assertEquals("sslProvider", url.getEndpointName());
    assertEquals(7856, url.getPort());
    assertEquals("localhost", url.getHost());
    assertEquals("ssl://localhost:7856?param=1&endpointName=sslProvider&blankParam=", url.toString());
    assertEquals("param=1&endpointName=sslProvider&blankParam=", url.getQuery());
    assertEquals(3, url.getParams().size());
    assertEquals("1", url.getParams().getProperty("param"));
    assertEquals("", url.getParams().getProperty("blankParam"));
  }
}
