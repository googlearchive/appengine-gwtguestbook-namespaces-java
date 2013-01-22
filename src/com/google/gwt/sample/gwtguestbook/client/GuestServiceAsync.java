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

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * The asynchronous counterpart to the {@link GuestService} service interface, 
 * used in GWT RPC. This interface defines a prototype for managing guestbook 
 * entries.
 */
public interface GuestServiceAsync {

  /**
   * Adds a guest entry and persists it in the data store.
   * 
   * @param entry the entry to add
   * @param async the asynchronous callback to be invoked by the GWT RPC 
   *              subsystem
   */
  public void addGuestEntry(GuestbookEntryTransferObject entry,
      AsyncCallback<List<GuestbookEntryTransferObject>> async);

  /**
   * Gets the ten latest guest entries from the data store.
   * 
   * @param async the asynchronous callback to be invoked by the GWT RPC 
   *              subsystem
   */
  public void getTenLatestEntries(
      AsyncCallback<List<GuestbookEntryTransferObject>> async);
}
