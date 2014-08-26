/**
 * Copyright (C) 2006-2014 phloc systems
 * http://www.phloc.com
 * office[at]phloc[dot]com
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
package com.helger.masterdata.price;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import com.helger.commons.microdom.IMicroElement;
import com.helger.masterdata.currency.ECurrency;
import com.helger.masterdata.currency.ReadonlyCurrencyValue;
import com.helger.masterdata.vat.VATManager;

public final class ReadonlyPriceMicroTypeConverter extends AbstractPriceMicroTypeConverter
{
  @Nonnull
  public final ReadonlyPrice convertToNative (@Nonnull final IMicroElement ePrice)
  {
    final ECurrency eCurrency = ECurrency.getFromIDOrNull (ePrice.getAttribute (ATTR_CURRENCY));
    final BigDecimal aValue = ePrice.getAttributeWithConversion (ATTR_NETAMOUNT, BigDecimal.class);
    final String sVATItemID = ePrice.getAttribute (ATTR_VATITEM);
    return new ReadonlyPrice (new ReadonlyCurrencyValue (eCurrency, aValue), VATManager.getDefaultInstance ()
                                                                                       .getVATItemOfID (sVATItemID));
  }
}