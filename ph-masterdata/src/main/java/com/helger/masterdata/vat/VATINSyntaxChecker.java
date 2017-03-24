package com.helger.masterdata.vat;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.function.IToBooleanFunction;

/**
 * Check the syntax of VATINs based on the published rules.
 *
 * @author Philip Helger
 * @since 5.0.4
 */
public class VATINSyntaxChecker
{
  private static final ICommonsMap <String, IToBooleanFunction <String>> s_aMap = new CommonsHashMap<> ();

  static
  {
    s_aMap.put ("AT", VATINSyntaxChecker::isValidVATIN_AT);
    s_aMap.put ("BE", VATINSyntaxChecker::isValidVATIN_BE);
    s_aMap.put ("BG", VATINSyntaxChecker::isValidVATIN_BG);
    s_aMap.put ("CY", VATINSyntaxChecker::isValidVATIN_CY);
    s_aMap.put ("CZ", VATINSyntaxChecker::isValidVATIN_CZ);
    s_aMap.put ("DE", VATINSyntaxChecker::isValidVATIN_DE);
    s_aMap.put ("DK", VATINSyntaxChecker::isValidVATIN_DK);
    s_aMap.put ("EE", VATINSyntaxChecker::isValidVATIN_EE);
    s_aMap.put ("EL", VATINSyntaxChecker::isValidVATIN_EL);
    s_aMap.put ("GR", VATINSyntaxChecker::isValidVATIN_EL);
    s_aMap.put ("ES", VATINSyntaxChecker::isValidVATIN_ES);
    s_aMap.put ("FI", VATINSyntaxChecker::isValidVATIN_FI);
    s_aMap.put ("FR", VATINSyntaxChecker::isValidVATIN_FR);
    s_aMap.put ("GB", VATINSyntaxChecker::isValidVATIN_GB);
    s_aMap.put ("HR", VATINSyntaxChecker::isValidVATIN_HR);
    s_aMap.put ("HU", VATINSyntaxChecker::isValidVATIN_HU);
    s_aMap.put ("IE", VATINSyntaxChecker::isValidVATIN_IE);
    s_aMap.put ("IT", VATINSyntaxChecker::isValidVATIN_IT);
    s_aMap.put ("LT", VATINSyntaxChecker::isValidVATIN_LT);
    s_aMap.put ("LU", VATINSyntaxChecker::isValidVATIN_LU);
    s_aMap.put ("LV", VATINSyntaxChecker::isValidVATIN_LV);
    s_aMap.put ("MT", VATINSyntaxChecker::isValidVATIN_MT);
    s_aMap.put ("NL", VATINSyntaxChecker::isValidVATIN_NL);
    s_aMap.put ("PL", VATINSyntaxChecker::isValidVATIN_PL);
    s_aMap.put ("PT", VATINSyntaxChecker::isValidVATIN_PT);
    s_aMap.put ("RO", VATINSyntaxChecker::isValidVATIN_RO);
    s_aMap.put ("SE", VATINSyntaxChecker::isValidVATIN_SE);
    s_aMap.put ("SI", VATINSyntaxChecker::isValidVATIN_SI);
    s_aMap.put ("SK", VATINSyntaxChecker::isValidVATIN_SK);
  }

  private VATINSyntaxChecker ()
  {}

  /**
   * Check if the provided VATIN is valid. This method handles VATINs for all
   * countries. This check uses only the checksum algorithm and does not call
   * any webservice etc.
   *
   * @param sVATIN
   *        VATIN to check. May not be <code>null</code>.
   * @return <code>true</code> if the VATIN is valid (or unknown).
   */
  public static boolean isValidVATIN (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    if (sVATIN.length () > 2)
    {
      final String sCountryCode = sVATIN.substring (0, 2).toUpperCase (Locale.US);
      final IToBooleanFunction <String> aValidator = s_aMap.get (sCountryCode);
      if (aValidator != null)
        return aValidator.applyAsBoolean (sVATIN.substring (2));
    }

    // No validator
    return true;
  }

  /**
   * Check if a validator is present for the provided VATIN.
   *
   * @param sVATIN
   *        VATIN to check. May not be <code>null</code>.
   * @return <code>true</code> if a validator is present, <code>false</code> if
   *         not.
   */
  public static boolean isValidatorPresent (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    if (sVATIN.length () <= 2)
      return false;

    final String sCountryCode = sVATIN.substring (0, 2).toUpperCase (Locale.US);
    return s_aMap.containsKey (sCountryCode);
  }

