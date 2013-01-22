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
package com.google.gwt.sample.gwtguestbook.client;

/**
 * Implemented by any widgets that fire guest entry update events.
 */
public interface FiresEntryUpdates {

  /**
   * Adds an entry update handler to the register.
   * 
   * @param handler the update handler to add
   */
  public void addEntryUpdateHandler(EntryUpdateHandler handler);

  /**
   * Removes an entry update handler from the register.
   * 
   * @param the update handler to remove
   */
  public void removeEntryUpdateHandler(EntryUpdateHandler handler);
}
