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

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

/**
 * The persistence manager helper class responsible for maintaining and 
 * providing access to a Singleton instance of the PersistenceManager.
 */
public class PersistenceManagerHelper {

  /* the singleton instance */
  private static PersistenceManager instance = null;

  /**
   * Hide the constructor.
   */
  private PersistenceManagerHelper() {
  }

  /**
   * Gets the singleton instance of the PersistenceManager
   * 
   * @return the singleton instance of the PersistenceManager
   */
  public static PersistenceManager getPersistenceManager() {
    if (instance == null) {
      instance =
          JDOHelper.getPersistenceManagerFactory("transactions-optional")
              .getPersistenceManager();
    }
    return instance;
  }
}
