/*
 * Java
 *
 * Copyright 2016 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.eclipse.edje.impl.microej.io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Instances of this class are delegating proxies for their ej.ecom.io.BitsInput
 * counterpart.
 */
public class BitsInputProxy extends DataInputStream implements org.eclipse.edje.io.BitsInput {

	final ej.ecom.io.BitsInput input;

	/**
	 * Creates a proxy for the specified InputStream
	 * 
	 * @param in
	 *            the InputStream to proxy for, must implement
	 *            ej.ecom.io.BitsInput.
	 * 
	 * @throws ClassCastException
	 *             if the specified InputStream doesn't implement
	 *             ej.ecom.io.BitsInput.
	 */
	public BitsInputProxy(InputStream in) {
		super(in);
		this.input = (ej.ecom.io.BitsInput) in;
	}

	@Override
	public int getLength() {
		return input.getLength();
	}

	@Override
	public int readBits(boolean signExtends) throws IOException, EOFException {
		return input.readBits(signExtends);
	}

	@Override
	public int readBits(int[] data, boolean signExtends) throws IOException {
		return input.readBits(data, signExtends);
	}

	@Override
	public int readBits(int[] data, int off, int len, boolean signExtends) throws IOException {
		return input.readBits(data, off, len, signExtends);
	}

	@Override
	public int readBits(short[] data, boolean signExtends) throws IOException {
		return input.readBits(data, signExtends);
	}

	@Override
	public int readBits(short[] data, int off, int len, boolean signExtends) throws IOException {
		return input.readBits(data, off, len, signExtends);
	}

	@Override
	public int readBits(byte[] data, boolean signExtends) throws IOException {
		return input.readBits(data, signExtends);
	}

	@Override
	public int readBits(byte[] data, int off, int len, boolean signExtends) throws IOException {
		return input.readBits(data, off, len, signExtends);
	}

}
