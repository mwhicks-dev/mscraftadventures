package dev.hicksm.minecraft.plugin.mscraftadventures;

import org.bukkit.plugin.java.JavaPlugin;

import dev.hicksm.minecraft.plugin.mscraftadventures.sylladex.SylladexListener;

public class MSCraftAdventures extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getLogger().info("onEnable Hit!");
		
		// add sylladex listener
		getServer().getPluginManager()
				.registerEvents(new SylladexListener(), this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable Hit!");
	}

}
