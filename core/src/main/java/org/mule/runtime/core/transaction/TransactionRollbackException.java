/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.core.transaction;

import org.mule.runtime.api.tx.TransactionException;
import org.mule.runtime.core.config.i18n.CoreMessages;
import org.mule.runtime.api.i18n.I18nMessage;

public class TransactionRollbackException extends TransactionException {

  /**
   * Serial version
   */
  private static final long serialVersionUID = -3247455113055556221L;

  /**
   * @param message the exception message
   */
  public TransactionRollbackException(I18nMessage message) {
    super(message);
  }

  /**
   * @param message the exception message
   * @param cause the exception that cause this exception to be thrown
   */
  public TransactionRollbackException(I18nMessage message, Throwable cause) {
    super(message, cause);
  }

  public TransactionRollbackException(Throwable cause) {
    super(CoreMessages.transactionRollbackFailed(), cause);
  }

}
