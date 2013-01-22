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

import com.google.gwt.event.shared.EventHandler;

import java.util.List;

/**
 * Implemented by any widgets / components interesting in handling guest entry 
 * update events.
 */
public interface EntryUpdateHandler extends EventHandler {

  /**
   * Updates the guest entries table with the list of entries.
   * 
   * @param entries the list of entries to add to the guest entries table.
   */
  public void updateEntries(List<GuestbookEntryTransferObject> entries);

  /**
   * Updates the error message.
   * 
   * @param errorMessage the new error message to display
   */
  public void updateError(String errorMessage);

  /**
   * Clears the error state when called locally or by other participating 
   * widgets.
   */
  public void clearError();
}
