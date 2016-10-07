/*
 * Java
 *
 * Copyright 2016 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.eclipse.edje.impl.microej;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Instances of this class are delegating proxies for their
 * ej.ecom.io.BitsOutput counterpart.
 */
class BitsOutputProxy extends DataOutputStream implements org.eclipse.edje.comm.BitsOutput {

	protected final ej.ecom.io.BitsOutput output;

	BitsOutputProxy(OutputStream out) {
		super(out);
		this.output = (ej.ecom.io.BitsOutput) out;
	}

	@Override
	public int getLength() {
		return output.getLength();
	}

	@Override
	public void writeBits(int val) throws IOException {
		output.writeBits(val);
	}

	@Override
	public void writeBits(int[] data) throws IOException {
		output.writeBits(data);
	}

	@Override
	public void writeBits(int[] data, int off, int len) throws IOException {
		output.writeBits(data, off, len);
	}

	@Override
	public void writeBits(short[] data, boolean signExtends) throws IOException {
		output.writeBits(data, signExtends);
	}

	@Override
	public void writeBits(short[] data, int off, int len, boolean signExtends) throws IOException {
		output.writeBits(data, off, len, signExtends);
	}

	@Override
	public void writeBits(byte[] data, boolean signExtends) throws IOException {
		output.writeBits(data, signExtends);
	}

	@Override
	public void writeBits(byte[] data, int off, int len, boolean signExtends) throws IOException {
		output.writeBits(data, off, len, signExtends);
	}
}
