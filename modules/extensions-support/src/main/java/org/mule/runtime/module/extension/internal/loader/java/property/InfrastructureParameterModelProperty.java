/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.extension.internal.loader.java.property;

import org.mule.runtime.api.meta.model.ModelProperty;
import org.mule.runtime.api.meta.model.parameter.ParameterModel;

/**
 * A {@link ModelProperty} meant to be applied on {@link ParameterModel} instances which represent an infrastructure parameter
 *
 * @since 4.0
 */
public class InfrastructureParameterModelProperty implements ModelProperty {

  /**
   * @return {@code infrastructureParameter}
   */
  @Override
  public String getName() {
    return "infrastructureParameter";
  }

  /**
   * @return {@code false}
   */
  @Override
  public boolean isPublic() {
    return false;
  }
}
