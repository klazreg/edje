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

package org.eclipse.edje.impl.microej.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.edje.comm.CommConnection;
import org.eclipse.edje.impl.microej.io.BitsInputProxy;
import org.eclipse.edje.impl.microej.io.BitsOutputProxy;
import org.eclipse.edje.impl.microej.io.ConnectionProxy;

/**
 * Instances of this class are delegating proxies for their
 * ej.ecom.io.CommConnection counterpart.
 */
public class CommConnectionProxy extends ConnectionProxy implements CommConnection {

	/**
	 * Creates a proxy for the specified ej.ecom.io.CommConnection
	 * 
	 * @param ecomConnection
	 *            the connection to proxy for.
	 */
	public CommConnectionProxy(ej.ecom.io.CommConnection ecomConnection) {
		super(ecomConnection);
	}

	@Override
	public InputStream openInputStream() throws IOException {
		return new BitsInputProxy(((ej.ecom.io.CommConnection) ecomConnection).openInputStream());
	}

	@Override
	public DataInputStream openDataInputStream() throws IOException {
		return new BitsInputProxy(((ej.ecom.io.CommConnection) ecomConnection).openDataInputStream());
	}

	@Override
	public OutputStream openOutputStream() throws IOException {
		return new BitsOutputProxy(((ej.ecom.io.CommConnection) ecomConnection).openOutputStream());
	}

	@Override
	public DataOutputStream openDataOutputStream() throws IOException {
		return new BitsOutputProxy(((ej.ecom.io.CommConnection) ecomConnection).openDataOutputStream());
	}

	@Override
	public int getBaudrate() {
		return ((ej.ecom.io.CommConnection) ecomConnection).getBaudrate();
	}

	@Override
	public int setBaudrate(int baudrate) {
		return ((ej.ecom.io.CommConnection) ecomConnection).setBaudrate(baudrate);
	}

}
