/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.masterdata.currencyvalue;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.math.MathHelper;
import com.helger.masterdata.currency.IHasCurrency;

/**
 * Read only interface of a currency value.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface ICurrencyValue extends IHasCurrency, Serializable
{
  /**
   * @return The contained numeric currency value.
   */
  @Nonnull
  BigDecimal getValue ();

  /**
   * @return <code>true</code> if the value is &lt; 0, <code>false</code> if it
   *         is &ge; 0.
   */
  default boolean isLowerThanZero ()
  {
    return MathHelper.isLowerThanZero (getValue ());
  }

  /**
   * @return <code>true</code> if the value is &lt; 0, <code>false</code> if it
   *         is &le; 0.
   */
  default boolean isGreaterThanZero ()
  {
    return MathHelper.isGreaterThanZero (getValue ());
  }

  /**
   * Sum up this currency value with the passed scalar value.
   *
   * @param aValue
   *        The value to add.
   * @return The added value with the same currency.
   */
  @Nonnull
  @CheckReturnValue
  ICurrencyValue getAdded (@Nonnull BigDecimal aValue);

  /**
   * Sum up this currency value with the passed scalar value.
   *
   * @param nValue
   *        The value to add.
   * @return The added value with the same currency.
   */
  @Nonnull
  @CheckReturnValue
  default ICurrencyValue getAdded (final long nValue)
  {
    if (nValue == 0)
      return this;
    return getAdded (MathHelper.toBigDecimal (nValue));
  }

  /**
   * Subtract this currency value with the passed scalar value.
   *
   * @param aValue
   *        The value to subtract.
   * @return The subtracted value with the same currency.
   */
  @Nonnull
  @CheckReturnValue
  ICurrencyValue getSubtracted (@Nonnull BigDecimal aValue);

  /**
   * Subtract this currency value with the passed scalar value.
   *
   * @param nValue
   *        The value to subtract.
   * @return The subtracted value with the same currency.
   */
  @Nonnull
  @CheckReturnValue
  default ICurrencyValue getSubtracted (final long nValue)
  {
    if (nValue == 0)
      return this;
    return getSubtracted (MathHelper.toBigDecimal (nValue));
  }

  /**
   * Multiply this currency value with the passed scalar value.
   *
   * @param aValue
   *        The multiplicator to use.
   * @return The multiplied value with the same currency.
   */
  @Nonnull
  @CheckReturnValue
  ICurrencyValue getMultiplied (@Nonnull BigDecimal aValue);

  /**
   * Multiply this currency value with the passed scalar value.
   *
   * @param nValue
   *        The multiplicator to use.
   * @return The multiplied value with the same currency.
   */
  @Nonnull
  @CheckReturnValue
  default ICurrencyValue getMultiplied (final long nValue)
  {
    if (nValue == 1)
      return this;
    return getMultiplied (MathHelper.toBigDecimal (nValue));
  }

  /**
   * Divide this currency value with the passed scalar value.
   *
   * @param aValue
   *        The divisor to use.
   * @return The divided value with the same currency.
   */
  @Nonnull
  @CheckReturnValue
  ICurrencyValue getDivided (@Nonnull BigDecimal aValue);

  /**
   * Divide this currency value with the passed scalar value.
   *
   * @param nValue
   *        The divisor to use.
   * @return The divided value with the same currency.
   */
  @Nonnull
  @CheckReturnValue
  default ICurrencyValue getDivided (final long nValue)
  {
    if (nValue == 1)
      return this;
    return getDivided (MathHelper.toBigDecimal (nValue));
  }

  /**
   * @return The value as a formatted currency including the currency sign. The
   *         scale is defined by the currency.
   */
  @Nonnull
  default String getCurrencyFormatted ()
  {
    return getCurrency ().getCurrencyFormatted (getValue ());
  }

  /**
   * @param nFractionDigits
   *        The number of fraction digits to be used.
   * @return The value as a formatted currency including the currency sign.
   */
  @Nonnull
  default String getCurrencyFormatted (@Nonnegative final int nFractionDigits)
  {
    return getCurrency ().getCurrencyFormatted (getValue (), nFractionDigits);
  }

  /**
   * @return The value as a formatted currency excluding the currency sign. The
   *         scale is defined by the currency.
   */
  @Nonnull
  default String getValueFormatted ()
  {
    return getCurrency ().getValueFormatted (getValue ());
  }

  /**
   * @param nFractionDigits
   *        The number of fraction digits to be used.
   * @return The value as a formatted currency excluding the currency sign.
   */
  @Nonnull
  default String getValueFormatted (@Nonnegative final int nFractionDigits)
  {
    return getCurrency ().getValueFormatted (getValue (), nFractionDigits);
  }
}
