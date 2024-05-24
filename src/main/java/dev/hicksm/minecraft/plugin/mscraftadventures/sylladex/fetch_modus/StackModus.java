package dev.hicksm.minecraft.plugin.mscraftadventures.sylladex.fetch_modus;

import org.bukkit.entity.Item;

public class StackModus extends FetchModus {
	
	private void collapseItemsLeft() {
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 1; i < items.length; i++) {
				if (items[i-1] == null && items[i] != null) {
					Item tmp = items[i-1];
					items[i-1] = items[i];
					items[i] = tmp;
					sorted = false;
				}
			}
		}
	}
	
	/** Copies other items and then collapses to left */
	public StackModus(FetchModus other) {
		super(other);
		collapseItemsLeft();
	}

	@Override
	public void addItem(Item item) {
		// get item at end
		Item drop = items[items.length - 1];
		items[items.length - 1] = item;
		collapseItemsLeft();
		
		// drop non-null item if forced out
		if (drop != null) {
			// TODO: check if captchalogue card
			drop.copy(item.getLocation());
		}
	}

	@Override
	public boolean getIndexRetrievable(int index) {
		if (index < 0 || index >= items.length) return false;
		
		// return item is most recent one added by stack rules
		if (items[index] != null && (index == items.length - 1
				|| items[index + 1] == null)) return true;
		
		return false;
	}

	@Override
	public Item retrieveItem(int index) throws IllegalArgumentException {
		if (!getIndexRetrievable(index)) throw new IllegalArgumentException(
				"Cannot retrieve item at index " + index);
		
		return items[index];
	}

}
