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

import com.google.gwt.sample.gwtguestbook.client.GuestbookEntryTransferObject;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * The Guestbook entry JDO object, used to persist guestbook entries.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class GuestbookEntry {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;

  @Persistent
  private String name;

  @Persistent
  private String message;

  @Persistent
  private long timestamp;

  /**
   * Creates an empty guestbook entry.
   */
  public GuestbookEntry() {
  }

  /**
   * Creates a guestbook entry from an existing guest book entry transfer
   * object, copying its name and message.
   * 
   * @param entry the guestbook transfer object to copy from
   */
  public GuestbookEntry(GuestbookEntryTransferObject entry) {
    name = entry.getName();
    message = entry.getMessage();
  }

  /**
   * Gets the guest entry identifier.
   * 
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the guest entry identifier.
   * 
   * @param id the new id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the guest entry name.
   * 
   * @return the guest entry name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the guest entry name.
   * 
   * @param name the new name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the guest entry timestamp.
   * 
   * @return the guest entry timestamp
   */
  public long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the guest entry timestamp.
   * 
   * @param entryTimestamp the new entry timestamp to set
   */
  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Gets the guest entry message.
   * 
   * @return the guest entry message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the guest entry message.
   * 
   * @param message the new message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
