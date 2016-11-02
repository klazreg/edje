<!--
/*******************************************************************************
 * Copyright (c) 2016 IS2T S.A. Operating under the brand name MicroEJ(r).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *    {Guillaume Balan, MicroEJ} - initial documentation
 *    {Laurent Lagosanto, MicroEJ} - documentation updates
 *******************************************************************************/
-->

# Overview
The goal of the Eclipse Edje project is to define a standard high-level Java API called Hardware Abstraction Layer (HAL) for 
accessing hardware features delivered by microcontrollers such as GPIO, DAC, ADC, PWM, MEMS, UART, CAN, Network, LCD, 
etc. that can directly connect to native libraries, drivers and board support packages provided by silicon vendors 
with their evaluation kits.

# Usage
The [org.eclipse.edje](org.eclipse.edje) folder contains the generic code for the Eclipse Edje API and implementation, as well as generic test code.
<br>The [org.eclipse.edje.impl.*] folders contain target-specific code of the implementation.
<br>The [org.eclipse.edje.samples.*] folders contain sample code for the project.

# License
- See the license file [LICENSE.txt](LICENSE.txt) located at the root of this repository.
