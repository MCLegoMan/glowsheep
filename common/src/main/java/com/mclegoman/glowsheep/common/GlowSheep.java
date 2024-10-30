/*
    Glow Sheep
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/glowsheep
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common;

import com.mclegoman.glowsheep.common.block.GlowCarpetBlock;
import com.mclegoman.glowsheep.common.block.GlowWoolVariant;
import com.mclegoman.glowsheep.common.block.GlowWoolColor;
import com.mclegoman.glowsheep.common.loot.GlowLootTables;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GlowSheep {
    public static final String modId = "glowsheep";

    public static final List<GlowWoolVariant> woolBlocks = addWoolBlocks(woolColors());

    public static void init() {
        GlowLootTables.init();
    }
    private static List<GlowWoolColor> woolColors() {
        List<GlowWoolColor> values = new ArrayList<>();
        values.add(new GlowWoolColor("white", MapColor.SNOW, DyeColor.WHITE));
        values.add(new GlowWoolColor("light_gray", MapColor.COLOR_LIGHT_GRAY, DyeColor.LIGHT_GRAY));
        values.add(new GlowWoolColor("gray", MapColor.COLOR_GRAY, DyeColor.GRAY));
        values.add(new GlowWoolColor("black", MapColor.COLOR_BLACK, DyeColor.BLACK));
        values.add(new GlowWoolColor("brown", MapColor.COLOR_BROWN, DyeColor.BROWN));
        values.add(new GlowWoolColor("red", MapColor.COLOR_RED, DyeColor.RED));
        values.add(new GlowWoolColor("orange", MapColor.COLOR_ORANGE, DyeColor.ORANGE));
        values.add(new GlowWoolColor("yellow", MapColor.COLOR_YELLOW, DyeColor.YELLOW));
        values.add(new GlowWoolColor("lime", MapColor.COLOR_LIGHT_GREEN, DyeColor.LIME));
        values.add(new GlowWoolColor("green", MapColor.COLOR_GREEN, DyeColor.GREEN));
        values.add(new GlowWoolColor("cyan", MapColor.COLOR_CYAN, DyeColor.CYAN));
        values.add(new GlowWoolColor("light_blue", MapColor.COLOR_LIGHT_BLUE, DyeColor.LIGHT_BLUE));
        values.add(new GlowWoolColor("blue", MapColor.COLOR_BLUE, DyeColor.BLUE));
        values.add(new GlowWoolColor("purple", MapColor.COLOR_PURPLE, DyeColor.PURPLE));
        values.add(new GlowWoolColor("magenta", MapColor.COLOR_MAGENTA, DyeColor.MAGENTA));
        values.add(new GlowWoolColor("pink", MapColor.COLOR_PINK, DyeColor.PINK));
        return values;
    }
    private static List<GlowWoolVariant> addWoolBlocks(List<GlowWoolColor> woolColors) {
        List<GlowWoolVariant> values = new ArrayList<>();
        woolColors.forEach((color) -> {
            Block woolBlock = Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(modId, color.color() + "_glow_wool"), new Block(BlockBehaviour.Properties.of().mapColor(color.mapColor()).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava().lightLevel((blockState) -> 7)));
            BlockItem woolItem = Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modId, color.color() + "_glow_wool"), new BlockItem(woolBlock, new Item.Properties()));

            Block carpetBlock = Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(modId, color.color() + "_glow_carpet"), new GlowCarpetBlock(color, BlockBehaviour.Properties.of()));
            BlockItem carpetItem = Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(modId, color.color() + "_glow_carpet"), new BlockItem(carpetBlock, new Item.Properties()));

            values.add(new GlowWoolVariant(color, woolBlock, woolItem, carpetBlock, carpetItem));
        });
        return values;
    }
    public static Optional<GlowWoolVariant> getWoolBlock(String color) {
        for (GlowWoolVariant variant : woolBlocks) {
            if (variant.color().color().equals(color)) return Optional.of(variant);
        }
        return Optional.empty();
    }
    public static Optional<GlowWoolVariant> getWoolBlock(DyeColor color) {
        for (GlowWoolVariant variant : woolBlocks) {
            if (variant.color().dyeColor().equals(color)) return Optional.of(variant);
        }
        return Optional.empty();
    }
}
