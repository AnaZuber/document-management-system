/**
 * OpenKM, Open Document Management System (http://www.openkm.com)
 * Copyright (c) Paco Avila & Josep Llort
 * <p>
 * No bytes were intentionally harmed during the development of this application.
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.openkm.extension.frontend.client.widget.messaging.propose;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.*;
import com.openkm.frontend.client.extension.comunicator.GeneralComunicator;

/**
 * NotifyPanel
 *
 * @author jllort
 */
public class NotifyPanel extends Composite {
	private static final int TAB_HEIGHT = 20;
	private static final int TAB_USERS = 0;
	private static final int TAB_GROUPS = 1;

	public TabLayoutPanel tabPanel;
	private VerticalPanel vPanel;
	private NotifyUser notifyUser;
	private NotifyRole notifyRole;
	private boolean filterView = false;
	private CheckBox checkBoxFilter;
	private TextBox filter;
	private HorizontalPanel filterPanel;
	private HTML filterText;
	private String usersFilter = "";
	private String groupsFilter = "";

	/**
	 * NotifyPanel
	 */
	public NotifyPanel() {
		vPanel = new VerticalPanel();
		notifyUser = new NotifyUser();
		notifyRole = new NotifyRole();
		tabPanel = new TabLayoutPanel(TAB_HEIGHT, Unit.PX);

		tabPanel.add(notifyUser, GeneralComunicator.i18nExtension("messaging.label.users"));
		tabPanel.add(notifyRole, GeneralComunicator.i18nExtension("messaging.label.roles"));
		tabPanel.selectTab(TAB_USERS);
		tabPanel.setWidth("374px");
		tabPanel.setHeight("140px");

		tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				switch (event.getSelectedItem().intValue()) {
					case TAB_USERS:
						groupsFilter = filter.getText();
						filter.setText(usersFilter);
						filterText.setHTML(GeneralComunicator.i18nExtension("security.filter.by.users"));
						break;
					case TAB_GROUPS:
						usersFilter = filter.getText();
						filter.setText(groupsFilter);
						filterText.setHTML(GeneralComunicator.i18nExtension("security.filter.by.roles"));
						break;
				}
			}
		});

		filterPanel = new HorizontalPanel();
		filterPanel.setVisible(false);
		checkBoxFilter = new CheckBox();
		checkBoxFilter.setValue(false);
		checkBoxFilter.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				notifyUser.resetAvailableUsersTable();
				notifyRole.resetAvailableRolesTable();
				Widget sender = (Widget) event.getSource();
				if (((CheckBox) sender).getValue()) {
					filter.setText("");
					filter.setEnabled(true);
				} else {
					filter.setText("");
					filter.setEnabled(false);
					usersFilter = "";
					groupsFilter = "";
					getAll();
				}
			}
		});
		filter = new TextBox();
		filterText = new HTML(GeneralComunicator.i18nExtension("security.filter.by.users"));
		filterPanel.add(checkBoxFilter);
		filterPanel.add(new HTML("&nbsp;"));
		filterPanel.add(filterText);
		filterPanel.add(new HTML("&nbsp;"));
		filterPanel.add(filter);

		filterPanel.setCellVerticalAlignment(checkBoxFilter, HasAlignment.ALIGN_MIDDLE);
		filterPanel.setCellVerticalAlignment(filterText, HasAlignment.ALIGN_MIDDLE);
		filterPanel.setCellVerticalAlignment(filter, HasAlignment.ALIGN_MIDDLE);

		filter.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (filter.getText().length() >= 3) {
					int selected = tabPanel.getSelectedIndex();
					switch (selected) {
						case TAB_USERS:
							notifyUser.resetAvailableUsersTable();
							notifyUser.getFilteredAllUsers(filter.getText());
							break;

						case TAB_GROUPS:
							notifyRole.resetAvailableRolesTable();
							notifyRole.getFilteredAllRoles(filter.getText());
							break;
					}
				} else {
					notifyUser.resetAvailableUsersTable();
					notifyRole.resetAvailableRolesTable();
				}
			}
		});

		vPanel.add(filterPanel);
		vPanel.add(tabPanel);

		vPanel.setCellHorizontalAlignment(filterPanel, VerticalPanel.ALIGN_RIGHT);

		vPanel.addStyleName("okm-DisableSelect");
		tabPanel.addStyleName("okm-Border-Bottom");
		filter.setStyleName("okm-Input");

		initWidget(vPanel);
	}

	/**
	 * reset
	 */
	public void reset() {
		notifyUser.reset();
		notifyRole.reset();
	}

	/**
	 * langRefresh
	 */
	public void langRefresh() {
		int selected = tabPanel.getSelectedIndex();

		while (tabPanel.getWidgetCount() > 0) {
			tabPanel.remove(0);
		}

		tabPanel.add(notifyUser, GeneralComunicator.i18nExtension("messaging.label.users"));
		tabPanel.add(notifyRole, GeneralComunicator.i18nExtension("messaging.label.roles"));
		tabPanel.selectTab(selected);

		filterText.setHTML(GeneralComunicator.i18nExtension("security.filter.by.users"));

		switch (selected) {
			case TAB_USERS:
				filterText.setHTML(GeneralComunicator.i18nExtension("security.filter.by.users"));
				break;
			case TAB_GROUPS:
				filterText.setHTML(GeneralComunicator.i18nExtension("security.filter.by.roles"));
				break;
		}

		notifyUser.langRefresh();
		notifyRole.langRefresh();
	}

	/**
	 * Gets all users and roles
	 */
	public void getAll() {
		if (!filterView || !checkBoxFilter.getValue()) {
			notifyUser.getAllUsers();
			notifyRole.getAllRoles();
		}
	}

	/**
	 * enableAdvancedFilter
	 */
	public void enableAdvancedFilter() {
		filterView = true;
		filterPanel.setVisible(true);
		checkBoxFilter.setValue(true);
	}

	/**
	 * getRolesToNotify
	 *
	 * @return
	 */
	public String getRolesToNotify() {
		return notifyRole.getRolesToNotify();
	}

	/**
	 * getUsersToNotify
	 *
	 * @return
	 */
	public String getUsersToNotify() {
		return notifyUser.getUsersToNotify();
	}
}
