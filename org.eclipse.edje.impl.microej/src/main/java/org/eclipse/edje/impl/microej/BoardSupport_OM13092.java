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

/**
 * MicroEJ-specific GPIO port factory registration for the GR-Peach board.
 */

public class BoardSupport_OM13092 extends BoardSupport {
	
	
	
	private static final int OM13092_ANALOG_MIN = 0; // there's no analogic input in lpc
	private static final int OM13092_ANALOG_MAX = 4095; //there's no analogic input in lpc
	
	private static final int OM13092_PORT_MCU_ALL = 0;
	private static final int OM13092_PORT_0 = 1; 
	private static final int OM13092_PORT_1 = 2;
	private static final int OM13092_PORT_2 = 3;
	private static final int OM13092_PORT_3 = 4;
	private static final int OM13092_PORT_4 = 5;
	private static final int OM13092_PORT_5 = 6;
	
	
	private static final int OM13092_PORT_J9 = 64;  
	private static final int OM13092_PORT_J10 = 65;  
	private static final int OM13092_PORT_J12 = 66; 
	private static final int OM13092_PORT_J13 = 67;
	
	private static final int OM13092_PORT_ARDUINO_DIGITAL = 30;
	private static final int OM13092_PORT_ARDUINO_ANALOG = 31;

	void register(MicroEJPeripheralRegistry registry, int id, String name, int pinMin, int pinMax) {
		registry.register(GPIOPort.class, new GPIOPortImpl(id, name, pinMin, pinMax, OM13092_ANALOG_MIN, OM13092_ANALOG_MAX), false, true);
	}

	@Override
	void init(MicroEJPeripheralRegistry registry) {
		register(registry, OM13092_PORT_MCU_ALL, "GPIO_MCU_ALL", 0, 170);		
		register(registry, OM13092_PORT_0, "GPIO_PORT_0", 0, 31);
		register(registry, OM13092_PORT_1, "GPIO_PORT_1", 0, 31);
		register(registry, OM13092_PORT_2, "GPIO_PORT_2", 0, 31);
		register(registry, OM13092_PORT_3, "GPIO_PORT_3", 0, 31);
		register(registry, OM13092_PORT_4, "GPIO_PORT_4", 0, 31);
		register(registry, OM13092_PORT_5, "GPIO_PORT_5", 0, 10);
		
		
		register(registry, OM13092_PORT_J9, "GPIO_J9", 1, 20); 
		register(registry, OM13092_PORT_J12, "GPIO_J12", 1, 12); 
		register(registry, OM13092_PORT_J13, "GPIO_J13", 1, 20); 	
		
		
		
		register(registry, OM13092_PORT_ARDUINO_DIGITAL, "GPIO_ARDUINO_DIGITAL", 0, 15);
		register(registry, OM13092_PORT_ARDUINO_ANALOG, "GPIO_ARDUINO_ANALOG", 0, 5);
	}
	

}
