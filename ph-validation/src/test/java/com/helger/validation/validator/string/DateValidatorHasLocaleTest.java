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
package com.helger.validation.validator.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

import com.helger.commons.mock.CommonsTestHelper;
import com.helger.validation.mock.MockHasLocale;

/**
 * Test class for class {@link DateValidatorHasLocale}.
 *
 * @author Philip Helger
 */
public final class DateValidatorHasLocaleTest
{
  @Test
  public void testAll ()
  {
    final DateValidatorHasLocale v = new DateValidatorHasLocale (new MockHasLocale (Locale.GERMAN));
    assertFalse (v.validate (null).isValid ());
    assertFalse (v.validate ("").isValid ());
    assertFalse (v.validate ("TRUE").isValid ());
    assertTrue (v.validate ("05.01.2010").isValid ());
    assertFalse (v.validate ("       05.01.2010        ").isValid ());
    assertFalse (v.validate ("30.02.2010").isValid ());
  }

  @Test
  public void testStandard ()
  {
    CommonsTestHelper.testDefaultImplementationWithEqualContentObject (new DateValidatorHasLocale (new MockHasLocale (Locale.GERMAN)),
                                                                       new DateValidatorHasLocale (new MockHasLocale (Locale.CANADA)));
  }
}
