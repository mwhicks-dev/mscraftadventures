package dev.hicksm.minecraft.plugin.mscraftadventures.sylladex;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class SylladexListener implements Listener {
	
	private static final int MAIN_SLOT_ID = 36;
	
	@EventHandler
	public void onItemPickup(EntityPickupItemEvent e) {
		// handle as normal if item is not picked up by player
		if (e.getEntityType() != EntityType.PLAYER) return;
		
		// retrieve player inventory
		Player p = (Player) e.getEntity();
		ItemStack[] items = p.getInventory().getContents();
		
		if (items[MAIN_SLOT_ID] == null) {
			// if main hand free, put item there
			items[MAIN_SLOT_ID] = e.getItem().getItemStack();
		} else {
			// otherwise, cancel
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onItemDrop(EntityDropItemEvent e) {
		// handle as normal if item is not picked up by player
		if (e.getEntityType() != EntityType.PLAYER) return;
		
		// validate slot
		Player p = (Player) e.getEntity();
		if (p.getInventory().getHeldItemSlot() != 36) {
			e.setCancelled(true);
		}
		// else if player is sneaking add to sylladex
		else if (p.isSneaking()) {
			// cancel actual Item drop
			e.setCancelled(true);
			
			// add to sylladex
			Item drop = e.getItemDrop();
			if (drop != null) {
				SylladexManager.getInstance().getPlayerFetchModus(p)
						.addItem(drop);
			}
		}
	}

}
