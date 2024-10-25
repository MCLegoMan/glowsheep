package com.mclegoman.glowsheep.common.loot;

import com.mclegoman.glowsheep.common.GlowSheep;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class GlowLootTables {
	public static final ResourceKey<LootTable> GLOW_SHEEP_WHITE;
	public static final ResourceKey<LootTable> GLOW_SHEEP_ORANGE;
	public static final ResourceKey<LootTable> GLOW_SHEEP_MAGENTA;
	public static final ResourceKey<LootTable> GLOW_SHEEP_LIGHT_BLUE;
	public static final ResourceKey<LootTable> GLOW_SHEEP_YELLOW;
	public static final ResourceKey<LootTable> GLOW_SHEEP_LIME;
	public static final ResourceKey<LootTable> GLOW_SHEEP_PINK;
	public static final ResourceKey<LootTable> GLOW_SHEEP_GRAY;
	public static final ResourceKey<LootTable> GLOW_SHEEP_LIGHT_GRAY;
	public static final ResourceKey<LootTable> GLOW_SHEEP_CYAN;
	public static final ResourceKey<LootTable> GLOW_SHEEP_PURPLE;
	public static final ResourceKey<LootTable> GLOW_SHEEP_BLUE;
	public static final ResourceKey<LootTable> GLOW_SHEEP_BROWN;
	public static final ResourceKey<LootTable> GLOW_SHEEP_GREEN;
	public static final ResourceKey<LootTable> GLOW_SHEEP_RED;
	public static final ResourceKey<LootTable> GLOW_SHEEP_BLACK;
	public static void init() {}
	private static ResourceKey<LootTable> register(String key) {
		return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(GlowSheep.modId, key));
	}
	static {
		GLOW_SHEEP_WHITE = register("entities/sheep/glow_white");
		GLOW_SHEEP_ORANGE = register("entities/sheep/glow_orange");
		GLOW_SHEEP_MAGENTA = register("entities/sheep/glow_magenta");
		GLOW_SHEEP_LIGHT_BLUE = register("entities/sheep/glow_light_blue");
		GLOW_SHEEP_YELLOW = register("entities/sheep/glow_yellow");
		GLOW_SHEEP_LIME = register("entities/sheep/glow_lime");
		GLOW_SHEEP_PINK = register("entities/sheep/glow_pink");
		GLOW_SHEEP_GRAY = register("entities/sheep/glow_gray");
		GLOW_SHEEP_LIGHT_GRAY = register("entities/sheep/glow_light_gray");
		GLOW_SHEEP_CYAN = register("entities/sheep/glow_cyan");
		GLOW_SHEEP_PURPLE = register("entities/sheep/glow_purple");
		GLOW_SHEEP_BLUE = register("entities/sheep/glow_blue");
		GLOW_SHEEP_BROWN = register("entities/sheep/glow_brown");
		GLOW_SHEEP_GREEN = register("entities/sheep/glow_green");
		GLOW_SHEEP_RED = register("entities/sheep/glow_red");
		GLOW_SHEEP_BLACK = register("entities/sheep/glow_black");
	}
}
