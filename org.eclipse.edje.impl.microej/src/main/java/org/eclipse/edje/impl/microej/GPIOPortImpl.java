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

import org.eclipse.edje.HardwareDescriptor;
import org.eclipse.edje.Peripheral;
import org.eclipse.edje.gpio.GPIOPort;

/**
 * MicroEJ-specific GPIO port implementation. It's actually valid only on the F7
 * board for the moment.
 */
class GPIOPortImpl implements GPIOPort {
	private static final int ANALOG_MAX = 4095;
	static final int F7_PORT_MCU_ALL = 0;
	static final int F7_PORT_MCU_A = 1;
	static final int F7_PORT_MCU_B = 2;
	static final int F7_PORT_MCU_F = 6;
	static final int F7_PORT_MCU_G = 7;
	static final int F7_PORT_MCU_H = 8;
	static final int F7_PORT_MCU_I = 9;
	static final int F7_PORT_CN4 = 64;
	static final int F7_PORT_CN5 = 65;
	static final int F7_PORT_CN7 = 67;
	static final int F7_PORT_ARDUINO_DIGITAL = 30;
	static final int F7_PORT_ARDUINO_ANALOG = 31;

	private static void register(MicroEJPeripheralRegistry registry, int id, String name, int pinMin, int pinMax) {
		System.out.println("Registering " + name);
		registry.register(GPIOPort.class, new GPIOPortImpl(id, name, pinMin, pinMax), false, true);
	}

	static void init(MicroEJPeripheralRegistry registry) {
		register(registry, F7_PORT_MCU_ALL, "GPIO_MCU_ALL", 0, 143);
		register(registry, F7_PORT_MCU_A, "GPIO_MCU_A", 0, 15);
		register(registry, F7_PORT_MCU_B, "GPIO_MCU_B", 0, 15);
		register(registry, F7_PORT_MCU_F, "GPIO_MCU_F", 0, 15);
		register(registry, F7_PORT_MCU_G, "GPIO_MCU_G", 0, 15);
		register(registry, F7_PORT_MCU_H, "GPIO_MCU_H", 0, 15);
		register(registry, F7_PORT_MCU_I, "GPIO_MCU_I", 0, 15);
		register(registry, F7_PORT_CN4, "GPIO_CN4", 1, 8);
		register(registry, F7_PORT_CN5, "GPIO_CN5", 1, 10);
		register(registry, F7_PORT_CN7, "GPIO_CN7", 1, 6);
		register(registry, F7_PORT_ARDUINO_DIGITAL, "GPIO_ARDUINO_DIGITAL", 0, 15);
		register(registry, F7_PORT_ARDUINO_ANALOG, "GPIO_ARDUINO_ANALOG", 0, 7);
	}

	private final int port_id;
	private final String name;
	private final int pinMin;
	private final int pinMax;

	GPIOPortImpl(int id, String name, int pinMin, int pinMax) {
		this.port_id = id;
		this.name = name;
		this.pinMin = pinMin;
		this.pinMax = pinMax;
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
		if (value > ANALOG_MAX) {
			throw new IllegalArgumentException();
		}
		int percentage = (value * 100) / ANALOG_MAX;
		ej.hal.gpio.GPIO.setAnalogValue(port_id, pin, percentage);
	}

	@Override
	public int getAnalogMaxValue(int pin) {
		// FIXME: this is an hardcoded value for the F7 because the hal
		// doesn't offer such an interface yet
		// we know that the ADCs are 12bits on this platform.
		if ((pin < pinMin) || (pin > pinMax)) {
			throw new IllegalArgumentException();
		}
		return ANALOG_MAX;
	}

	@Override
	public int getAnalogMinValue(int pin) {
		// FIXME: this is an hardcoded value for the F7 because the hal
		// doesn't offer such an interface yet
		// we know that the ADCs are 12bits on this platform.
		return 0;
	}
}
