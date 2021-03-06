/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.validation.validator.string;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.locale.IHasLocale;
import com.helger.commons.string.ToStringGenerator;

/**
 * Validating date values with a fixed locale provider.
 *
 * @author Philip Helger
 */
@Immutable
public class DateValidatorHasLocale extends AbstractStringDateValidator
{
  private final IHasLocale m_aLocaleProvider;

  public DateValidatorHasLocale (@Nonnull final IHasLocale aLocaleProvider)
  {
    m_aLocaleProvider = ValueEnforcer.notNull (aLocaleProvider, "LocaleProvider");
  }

  @Override
  @Nonnull
  protected final Locale getParseLocale ()
  {
    return m_aLocaleProvider.getLocale ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    // localeProvider is not required to implement equals/hashCode
    return true;
  }

  @Override
  public int hashCode ()
  {
    // localeProvider is not required to implement equals/hashCode
    return super.hashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("localeProvider", m_aLocaleProvider).getToString ();
  }
}
