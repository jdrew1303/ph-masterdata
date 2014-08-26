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
package com.helger.masterdata.swift;

import java.util.regex.Pattern;

import javax.annotation.Nullable;
import javax.annotation.RegEx;

import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.regex.RegExPool;
import com.helger.commons.string.StringHelper;

/**
 * This class manages the SWIFT Bank Identification Code (BIC).
 * 
 * @author Philip Helger
 */
public final class BICManager
{
  /** Minimum BIC length */
  public static final int BIC_LENGTH_MIN = 8;

  /** Maximum BIC length */
  public static final int BIC_LENGTH_MAX = 11;

  /** The suffix to be appended to a "short" BIC to make it a "long" BIC */
  public static final String BIC_SUFFIX_MIN_TO_MAX = "XXX";

  /** The regular expression a BIC must conform */
  @RegEx
  public static final String BIC_PATTERN = "(?i)^[a-z]{6}[a-z0-9]{2}([a-z0-9]{3})?$";

  /** Precompiled pattern */
  private static final Pattern s_aBICPattern = RegExPool.getPattern (BIC_PATTERN);

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final BICManager s_aInstance = new BICManager ();

  private BICManager ()
  {}

  /**
   * Check if the passed BIC is valid.<br>
   * 
   * <pre>
   * BBBB  4-stelliger Bankcode, vom Geldinstitut frei wählbar (nur Alphazeichen)
   *  CC    2-stelliger Ländercode nach ISO 3166-1 (nur Alphazeichen)
   *  LL    2-stellige Codierung des Ortes (alphanumerische Zeichen; wenn das zweite
   *        Zeichen eine 1 ist, so handelt es sich um einen passiven SWIFT-Teilnehmer;
   *        wenn das zweite Zeichen eine 0 ist, so handelt es sich um einen Test-BIC)
   *  bbb   3-stellige Kennzeichnung (Branch-Code) der Filiale oder Abteilung (optional,
   *        Standard: <code>XXX</code> (ohne Leerzeichen!), kann weggelassen werden,
   *        andere Kennzeichen nicht) (alphanumerische Zeichen)
   * </pre>
   * 
   * @param sBIC
   *        The BIC to check. May be <code>null</code>.
   * @return <code>true</code> if the passed BIC is valid, <code>false</code>
   *         otherwise.
   */
  public static boolean isValidBIC (@Nullable final String sBIC)
  {
    if (StringHelper.hasNoText (sBIC))
      return false;
    return s_aBICPattern.matcher (sBIC).matches ();
  }

  public static boolean isPassiveBICParticipant (@Nullable final String sBIC)
  {
    return isValidBIC (sBIC) && sBIC.charAt (7) == '1';
  }

  /**
   * Determine whether the passed BIC is a test-only BIC.
   * 
   * @param sBIC
   *        The BIC to be tested.
   * @return <code>true</code> if the passed BIC is a test BIC,
   *         <code>false</code> otherwise.
   */
  public static boolean isTestBIC (@Nullable final String sBIC)
  {
    return isValidBIC (sBIC) && sBIC.charAt (7) == '0';
  }
}