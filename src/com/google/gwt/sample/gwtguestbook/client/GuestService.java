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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Client-side interface for {@link GuestServiceImpl}, used in GWT RPC. 
 * This service interface defines a prototype for managing guestbook entries.
 */
@RemoteServiceRelativePath("guestservice")
public interface GuestService extends RemoteService {

  /**
   * Adds a guest entry and persists it in the data store.
   * 
   * @param entry the entry to add
   * @return the ten latest guest entries from the data store
   */
  public List<GuestbookEntryTransferObject> addGuestEntry(
      GuestbookEntryTransferObject entry);

  /**
   * Gets the ten latest guest entries from the data store.
   * 
   * @return the ten latest guest entries from the data store
   */
  public List<GuestbookEntryTransferObject> getTenLatestEntries();
}
