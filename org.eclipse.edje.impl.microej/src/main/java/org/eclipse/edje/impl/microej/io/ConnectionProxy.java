/*******************************************************************************
 * Copyright (c) 2016 IS2T S.A. Operating under the brand name MicroEJ(r).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *    {Laurent Lagosanto, MicroEJ} - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.edje.impl.microej.io;

import java.io.IOException;

/**
 * Instances of this class are delegating proxies for their
 * ej.ecom.io.Connection counterpart.
 */
public class ConnectionProxy implements org.eclipse.edje.io.Connection {

	/**
	 * The connection to proxy for.
	 */
	protected final ej.ecom.io.Connection ecomConnection;

	/**
	 * Creates a proxy for the specified OutputStream
	 * 
	 * @param ecomConnection
	 *            the OutputStream to proxy for, must implement
	 *            ej.ecom.io.BitsOutput.
	 */
	public ConnectionProxy(ej.ecom.io.Connection ecomConnection) {
		this.ecomConnection = ecomConnection;
	}

	@Override
	public void close() throws IOException {
		this.ecomConnection.close();
	}

}
