/*
 * Java
 *
 * Copyright 2016 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.eclipse.edje.impl.microej;

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
