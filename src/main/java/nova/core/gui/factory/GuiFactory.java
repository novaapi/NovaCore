package nova.core.gui.factory;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import nova.core.gui.Gui;
import nova.core.gui.GuiConstraints;
import nova.core.gui.GuiEvent.BindEvent;
import nova.core.loader.NovaMod;
import nova.core.player.Player;
import nova.core.util.Registry;
import nova.core.util.exception.NovaException;
import nova.core.util.transform.Vector3i;

public abstract class GuiFactory {

	protected HashMap<String, Registry<Gui>> guiRegistry = new HashMap<>();
	protected EnumMap<GuiType, List<Gui>> overlayRegistry = new EnumMap<>(GuiType.class);

	protected Optional<Gui> activeGUI;

	public static enum GuiType {
		INGAME, TITLE, OPTIONS, INGAME_OPTIONS, CRAFTING, NATIVE, CUSTOM
	}

	/**
	 * Register a NOVA {@link Gui}.
	 * 
	 * @param gui The GUI you want to register
	 * @param modID Your mod's ID
	 */
	public void registerGui(Gui gui, String modID) {

		// TODO verify mod id? Register with the mod instance rather?
		gui.setModID(modID);
		if (!guiRegistry.containsKey(modID))
			guiRegistry.put(modID, new Registry<>());
		guiRegistry.get(modID).register(gui);
	}

	public void registerOverlay(Gui gui, GuiType guiType) {
		if (!overlayRegistry.containsKey(guiType))
			overlayRegistry.put(guiType, new ArrayList<>());
		overlayRegistry.get(guiType).add(gui);
	}

	/**
	 * Reduced version of {@link #showGui(String, String, GuiConstraints)}, will
	 * create a new instance of {@link GuiConstraints} with the supplied player
	 * as argument.
	 * 
	 * @param modID Id of the {@link NovaMod} that registered the GU
	 * @param identifier Unique identifier for the GUI
	 * @param player {@link Player} who opened the GUI
	 * @param position The block coordinate on which to open the GUI
	 */
	public void showGui(String modID, String identifier, Player player, Vector3i position) {
		showGui(modID, identifier, new GuiConstraints(player, position));
	}

	/**
	 * Shows the provided {@link Gui} previously registered over the factory
	 * instance. It will trigger the {@link BindEvent}, so any changes to the
	 * instance can be done there.
	 * 
	 * @param modID Id of the {@link NovaMod} that registered the GU
	 * @param identifier Unique identifier for the GUI
	 * @param constraints Constraints object to provide arguments to the GUI
	 *        instance
	 */
	public void showGui(String modID, String identifier, GuiConstraints constraints) {
		Registry<Gui> gr = guiRegistry.get(modID);
		if (gr == null)
			throw new NovaException(String.format("No GUI called %s registered for mod %s!", identifier, modID));
		Optional<Gui> optGui = gr.get(identifier);
		if (!optGui.isPresent())
			throw new NovaException(String.format("No GUI called %s registered for mod %s!", identifier, modID));
		Gui gui = optGui.get();
		bind(gui, constraints);
		gui.bind(constraints);
	}

	public abstract void bind(Gui gui, GuiConstraints constraints);

	/**
	 * Closes the currently open NOVA {@link Gui}, if present, and returns to
	 * the in-game GUI. It will not affect any native GUIs that might exist
	 * along with NOVA.
	 */
	public void closeGui() {
		if (activeGUI.isPresent()) {
			activeGUI.get().unbind();
			unbind(activeGUI.get());
			activeGUI = Optional.empty();
		}
	}

	protected abstract void unbind(Gui gui);

	public Optional<Gui> getActiveGui() {
		return activeGUI;
	}

	public GuiType getActiveGuiType() {
		return activeGUI.isPresent() ? GuiType.CUSTOM : GuiType.NATIVE;
	}
}