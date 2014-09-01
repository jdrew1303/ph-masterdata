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
package com.helger.masterdata.vat.validation;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.string.StringHelper;
import com.helger.masterdata.vat.VATINStructure;
import com.helger.masterdata.vat.VATINStructureManager;
import com.helger.validation.result.IValidationResult;
import com.helger.validation.result.ValidationResultError;
import com.helger.validation.result.ValidationResultSuccess;
import com.helger.validation.validator.string.AbstractStringValidator;

public class StringVATINValidator extends AbstractStringValidator
{
  @Nullable
  @OverrideOnDemand
  protected Locale getDisplayLocale ()
  {
    return null;
  }

  @Nonnull
  public IValidationResult validate (@Nullable final String sValue)
  {
    if (VATINStructureManager.isValidVATIN (sValue))
      return ValidationResultSuccess.getInstance ();

    final VATINStructure aStructure = VATINStructureManager.getFromVATINCountry (sValue);
    if (aStructure != null)
    {
      final Locale aDisplayLocale = getDisplayLocale ();
      final String sCountry = aDisplayLocale == null ? aStructure.getCountry ().getDisplayCountry ()
                                                    : aStructure.getCountry ().getDisplayCountry (aDisplayLocale);
      final String sExamples = StringHelper.getImploded (", ", aStructure.getExamples ());
      final Object [] aArgs = { sCountry, sExamples };
      return new ValidationResultError (EVATErrorTexts.INVALID_VATIN_WITH_EXAMPLES, aArgs);
    }

    return new ValidationResultError (EVATErrorTexts.INVALID_VATIN);
  }
}
