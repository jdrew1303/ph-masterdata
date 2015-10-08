/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.masterdata.address;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.string.ToStringGenerator;

/**
 * Read-only implementation of the {@link IAddress} interface.
 *
 * @author Philip Helger
 */
@Immutable
public final class ReadOnlyAddress implements IAddress
{
  private final IAddressType m_aAddressType;
  private final String m_sCountry;
  private final String m_sState;
  private final String m_sPostalCode;
  private final String m_sCity;
  private final String m_sStreet;
  private final String m_sBuildingNumber;
  private final String m_sPostOfficeBox;
  private final String m_sCareOf;

  public ReadOnlyAddress (@Nonnull final IAddress aBase, @Nonnull final Locale aSortLocale)
  {
    this (aBase.getType (),
          aBase.getCountry (),
          aBase.getState (),
          aBase.getPostalCode (),
          aBase.getCity (),
          aBase.getStreet (),
          aBase.getBuildingNumber (),
          aBase.getPostOfficeBox (),
          aBase.getCareOf (),
          aSortLocale);
  }

  public ReadOnlyAddress (@Nullable final IAddressType aAddressType,
                          @Nullable final String sCountry,
                          @Nullable final String sState,
                          @Nullable final String sPostalCode,
                          @Nullable final String sCity,
                          @Nullable final String sStreet,
                          @Nullable final String sBuildingNumber,
                          @Nullable final String sPostOfficeBox,
                          @Nullable final String sCareOf,
                          @Nonnull final Locale aSortLocale)
  {
    m_aAddressType = aAddressType;
    m_sCountry = AddressHelper.getUnifiedCountry (sCountry, aSortLocale);
    m_sState = AddressHelper.getUnifiedState (sState, aSortLocale);
    m_sPostalCode = sPostalCode;
    m_sCity = AddressHelper.getUnifiedCity (sCity, aSortLocale);
    m_sStreet = AddressHelper.getUnifiedStreet (sStreet, aSortLocale);
    m_sBuildingNumber = sBuildingNumber;
    m_sPostOfficeBox = AddressHelper.getUnifiedPOBox (sPostOfficeBox, aSortLocale);
    m_sCareOf = AddressHelper.getUnifiedCareOf (sCareOf, aSortLocale);
  }

  @Nullable
  public IAddressType getType ()
  {
    return m_aAddressType;
  }

  @Nullable
  public String getCountry ()
  {
    return m_sCountry;
  }

  @Nullable
  public Locale getCountryLocale ()
  {
    return CountryCache.getInstance ().getCountry (m_sCountry);
  }

  @Nullable
  public String getCountryDisplayName (@Nonnull final Locale aDisplayLocale)
  {
    final Locale aCountry = getCountryLocale ();
    return aCountry == null ? null : aCountry.getDisplayCountry (aDisplayLocale);
  }

  @Nullable
  public String getState ()
  {
    return m_sState;
  }

  @Nullable
  public String getPostalCode ()
  {
    return m_sPostalCode;
  }

  @Nullable
  public String getCity ()
  {
    return m_sCity;
  }

  @Nullable
  public String getStreet ()
  {
    return m_sStreet;
  }

  @Nullable
  public String getBuildingNumber ()
  {
    return m_sBuildingNumber;
  }

  @Nullable
  public String getPostOfficeBox ()
  {
    return m_sPostOfficeBox;
  }

  @Nullable
  public String getCareOf ()
  {
    return m_sCareOf;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ReadOnlyAddress rhs = (ReadOnlyAddress) o;
    return EqualsHelper.equals (m_aAddressType, rhs.m_aAddressType) &&
           EqualsHelper.equals (m_sCountry, rhs.m_sCountry) &&
           EqualsHelper.equals (m_sState, rhs.m_sState) &&
           EqualsHelper.equals (m_sPostalCode, rhs.m_sPostalCode) &&
           EqualsHelper.equals (m_sCity, rhs.m_sCity) &&
           EqualsHelper.equals (m_sStreet, rhs.m_sStreet) &&
           EqualsHelper.equals (m_sBuildingNumber, rhs.m_sBuildingNumber) &&
           EqualsHelper.equals (m_sPostOfficeBox, rhs.m_sPostOfficeBox) &&
           EqualsHelper.equals (m_sCareOf, rhs.m_sCareOf);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aAddressType)
                                       .append (m_sCountry)
                                       .append (m_sState)
                                       .append (m_sPostalCode)
                                       .append (m_sCity)
                                       .append (m_sStreet)
                                       .append (m_sBuildingNumber)
                                       .append (m_sPostOfficeBox)
                                       .append (m_sCareOf)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).appendIfNotNull ("addressType", m_aAddressType)
                                       .appendIfNotNull ("country", m_sCountry)
                                       .appendIfNotNull ("state", m_sState)
                                       .appendIfNotNull ("postalCodeCode", m_sPostalCode)
                                       .appendIfNotNull ("city", m_sCity)
                                       .appendIfNotNull ("street", m_sStreet)
                                       .appendIfNotNull ("buildingNumber", m_sBuildingNumber)
                                       .appendIfNotNull ("pobox", m_sPostOfficeBox)
                                       .appendIfNotNull ("careOf", m_sCareOf)
                                       .toString ();
  }
}
