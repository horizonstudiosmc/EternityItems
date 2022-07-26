package org.horizon.plugins.eternityitems.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.horizon.plugins.eternityitems.EternityItems;
import org.horizon.plugins.eternityitems.item.CustomItem;
import org.horizon.plugins.eternityitems.utils.CICT;

import java.util.ArrayList;
import java.util.List;

public class DarkSword extends CustomItem {

    JavaPlugin plugin;


    @Override
    public void handleLeftClick(CICT type, PlayerInteractEvent event) {

    }

    @Override
    public void handleRightClick(CICT type, PlayerInteractEvent event) {

    }

    @Override
    public void handleAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            victim.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 10, 1));
        }
        Player victim = (Player) event.getDamager();
        victim.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 10, 1));
    }

    @Override
    public void init(EternityItems plugin) {
        key = "dark_sword";
        this.plugin = plugin;
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "The souls of the damned.");
        lore.add(ChatColor.BLACK + "Gather into the blade of darkness");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.BLACK + "The Blade Of Darkness");
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("dark_sword_speed", 5.0, AttributeModifier.Operation.ADD_NUMBER));
        plugin.itemManager.generateDataContainer(key, meta.getPersistentDataContainer());
        item.setItemMeta(meta);
        NamespacedKey namespacedKey = NamespacedKey.fromString(key + "_rec", plugin);
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey, item);

        recipe.shape("OOO", "ONO", "OOO");

        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setIngredient('N', Material.NETHERITE_SWORD);

        Bukkit.addRecipe(recipe);
    }
}
