package org.horizon.plugins.eternityitems;

import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.eternityitems.item.ItemManager;
import org.horizon.plugins.eternityitems.items.DarkSword;

public final class EternityItems extends JavaPlugin {

    public ItemManager itemManager;

    // Items
    public DarkSword darkSword;

    @Override
    public void onEnable() {
        itemManager = new ItemManager(this);
        getServer().getPluginManager().registerEvents(itemManager, this);
        darkSword = new DarkSword();
        itemManager.addCustomItem(darkSword);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
