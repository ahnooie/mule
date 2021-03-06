/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.tck.testmodels.mule;

import org.mule.runtime.core.api.Event;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.i18n.I18nMessageFactory;
import org.mule.runtime.core.processor.AbstractFilteringMessageProcessor;

public class FailingRouter extends AbstractFilteringMessageProcessor {

  @Override
  protected boolean accept(Event event, Event.Builder builder) {
    throw new MuleRuntimeException(I18nMessageFactory.createStaticMessage("Failure"));
  }
}
