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

package com.openkm.frontend.client.bean.form;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * GWTFormElement
 *
 * @author jllort
 *
 */
public class GWTFormElement implements IsSerializable {
	protected String label = "";
	protected String name = "";
	protected String width = "100px";
	protected String height = "25px";

	public String getWidth() {
		// Ensure is not only a number
		if (!width.endsWith("%") && !width.endsWith("px")) {
			return (width + "px");
		}
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		// Ensure is not only a number
		if (!height.endsWith("%") && !height.endsWith("px")) {
			return (height + "px");
		}
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
