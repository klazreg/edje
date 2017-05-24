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

/**
 * Base class for BoardSupport classes factories, to be specialized per boards.
 * <br>
 * The naming scheme for the subclasses must be:
 * <code>org.eclipse.edje.impl.microej.BoardSupport_&lt;board name&gt;</code> .
 * Subclasses must implement the {@link #init(MicroEJPeripheralRegistry)}
 * method, discover the devices on the target board, in a board-specific way,
 * and call
 * {@link MicroEJPeripheralRegistry#register(Class, org.eclipse.edje.Peripheral, boolean, boolean)}
 * accordingly.
 *
 */
abstract class BoardSupport {

	/**
	 * Initialization method to be implemented for each supported board.
	 * @param registry the registry implementation triggering this initialization.
	 */
	abstract void init(MicroEJPeripheralRegistry registry);

}