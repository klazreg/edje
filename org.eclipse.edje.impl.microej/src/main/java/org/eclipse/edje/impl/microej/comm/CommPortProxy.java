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

import java.io.IOException;

import org.eclipse.edje.HardwareDescriptor;

/**
 * Instances of this class are delegating proxies for their
 * ej.ecom.io.CommConnection counterpart.
 */
public class CommPortProxy implements org.eclipse.edje.comm.CommPort {
	private final ej.ecom.io.CommPort ecomCommPort;
	private final HardwareDescriptor<org.eclipse.edje.comm.CommPort> desc;

	/**
	 * Creates a proxy for the specified ej.ecom.io.CommPort
	 * 
	 * @param ecomCommPort
	 *            the port to proxy for.
	 */
	public CommPortProxy(ej.ecom.io.CommPort ecomCommPort) {
		this.ecomCommPort = ecomCommPort;
		this.desc = new HardwareDescriptor<org.eclipse.edje.comm.CommPort>() {

			private final ej.ecom.HardwareDescriptor<ej.ecom.io.CommPort> ecomDescriptor = CommPortProxy.this.ecomCommPort
					.getDescriptor();

			@Override
			public String getName() {
				return ecomDescriptor.getName();
			}

			@Override
			public String getProperty(String propertyName) {
				return ecomDescriptor.getProperty(propertyName);
			}

			@Override
			public String[] getPropertyNames() {
				return ecomDescriptor.getPropertyNames();
			}

			@Override
			public String[] getPropertyValues() {
				return ecomDescriptor.getPropertyValues();
			}
		};
	}

	@Override
	public org.eclipse.edje.HardwareDescriptor<org.eclipse.edje.comm.CommPort> getDescriptor() {
		return desc;
	}

	@Override
	public String getName() {
		return ecomCommPort.getName();
	}

	@Override
	public org.eclipse.edje.io.Connection openConnection(String args) throws IOException {
		try {
			ej.ecom.io.Connection ecomConnection = ecomCommPort.openConnection(args);
			return new CommConnectionProxy((ej.ecom.io.CommConnection) ecomConnection);
		} catch (ej.ecom.io.ConnectionNotFoundException io) {
			throw new org.eclipse.edje.io.ConnectionNotFoundException(io.getMessage());
		}
	}

}
