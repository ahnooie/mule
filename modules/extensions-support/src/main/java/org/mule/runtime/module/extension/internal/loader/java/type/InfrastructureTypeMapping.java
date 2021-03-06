/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.extension.internal.loader.java.type;

import static java.util.stream.Collectors.toMap;
import static org.mule.runtime.extension.api.ExtensionConstants.TLS_ATTRIBUTE_NAME;
import org.mule.runtime.api.tls.TlsContextFactory;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Mapping for types considered of "Infrastructure", of the {@link Class} of the infrastructure type and the {@link String} name
 * of it.
 *
 * @since 4.0
 */
public class InfrastructureTypeMapping {

  private static Map<Class<?>, String> mapping = ImmutableMap.<Class<?>, String>builder()
      .put(TlsContextFactory.class, TLS_ATTRIBUTE_NAME).build();

  public static Map<Class<?>, String> getMap() {
    return mapping;
  }

  public static Map<String, String> getNameMap() {
    return mapping.entrySet().stream().collect(toMap(e -> e.getKey().getName(), Map.Entry::getValue));
  }
}
