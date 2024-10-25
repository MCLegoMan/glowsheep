/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public record GlowWoolVariant(GlowWoolColor color, Block woolBlock, BlockItem woolItem, Block carpetBlock, BlockItem carpetItem) {
}
