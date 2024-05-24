package dev.hicksm.minecraft.plugin.mscraftadventures.sylladex.fetch_modus;

import java.util.Arrays;

import org.bukkit.entity.Item;

/**
 * Abstract parent of all Fetch Modi. This is used so that Fetch Modi are 
 * easily interchangable while encapsulating their held items.
 */
public abstract class FetchModus {
	
	/** Items contained in FetchModus. */
	private Item[] items;
	
	/** Create empty fetch modus with 5 slots. */
	public FetchModus() { this(5); }
	
	/** Create empty fetch modus with capacity cards. */
	public FetchModus(int capacity) { items = new Item[capacity]; }
	
	/** Create fetch modus with copied contents from other. */
	public FetchModus(FetchModus other) {
		this.items = Arrays.copyOf(other.items, other.items.length);
	}
	
	/** Read all items from fetch modus. */
	public Item[] readItems() { return Arrays.copyOf(items, items.length); }
	
	public abstract void addItem(Item item);
	
	public abstract boolean getIndexRetrievable(int index);
	
	public abstract Item retrieveItem(int index) 
			throws IllegalArgumentException;

}
