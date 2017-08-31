/*******************************************************************************
 * Copyright (c) 2016-2017 IS2T S.A. Operating under the brand name MicroEJ(r).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *    {Khaoula LAZREG, MicroEJ} 
 *******************************************************************************/

package org.eclipse.edje.impl.microej;

import org.eclipse.edje.gpio.GPIOPort;


public class BoardSupport_GRPEACH extends BoardSupport {
	
	private static final int PEACH_ANALOG_MIN = 0;
	private static final int PEACH_ANALOG_MAX = 4095;
	
	private static final int PEACH_PORT_0 = 0; 
	private static final int PEACH_PORT_1 = 1;
	private static final int PEACH_PORT_2 = 2;
	private static final int PEACH_PORT_3 = 3;
	private static final int PEACH_PORT_4 = 4;
	private static final int PEACH_PORT_5 = 5;
	private static final int PEACH_PORT_6 = 6;
	private static final int PEACH_PORT_7 = 7;
	private static final int PEACH_PORT_8 = 8;
	private static final int PEACH_PORT_9 = 9;
	private static final int PEACH_PORT_10 = 10;
	private static final int PEACH_PORT_11 = 11;
	
	private static final int PEACH_PORT_CN9 = 69;
	private static final int PEACH_PORT_CN14 = 74;
	private static final int PEACH_PORT_CN15 = 75;
	
	private static final int PEACH_PORT_ARDUINO_DIGITAL = 30;
	private static final int PEACH_PORT_ARDUINO_ANALOG = 31;

	void register(MicroEJPeripheralRegistry registry, int id, String name, int pinMin, int pinMax) {
		registry.register(GPIOPort.class, new GPIOPortImpl(id, name, pinMin, pinMax, PEACH_ANALOG_MIN, PEACH_ANALOG_MAX), false, true);
	}

	@Override
	void init(MicroEJPeripheralRegistry registry) {
		
		register(registry, PEACH_PORT_0, "GPIO_PORT_0", 0, 1);
		register(registry, PEACH_PORT_1, "GPIO_PORT_1", 0, 5);
		register(registry, PEACH_PORT_2, "GPIO_PORT_2", 0, 15);
		register(registry, PEACH_PORT_3, "GPIO_PORT_3", 0, 15);
		register(registry, PEACH_PORT_4, "GPIO_PORT_4", 0, 15);
		register(registry, PEACH_PORT_5, "GPIO_PORT_5", 0, 15);
		register(registry, PEACH_PORT_6, "GPIO_PORT_6", 0, 15);
		register(registry, PEACH_PORT_7, "GPIO_PORT_7", 1, 15);
		register(registry, PEACH_PORT_8, "GPIO_PORT_8", 1, 15);
		register(registry, PEACH_PORT_9, "GPIO_PORT_9", 1, 7);
		register(registry, PEACH_PORT_10, "GPIO_PORT_10", 1, 15);
		register(registry, PEACH_PORT_11, "GPIO_PORT_11", 1, 15);
		
		register(registry, PEACH_PORT_CN9, "GPIO_CN9", 1, 10);
		register(registry, PEACH_PORT_CN14, "GPIO_CN14", 1, 8);
		register(registry, PEACH_PORT_CN15, "GPIO_CN15", 1, 6);
		
		
		
		
		register(registry, PEACH_PORT_ARDUINO_DIGITAL, "GPIO_ARDUINO_DIGITAL", 0, 15);
		register(registry, PEACH_PORT_ARDUINO_ANALOG, "GPIO_ARDUINO_ANALOG", 0, 5);
	}

}
