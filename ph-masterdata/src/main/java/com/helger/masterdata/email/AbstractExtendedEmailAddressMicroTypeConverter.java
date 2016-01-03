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
package com.helger.masterdata.email;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroElement;
import com.helger.commons.microdom.convert.IMicroTypeConverter;

public abstract class AbstractExtendedEmailAddressMicroTypeConverter implements IMicroTypeConverter
{
  protected static final String ATTR_TYPE = "type";
  protected static final String ATTR_ADDRESS = "address";
  protected static final String ATTR_PERSONAL = "personal";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IExtendedEmailAddress aEmail = (IExtendedEmailAddress) aObject;
    final IMicroElement eEmail = new MicroElement (sNamespaceURI, sTagName);
    if (aEmail.getType () != null)
      eEmail.setAttribute (ATTR_TYPE, aEmail.getType ().getID ());
    eEmail.setAttribute (ATTR_ADDRESS, aEmail.getAddress ());
    eEmail.setAttribute (ATTR_PERSONAL, aEmail.getPersonal ());
    return eEmail;
  }
}