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

import com.google.gwt.sample.gwtguestbook.client.GuestService;
import com.google.gwt.sample.gwtguestbook.client.GuestbookEntryTransferObject;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * The guest service implementation class responsible for managing guestbook
 * entries.
 */
public class GuestServiceImpl extends RemoteServiceServlet implements
    GuestService {

  // A clock used in persisting guest entry timestamps.
  private final Clock clock;

  /**
  * Creates an instance of the guest service implementation class, required
  * for the GWT.create() call used to instantiate the RPC service interface.
  */
  public GuestServiceImpl(){
    this(new RealTimeClock());
  }

  /**
  * Constructor taking a clock used for persisting guestbook entry timestamps.
  */ 
  public GuestServiceImpl(Clock clock) {
    super();
    this.clock = clock;
  }

  @Override
  public List<GuestbookEntryTransferObject> addGuestEntry(
      GuestbookEntryTransferObject entry) {
    PersistenceManager pm = PersistenceManagerHelper.getPersistenceManager();
    try {
      // Create a new guestbook entry and persist
      pm.currentTransaction().begin();
      GuestbookEntry entryToPersist = new GuestbookEntry(entry);
      entryToPersist.setTimestamp(clock.getTime());
      pm.makePersistent(entryToPersist);
      pm.currentTransaction().commit();
      return getTenLatestEntries();
    } finally {
      if (pm.currentTransaction().isActive()) {
        pm.currentTransaction().rollback();
      }
    }
  }

  @Override
  public List<GuestbookEntryTransferObject> getTenLatestEntries() {
    PersistenceManager pm = PersistenceManagerHelper.getPersistenceManager();
    try {
      // Set the query to get the ten latest guest entries
      Query query = pm.newQuery(GuestbookEntry.class);
      query.setOrdering("timestamp DESC");
      query.setRange("0, 10");
      List<GuestbookEntry> entries = (List<GuestbookEntry>) query.execute();

      // Create a new guestbook entry transfer object for each entry and add
      // them to the list
      List<GuestbookEntryTransferObject> entryTransferObjects =
          new ArrayList<GuestbookEntryTransferObject>(entries.size());
      for (GuestbookEntry entry : entries) {
        entryTransferObjects.add(new GuestbookEntryTransferObject(entry
            .getName(), entry.getMessage()));
      }
      return entryTransferObjects;
    } finally {
      if (pm.currentTransaction().isActive()) {
        pm.currentTransaction().rollback();
      }
    }
  }
}
