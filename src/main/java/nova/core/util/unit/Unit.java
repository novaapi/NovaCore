/*
 * Copyright (c) 2015 NOVA, All rights reserved.
 * This library is free software, licensed under GNU Lesser General Public License version 3
 *
 * This file is part of NOVA.
 *
 * NOVA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NOVA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NOVA.  If not, see <http://www.gnu.org/licenses/>.
 */

package nova.core.util.unit;

import nova.core.language.LanguageManager;
import nova.core.language.Translateable;
import nova.core.util.Identifiable;
import nova.core.util.registry.Registry;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ExE Boss
 */
public final class Unit implements Identifiable, Translateable {
	private static final Registry<Unit> REGISTRY = new Registry<>();

	public static final Unit METRE = getOrCreateUnit("nova:metre", "m");
	public static final Unit LITRE = getOrCreateUnit("nova:litre", "l");
	public static final Unit GRAM = getOrCreateUnit("nova:gram", "g", UnitPrefix.KILO);
	// TODO: Define More Units

	public static final Unit AMPERE = getOrCreateUnit("nova:ampere", "I");
	public static final Unit AMP_HOUR = getOrCreateUnit("nova:amp_hour", "Ah");
	public static final Unit VOLTAGE = getOrCreateUnit("nova:voltage", "V");
	public static final Unit WATT = getOrCreateUnit("nova:watt", "W");
	public static final Unit WATT_HOUR = getOrCreateUnit("nova:watt_hour", "Wh");
	public static final Unit RESISTIVITY = getOrCreateUnit("nova:resistivity", "Ω");
	public static final Unit CONDUCTIVITY = getOrCreateUnit("nova:conductivity", "S");
	public static final Unit JOULE = getOrCreateUnit("nova:joule", "J");
	public static final Unit NEWTON_METRE = getOrCreateUnit("nova:newton_metre", "Nm");

	static {
		// TODO: Load from `en-US.lang`
		LanguageManager.instance().register("en-US", "unit.nova.metre.name", "Meter");
		LanguageManager.instance().register("en-US", "unit.nova.metre.plural", "Meters");
		LanguageManager.instance().register("en-US", "unit.nova.litre.name", "Liter");
		LanguageManager.instance().register("en-US", "unit.nova.litre.plural", "Liters");
		LanguageManager.instance().register("en-US", "unit.nova.gram.name", "Gram");
		LanguageManager.instance().register("en-US", "unit.nova.gram.plural", "Grams");
		LanguageManager.instance().register("en-US", "unit.nova.ampere.name", "Amp");
		LanguageManager.instance().register("en-US", "unit.nova.ampere.plural", "Amps");
		LanguageManager.instance().register("en-US", "unit.nova.amp_hour.name", "Amp Hour");
		LanguageManager.instance().register("en-US", "unit.nova.amp_hour.plural", "Amp Hours");
		LanguageManager.instance().register("en-US", "unit.nova.voltage.name", "Volt");
		LanguageManager.instance().register("en-US", "unit.nova.voltage.plural", "Volts");
		LanguageManager.instance().register("en-US", "unit.nova.watt.name", "Watt");
		LanguageManager.instance().register("en-US", "unit.nova.watt.plural", "Watts");
		LanguageManager.instance().register("en-US", "unit.nova.watt_hour.name", "Watt Hour");
		LanguageManager.instance().register("en-US", "unit.nova.watt_hour.plural", "Watt Hours");
		LanguageManager.instance().register("en-US", "unit.nova.resistivity.name", "Ohm");
		LanguageManager.instance().register("en-US", "unit.nova.resistivity.plural", "Ohms");
		LanguageManager.instance().register("en-US", "unit.nova.conductivity.name", "Siemen");
		LanguageManager.instance().register("en-US", "unit.nova.conductivity.plural", "Siemens");
		LanguageManager.instance().register("en-US", "unit.nova.joule.name", "Joule");
		LanguageManager.instance().register("en-US", "unit.nova.joule.plural", "Joules");
		LanguageManager.instance().register("en-US", "unit.nova.newton_meter.name", "Newton Meter");
		LanguageManager.instance().register("en-US", "unit.nova.newton_meter.plural", "Newton Meters");

		// TODO: Load from `en-GB.lang`
		LanguageManager.instance().register("en-GB", "unit.nova.metre.name", "Metre");
		LanguageManager.instance().register("en-GB", "unit.nova.metre.plural", "Metres");
		LanguageManager.instance().register("en-GB", "unit.nova.litre.name", "Litre");
		LanguageManager.instance().register("en-GB", "unit.nova.litre.plural", "Litres");
		LanguageManager.instance().register("en-GB", "unit.nova.gram.name", "Gram");
		LanguageManager.instance().register("en-GB", "unit.nova.gram.plural", "Grams");
		LanguageManager.instance().register("en-GB", "unit.nova.ampere.name", "Amp");
		LanguageManager.instance().register("en-GB", "unit.nova.ampere.plural", "Amps");
		LanguageManager.instance().register("en-GB", "unit.nova.amp_hour.name", "Amp Hour");
		LanguageManager.instance().register("en-GB", "unit.nova.amp_hour.plural", "Amp Hours");
		LanguageManager.instance().register("en-GB", "unit.nova.voltage.name", "Volt");
		LanguageManager.instance().register("en-GB", "unit.nova.voltage.plural", "Volts");
		LanguageManager.instance().register("en-GB", "unit.nova.watt.name", "Watt");
		LanguageManager.instance().register("en-GB", "unit.nova.watt.plural", "Watts");
		LanguageManager.instance().register("en-GB", "unit.nova.watt_hour.name", "Watt Hour");
		LanguageManager.instance().register("en-GB", "unit.nova.watt_hour.plural", "Watt Hours");
		LanguageManager.instance().register("en-GB", "unit.nova.resistivity.name", "Ohm");
		LanguageManager.instance().register("en-GB", "unit.nova.resistivity.plural", "Ohms");
		LanguageManager.instance().register("en-GB", "unit.nova.conductivity.name", "Siemen");
		LanguageManager.instance().register("en-GB", "unit.nova.conductivity.plural", "Siemens");
		LanguageManager.instance().register("en-GB", "unit.nova.joule.name", "Joule");
		LanguageManager.instance().register("en-GB", "unit.nova.joule.plural", "Joules");
		LanguageManager.instance().register("en-GB", "unit.nova.newton_meter.name", "Newton Metre");
		LanguageManager.instance().register("en-GB", "unit.nova.newton_meter.plural", "Newton Metres");
	}

