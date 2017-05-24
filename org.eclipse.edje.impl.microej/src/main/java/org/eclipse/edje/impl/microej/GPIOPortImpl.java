/*******************************************************************************
 * Copyright (c) 2016-2017 IS2T S.A. Operating under the brand name MicroEJ(r).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *    {Laurent Lagosanto, MicroEJ} - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.edje.impl.microej;

import org.eclipse.edje.HardwareDescriptor;
import org.eclipse.edje.Peripheral;
import org.eclipse.edje.gpio.GPIOPort;

/**
 * MicroEJ-specific GPIO port implementation.
 */
class GPIOPortImpl implements GPIOPort {

	private final int port_id;
	private final String name;
	private final int pinMin;
	private final int pinMax;
	private final int analogMin;
	private final int analogMax;

	GPIOPortImpl(int id, String name, int pinMin, int pinMax, int analogMin, int analogMax) {
		this.port_id = id;
		this.name = name;
		this.pinMin = pinMin;
		this.pinMax = pinMax;
		this.analogMin = analogMin;
		this.analogMax = analogMax;
	}

	@Override
	public HardwareDescriptor<? extends Peripheral> getDescriptor() {
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setMode(int pin, Mode mode) {
		if ((pin < pinMin) || (pin > pinMax)) {
			throw new IllegalArgumentException();
		}
		ej.hal.gpio.GPIO.Mode halMode;
		switch (mode) {
		case ANALOG_INPUT:
			halMode = ej.hal.gpio.GPIO.Mode.ANALOG_INPUT;
			break;
		case ANALOG_OUTPUT:
			halMode = ej.hal.gpio.GPIO.Mode.ANALOG_OUTPUT;
			break;
		case DIGITAL_INPUT:
			halMode = ej.hal.gpio.GPIO.Mode.DIGITAL_INPUT;
			break;
		case DIGITAL_INPUT_PULLDOWN:
			// FIXME: this is an hardcoded bail out because the hal
			// doesn't offer such an interface yet
			throw new IllegalStateException();
		case DIGITAL_INPUT_PULLUP:
			halMode = ej.hal.gpio.GPIO.Mode.DIGITAL_INPUT_PULLUP;
			break;
		case DIGITAL_OUTPUT:
			halMode = ej.hal.gpio.GPIO.Mode.DIGITAL_OUTPUT;
			break;
		default:
			throw new IllegalArgumentException();
		}
		ej.hal.gpio.GPIO.setMode(port_id, pin, halMode);
	}

	@Override
	public boolean getDigitalValue(int pin) {
		return ej.hal.gpio.GPIO.getDigitalValue(port_id, pin);
	}

	@Override
	public void setDigitalValue(int pin, boolean value) {
		ej.hal.gpio.GPIO.setDigitalValue(port_id, pin, value);
	}

	@Override
	public int getAnalogValue(int pin) {
		return ej.hal.gpio.GPIO.getAnalogValue(port_id, pin);
	}

	@Override
	public void setAnalogValue(int pin, int value) {
		// FIXME: this is an hardcoded conversion to a percentage,
		// because the hal only offers such an interface.
		if (value > analogMax) {
			throw new IllegalArgumentException();
		}
		int percentage = (value * 100) / analogMax;
		ej.hal.gpio.GPIO.setAnalogValue(port_id, pin, percentage);
	}

	@Override
	public int getAnalogMaxValue(int pin) {
		if ((pin < pinMin) || (pin > pinMax)) {
			throw new IllegalArgumentException();
		}
		return analogMax;
	}

	@Override
	public int getAnalogMinValue(int pin) {
		if ((pin < pinMin) || (pin > pinMax)) {
			throw new IllegalArgumentException();
		}
		return analogMin;
	}
}
