<!--
/*******************************************************************************
 * Copyright (c) 2016 IS2T S.A. Operating under the brand name MicroEJ(r).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0
 * which accompanies this distribution, and is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *    {LAurent Lagosanto, MicroEJ} - initial documentation
 *******************************************************************************/
-->
# Overview
MicroEJ-specific implementation for the Edje library.

## Usage
Add the following line to your `module.ivy` or your `ivy.xml`:
> `<dependency org="org.eclipse.edje" name="edje" rev="[0.2.0-RC0,1.0.0-RC0[" conf="default->*"/>`
> `<dependency org="org.eclipse.edje" name="edje.impl.microej" rev="[0.1.0-RC0,1.0.0-RC0[" conf="default->*"/>`

# Requirements
  - EDJE-0.2 or higher
  - EDC-1.2 or higher
  - HAL-1.0 or higher
  - ECOM-1.1.0 or higher
  - ECOM-COMM-1.1.0 or higher

## Dependencies
_All dependencies are retrieved transitively by Ivy resolver_.

# Source
N/A

## Restrictions
This version contains an implementation dedicated for the STM32F746G-DISCO board.