/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.core.routing.filters;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.mule.runtime.core.api.Event;
import org.mule.runtime.core.api.message.InternalMessage;
import org.mule.tck.junit4.AbstractMuleTestCase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

public class ConsumableFilterTestCase extends AbstractMuleTestCase {

  private ConsumableMuleMessageFilter filter;

  @Before
  public void setUp() throws Exception {
    filter = new ConsumableMuleMessageFilter();
  }

  @Test
  public void testRejectsConsumablePayload() throws Exception {
    InputStream is = new ByteArrayInputStream("TEST".getBytes());
    InternalMessage message = InternalMessage.builder().payload(is).build();
    assertThat("Should reject consumable payload", filter.accept(message, mock(Event.Builder.class)), is(false));
  }

  @Test
  public void testAcceptsNonConsumablePayload() throws Exception {
    InternalMessage message = InternalMessage.builder().payload("TEST").build();
    assertThat("Should accept non consumable payload", filter.accept(message, mock(Event.Builder.class)), is(true));
  }
}
