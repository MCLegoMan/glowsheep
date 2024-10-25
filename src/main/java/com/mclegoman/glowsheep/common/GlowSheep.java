/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common;

import com.mclegoman.glowsheep.common.block.GlowWoolVariant;
import com.mclegoman.glowsheep.common.block.GlowWoolColor;
import com.mclegoman.glowsheep.common.loot.GlowLootTables;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mod(GlowSheep.modId)
public class GlowSheep {
    public static final String modId = "glowsheep";
    public static final DeferredRegister.Blocks blocks = DeferredRegister.createBlocks(modId);
    public static final DeferredRegister.Items items = DeferredRegister.createItems(modId);
    public static final DeferredRegister<CreativeModeTab> creativeTabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modId);

    public static final List<GlowWoolVariant> woolBlocks = addWoolBlocks(woolColors());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> creativeTab = creativeTabs.register(modId, () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + modId))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> woolBlocks.getFirst().woolItem().get().getDefaultInstance())
            .displayItems((parameters, output) -> woolBlocks.forEach((block) -> {
                output.accept(block.woolItem().get());
                output.accept(block.carpetItem().get());
                })).build());

    public GlowSheep(IEventBus modEventBus, ModContainer modContainer) {
        blocks.register(modEventBus);
        items.register(modEventBus);
        creativeTabs.register(modEventBus);
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
            DeferredBlock<Block> woolBlock = blocks.registerSimpleBlock(color.color() + "_glow_wool", BlockBehaviour.Properties.of().mapColor(color.mapColor()).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).ignitedByLava().lightLevel((blockState) -> 7));
            DeferredItem<BlockItem> woolItem = items.registerSimpleBlockItem(color.color() + "_glow_wool", woolBlock);

            DeferredBlock<Block> carpetBlock = blocks.registerBlock(color.color() + "_glow_carpet", (properties) -> new WoolCarpetBlock(color.dyeColor(), properties), BlockBehaviour.Properties.of().mapColor(color.mapColor()).instrument(NoteBlockInstrument.GUITAR).strength(0.1F).sound(SoundType.WOOL).ignitedByLava().lightLevel((blockState) -> 7));
            DeferredItem<BlockItem> carpetItem = items.registerSimpleBlockItem(color.color() + "_glow_carpet", carpetBlock);

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
