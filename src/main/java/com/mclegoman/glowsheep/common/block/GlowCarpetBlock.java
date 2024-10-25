/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WoolCarpetBlock;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class GlowCarpetBlock extends WoolCarpetBlock {
	public GlowCarpetBlock(GlowWoolColor color, Properties settings) {
		super(color.dyeColor(), settings.mapColor(color.mapColor()).instrument(NoteBlockInstrument.GUITAR).strength(0.1F).sound(SoundType.WOOL).ignitedByLava().lightLevel((blockState) -> 7));
	}
}
