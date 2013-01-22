/* 
 * Copyright 2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.gwt.sample.gwtguestbook.server;

/**
 * A clock interface that will have real and virtual clock implementations for
 * use by actual services in production and unit testing, respectively.
 */
public interface Clock {

  /**
   * Gets the current time represented by this clock.
   *
   * @return the current clock time
   */ 
  public long getTime();
}