  private static boolean _isNum (final char c)
  {
    return c >= '0' && c <= '9';
  }

  private static boolean _isNum1to9 (final char c)
  {
    return c >= '1' && c <= '9';
  }

  private static boolean _isLetter (final char c)
  {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
  }

  private static boolean _isLetterOrNum (final char c)
  {
    return _isLetter (c) || _isNum (c);
  }

  private static int _toInt (final char c)
  {
    return c - '0';
  }

  private static int _toInt (final char c0, final char c1)
  {
    return _toInt (c0) * 10 + _toInt (c1);
  }

  private static int _toInt (final char c0, final char c1, final char c2)
  {
    return _toInt (c0) * 100 + _toInt (c1) * 10 + _toInt (c2);
  }

  private static int _toInt (final char c0, final char c1, final char c2, final char c3, final char c4, final char c5)
  {
    return _toInt (c0) * 100_000 +
           _toInt (c1) * 10_000 +
           _toInt (c2) * 1_000 +
           _toInt (c3) * 100 +
           _toInt (c4) * 10 +
           _toInt (c5);
  }

  private static int _toInt (final char c0,
                             final char c1,
                             final char c2,
                             final char c3,
                             final char c4,
                             final char c5,
                             final char c6)
  {
    return _toInt (c0) * 1_000_000 +
           _toInt (c1) * 100_000 +
           _toInt (c2) * 10_000 +
           _toInt (c3) * 1_000 +
           _toInt (c4) * 100 +
           _toInt (c5) * 10 +
           _toInt (c6);
  }

  private static int _toInt (final char c0,
                             final char c1,
                             final char c2,
                             final char c3,
                             final char c4,
                             final char c5,
                             final char c6,
                             final char c7)
  {
    return _toInt (c0) * 10_000_000 +
           _toInt (c1) * 1_000_000 +
           _toInt (c2) * 100_000 +
           _toInt (c3) * 10_000 +
           _toInt (c4) * 1_000 +
           _toInt (c5) * 100 +
           _toInt (c6) * 10 +
           _toInt (c7);
  }

  private static int _toInt (final char c0,
                             final char c1,
                             final char c2,
                             final char c3,
                             final char c4,
                             final char c5,
                             final char c6,
                             final char c7,
                             final char c8)
  {
    return _toInt (c0) * 100_000_000 +
           _toInt (c1) * 10_000_000 +
           _toInt (c2) * 1_000_000 +
           _toInt (c3) * 100_000 +
           _toInt (c4) * 10_000 +
           _toInt (c5) * 1_000 +
           _toInt (c6) * 100 +
           _toInt (c7) * 10 +
           _toInt (c8);
  }

  private static long _toLong (final char c0,
                               final char c1,
                               final char c2,
                               final char c3,
                               final char c4,
                               final char c5,
                               final char c6,
                               final char c7,
                               final char c8,
                               final char c9)
  {
    return _toInt (c0) * 1_000_000_000L +
           _toInt (c1) * 100_000_000L +
           _toInt (c2) * 10_000_000L +
           _toInt (c3) * 1_000_000L +
           _toInt (c4) * 100_000L +
           _toInt (c5) * 10_000L +
           _toInt (c6) * 1_000L +
           _toInt (c7) * 100L +
           _toInt (c8) * 10L +
           _toInt (c9);
  }

  private static int _at_s (final char c)
  {
    // Si = INT(Ci / 5) + (Ci * 2) modulo 10
    final int n = _toInt (c);
    return n / 5 + (n * 2) % 10;
  }

