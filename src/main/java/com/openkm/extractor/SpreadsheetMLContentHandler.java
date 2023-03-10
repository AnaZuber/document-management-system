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

package com.openkm.extractor;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SpreadsheetMLContentHandler extends MsOffice2007ContentHandler {

	public String getFilePattern() {
		return "xl/sharedStrings.xml";
	}

	public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) {
		if (rawName.equals("t")) {
			appendChar = true;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		if (appendChar) {
			content.append(ch, start, length);
		}
	}

	public void endElement(String namespaceURI, String localName, String qName) {
		if (qName.equals("si")) {
			content.append("\n");
		}
		appendChar = false;
	}
}
