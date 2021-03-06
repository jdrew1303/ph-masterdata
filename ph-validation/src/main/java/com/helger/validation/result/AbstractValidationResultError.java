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
package com.helger.validation.result;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.ToStringGenerator;

/**
 * Abstract implementation of the {@link IValidationResult} interface for
 * errors.
 *
 * @author Philip Helger
 */
@Immutable
public abstract class AbstractValidationResultError implements IValidationError
{
  public final boolean isValid ()
  {
    return false;
  }

  public final boolean isInvalid ()
  {
    return true;
  }

  @Nullable
  public String getErrorID ()
  {
    return null;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).getToString ();
  }
}
