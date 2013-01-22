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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Guest signature widget class, containing the components needed to sign the
 * guestbook.
 */
public class GuestSignaturePanel extends Composite implements ClickHandler,
    FiresEntryUpdates {
  private final HorizontalPanel guestSignaturePanel;
  private final TextBox guestNameBox;
  private final Label guestNameLabel;
  private final TextBox guestMessageBox;
  private final Label guestMessageLabel;
  private final Button signButton;
  private boolean guestSignatureError = false;

  /* Register for entry update handlers listening for guest signatures */
  private List<EntryUpdateHandler> entryUpdateHandlers;

  /**
   * Creates a guest signature panel, with components fully instantiated and
   * attached. 
   * 
   */
  public GuestSignaturePanel() {
    entryUpdateHandlers = new ArrayList<EntryUpdateHandler>();
    guestSignaturePanel = new HorizontalPanel();

    // Create guest signature panel widgets
    guestNameBox = new TextBox();
    guestMessageBox = new TextBox();
    guestNameLabel = new Label("Your Name:");
    guestMessageLabel = new Label("Message:");
    signButton = new Button("Sign!");

    // Set up the widget guest signature panel widget styles (additional to
    // standard styles)
    guestNameLabel.addStyleName("gb-Label");
    guestMessageLabel.addStyleName("gb-Label");
    guestNameBox.addStyleName("gb-NameBox");
    guestMessageBox.addStyleName("gb-MessageBox");

    // Attach components together
    guestSignaturePanel.add(guestNameLabel);
    guestSignaturePanel.add(guestNameBox);
    guestSignaturePanel.add(guestMessageLabel);
    guestSignaturePanel.add(guestMessageBox);
    guestSignaturePanel.add(signButton);

    signButton.addClickHandler(this);

   /* The initWidget(Widget) method inherited from the Composite class must be
    * called exactly once in the constructor to declare the widget that this 
    * composite class is wrapping. */
    initWidget(guestSignaturePanel);
  }

  /**
   * Listens for the guest entry update event, and persists the guest entry
   */
  @Override
  public void onClick(ClickEvent event) {
    if ("".equals(guestNameBox.getText())
        || "".equals(guestMessageBox.getText())) {
      guestSignatureError = true;
      fireUpdateError("Please make sure you've filled in all fields");
    } else {
      // Make the entry
      GuestbookEntryTransferObject entry =
          new GuestbookEntryTransferObject(
              guestNameBox.getText(), guestMessageBox.getText());
      GuestServiceAsync guestService =
          (GuestServiceAsync) GWT.create(GuestService.class);
      guestService.addGuestEntry(entry,
          new AsyncCallback<List<GuestbookEntryTransferObject>>() {
            @Override
            public void onFailure(Throwable caught) {
              fireUpdateError("Failed to add new entry to guest book");
            }

            @Override
            public void onSuccess(List<GuestbookEntryTransferObject> result) {
              // Fire an update
              fireUpdate(result);
            }
          });
    }
  }

  /**
   * Fires an update error and passes the error message to all registered update
   * handlers
   * 
   * @param errorMessage the error message to pass on
   */
  protected void fireUpdateError(String errorMessage) {
    for (EntryUpdateHandler handler : entryUpdateHandlers) {
      handler.updateError(errorMessage);
    }
  }

  /**
   * Fires an update and passes the entries to all registered update handlers
   * 
   * @param entries the latest guest entries to be displayed
   */
  protected void fireUpdate(List<GuestbookEntryTransferObject> entries) {
    for (EntryUpdateHandler handler : entryUpdateHandlers) {
      // Signal that any previous sign in errors are now cleared to all
      // registered handlers
      if (guestSignatureError) {
        handler.clearError();
        guestSignatureError = false;
      }

      handler.updateEntries(entries);
    }
  }

  @Override
  public void addEntryUpdateHandler(EntryUpdateHandler handler) {
    entryUpdateHandlers.add(handler);
  }

  @Override
  public void removeEntryUpdateHandler(EntryUpdateHandler handler) {
    entryUpdateHandlers.remove(handler);
  }
}
