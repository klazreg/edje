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

package org.eclipse.edje.connection.comm;

import java.io.IOException;

import org.eclipse.edje.impl.microej.CommConnectionFactory;
import org.eclipse.edje.io.Connection;

/**
 * This class s actually delegating to
 * org.eclipse.edje.impl.microej.CommConnectionFactory
 *
 */
public class ConnectionFactory implements org.eclipse.edje.io.ConnectionFactory {

	private final CommConnectionFactory factory = new CommConnectionFactory();

	@Override
	public Connection open(String name) throws IOException {
		return factory.open(name);
	}

}
