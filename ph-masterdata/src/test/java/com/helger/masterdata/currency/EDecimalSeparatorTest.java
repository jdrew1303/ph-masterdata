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
package com.helger.masterdata.currency;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for class {@link EDecimalSeparator}.
 *
 * @author Philip Helger
 */
public final class EDecimalSeparatorTest
{
  @Test
  public void testAll ()
  {
    for (final EDecimalSeparator e : EDecimalSeparator.values ())
    {
      assertTrue (e.getChar () != 0);
      assertSame (e, EDecimalSeparator.valueOf (e.name ()));
      assertSame (e, EDecimalSeparator.getFromCharOrNull (e.getChar ()));
      assertSame (e, EDecimalSeparator.getFromCharOrDefault (e.getChar (), EDecimalSeparator.COMMA));
    }
    assertNull (EDecimalSeparator.getFromCharOrNull ('a'));
  }
}
