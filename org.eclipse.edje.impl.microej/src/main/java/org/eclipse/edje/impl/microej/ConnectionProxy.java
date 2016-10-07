/*
 * Java
 *
 * Copyright 2016 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.eclipse.edje.impl.microej;

import java.io.IOException;

/**
 *
 */
class ConnectionProxy implements org.eclipse.edje.io.Connection {

	final ej.ecom.io.Connection ecomConnection;

	/**
	 * @param ecomConnection
	 */
	ConnectionProxy(ej.ecom.io.Connection ecomConnection) {
		this.ecomConnection = ecomConnection;
	}

	@Override
	public void close() throws IOException {
		this.ecomConnection.close();
	}

}