	private final String id;
	private final String unlocalizedName;
	private final String symbol;
	private UnitPrefix basePrefix = null;

	private Unit(String id, String symbol) {
		this(id, id.replace(':', '.'), symbol);
	}

	private Unit(String id, String unlocalizedName, String symbol) {
		this.id = id;
		this.unlocalizedName = unlocalizedName;
		this.symbol = symbol;
	}

	private Unit setBasePrefix(UnitPrefix basePrefix) {
		if (this.basePrefix == null)
			this.basePrefix = basePrefix;
		return this;
	}

	@Override
	public String getUnlocalizedName() {
		return "unit." + this.unlocalizedName;
	}

	@Override
	public String getLocalizedName() {
		return LanguageManager.instance().translate(this.getUnlocalizedName() + ".name", this.getReplacements());
	}

	public String getPluralName() {
		return LanguageManager.instance().translate(this.getUnlocalizedName() + ".plural", this.getReplacements());
	}

	public String getSymbol() {
		return this.symbol;
	}

	public UnitPrefix getBasePrefix() {
		return basePrefix == null ? UnitPrefix.BASE : basePrefix;
	}

	@Override
	public String getID() {
		return id;
	}

	// TODO: Move to Registry
	public Set<Unit> getUnitsFromMod(String modId) {
		return REGISTRY.stream().filter((e) -> {
			String id = e.getID();
			if (id.contains(":")) {
				return id.substring(0, id.lastIndexOf(':')).equals(modId);
			} else {
				return modId == null || modId.isEmpty();
			}
		}).collect(Collectors.toSet());
	}

	public Optional<Unit> getUnit(String id) {
		return REGISTRY.get(id);
	}

	public static Unit getOrCreateUnit(String id, String unit) {
		if (REGISTRY.contains(id)) return REGISTRY.get(id).get();
		return REGISTRY.register(new Unit(id, unit));
	}

	public static Unit getOrCreateUnit(String id, String unlocalizedName, String unit) {
		if (REGISTRY.contains(id)) return REGISTRY.get(id).get();
		return REGISTRY.register(new Unit(id, unlocalizedName, unit));
	}

	public static Unit getOrCreateUnit(String id, String unit, UnitPrefix basePrefix) {
		if (REGISTRY.contains(id)) return REGISTRY.get(id).get();
		return REGISTRY.register(new Unit(id, unit)).setBasePrefix(basePrefix);
	}

	public static Unit getOrCreateUnit(String id, String unlocalizedName, String unit, UnitPrefix basePrefix) {
		if (REGISTRY.contains(id)) return REGISTRY.get(id).get();
		return REGISTRY.register(new Unit(id, unlocalizedName, unit)).setBasePrefix(basePrefix);
	}
}