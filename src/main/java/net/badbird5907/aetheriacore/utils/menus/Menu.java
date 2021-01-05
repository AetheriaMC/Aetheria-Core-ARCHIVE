package net.badbird5907.aetheriacore.utils.menus;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.IntStream.range;
import static org.bukkit.Bukkit.createInventory;
import static org.bukkit.Material.GRAY_STAINED_GLASS_PANE;

/*
    Defines the behavior and attributes of all menus in our plugin
 */
public abstract class Menu implements InventoryHolder {

	//public values that can be accessed in the menus
	public PlayerMenuUtility playerMenuUtility;
	public Inventory inventory;
	public ItemStack FILLER_GLASS = makeItem(GRAY_STAINED_GLASS_PANE, " ");

	//Constructor for Menu. Pass in a PlayerMenuUtility so that
	// we have information on who's menu this is and
	// what info is to be transfered
	public Menu(PlayerMenuUtility playerMenuUtility) {
		this.playerMenuUtility = playerMenuUtility;
	}

	//let each menu decide their name
	public abstract String getMenuName();

	//let each menu decide their slot amount
	public abstract int getSlots();

	//let each menu decide how the items in the menu will be handled when clicked
	public abstract void handleMenu(InventoryClickEvent e);

	//let each menu decide what items are to be placed in the inventory menu
	public abstract void setMenuItems();

	//When called, an inventory is created and opened for the player
	public void open() {
		//The owner of the inventory created is the Menu itself,
		// so we are able to reverse engineer the Menu object from the
		// inventoryHolder in the MenuListener class when handling clicks
		inventory = createInventory(this, getSlots(), getMenuName());
		//grab all the items specified to be used for this menu and add to inventory
		this.setMenuItems();
		//open the inventory for the player
		playerMenuUtility.getOwner().openInventory(inventory);
	}

	//Overridden method from the InventoryHolder interface
	@Override
	public Inventory getInventory() {
		return inventory;
	}

	//Helpful utility method to fill all remaining slots with "filler glass"
	public void setFillerGlass() {
		range(0, getSlots()).filter(i -> inventory.getItem(i) == null).forEach(i -> inventory.setItem(i, FILLER_GLASS));
	}

	public ItemStack makeItem(Material material, String displayName, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		requireNonNull(itemMeta).setDisplayName(displayName);
		itemMeta.setLore(asList(lore));
		item.setItemMeta(itemMeta);
		return item;
	}
}