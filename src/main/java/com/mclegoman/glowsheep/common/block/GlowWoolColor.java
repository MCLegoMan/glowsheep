package com.mclegoman.glowsheep.common.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.material.MapColor;

public record GlowWoolColor(String color, MapColor mapColor, DyeColor dyeColor) {
}
