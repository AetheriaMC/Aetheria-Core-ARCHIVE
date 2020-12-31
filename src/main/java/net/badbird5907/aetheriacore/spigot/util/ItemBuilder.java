package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.Collections.addAll;
import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.util.XMaterial.*;
import static org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES;

/**
 * @deprecated
 */
public class ItemBuilder {
	private final ItemStack item;
	private final ItemMeta itemM;

	/**
	 * Init item chainable via given Material parameter.
	 *
	 * @param itemType the {@link Material} to initiate the instance with.
	 * @since 1.0
	 */
	public ItemBuilder(final Material itemType) {
		item = new ItemStack(itemType);
		itemM = item.getItemMeta();
	}

	/**
	 * Init item chainable via given ItemStack parameter.
	 *
	 * @param itemStack the {@link ItemStack} to initialize the instance with.
	 * @since 1.0
	 */
	public ItemBuilder(final ItemStack itemStack) {
		item = itemStack;
		itemM = item.getItemMeta();
	}

	/**
	 * Init the item chainable with no defined Material/ItemStack
	 *
	 * @since 1.0
	 */
	public ItemBuilder() {
		item = new ItemStack(Material.AIR);
		itemM = item.getItemMeta();
	}

	/**
	 * Changes the Material type of the {@link ItemStack}
	 *
	 * @param material the new {@link Material} to set for the ItemStack.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder type(final Material material) {
		build().setType(material);
		return this;
	}

	/**
	 * Changes the Material type of the {@link ItemStack}
	 *
	 * @param material the new {@link Material} to set for the ItemStack.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder material(final Material material) {
		build().setType(material);
		return this;
	}

	/**
	 * Changes the {@link ItemStack}s size.
	 *
	 * @param itemAmt the new Integer count of the ItemStack.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder amount(final Integer itemAmt) {
		build().setAmount(itemAmt);
		return this;
	}

	/**
	 * Changes the {@link ItemStack}s display name.
	 *
	 * @param name the new String for the ItemStack's display name to be set to.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder name(final String name) {
		meta().setDisplayName(name);
		build().setItemMeta(meta());
		return this;
	}

	/**
	 * Adds a line of lore to the {@link ItemStack}
	 *
	 * @param lore String you want to add to the ItemStack's lore.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder lore(final String lore) {
		List<String> lores = meta().getLore();
		if (lores == null) lores = new ArrayList<>();
		lores.add(lore);
		meta().setLore(lores);
		build().setItemMeta(meta());
		return this;
	}

	/**
	 * Clears the {@link ItemStack}s lore and replaces it with the defined String array.
	 *
	 * @param lores String array you want to set the ItemStack's lore to.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder lores(final String[] lores) {
		List<String> loresList = meta().getLore();
		if (loresList == null) loresList = new ArrayList<>();
		else loresList.clear();
		addAll(loresList, lores);
		meta().setLore(loresList);
		return this;
	}

	public ItemBuilder lores(final List lores) {
		addAll(lores);
		meta().setLore(lores);
		return this;
	}

	/**
	 * Changes the durability of the current {@link ItemStack}
	 *
	 * @param durability the new int amount to set the ItemStack's durability to.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder durability(final int durability) {
		build().setDurability((short) durability);
		return this;
	}

	/**
	 * Changes the data value of the {@link ItemStack}
	 *
	 * @param data the new int data value (parsed as byte) to set the ItemStack's durability to.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	@SuppressWarnings("deprecation")
	public ItemBuilder data(final int data) {
		build().setData(new MaterialData(build().getType(), (byte) data));
		return this;
	}

	/**
	 * Adds and UnsafeEnchantment to the {@link ItemStack} with a defined level int value.
	 *
	 * @param enchantment the {@link Enchantment} to add to the ItemStack.
	 * @param level       the int amount that the Enchantment's level will be set to.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder enchantment(final Enchantment enchantment, final int level) {
		build().addUnsafeEnchantment(enchantment, level);
		return this;
	}

	/**
	 * Adds and UnsafeEnchantment to the {@Link} with a level int value of 1.
	 *
	 * @param enchantment the {@link Enchantment} to add to the ItemStack.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder enchantment(final Enchantment enchantment) {
		build().addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	/**
	 * Clears all {@link Enchantment}s from the current {@link ItemStack} then adds the defined array of Enchantments to the ItemStack.
	 *
	 * @param enchantments the Enchantment array to replace any current enchantments applied on the ItemStack.
	 * @param level        the int level value for all Enchantments to be set to.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder enchantments(final Enchantment[] enchantments, final int level) {
		build().getEnchantments().clear();
		stream(enchantments).forEach(enchantment -> build().addUnsafeEnchantment(enchantment, level));
		return this;
	}

	/**
	 * Clears all {@link Enchantment}s from the current {@link ItemStack} then adds the defined array of Enchantments to the ItemStack with a level int value of 1.
	 *
	 * @param enchantments the Enchantment array to replace any current enchantments applied on the ItemStack.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder enchantments(final Enchantment[] enchantments) {
		build().getEnchantments().clear();
		stream(enchantments).forEach(enchantment -> build().addUnsafeEnchantment(enchantment, 1));
		return this;
	}

	/**
	 * Clears the defined {@link Enchantment} from the {@link ItemStack}
	 *
	 * @param enchantment the Enchantment to remove from the ItemStack.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder clearEnchantment(final Enchantment enchantment) {
		Map<Enchantment, Integer> itemEnchantments = build().getEnchantments();
		itemEnchantments.keySet().stream().filter(enchantmentC -> enchantment == enchantmentC).forEach(itemEnchantments::remove);
		return this;
	}

	/**
	 * Clears all {@link Enchantment}s from the {@link ItemStack}
	 *
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder clearEnchantments() {
		build().getEnchantments().clear();
		return this;
	}

	/**
	 * Clears the defined {@link String} of lore from the {@link ItemStack}
	 *
	 * @param lore the String to be removed from the ItemStack.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder clearLore(final String lore) {
		requireNonNull(meta().getLore()).remove(lore);
		build().setItemMeta(meta());
		return this;
	}

	/**
	 * Clears all lore {@link String}s from the {@link ItemStack}
	 *
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder clearLores() {
		requireNonNull(meta().getLore()).clear();
		build().setItemMeta(meta());
		return this;
	}

	/**
	 * Sets the {@link Color} of any LEATHER_ARMOR {@link Material} types of the {@link ItemStack}
	 *
	 * @param color the Color to set the LEATHER_ARMOR ItemStack to.
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder color(final Color color) {
		if (build().getType() == Material.LEATHER_HELMET || build().getType() == Material.LEATHER_CHESTPLATE || build().getType() == Material.LEATHER_LEGGINGS || build().getType() == Material.LEATHER_BOOTS) {
			LeatherArmorMeta meta = (LeatherArmorMeta) meta();
			meta.setColor(color);
			build().setItemMeta(meta);
		}
		return this;
	}

	/**
	 * Clears the {@link Color} of any LEATHER_ARMOR {@link Material} types of the {@link ItemStack}
	 *
	 * @return the current instance for chainable application.
	 * @since 1.0
	 */
	public ItemBuilder clearColor() {
		if ((build().getType() == LEATHER_HELMET.parseMaterial()) || (build().getType() == LEATHER_CHESTPLATE.parseMaterial()) || (build().getType() == LEATHER_LEGGINGS.parseMaterial()) || (build().getType() == LEATHER_BOOTS.parseMaterial())) {
			LeatherArmorMeta meta = (LeatherArmorMeta) meta();
			meta.setColor(null);
			build().setItemMeta(meta);
		}
		return this;
	}

