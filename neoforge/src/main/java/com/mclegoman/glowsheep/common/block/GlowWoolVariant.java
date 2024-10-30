/*
    Glow Sheep
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/glowsheep
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public record GlowWoolVariant(GlowWoolColor color, DeferredBlock<Block> woolBlock, DeferredItem<BlockItem> woolItem, DeferredBlock<Block> carpetBlock, DeferredItem<BlockItem> carpetItem) {
}
