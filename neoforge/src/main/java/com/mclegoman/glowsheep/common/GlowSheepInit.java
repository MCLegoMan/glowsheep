/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(GlowSheep.modId)
public class GlowSheepInit {
    public GlowSheepInit(IEventBus modEventBus, ModContainer modContainer) {
        GlowSheep.blocks.register(modEventBus);
        GlowSheep.items.register(modEventBus);
        GlowSheep.creativeTabs.register(modEventBus);
        GlowSheep.init();
    }
}
