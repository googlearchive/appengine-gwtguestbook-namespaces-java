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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Guestbook implements EntryPoint, EntryUpdateHandler {
  private final VerticalPanel mainPanel = new VerticalPanel();
  private final Label errorLabel = new Label();
  private final GuestSignaturePanel guestSignaturePanel = 
      new GuestSignaturePanel();
  private final Grid guestEntries = new Grid(11, 2);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    // Set up guest entries tables, first row for headers.
    guestEntries.setHTML(0, 0, "<b>Guest Name</b>");
    guestEntries.setHTML(0, 1, "<b>Message</b>");

    // Style the entries table.
    guestEntries.addStyleName("gb-GuestEntries");
    guestEntries.getRowFormatter().addStyleName(0, "gb-GuestEntriesHeader");
    guestEntries.getCellFormatter().addStyleName(0, 0, "gb-GuestHeader");
    guestEntries.getCellFormatter().addStyleName(0, 1, "gb-MessageHeader");

    // Attach components together.
    mainPanel.add(errorLabel);
    mainPanel.add(guestSignaturePanel);
    mainPanel.add(guestEntries);

    // Align the signature panel and entries table.
    mainPanel.setWidth("100%");
    mainPanel.setCellHorizontalAlignment(guestSignaturePanel,
        HasAlignment.ALIGN_CENTER);
    mainPanel.setCellHorizontalAlignment(guestEntries,
        HasAlignment.ALIGN_CENTER);
    mainPanel.setCellWidth(guestEntries, "550px");

    // Load and display existing guest entries.
    loadGuestEntries();

    // Attach handlers onto UI components.
    guestSignaturePanel.addEntryUpdateHandler(this);

    // Attach main panel to host HTML page.
    RootPanel.get().add(mainPanel);
  }

  /**
   * Loads the ten latest guest entries and sets them in the guest entries
   * table.
   */
  private void loadGuestEntries() {
    // Get the latest guest entries.
    GuestServiceAsync guestService =
        (GuestServiceAsync) GWT.create(GuestService.class);
    guestService
        .getTenLatestEntries(
            new AsyncCallback<List<GuestbookEntryTransferObject>>() {
          @Override
          public void onFailure(Throwable caught) {
            updateError("Failed to load guestbook entries");
            errorLabel.setVisible(true);
          }

          @Override
          public void onSuccess(List<GuestbookEntryTransferObject> result) {
            updateEntries(result);
          }
        });
  }

  /**
   * Updates the grid display with the latest guestbook entries.
   * 
   * @param entries the new entries to update in the display
   */
  public void updateEntries(List<GuestbookEntryTransferObject> entries) {
    // Hide the error label if it's still present.
    if (errorLabel.isVisible()) {
      errorLabel.setVisible(false);
    }

    // Update entries in guest entries table.
    if (entries != null) {
      // Start at +1 offset to skip table header.
      for (int i = 1; i <= entries.size(); i++) {
        guestEntries.setText(i, 0, entries.get(i - 1).getName());
        guestEntries.setText(i, 1, entries.get(i - 1).getMessage());
        guestEntries.getCellFormatter().addStyleName(i, 0, "gb-GuestEntry");
        guestEntries.getCellFormatter().addStyleName(i, 1, "gb-GuestEntry");
        if (i % 2 != 0) {
          guestEntries.getRowFormatter().addStyleName(i, "gb-GuestEntryOdd");
        }
      }
    }
  }

  @Override
  public void updateError(String errorMessage) {
    // Update error label with error message.
    errorLabel.setText(errorMessage);
    errorLabel.setVisible(true);
  }

  @Override
  public void clearError() {
    errorLabel.setVisible(false);
  }

  /**
   * Gets the error label.
   * 
   * @return the error label
   */
  public Label getErrorLabel() {
    return errorLabel;
  }

  /**
   * Gets the guest entries display grid.
   * 
   * @return the guest entries display grid
   */
  public Grid getGuestEntries() {
    return guestEntries;
  }
}
