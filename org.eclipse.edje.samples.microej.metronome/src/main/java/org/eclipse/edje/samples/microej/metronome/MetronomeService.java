/*
 * Java
 *
 * Copyright 2016 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
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
