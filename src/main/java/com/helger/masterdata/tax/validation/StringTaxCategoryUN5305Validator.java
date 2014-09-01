/**
 * Copyright (C) 2014 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.masterdata.tax.validation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.masterdata.tax.ETaxCategoryUN5305;
import com.helger.validation.result.IValidationResult;
import com.helger.validation.result.ValidationResultError;
import com.helger.validation.result.ValidationResultSuccess;
import com.helger.validation.validator.string.AbstractStringValidator;

public final class StringTaxCategoryUN5305Validator extends AbstractStringValidator
{
  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (ETaxCategoryUN5305.getFromIDOrNull (sValue) != null)
      return ValidationResultSuccess.getInstance ();
    return new ValidationResultError (ETaxErrorTexts.INVALID_TAX_CATEGORY_UN5305);
  }
}
