package dev.hicksm.minecraft.plugin.mscraftadventures;

import org.bukkit.plugin.java.JavaPlugin;

public class MSCraftAdventures extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable Hit!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable Hit!");
	}

}