	/**
	 * Sets the skullOwner {@link SkullMeta} of the current SKULL_ITEM {@link Material} type {@link ItemStack}
	 *
	 * @param name the {@link String} value to set the SkullOwner meta to for the SKULL_ITEM Material type ItemStack.
	 * @return the current instance for chainable application
	 * @since 1.0
	 */
	public ItemBuilder skullOwner(final String name) {
		if ((build().getType() == PLAYER_HEAD.parseMaterial()) && (build().getDurability() == (byte) 3)) {
			SkullMeta skullMeta = (SkullMeta) meta();
			skullMeta.setOwner(name);
			build().setItemMeta(meta());
		}
		return this;
	}

	/**
	 * Returns the {@link ItemMeta} of the {@link ItemStack}
	 *
	 * @return the current instance for chainable application
	 */
	public ItemBuilder hideAttributes() {
		requireNonNull(build().getItemMeta()).addItemFlags(HIDE_ATTRIBUTES);
		return this;
	}

	/**
	 * @param iflag sets the {@link ItemFlag} if the item
	 *              Returns the {@link ItemMeta} of the {@link ItemStack}
	 * @return the current instance for chainable application
	 */
	public ItemBuilder setAttributes(ItemFlag iflag) {
		requireNonNull(build().getItemMeta()).addItemFlags(iflag);
		return this;
	}

	/**
	 * Returns the {@link ItemMeta} of the {@link ItemStack}
	 *
	 * @return the ItemMeta of the ItemStack.
	 */
	public ItemMeta meta() {
		return itemM;
	}

	/**
	 * Returns the {@link ItemStack} of the {@link ItemBuilder} instance.
	 *
	 * @return the ItemStack of the ItemstackBuilder instance.
	 */
	public ItemStack build() {
		return item;
	}
}