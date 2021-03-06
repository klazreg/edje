/*******************************************************************************
 * Copyright (c) 2016 IS2T S.A. Operating under the brand name MicroEJ(r).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *    {Guillaume Balan, MicroEJ} - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.edje.io;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.edje.util.Util;

/**
 * This abstract class is the {@link InputStream} implementation for a
 * {@link AbstractConnection}. It manages the {@link Connection}'s close policy.
 */
public abstract class AbstractConnectionInputStream extends InputStream {

	/**
	 * The {@link Connection} which has opened the stream.
	 */
	protected final AbstractConnection connection;

	/**
	 * Close state. Not closed on stream creation.
	 */
	private boolean isClosed = false;

	/**
	 * Creates a {@link InputStream} implementation for a
	 * {@link AbstractConnection}.
	 *
	 * @param connection
	 *            the {@link AbstractConnection} which holds the stream
	 */
	public AbstractConnectionInputStream(AbstractConnection connection) {
		this.connection = connection;
	}

	/**
	 * Close the stream, the hardware stream and the {@link AbstractConnection}
	 * if it is ready to be closed. Nothing occurs when the stream is already
	 * closed.
	 */
	@Override
	public final void close() throws IOException {
		synchronized (connection) {
			if (isClosed) {
				// stream already closed
				return;
			}

			// stream is closed for sure now
			isClosed = true;

			// close the hardware stream
			hardwareClose();

			// try to close the connection
			connection.removeInputStream();
		}
	}

	/**
	 * Check if the stream is ready to be used. Subclass must always call this
	 * method before performing a new single read action.
	 *
	 * @throws IOException
	 *             if the stream is closed.
	 */
	protected void checkRead() throws IOException {
		// just check if stream is closed
		if (isClosed) {
			throw new IOException("Stream already closed.");
		}
	}

	/**
	 * Check if the stream is ready to be used and if the array arguments are
	 * valid. Subclass must always call this method before performing a new
	 * multiple read action.
	 *
	 * @param arrayLength
	 *            the destination array's length where store the new data
	 * @param offset
	 *            the destination array's offset where store the new data
	 * @param length
	 *            the number of data to store
	 * @throws IOException
	 *             if the stream is closed
	 * @throws IndexOutOfBoundsException
	 *             if the given arguments are incoherent
	 */
	protected final void checkRead(int arrayLength, int offset, int length) throws IOException {
		checkRead();
		Util.checkBounds(arrayLength, offset, length);
	}

	/**
	 * Check if the stream is ready to be used and if the array arguments are
	 * valid. Subclass must always call this method before performing a new
	 * multiple read action.
	 *
	 * @param array
	 *            the destination array where store the new data
	 * @param offset
	 *            the destination array's offset where store the new data
	 * @param length
	 *            the number of data to store
	 * @throws IOException
	 *             if the stream is closed
	 * @throws IndexOutOfBoundsException
	 *             if the given arguments are incoherent
	 */
	protected final void checkRead(byte[] array, int offset, int length) throws IOException {
		checkRead(array.length, offset, length);
	}

	/**
	 * Close the hardware stream.
	 */
	protected abstract void hardwareClose();
}
