package com.mclegoman.glowsheep.common.block;

import com.mclegoman.glowsheep.common.GlowSheep;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class GlowBlockTags {
	public static final TagKey<Block> glowFrom = create("glow_from");
	public static final TagKey<Block> wool = glow("wool");
	public static final TagKey<Block> whiteWool = glow("white_wool");
	public static final TagKey<Block> lightGrayWool = glow("light_gray_wool");
	public static final TagKey<Block> grayWool = glow("gray_wool");
	public static final TagKey<Block> blackWool = glow("black_wool");
	public static final TagKey<Block> brownWool = glow("brown_wool");
	public static final TagKey<Block> redWool = glow("red_wool");
	public static final TagKey<Block> orangeWool = glow("orange_wool");
	public static final TagKey<Block> yellowWool = glow("yellow_wool");
	public static final TagKey<Block> limeWool = glow("lime_wool");
	public static final TagKey<Block> greenWool = glow("green_wool");
	public static final TagKey<Block> cyanWool = glow("cyan_wool");
	public static final TagKey<Block> lightBlueWool = glow("light_blue_wool");
	public static final TagKey<Block> blueWool = glow("blue_wool");
	public static final TagKey<Block> purpleWool = glow("purple_wool");
	public static final TagKey<Block> magentaWool = glow("magenta_wool");
	public static final TagKey<Block> pinkWool = glow("pink_wool");
	public static final TagKey<Block> carpet = glow("carpet");
	public static final TagKey<Block> whiteCarpet = glow("white_carpet");
	public static final TagKey<Block> lightGrayCarpet = glow("light_gray_carpet");
	public static final TagKey<Block> grayCarpet = glow("gray_carpet");
	public static final TagKey<Block> blackCarpet = glow("black_carpet");
	public static final TagKey<Block> brownCarpet = glow("brown_carpet");
	public static final TagKey<Block> redCarpet = glow("red_carpet");
	public static final TagKey<Block> orangeCarpet = glow("orange_carpet");
	public static final TagKey<Block> yellowCarpet = glow("yellow_carpet");
	public static final TagKey<Block> limeCarpet = glow("lime_carpet");
	public static final TagKey<Block> greenCarpet = glow("green_carpet");
	public static final TagKey<Block> cyanCarpet = glow("cyan_carpet");
	public static final TagKey<Block> lightBlueCarpet = glow("light_blue_carpet");
	public static final TagKey<Block> blueCarpet = glow("blue_carpet");
	public static final TagKey<Block> purpleCarpet = glow("purple_carpet");
	public static final TagKey<Block> magentaCarpet = glow("magenta_carpet");
	public static final TagKey<Block> pinkCarpet = glow("pink_carpet");
	private static TagKey<Block> glow(String key) {
		return create(GlowSheep.modId, "glow_from_" + key);
	}
	private static TagKey<Block> create(String key) {
		return create(GlowSheep.modId, key);
	}
	private static TagKey<Block> create(String namespace, String key) {
		return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, key));
	}
}
