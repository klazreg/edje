/*
 * Java
 *
 * Copyright 2016 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.eclipse.edje.samples.microej.metronome;

import org.eclipse.edje.gpio.GPIOPort;
import org.eclipse.edje.gpio.GPIOPort.Mode;

/**
 *
 */
public class Metronome implements Runnable {

	private static final int SLEEP_MAX = 500;

	private final GPIOPort ledPort;
	private final GPIOPort potPort;
	private final int ledPinTick;
	private volatile boolean stop;

	/**	
	 *
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

	public void stop() {
		this.stop = true;
	}
}
