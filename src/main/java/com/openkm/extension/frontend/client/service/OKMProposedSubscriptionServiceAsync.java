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

package com.openkm.extension.frontend.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

import java.util.Map;

/**
 * @author jllort
 *
 */
public interface OKMProposedSubscriptionServiceAsync extends RemoteService {
	void send(String uuid, String type, String users, String roles, String comment, AsyncCallback<?> callback);

	void findProposedSubscriptionsUsersFrom(AsyncCallback<Map<String, Long>> callback);

	void markSeen(int msgId, AsyncCallback<?> callback);

	void markAccepted(int msgId, AsyncCallback<?> callback);

	void deleteReceived(int msgId, AsyncCallback<?> callback);

	void deleteSent(int msgId, AsyncCallback<?> callback);

	void deleteProposedSubscriptionByMeFromUser(String sender, AsyncCallback<?> callback);

	void findProposedSubscriptionByMeFromUser(String user, AsyncCallback<?> asyncCallback);
}
