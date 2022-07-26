package org.horizon.plugins.eternityitems.item;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.eternityitems.EternityItems;
import org.horizon.plugins.eternityitems.utils.CICT;

public abstract class CustomItem {
    
    public String key = "custom_item_null";

    public abstract void handleLeftClick(CICT type, PlayerInteractEvent event);

    public abstract void handleRightClick(CICT type, PlayerInteractEvent event);

    public abstract void handleAttack(EntityDamageByEntityEvent event);

    public abstract void init(EternityItems plugin);

}
