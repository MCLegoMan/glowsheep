/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.mixin;

import com.mclegoman.glowsheep.common.GlowSheep;
import com.mclegoman.glowsheep.common.tags.GlowBlockTags;
import com.mclegoman.glowsheep.common.block.GlowWoolVariant;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;

@Mixin(GlowInkSacItem.class)
public abstract class GlowInkSacItemMixin extends Item {
    public GlowInkSacItemMixin(Properties p_41383_) {
        super(p_41383_);
    }
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        Optional<BlockState> blockState = this.glowsheep$evaluateNewBlockState(world, blockPos, player, world.getBlockState(blockPos));
        if (blockState.isEmpty()) return super.useOn(context);
        else {
            ItemStack itemstack = context.getItemInHand();
            if (player instanceof ServerPlayer) CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockPos, itemstack);
            world.setBlock(blockPos, blockState.get(), 11);
            world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState.get()));
            if (player != null) itemstack.shrink(1);
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
    }
    @Unique
    private Optional<BlockState> glowsheep$evaluateNewBlockState(Level world, BlockPos blockPos, @Nullable Player player, BlockState blockState) {
        if (!blockState.isAir()) {
            if (blockState.is(GlowBlockTags.glowFrom)) {
                Optional<String> color = Optional.empty();
                if (blockState.is(GlowBlockTags.wool)) {
                    if (blockState.is(GlowBlockTags.whiteWool)) color = Optional.of("white");
                    else if (blockState.is(GlowBlockTags.lightGrayWool)) color = Optional.of("light_gray");
                    else if (blockState.is(GlowBlockTags.grayWool)) color = Optional.of("gray");
                    else if (blockState.is(GlowBlockTags.blackWool)) color = Optional.of("black");
                    else if (blockState.is(GlowBlockTags.brownWool)) color = Optional.of("brown");
                    else if (blockState.is(GlowBlockTags.redWool)) color = Optional.of("red");
                    else if (blockState.is(GlowBlockTags.orangeWool)) color = Optional.of("orange");
                    else if (blockState.is(GlowBlockTags.yellowWool)) color = Optional.of("yellow");
                    else if (blockState.is(GlowBlockTags.limeWool)) color = Optional.of("lime");
                    else if (blockState.is(GlowBlockTags.greenWool)) color = Optional.of("green");
                    else if (blockState.is(GlowBlockTags.cyanWool)) color = Optional.of("cyan");
                    else if (blockState.is(GlowBlockTags.lightBlueWool)) color = Optional.of("light_blue");
                    else if (blockState.is(GlowBlockTags.blueWool)) color = Optional.of("blue");
                    else if (blockState.is(GlowBlockTags.purpleWool)) color = Optional.of("purple");
                    else if (blockState.is(GlowBlockTags.magentaWool)) color = Optional.of("magenta");
                    else if (blockState.is(GlowBlockTags.pinkWool)) color = Optional.of("pink");
                    if (color.isPresent()) return glowsheep$getGlowBlock(color.get(), false, world, player, blockPos);
                }
                else if (blockState.is(GlowBlockTags.carpet)) {
                    if (blockState.is(GlowBlockTags.whiteCarpet)) color = Optional.of("white");
                    else if (blockState.is(GlowBlockTags.lightGrayCarpet)) color = Optional.of("light_gray");
                    else if (blockState.is(GlowBlockTags.grayCarpet)) color = Optional.of("gray");
                    else if (blockState.is(GlowBlockTags.blackCarpet)) color = Optional.of("black");
                    else if (blockState.is(GlowBlockTags.brownCarpet)) color = Optional.of("brown");
                    else if (blockState.is(GlowBlockTags.redCarpet)) color = Optional.of("red");
                    else if (blockState.is(GlowBlockTags.orangeCarpet)) color = Optional.of("orange");
                    else if (blockState.is(GlowBlockTags.yellowCarpet)) color = Optional.of("yellow");
                    else if (blockState.is(GlowBlockTags.limeCarpet)) color = Optional.of("lime");
                    else if (blockState.is(GlowBlockTags.greenCarpet)) color = Optional.of("green");
                    else if (blockState.is(GlowBlockTags.cyanCarpet)) color = Optional.of("cyan");
                    else if (blockState.is(GlowBlockTags.lightBlueCarpet)) color = Optional.of("light_blue");
                    else if (blockState.is(GlowBlockTags.blueCarpet)) color = Optional.of("blue");
                    else if (blockState.is(GlowBlockTags.purpleCarpet)) color = Optional.of("purple");
                    else if (blockState.is(GlowBlockTags.magentaCarpet)) color = Optional.of("magenta");
                    else if (blockState.is(GlowBlockTags.pinkCarpet)) color = Optional.of("pink");
                    if (color.isPresent()) return glowsheep$getGlowBlock(color.get(), true, world, player, blockPos);
                }
            }
        }
        return Optional.empty();
    }
    @Unique
    private Optional<BlockState> glowsheep$getGlowBlock(String color, boolean carpet, Level world, @Nullable Player player, BlockPos blockPos) {
        Optional<GlowWoolVariant> variant = GlowSheep.getWoolBlock(color);
        if (variant.isPresent()) {
            world.playSound(player, blockPos, SoundEvents.GLOW_INK_SAC_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (carpet) return Optional.of(variant.get().carpetBlock().defaultBlockState());
            else return Optional.of(variant.get().woolBlock().defaultBlockState());
        }
        return Optional.empty();
    }
}
