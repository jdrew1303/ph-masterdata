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
package com.helger.masterdata.trade;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Translatable;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.ITextProvider;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;

/**
 * Incoterms texts
 *
 * @author Philip Helger
 */
@Translatable
public enum EIncotermName implements IHasDisplayText
{
  EXW ("ab Werk", "ex works"),
  FCA ("frei Frachtführer", "free carrier"),
  FAS ("frei längsseits Schiff", "free alongside ship"),
  FOB ("frei an Bord", "free on board"),
  CFR ("Kosten und Fracht", "cost and freight"),
  CIF ("Kosten, Versicherung und Fracht bis zum Bestimmungshafen/Bestimmungsort", "cost,insurance and freight"),
  CPT ("Fracht, Porto bezahlt bis", "carriage paid to"),
  CIP ("Fracht, Porto und Versicherung bezahlt bis", "carriage and insurance paid to"),
  DAF ("frei Grenze", "delivered at frontier"),
  DES ("frei ab Schiff", "delivered ex ship"),
  DEQ ("frei ab Kai", "delivered ex quay"),
  DDU ("frei unverzollt", "delivered duty unpaid"),
  DDP ("frei verzollt", "delivered duty paid");

  private final ITextProvider m_aTP;

  private EIncotermName (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextProvider.create_DE_EN (sDE, sEN);
  }

  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
  }
}
