package dev.hicksm.minecraft.plugin.mscraftadventures.sylladex;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import dev.hicksm.minecraft.plugin.mscraftadventures.sylladex.fetch_modus.FetchModus;

public class SylladexManager {
	
	private Map<Player, FetchModus> inventories = null;
	
	private static SylladexManager instance = null;
	
	private SylladexManager() {
		inventories = new HashMap<>();
	}
	
	/** Get singleton SylladexManager */
	public SylladexManager getInstance() {
		if (instance == null) instance = new SylladexManager();
		return instance;
	}
	
	public FetchModus getPlayerInventory(Player p) {
		return inventories.get(p);
	}

}
