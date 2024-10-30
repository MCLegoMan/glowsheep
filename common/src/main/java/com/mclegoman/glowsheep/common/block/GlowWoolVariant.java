/*
    Glow Sheep
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/glowsheep
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public record GlowWoolVariant(GlowWoolColor color, Block woolBlock, BlockItem woolItem, Block carpetBlock, BlockItem carpetItem) {
}
