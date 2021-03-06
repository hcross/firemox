/*
 *   Firemox is a turn based strategy simulator
 *   Copyright (C) 2003-2007 Fabrice Daugan
 *
 *   This program is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) any
 * later version.
 *
 *   This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more 
 * details.
 *
 *   You should have received a copy of the GNU General Public License along  
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sf.firemox.expression;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.clickable.target.Target;
import net.sf.firemox.database.propertyconfig.PropertyProxyConfig;
import net.sf.firemox.event.context.ContextEventListener;
import net.sf.firemox.tools.MToolKit;

/**
 * This expression represent a referencable value.
 * 
 * @author <a href="mailto:fabdouglas@users.sourceforge.net">Fabrice Daugan </a>
 * @since 0.90
 */
public class ReferenceValue extends Expression {

	/**
	 * Creates a new instance of ReferenceValue <br>
	 * <ul>
	 * Structure of InputStream : Data[size]
	 * <li>expressionName + '\0' [...]</li>
	 * </ul>
	 * 
	 * @param inputFile
	 *          file containing this action
	 * @throws IOException
	 *           if error occurred during the reading process from the specified
	 *           input stream
	 */
	public ReferenceValue(InputStream inputFile) throws IOException {
		super();
		expressionName = MToolKit.readString(inputFile);
	}

	@Override
	public Expression getConstraintExpression(Map<String, Expression> values) {
		return values.get(expressionName);
	}

	@Override
	public Object getObject(Ability ability, Target tested,
			ContextEventListener context) {
		return PropertyProxyConfig.values.get(expressionName).getObject(ability,
				tested, context);
	}

	@Override
	public int getValue(Ability ability, Target tested,
			ContextEventListener context) {
		return PropertyProxyConfig.values.get(expressionName).getValue(ability,
				tested, context);
	}

	/**
	 * The name of expression to reference.
	 */
	private String expressionName;
}
