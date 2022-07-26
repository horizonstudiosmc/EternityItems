package org.horizon.plugins.eternityitems.utils;

import org.bukkit.event.block.Action;

public class TranslationTools {

    public static CICT actionToCICT(Action action) {
        switch (action) {
            case LEFT_CLICK_AIR:
                return CICT.AIR;
            case LEFT_CLICK_BLOCK:
                return CICT.BLOCK;
            case RIGHT_CLICK_AIR:
                return CICT.AIR;
            case RIGHT_CLICK_BLOCK:
                return CICT.BLOCK;
        }
        return null;
    }

}
