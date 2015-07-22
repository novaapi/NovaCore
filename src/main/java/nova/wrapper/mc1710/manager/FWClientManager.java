package nova.wrapper.mc1710.manager;

import net.minecraft.client.Minecraft;
import nova.core.entity.Entity;
import nova.core.game.ClientManager;
import nova.wrapper.mc1710.launcher.NovaMinecraft;
import nova.wrapper.mc1710.wrapper.entity.BWEntity;

/**
 * @author Calclavia
 */
public class FWClientManager extends ClientManager {

	@Override
	public Entity getPlayer() {
		return new BWEntity(NovaMinecraft.proxy.getClientPlayer());
	}

	@Override
	public boolean isPaused() {
		return Minecraft.getMinecraft().isGamePaused();
	}
}
