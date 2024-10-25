/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;

public record GlowWoolColor(String color, MapColor mapColor, DyeColor dyeColor) {
}
