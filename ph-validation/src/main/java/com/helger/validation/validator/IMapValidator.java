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
package com.helger.validation.validator;

import java.util.Map;

/**
 * Validator for a list type field. May check for the existence of a certain
 * amount of entries.
 *
 * @author Boris
 * @param <KEYTYPE>
 *        The map key type
 * @param <VALUETYPE>
 *        The map value type
 */
@FunctionalInterface
public interface IMapValidator <KEYTYPE, VALUETYPE>
                               extends IBaseValidator <Map <? extends KEYTYPE, ? extends VALUETYPE>>
{
  /* empty */
}
