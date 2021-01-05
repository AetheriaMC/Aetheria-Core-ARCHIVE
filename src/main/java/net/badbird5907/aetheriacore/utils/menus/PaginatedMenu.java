package net.badbird5907.aetheriacore.utils.menus;

import java.util.stream.IntStream;

import static org.bukkit.ChatColor.DARK_RED;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.Material.BARRIER;
import static org.bukkit.Material.DARK_OAK_BUTTON;

/*
A class extending the functionality of the regular Menu, but making it Paginated
This pagination system was made from Jer's code sample. <3
 */
public abstract class PaginatedMenu extends Menu {

	//Keep track of what page the menu is on
	public int page = 0;
	//28 is max items because with the border set below,
	//28 empty slots are remaining.
	public int maxItemsPerPage = 28;
	//the index represents the index of the slot
	//that the loop is on
	protected int index = 0;

	public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
	}

	//Set the border and menu buttons for the menu
	public void addMenuBorder() {
		inventory.setItem(48, makeItem(DARK_OAK_BUTTON, GREEN + "Left"));
		inventory.setItem(49, makeItem(BARRIER, DARK_RED + "Close"));
		inventory.setItem(50, makeItem(DARK_OAK_BUTTON, GREEN + "Right"));

		IntStream.range(0, 10).filter(i -> inventory.getItem(i) == null).forEach(i -> inventory.setItem(i, super.FILLER_GLASS));
		inventory.setItem(17, super.FILLER_GLASS);
		inventory.setItem(18, super.FILLER_GLASS);
		inventory.setItem(26, super.FILLER_GLASS);
		inventory.setItem(27, super.FILLER_GLASS);
		inventory.setItem(35, super.FILLER_GLASS);
		inventory.setItem(36, super.FILLER_GLASS);
		IntStream.range(44, 54).filter(i -> inventory.getItem(i) == null).forEach(i -> inventory.setItem(i, super.FILLER_GLASS));
	}

	public int getMaxItemsPerPage() {
		return maxItemsPerPage;
	}
}