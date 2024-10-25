package com.mclegoman.glowsheep.common.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public record GlowWoolBlock(DeferredBlock<Block> block, DeferredItem<BlockItem> item, GlowWoolColor color) {
}
