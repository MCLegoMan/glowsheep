/*
    Glow Sheep
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/glowsheep
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class GlowSheepInit implements ModInitializer {
    public static final CreativeModeTab creativeTab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(GlowSheep.modId, GlowSheep.modId), FabricItemGroup.builder()
            .icon(() -> new ItemStack(GlowSheep.woolBlocks.getFirst().woolItem()))
            .title(Component.translatable("itemGroup." + GlowSheep.modId))
            .displayItems((context, entries) -> GlowSheep.woolBlocks.forEach((block) -> {
                entries.accept(block.woolBlock());
                entries.accept(block.carpetBlock());
            })).build());
    public void onInitialize()  {
        GlowSheep.init();
    }
}
