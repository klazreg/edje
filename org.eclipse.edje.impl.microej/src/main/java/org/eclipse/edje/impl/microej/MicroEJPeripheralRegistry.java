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

package org.eclipse.edje.impl.microej;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.edje.DefaultPeripheralRegistry;
import org.eclipse.edje.PeripheralManager;
import org.eclipse.edje.comm.CommPort;
import org.eclipse.edje.impl.microej.comm.CommPortProxy;
import org.eclipse.edje.util.Pump;

import ej.ecom.DeviceManager;
import ej.ecom.RegistrationEvent;
import ej.ecom.RegistrationListener;

/**
 * This is a registry implementation dedicated specifically to the MicroEJ OS
 * port. It does the brdige between the ECOM CommPort devices to transform them
 * into Edje peripherals. It also creates GPIOPorts over the existing
 * ej.hal.gpio.GPIO API.
 * 
 */
public class MicroEJPeripheralRegistry extends DefaultPeripheralRegistry {

	/**
	 * Maps ECOM CommPorts to their Edje equivalent.
	 */
	Map<ej.ecom.io.CommPort, CommPortProxy> map = new HashMap<>();

	/**
	 * ECOM listener, used to (un)register Edje peripherals when ECOM devices
	 * are (un)registered.
	 */
	private final RegistrationListener<ej.ecom.io.CommPort> ecomCommPortListener = new RegistrationListener<ej.ecom.io.CommPort>() {

		@Override
		public void deviceUnregistered(RegistrationEvent<ej.ecom.io.CommPort> event) {
			CommPort commPort = map.get(event.getDevice());
			if (commPort != null) {
				PeripheralManager.unregister(map.get(commPort));
			}
		}

		@Override
		public void deviceRegistered(RegistrationEvent<ej.ecom.io.CommPort> event) {
			CommPortProxy commPort = new CommPortProxy(event.getDevice());
			PeripheralManager.register(CommPort.class, commPort);
		}
	};

	@Override
	public void start(Pump<org.eclipse.edje.RegistrationEvent<?>> pump) {
		// start the pump and register for ECOM events only if events are
		// enabled (i.e. the pump parameter is not null)
		if (pump != null) {
			Thread t = new Thread(pump);
			t.setPriority(pump.getPriority());
			t.start();
			DeviceManager.addRegistrationListener(ecomCommPortListener, ej.ecom.io.CommPort.class);
		}

		// enumerate ECOM devices and proxy them
		System.out.println("Enumerating ECOM CommPorts devices...");
		Iterator<ej.ecom.io.CommPort> ecomCommPorts = DeviceManager.list(ej.ecom.io.CommPort.class);
		if (ecomCommPorts.hasNext()) {
			while (ecomCommPorts.hasNext()) {
				CommPort commPort = new CommPortProxy(ecomCommPorts.next());
				System.out.println("Registering " + commPort.getName());
				register(CommPort.class, commPort, false, true);
			}
		}

		// enumerate GPIOs
		System.out.println("Enumerating GPIO ports...");
		GPIOPortImpl.init(this);
	}
}
