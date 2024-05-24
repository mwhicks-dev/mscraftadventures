package dev.hicksm.minecraft.plugin.mscraftadventures.sylladex;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import dev.hicksm.minecraft.plugin.mscraftadventures.sylladex.fetch_modus.StackModus;

public class SylladexListener implements Listener {
	
	private static final int MAIN_SLOT_ID = 0;
	
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
			e.getItem().remove();
		} else {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		
		if (p.getInventory().getHeldItemSlot() != MAIN_SLOT_ID) {
			e.setCancelled(true);
		}
		// else if player is sneaking add to sylladex
		else if (p.isSneaking()) {
			// add to sylladex
			Item drop = e.getItemDrop();
			if (drop != null) {
				SylladexManager.getInstance().getPlayerFetchModus(p)
						.addItem(drop);
			}
			
			// remove actual item drop
			e.getItemDrop().remove();
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		// if player has no fetch modus give them stack
		// TODO: initial set-up flow
		if (SylladexManager.getInstance().getPlayerFetchModus(e.getPlayer()) == null)
			SylladexManager.getInstance().setPlayerFetchModus(e.getPlayer(), new StackModus());
	}

}
