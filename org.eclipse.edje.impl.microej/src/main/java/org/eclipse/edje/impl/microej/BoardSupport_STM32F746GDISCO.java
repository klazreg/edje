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

import org.eclipse.edje.gpio.GPIOPort;

/**
 * MicroEJ-specific GPIO port factory registration for the STM32F746G-DISCO board.
 */
class BoardSupport_STM32F746GDISCO extends BoardSupport {
	private static final int F7_ANALOG_MIN = 0;
	private static final int F7_ANALOG_MAX = 4095;
	private static final int F7_PORT_MCU_ALL = 0;
	private static final int F7_PORT_MCU_A = 1;
	private static final int F7_PORT_MCU_B = 2;
	private static final int F7_PORT_MCU_F = 6;
	private static final int F7_PORT_MCU_G = 7;
	private static final int F7_PORT_MCU_H = 8;
	private static final int F7_PORT_MCU_I = 9;
	private static final int F7_PORT_CN4 = 64;
	private static final int F7_PORT_CN5 = 65;
	private static final int F7_PORT_CN7 = 67;
	private static final int F7_PORT_ARDUINO_DIGITAL = 30;
	private static final int F7_PORT_ARDUINO_ANALOG = 31;

	void register(MicroEJPeripheralRegistry registry, int id, String name, int pinMin, int pinMax) {
		registry.register(GPIOPort.class, new GPIOPortImpl(id, name, pinMin, pinMax, F7_ANALOG_MIN, F7_ANALOG_MAX), false, true);
	}

	void init(MicroEJPeripheralRegistry registry) {
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
}
