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
package com.helger.masterdata.person;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.masterdata.address.Address;
import com.helger.masterdata.address.IAddress;

/**
 * Person specific implementation
 *
 * @author Philip Helger
 */
public class PersonAddress extends Address
{
  private Person m_aOwner;

  public PersonAddress ()
  {}

  public PersonAddress (@Nonnull final Person aOwner)
  {
    setOwner (aOwner);
  }

  public PersonAddress (@Nonnull final Person aOwner, @Nonnull final IAddress aBase, @Nonnull final Locale aSortLocale)
  {
    super (aBase, aSortLocale);
    setOwner (aOwner);
  }

  @Nullable
  public Person getOwner ()
  {
    return m_aOwner;
  }

  @Nullable
  public String getOwnerID ()
  {
    return m_aOwner == null ? null : m_aOwner.getID ();
  }

  public void setOwner (@Nonnull final Person aOwner)
  {
    ValueEnforcer.notNull (aOwner, "Owner");
    m_aOwner = aOwner;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final PersonAddress rhs = (PersonAddress) o;
    return EqualsHelper.equals (getOwnerID (), rhs.getOwnerID ());
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (getOwnerID ()).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("ownerID", getOwnerID ()).getToString ();
  }
}
