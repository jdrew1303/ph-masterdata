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
package com.helger.masterdata.locale;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Translatable;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.ITextProvider;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;

/**
 * Name of the continents as used by {@link EContinent}.
 * 
 * @author Philip Helger
 */
@Translatable
public enum EContinentName implements IHasDisplayText
{
  AFRICA ("Afrika", "Africa"),
  ANTARCTICA ("Antarktis", "Antarctica"),
  ASIA ("Asien", "Asia"),
  EUROPE ("Europa", "Europe"),
  NORTH_AMERICA ("Nordamerika", "North America"),
  OCEANIA ("Ozeanien", "Oceania"),
  SOUTH_AMERICA ("Südamerika", "South America"),
  UNDEFINED ("Undefiniert", "Undefined");

  private final ITextProvider m_aTP;

  private EContinentName (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextProvider.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
  }
}
