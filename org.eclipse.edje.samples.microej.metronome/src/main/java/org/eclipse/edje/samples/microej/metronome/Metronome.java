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

import org.eclipse.edje.gpio.GPIOPort;
import org.eclipse.edje.gpio.GPIOPort.Mode;

/**
 * The Metronome will tick a LED at a frequency determined by reading a
 * potentiometer.
 */
public class Metronome implements Runnable {

	private static final int SLEEP_MAX = 500;

	private final GPIOPort ledPort;
	private final GPIOPort potPort;
	private final int ledPinTick;
	private volatile boolean stop;

	/**
	 * Creates a Metronome configured a a specific hardware:
	 * <li>A LED on a pin of a digital port
	 * <li>A Potentiometer on a pin of an analog port
	 *
	 * @param ledPort
	 *            the digital port on which the LED is connected to.
	 * @param ledPinTick
	 *            the id of the pin on which the LED is connected to.
	 * @param potPort
	 *            the analog port on which the potentiometer is connected to.
	 * @param potPin
	 *            the id of the pin on which the potentiometer is connected to.
	 */
	public Metronome(GPIOPort ledPort, int ledPinTick, GPIOPort potPort, int potPin) {
		this.ledPort = ledPort;
		this.potPort = potPort;
		this.ledPinTick = ledPinTick;
		ledPort.setMode(ledPinTick, Mode.DIGITAL_OUTPUT);
		potPort.setMode(potPin, Mode.ANALOG_INPUT);
		potPort.setMode(1, Mode.ANALOG_INPUT);
	}

	@Override
	public void run() {
		boolean toggle = true;
		while (!stop) {
			ledPort.setDigitalValue(ledPinTick, toggle);
			toggle = !toggle;
			int value = potPort.getAnalogValue(0);
			int delay = (SLEEP_MAX * value / 4095);
			sleep(delay);
		}
		ledPort.setDigitalValue(ledPinTick, false);
	}

	private void sleep(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			stop();
		}
	}

	void stop() {
		this.stop = true;
	}
}
