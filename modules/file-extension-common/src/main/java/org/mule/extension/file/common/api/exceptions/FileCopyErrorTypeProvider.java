/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extension.file.common.api.exceptions;

import static org.mule.extension.file.common.api.exceptions.FileErrors.*;
import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * @since 1.0
 */
public class FileCopyErrorTypeProvider implements ErrorTypeProvider {

  @Override
  public Set<ErrorTypeDefinition> getErrorTypes() {
    return ImmutableSet.<ErrorTypeDefinition>builder()
        .add(ILLEGAL_PATH)
        .add(FILE_ALREADY_EXISTS)
        .build();
  }
}

