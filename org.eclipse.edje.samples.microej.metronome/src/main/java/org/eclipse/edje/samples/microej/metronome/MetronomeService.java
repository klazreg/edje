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

package org.eclipse.edje.samples.microej.metronome;

import org.eclipse.edje.PeripheralManager;
import org.eclipse.edje.gpio.GPIOPort;

import ej.wadapps.app.BackgroundService;

/**
 *
 */
public class MetronomeService implements BackgroundService {

	private Metronome metronome;

	@Override
	public void onStart() {
		GPIOPort portD = PeripheralManager.find(GPIOPort.class, "GPIO_ARDUINO_DIGITAL");
		GPIOPort portA = PeripheralManager.find(GPIOPort.class, "GPIO_ARDUINO_ANALOG");
		metronome = new Metronome(portD, 12, portA, 0);
		new Thread(metronome).start();
	}

	@Override
	public void onStop() {
		metronome.stop();
		metronome = null;
	}
}
