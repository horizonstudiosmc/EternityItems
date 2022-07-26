package org.horizon.plugins.eternityitems.item;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.eternityitems.EternityItems;
import org.horizon.plugins.eternityitems.utils.TranslationTools;

import java.util.HashMap;
import java.util.Map;

public class ItemManager implements Listener {
    Map<NamespacedKey, CustomItem> customItems = new HashMap<>();
    EternityItems mainPlugin;

    public ItemManager(EternityItems plugin) {
        this.mainPlugin = plugin;
    }

    public CustomItem getCustomItem(NamespacedKey key) {
        return customItems.get(key);
    }

    public NamespacedKey addCustomItem(CustomItem item) {
        NamespacedKey namespacedKey = new NamespacedKey(mainPlugin, item.key);
        customItems.put(namespacedKey, item);
        item.init(mainPlugin);
        return namespacedKey;
    }

    public CustomItem removeCustomItem(NamespacedKey key) {
        CustomItem result = customItems.remove(key);
        return result;
    }

    public PersistentDataContainer generateDataContainer(String id, PersistentDataContainer container) {
        NamespacedKey key = NamespacedKey.fromString("custom_item", mainPlugin);
        container.set(key, PersistentDataType.STRING, id);
        return container;
    }


    public CustomItem getItemFromDataContainer(ItemMeta itemMeta) {
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        NamespacedKey nk = NamespacedKey.fromString("custom_item", mainPlugin);
        if (!(container.get(nk, PersistentDataType.STRING) == null)) {
            return getCustomItem(NamespacedKey.fromString(container.get(nk, PersistentDataType.STRING), mainPlugin));
        }
        return null;
    }

    // Events
    @EventHandler
    public void playerClick(PlayerInteractEvent event) {
        // Left Click
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            CustomItem item  = getItemFromDataContainer(event.getItem().getItemMeta());
            if (!(item == null)) {
                item.handleLeftClick(TranslationTools.actionToCICT(event.getAction()), event);
            }
        }

        // Right Click
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            CustomItem item  = getItemFromDataContainer(event.getItem().getItemMeta());
            if (!(item == null)) {
                item.handleRightClick(TranslationTools.actionToCICT(event.getAction()), event);
            }
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            CustomItem item  = getItemFromDataContainer(((Player) event.getDamager()).getInventory().getItemInMainHand().getItemMeta());
            if (!(item == null)) {
                item.handleAttack(event);
            }
        }
    }
}
