/*
 * Java
 *
 * Copyright 2016 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.eclipse.edje.impl.microej;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.edje.comm.CommConnection;

/**
 * Instances of this class are delegating proxies for their
 * ej.ecom.io.BitsOutput counterpart.
 */
class CommConnectionProxy extends ConnectionProxy implements CommConnection {

	CommConnectionProxy(ej.ecom.io.CommConnection ecomConnection) {
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