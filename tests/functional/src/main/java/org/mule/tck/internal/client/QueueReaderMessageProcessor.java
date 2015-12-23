/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.tck.internal.client;

import static org.mule.tck.internal.client.TestConnectorConfig.DEFAULT_CONFIG_ID;
import static org.mule.util.Preconditions.checkArgument;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;
import org.mule.util.StringUtils;

/**
 * Reads {@link MuleEvent} from a test connector's queue.
 */
public class QueueReaderMessageProcessor implements MessageProcessor
{

    private final MuleContext muleContext;
    private final String queueName;
    private final Long timeout;

    /**
     * Creates a queue reader
     *
     * @param muleContext application's mule context. Not null.
     * @param queueName   name of the queue to use. Non empty
     * @param timeout number of milliseconds to wait for an available event. Non negative. Null means no timeout required.
     */
    public QueueReaderMessageProcessor(MuleContext muleContext, String queueName, Long timeout)
    {
        checkArgument(muleContext != null, "MuleContext cannot be null");
        checkArgument(!StringUtils.isEmpty(queueName), "Queue name cannot be empty");
        if (timeout != null)
        {
            checkArgument(timeout >= 0L, "Timeout cannot be negative");
        }

        this.muleContext = muleContext;
        this.queueName = queueName;
        this.timeout = timeout;
    }

    @Override
    public MuleEvent process(MuleEvent event) throws MuleException
    {
        TestConnectorConfig connectorConfig = muleContext.getRegistry().lookupObject(DEFAULT_CONFIG_ID);

        if (timeout == null)
        {
            return connectorConfig.take(queueName);
        }
        else
        {
            return connectorConfig.poll(queueName, timeout);
        }
    }
}
