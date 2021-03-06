/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.test.vegan.extension;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.message.Attributes;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.MetadataScope;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.UseConfig;
import org.mule.runtime.extension.api.runtime.source.Source;
import org.mule.runtime.extension.api.runtime.source.SourceCallback;
import org.mule.tck.testmodels.fruit.Apple;

@Alias("harvest-apples")
@MetadataScope(keysResolver = HarvestAppleKeyResolver.class,
    outputResolver = HarvestAppleKeyResolver.class)
public class HarvestApplesSource extends Source<Apple, Attributes> {

  @UseConfig
  AppleConfig appleConfig;

  @MetadataKeyId
  @Parameter
  @Optional
  private String key;

  @Override
  public void onStart(SourceCallback<Apple, Attributes> sourceCallback) throws MuleException {

  }

  @Override
  public void onStop() {}
}