  public static boolean isValidVATIN_AT (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 9)
      return false;
    if (c[0] != 'U')
      return false;
    for (int i = 1; i <= 8; ++i)
      if (!_isNum (c[i]))
        return false;
    final int r = _at_s (c[2]) + _at_s (c[4]) + _at_s (c[6]);
    final int n9 = (10 - (r + _toInt (c[1]) + _toInt (c[3]) + _toInt (c[5]) + _toInt (c[7]) + 4) % 10) % 10;
    return _toInt (c[8]) == n9;
  }

  public static boolean isValidVATIN_BE (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 10)
      return false;
    if (c[0] != '0')
      return false;
    if (!_isNum1to9 (c[1]))
      return false;
    for (int i = 2; i <= 9; ++i)
      if (!_isNum (c[i]))
        return false;
    final int nChecksum = 97 - (_toInt (c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7]) % 97);
    final int nExpected = _toInt (c[8], c[9]);
    return nExpected == nChecksum;
  }

  public static boolean isValidVATIN_DE (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 9)
      return false;
    if (!_isNum1to9 (c[0]))
      return false;
    for (int i = 1; i <= 8; ++i)
      if (!_isNum (c[i]))
        return false;

    int p = 10;
    for (int n = 0; n <= 7; ++n)
    {
      int m = (_toInt (c[n]) + p) % 10;
      if (m == 0)
        m = 10;
      p = (2 * m) % 11;
    }
    final int r = 11 - p;
    final int nChecksum = r == 10 ? 0 : r;
    final int nExpected = _toInt (c[8]);
    return nChecksum == nExpected;
  }

  public static boolean isValidVATIN_DK (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 8)
      return false;
    if (!_isNum1to9 (c[0]))
      return false;
    for (int i = 1; i <= 7; ++i)
      if (!_isNum (c[i]))
        return false;

    final int r = (2 * _toInt (c[0]) +
                   7 * _toInt (c[1]) +
                   6 * _toInt (c[2]) +
                   5 * _toInt (c[3]) +
                   4 * _toInt (c[4]) +
                   3 * _toInt (c[5]) +
                   2 * _toInt (c[6]) +
                   1 * _toInt (c[7]));
    return (r % 11) == 0;
  }

  // TODO EL
  public static boolean isValidVATIN_EL (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 9)
      return false;
    for (int i = 0; i <= 8; ++i)
      if (!_isNum (c[i]))
        return false;

    return true;
  }

  // TODO ES
  public static boolean isValidVATIN_ES (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 9)
      return false;
    if (!_isLetterOrNum (c[0]))
      return false;
    for (int i = 1; i <= 7; ++i)
      if (!_isNum (c[i]))
        return false;
    if (!_isLetterOrNum (c[8]))
      return false;
    // Only one can be a letter
    if (_isLetter (c[0]) && _isLetter (c[8]))
      return false;

    return true;
  }

  public static boolean isValidVATIN_FI (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 8)
      return false;
    for (int i = 0; i <= 7; ++i)
      if (!_isNum (c[i]))
        return false;

    final int r = 11 -
                  (7 * _toInt (c[0]) +
                   9 * _toInt (c[1]) +
                   10 * _toInt (c[2]) +
                   5 * _toInt (c[3]) +
                   8 * _toInt (c[4]) +
                   4 * _toInt (c[5]) +
                   2 * _toInt (c[6])) % 11;
    if (r == 10)
      return false;

    final int nChecksum = r == 11 ? 0 : r;
    final int nExpected = _toInt (c[7]);
    return nChecksum == nExpected;
  }

  // TODO FR
  public static boolean isValidVATIN_FR (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 11)
      return false;
    if (!_isLetterOrNum (c[0]))
      return false;
    if (!_isLetterOrNum (c[1]))
      return false;
    for (int i = 2; i <= 10; ++i)
      if (!_isNum (c[i]))
        return false;

    return true;
  }

  public static boolean isValidVATIN_GB (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length == 5)
    {
      // This format applies to Government departments and Health authorities
      if (c[0] == 'G' && c[1] == 'D')
      {
        final int n = _toInt (c[2], c[3], c[4]);
        return n >= 0 && n <= 499;
      }
      if (c[0] == 'H' && c[1] == 'A')
      {
        final int n = _toInt (c[2], c[3], c[4]);
        return n >= 500 && n <= 999;
      }
      return false;
    }

    // This format applies to all others
    if (c.length != 9 && c.length != 12)
      return false;
    for (int i = 0; i < c.length; ++i)
      if (!_isNum (c[i]))
        return false;

    final int v1 = _toInt (c[0], c[1], c[2], c[3], c[4], c[5], c[6]);
    if (v1 >= 100_000 && v1 <= 999_999)
      return false;
    if (v1 >= 9_490_001 && v1 <= 9_700_000)
      return false;
    if (v1 >= 9_990_001 && v1 <= 9_999_999)
      return false;
    if (c.length == 12)
    {
      final int v2b = _toInt (c[9], c[10], c[11]);
      if (v2b <= 0)
        return false;
    }

    final int v2 = _toInt (c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8]);
    if (v2 <= 0)
      return false;

    final int tmp = 8 * _toInt (c[0]) +
                    7 * _toInt (c[1]) +
                    6 * _toInt (c[2]) +
                    5 * _toInt (c[3]) +
                    4 * _toInt (c[4]) +
                    3 * _toInt (c[5]) +
                    2 * _toInt (c[6]) +
                    1 * _toInt (c[7], c[8]);
    final int r1 = tmp % 97;
    final int r2 = (tmp + 55) % 97;
    return r1 == 0 || r2 == 0;
  }

  private static boolean _ie_is2 (final char c)
  {
    return (c >= 'A' && c <= 'Z') || c == '+' || c == '*';
  }

  private static boolean _ie_is8 (final char c)
  {
    return (c >= 'A' && c <= 'W');
  }

  private static boolean _ie_is9 (final char c)
  {
    return (c >= 'A' && c <= 'I');
  }

  private static char _ie_checkChar (final int r)
  {
    return r == 0 ? 'W' : (char) ('A' + r - 1);
  }

  private static int _ie_toNum (final char c)
  {
    return c - 'A' + 1;
  }

  private static boolean _ie_isV1 (@Nonnull final char [] c)
  {
    if (c.length != 8)
      return false;
    if (!_isNum (c[0]))
      return false;
    if (!_ie_is2 (c[1]))
      return false;
    for (int i = 2; i <= 6; ++i)
      if (!_isNum (c[i]))
        return false;
    if (!_ie_is8 (c[7]))
      return false;
    final int r = (0 * 8 +
                   _toInt (c[2]) * 7 +
                   _toInt (c[3]) * 6 +
                   _toInt (c[4]) * 5 +
                   _toInt (c[5]) * 4 +
                   _toInt (c[6]) * 3 +
                   _toInt (c[0]) * 2) %
                  23;

    final char cCheck = _ie_checkChar (r);
    return c[7] == cCheck;
  }

  private static boolean _ie_isV2 (@Nonnull final char [] c)
  {
    if (c.length != 8)
      return false;
    for (int i = 0; i <= 6; ++i)
      if (!_isNum (c[i]))
        return false;
    if (!_ie_is8 (c[7]))
      return false;
    final int r = (_toInt (c[0]) * 8 +
                   _toInt (c[1]) * 7 +
                   _toInt (c[2]) * 6 +
                   _toInt (c[3]) * 5 +
                   _toInt (c[4]) * 4 +
                   _toInt (c[5]) * 3 +
                   _toInt (c[6]) * 2) %
                  23;

    final char cCheck = _ie_checkChar (r);
    return c[7] == cCheck;
  }

  public static boolean isValidVATIN_IE (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();

    // Version 1 (old Style)
    if (_ie_isV1 (c))
      return true;

    // Version 2 (new Style 8 characters)
    if (_ie_isV2 (c))
      return true;

    // Version 3 (new Style 9 characters)
    if (c.length != 9)
      return false;
    for (int i = 0; i <= 6; ++i)
      if (!_isNum (c[i]))
        return false;
    if (!_ie_is8 (c[7]))
      return false;
    if (!_ie_is9 (c[8]))
      return false;
    final int r = (_toInt (c[0]) * 8 +
                   _toInt (c[1]) * 7 +
                   _toInt (c[2]) * 6 +
                   _toInt (c[3]) * 5 +
                   _toInt (c[4]) * 4 +
                   _toInt (c[5]) * 3 +
                   _toInt (c[6]) * 2 +
                   _ie_toNum (c[8]) * 9) %
                  23;

    final char cCheck = _ie_checkChar (r);
    return c[7] == cCheck;
  }

  private static int _it_d (final char c)
  {
    final int n = _toInt (c);
    return n / 5 + (2 * n) % 10;
  }

  public static boolean isValidVATIN_IT (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();

    if (c.length != 11)
      return false;
    for (int i = 0; i <= 10; ++i)
      if (!_isNum (c[i]))
        return false;

    final int v = _toInt (c[7], c[8], c[9]);
    if (!((v > 0 && v < 101) || v == 120 || v == 121 || v == 999 || v == 888))
      return false;

    final int s1 = _toInt (c[0]) + _toInt (c[2]) + _toInt (c[4]) + _toInt (c[6]) + _toInt (c[8]);
    final int s2 = _it_d (c[1]) + _it_d (c[3]) + _it_d (c[5]) + _it_d (c[7]) + _it_d (c[9]);
    final int nChecksum = (10 - (s1 + s2) % 10) % 10;
    final int nExpected = _toInt (c[10]);
    return nChecksum == nExpected;
  }

  public static boolean isValidVATIN_LU (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();

    if (c.length != 8)
      return false;
    for (int i = 0; i <= 7; ++i)
      if (!_isNum (c[i]))
        return false;

    final int nChecksum = _toInt (c[0], c[1], c[2], c[3], c[4], c[5]) % 89;
    final int nExpected = _toInt (c[6], c[7]);
    return nChecksum == nExpected;
  }

  public static boolean isValidVATIN_NL (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();

    if (c.length != 12)
      return false;
    for (int i = 0; i <= 8; ++i)
      if (!_isNum (c[i]))
        return false;
    if (c[9] != 'B')
      return false;
    for (int i = 10; i <= 11; ++i)
      if (!_isNum (c[i]))
        return false;

    final int a1 = _toInt (c[0]) * 9 +
                   _toInt (c[1]) * 8 +
                   _toInt (c[2]) * 7 +
                   _toInt (c[3]) * 6 +
                   _toInt (c[4]) * 5 +
                   _toInt (c[5]) * 4 +
                   _toInt (c[6]) * 3 +
                   _toInt (c[7]) * 2;
    final int nChecksum = a1 % 11;
    if (nChecksum == 10)
      return false;

    final int v1 = _toInt (c[10], c[11]);
    if (v1 <= 0)
      return false;

    final int nExpected = _toInt (c[8]);
    return nChecksum == nExpected;
  }

  public static boolean isValidVATIN_PT (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();

    if (c.length != 9)
      return false;
    if (!_isNum1to9 (c[0]))
      return false;
    for (int i = 1; i <= 8; ++i)
      if (!_isNum (c[i]))
        return false;

    final int r = 11 -
                  (_toInt (c[0]) * 9 +
                   _toInt (c[1]) * 8 +
                   _toInt (c[2]) * 7 +
                   _toInt (c[3]) * 6 +
                   _toInt (c[4]) * 5 +
                   _toInt (c[5]) * 4 +
                   _toInt (c[6]) * 3 +
                   _toInt (c[7]) * 2) % 11;
    final int nChecksum = (r == 10 || r == 11) ? 0 : r;
    final int nExpected = _toInt (c[8]);
    return nChecksum == nExpected;
  }

  public static int _se_s (final char c)
  {
    final int n = _toInt (c);
    return n / 5 + (n * 2) % 10;
  }

  public static boolean isValidVATIN_SE (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();

    if (c.length != 12)
      return false;
    for (int i = 0; i <= 11; ++i)
      if (!_isNum (c[i]))
        return false;

    final int v1 = _toInt (c[10], c[11]);
    if (v1 < 1 || v1 > 94)
      return false;

    final int r = _se_s (c[0]) + _se_s (c[2]) + _se_s (c[4]) + _se_s (c[6]) + _se_s (c[8]);
    final int nChecksum = (10 - (r + _toInt (c[1]) + _toInt (c[3]) + _toInt (c[5]) + _toInt (c[7])) % 10) % 10;
    final int nExpected = _toInt (c[9]);
    return nChecksum == nExpected;
  }

  private static boolean _cy_is1 (final char c)
  {
    return c == '0' || c == '1' || c == '3' || c == '4' || c == '5' || c == '9';
  }

  private static final int [] cy_odd = new int [] { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21 };

  private static int _cy_odd (final char c)
  {
    return cy_odd[c - '0'];
  }

  public static boolean isValidVATIN_CY (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 9)
      return false;
    if (!_cy_is1 (c[0]))
      return false;
    for (int i = 1; i <= 7; ++i)
      if (!_isNum (c[i]))
        return false;
    if (!_isLetterOrNum (c[8]))
      return false;

    final int v = _toInt (c[0], c[1]);
    if (v == 12)
      return false;

    final int a1 = _cy_odd (c[0]) +
                   _toInt (c[1]) +
                   _cy_odd (c[2]) +
                   _toInt (c[3]) +
                   _cy_odd (c[4]) +
                   _toInt (c[5]) +
                   _cy_odd (c[6]) +
                   _toInt (c[7]);
    final char cChecksum = (char) ('A' + (a1 % 26));
    return cChecksum == c[8];
  }

  // TODO CZ
  public static boolean isValidVATIN_CZ (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length < 8 || c.length > 10)
      return false;
    for (int i = 0; i < c.length; ++i)
      if (!_isNum (c[i]))
        return false;

    return true;
  }

  public static boolean isValidVATIN_EE (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 9)
      return false;
    for (int i = 0; i <= 8; ++i)
      if (!_isNum (c[i]))
        return false;

    final int a1 = 3 * _toInt (c[0]) +
                   7 * _toInt (c[1]) +
                   1 * _toInt (c[2]) +
                   3 * _toInt (c[3]) +
                   7 * _toInt (c[4]) +
                   1 * _toInt (c[5]) +
                   3 * _toInt (c[6]) +
                   7 * _toInt (c[7]);
    // Round to ceiling multiple of 10
    final int a2 = (int) Math.ceil (a1 / 10.0) * 10;
    final int nChecksum = a2 - a1;
    final int nExpected = _toInt (c[8]);
    return nChecksum == nExpected;
  }

  public static boolean isValidVATIN_HU (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 8)
      return false;
    for (int i = 0; i <= 7; ++i)
      if (!_isNum (c[i]))
        return false;

    final int a1 = 9 * _toInt (c[0]) +
                   7 * _toInt (c[1]) +
                   3 * _toInt (c[2]) +
                   1 * _toInt (c[3]) +
                   9 * _toInt (c[4]) +
                   7 * _toInt (c[5]) +
                   3 * _toInt (c[6]);
    final int nRest = a1 % 10;
    final int nChecksum = nRest == 0 ? 0 : 10 - nRest;
    final int nExpected = _toInt (c[7]);
    return nChecksum == nExpected;
  }

  private static boolean _lt_isLegalPerson (@Nonnull final char [] c)
  {
    if (c.length != 9)
      return false;
    for (int i = 0; i <= 6; ++i)
      if (!_isNum (c[i]))
        return false;
    if (c[7] != '1')
      return false;
    if (!_isNum (c[8]))
      return false;

    final int a1 = 1 * _toInt (c[0]) +
                   2 * _toInt (c[1]) +
                   3 * _toInt (c[2]) +
                   4 * _toInt (c[3]) +
                   5 * _toInt (c[4]) +
                   6 * _toInt (c[5]) +
                   7 * _toInt (c[6]) +
                   8 * _toInt (c[7]);
    final int r1 = a1 % 11;
    int nChecksum;
    if (r1 != 10)
      nChecksum = r1;
    else
    {
      final int a2 = 3 * _toInt (c[0]) +
                     4 * _toInt (c[1]) +
                     5 * _toInt (c[2]) +
                     6 * _toInt (c[3]) +
                     7 * _toInt (c[4]) +
                     8 * _toInt (c[5]) +
                     9 * _toInt (c[6]) +
                     1 * _toInt (c[7]);
      final int r2 = a2 % 11;
      nChecksum = r2 == 10 ? 0 : r2;
    }
    final int nExpected = _toInt (c[8]);
    return nChecksum == nExpected;
  }

  public static boolean isValidVATIN_LT (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();

    // Format 1: Legal persons
    if (_lt_isLegalPerson (c))
      return true;

    // Format 2: Temporarily Registered Taxpayers
    if (c.length != 12)
      return false;
    for (int i = 0; i <= 9; ++i)
      if (!_isNum (c[i]))
        return false;
    if (c[10] != '1')
      return false;
    if (!_isNum (c[11]))
      return false;

    final int a1 = 1 * _toInt (c[0]) +
                   2 * _toInt (c[1]) +
                   3 * _toInt (c[2]) +
                   4 * _toInt (c[3]) +
                   5 * _toInt (c[4]) +
                   6 * _toInt (c[5]) +
                   7 * _toInt (c[6]) +
                   8 * _toInt (c[7]) +
                   9 * _toInt (c[8]) +
                   1 * _toInt (c[9]) +
                   2 * _toInt (c[10]);
    final int r1 = a1 % 11;
    int nChecksum;
    if (r1 != 10)
      nChecksum = r1;
    else
    {
      final int a2 = 3 * _toInt (c[0]) +
                     4 * _toInt (c[1]) +
                     5 * _toInt (c[2]) +
                     6 * _toInt (c[3]) +
                     7 * _toInt (c[4]) +
                     8 * _toInt (c[5]) +
                     9 * _toInt (c[6]) +
                     1 * _toInt (c[7]) +
                     2 * _toInt (c[8]) +
                     3 * _toInt (c[9]) +
                     4 * _toInt (c[10]);
      final int r2 = a2 % 11;
      nChecksum = r2 == 10 ? 0 : r2;
    }
    final int nExpected = _toInt (c[11]);
    return nChecksum == nExpected;
  }

  // TODO LV
  public static boolean isValidVATIN_LV (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 11)
      return false;
    for (int i = 0; i <= 10; ++i)
      if (!_isNum (c[i]))
        return false;

    return true;
  }

  // TODO MT
  public static boolean isValidVATIN_MT (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 8)
      return false;
    for (int i = 0; i <= 7; ++i)
      if (!_isNum (c[i]))
        return false;

    return true;
  }

  public static boolean isValidVATIN_PL (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 10)
      return false;
    for (int i = 0; i <= 9; ++i)
      if (!_isNum (c[i]))
        return false;

    final int a1 = 6 * _toInt (c[0]) +
                   5 * _toInt (c[1]) +
                   7 * _toInt (c[2]) +
                   2 * _toInt (c[3]) +
                   3 * _toInt (c[4]) +
                   4 * _toInt (c[5]) +
                   5 * _toInt (c[6]) +
                   6 * _toInt (c[7]) +
                   7 * _toInt (c[8]);
    final int nChecksum = a1 % 11;
    if (nChecksum == 10)
      return false;
    final int nExpected = _toInt (c[9]);
    return nChecksum == nExpected;
  }

  public static boolean isValidVATIN_SI (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 8)
      return false;
    for (int i = 0; i <= 7; ++i)
      if (!_isNum (c[i]))
        return false;

    final int v = _toInt (c[0], c[1], c[2], c[3], c[4], c[5], c[6]);
    if (v < 1_000_000 | v > 9_999_999)
      return false;

    final int a1 = 8 * _toInt (c[0]) +
                   7 * _toInt (c[1]) +
                   6 * _toInt (c[2]) +
                   5 * _toInt (c[3]) +
                   4 * _toInt (c[4]) +
                   3 * _toInt (c[5]) +
                   2 * _toInt (c[6]);
    final int r = 11 - (a1 % 11);
    if (r == 11)
      return false;
    final int nChecksum = r == 10 ? 0 : r;
    final int nExpected = _toInt (c[7]);
    return nChecksum == nExpected;
  }

  private static boolean _sk_is3 (final int c)
  {
    return c == '2' || c == '3' || c == '4' || c == '7' || c == '8' || c == '9';
  }

  public static boolean isValidVATIN_SK (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 10)
      return false;
    if (!_isNum1to9 (c[0]))
      return false;
    if (!_isNum (c[1]))
      return false;
    if (!_sk_is3 (c[2]))
      return false;
    for (int i = 3; i <= 9; ++i)
      if (!_isNum (c[i]))
        return false;

    final long v = _toLong (c[0], c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8], c[9]);
    return (v % 11) == 0;
  }

  private static boolean _bg_isV1 (@Nonnull final char [] c)
  {
    if (c.length != 9)
      return false;
    for (int i = 0; i <= 8; ++i)
      if (!_isNum (c[i]))
        return false;

    final int a1 = 1 * _toInt (c[0]) +
                   2 * _toInt (c[1]) +
                   3 * _toInt (c[2]) +
                   4 * _toInt (c[3]) +
                   5 * _toInt (c[4]) +
                   6 * _toInt (c[5]) +
                   7 * _toInt (c[6]) +
                   8 * _toInt (c[7]);
    final int r1 = a1 % 11;
    int nChecksum;
    if (r1 != 10)
      nChecksum = r1;
    else
    {
      final int a2 = 3 * _toInt (c[0]) +
                     4 * _toInt (c[1]) +
                     5 * _toInt (c[2]) +
                     6 * _toInt (c[3]) +
                     7 * _toInt (c[4]) +
                     8 * _toInt (c[5]) +
                     9 * _toInt (c[6]) +
                     10 * _toInt (c[7]);
      final int r2 = a2 % 11;
      nChecksum = r2 == 10 ? 0 : r2;
    }
    final int nExpected = _toInt (c[8]);
    return nChecksum == nExpected;
  }

  private static boolean _bg_isV2 (@Nonnull final char [] c)
  {
    if (c.length != 10)
      return false;
    for (int i = 0; i <= 9; ++i)
      if (!_isNum (c[i]))
        return false;

    final int m = _toInt (c[2], c[3]) % 20;
    final int d = _toInt (c[4], c[5]);
    if (m == 2)
    {
      if (d < 1 || d > 29)
        return false;
    }
    else
      if (m == 4 || m == 6 || m == 9 || m == 11)
      {
        if (d < 1 || d > 30)
          return false;
      }
      else
        if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)
        {
          if (d < 1 || d > 31)
            return false;
        }

    final int a1 = 2 * _toInt (c[0]) +
                   4 * _toInt (c[1]) +
                   8 * _toInt (c[2]) +
                   5 * _toInt (c[3]) +
                   10 * _toInt (c[4]) +
                   9 * _toInt (c[5]) +
                   7 * _toInt (c[6]) +
                   3 * _toInt (c[7]) +
                   6 * _toInt (c[8]);
    final int r1 = a1 % 11;
    final int nChecksum = r1 == 10 ? 0 : r1;
    final int nExpected = _toInt (c[9]);
    return nChecksum == nExpected;
  }

  private static boolean _bg_isV3 (@Nonnull final char [] c)
  {
    if (c.length != 10)
      return false;
    for (int i = 0; i <= 9; ++i)
      if (!_isNum (c[i]))
        return false;

    final int a1 = 21 * _toInt (c[0]) +
                   19 * _toInt (c[1]) +
                   17 * _toInt (c[2]) +
                   13 * _toInt (c[3]) +
                   11 * _toInt (c[4]) +
                   9 * _toInt (c[5]) +
                   7 * _toInt (c[6]) +
                   3 * _toInt (c[7]) +
                   1 * _toInt (c[8]);
    final int nChecksum = a1 % 10;
    final int nExpected = _toInt (c[9]);
    return nChecksum == nExpected;
  }

  public static boolean isValidVATIN_BG (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();

    // Format 1: 9 digits numbers for legal entities
    if (_bg_isV1 (c))
      return true;

    // Format 2: 10 digits numbers Bulgarian physical persons
    if (_bg_isV2 (c))
      return true;

    // Format 3: 10 digits numbers for foreigners
    if (_bg_isV3 (c))
      return true;

    // Format 4: 10 digits numbers for entities not covered by the above three
    // categories
    if (c.length != 10)
      return false;
    for (int i = 0; i <= 9; ++i)
      if (!_isNum (c[i]))
        return false;

    final int a1 = 4 * _toInt (c[0]) +
                   3 * _toInt (c[1]) +
                   2 * _toInt (c[2]) +
                   7 * _toInt (c[3]) +
                   6 * _toInt (c[4]) +
                   5 * _toInt (c[5]) +
                   4 * _toInt (c[6]) +
                   3 * _toInt (c[7]) +
                   2 * _toInt (c[8]);
    final int r1 = 11 - a1 % 11;
    if (r1 == 10)
      return false;
    final int nChecksum = r1 == 11 ? 0 : r1;
    final int nExpected = _toInt (c[9]);
    return nChecksum == nExpected;
  }

  // TODO RO
  public static boolean isValidVATIN_RO (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length < 2 || c.length > 10)
      return false;
    for (int i = 0; i < c.length; ++i)
      if (!_isNum (c[i]))
        return false;

    return true;
  }

  // TODO HR
  public static boolean isValidVATIN_HR (@Nonnull final String sVATIN)
  {
    ValueEnforcer.notNull (sVATIN, "VATIN");
    final char [] c = sVATIN.toCharArray ();
    if (c.length != 11)
      return false;
    for (int i = 0; i <= 10; ++i)
      if (!_isNum (c[i]))
        return false;

    return true;
  }
}
