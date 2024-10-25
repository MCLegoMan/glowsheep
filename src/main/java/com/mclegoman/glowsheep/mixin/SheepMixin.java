/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.mixin;

import com.mclegoman.glowsheep.common.GlowSheep;
import com.mclegoman.glowsheep.common.block.GlowWoolVariant;
import com.mclegoman.glowsheep.common.entity.Glow;
import com.mclegoman.glowsheep.common.entity.GlowComponent;
import com.mclegoman.glowsheep.common.loot.GlowLootTables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheep.class)
public abstract class SheepMixin extends Animal implements Glow {
    @Shadow public abstract boolean isSheared();
    @Shadow public abstract DyeColor getColor();
    @Unique
    private GlowComponent glowsheep$glowComponent;
    @Unique
    private static final EntityDataAccessor<Boolean> glowsheep$glow;
    protected SheepMixin(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }
    @Inject(method = "<init>", at = @At("TAIL"))
    private void glowsheep$addGlowComponent(EntityType<?> entityType, Level world, CallbackInfo ci) {
        this.glowsheep$glowComponent = new GlowComponent(this.entityData, glowsheep$glow);
    }
    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void glowsheep$initDataTracker(CallbackInfo ci) {
        this.entityData.define(glowsheep$glow, false);
    }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void glowsheep$writeCustomDataToNbt(CompoundTag nbt, CallbackInfo ci) {
        this.glowsheep$glowComponent.writeNbt(nbt);
    }
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void glowsheep$readCustomDataFromNbt(CompoundTag nbt, CallbackInfo ci) {
        this.glowsheep$glowComponent.readNbt(nbt);
    }
    public boolean glowsheep$isGlow() {
        return this.glowsheep$glowComponent.isGlow();
    }
    public void glowsheep$setGlow() {
        this.glowsheep$glowComponent.setGlow(true);
        this.level().playSound(null, this, SoundEvents.GLOW_INK_SAC_USE, this.getSoundSource(), 1.0F, 1.0F);
    }
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void glowsheep$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (!this.glowsheep$isGlow()) {
            if ((hand.equals(InteractionHand.MAIN_HAND) && player.getMainHandItem().is(Items.GLOW_INK_SAC)) || (hand.equals(InteractionHand.OFF_HAND) && player.getOffhandItem().is(Items.GLOW_INK_SAC))) {
                if (hand.equals(InteractionHand.MAIN_HAND)) player.getMainHandItem().shrink(1);
                else player.getOffhandItem().shrink(1);
                glowsheep$setGlow();
                cir.setReturnValue(InteractionResult.PASS);
            }
        }
    }
    @Inject(method = "getDefaultLootTable", at = @At("HEAD"), cancellable = true)
    private void glowsheep$getDefaultLootTable(CallbackInfoReturnable<ResourceLocation> cir) {
        if (this.glowsheep$isGlow()) {
            if (!this.isSheared()) {
                ResourceLocation lootTable = null;
                switch (this.getColor()) {
                    case WHITE -> lootTable = GlowLootTables.GLOW_SHEEP_WHITE;
                    case ORANGE -> lootTable = GlowLootTables.GLOW_SHEEP_ORANGE;
                    case MAGENTA -> lootTable = GlowLootTables.GLOW_SHEEP_MAGENTA;
                    case LIGHT_BLUE -> lootTable = GlowLootTables.GLOW_SHEEP_LIGHT_BLUE;
                    case YELLOW -> lootTable = GlowLootTables.GLOW_SHEEP_YELLOW;
                    case LIME -> lootTable = GlowLootTables.GLOW_SHEEP_LIME;
                    case PINK -> lootTable = GlowLootTables.GLOW_SHEEP_PINK;
                    case GRAY -> lootTable = GlowLootTables.GLOW_SHEEP_GRAY;
                    case LIGHT_GRAY -> lootTable = GlowLootTables.GLOW_SHEEP_LIGHT_GRAY;
                    case CYAN -> lootTable = GlowLootTables.GLOW_SHEEP_CYAN;
                    case PURPLE -> lootTable = GlowLootTables.GLOW_SHEEP_PURPLE;
                    case BLUE -> lootTable = GlowLootTables.GLOW_SHEEP_BLUE;
                    case BROWN -> lootTable = GlowLootTables.GLOW_SHEEP_BROWN;
                    case GREEN -> lootTable = GlowLootTables.GLOW_SHEEP_GREEN;
                    case RED -> lootTable = GlowLootTables.GLOW_SHEEP_RED;
                    case BLACK -> lootTable = GlowLootTables.GLOW_SHEEP_BLACK;
                }
                if (lootTable != null) cir.setReturnValue(lootTable);
            }
        }
    }
    @Redirect(method = "shear", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Sheep;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/entity/item/ItemEntity;"))
    private ItemEntity shear(Sheep instance, ItemLike itemLike, int i) {
        if (this.glowsheep$isGlow()) {
            for (GlowWoolVariant block : GlowSheep.woolBlocks) {
                if (block.color().dyeColor().equals(this.getColor())) {
                    return this.spawnAtLocation(block.woolItem(), i);
                }
            }
        }
        return this.spawnAtLocation(itemLike, i);
    }
    @Inject(method = "finalizeSpawn", at = @At("TAIL"))
    private void glowsheep$finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, SpawnGroupData entityData, CompoundTag entityNbt, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (level().getRandom().nextInt(128) == 0) glowsheep$setGlow();
    }
    static {
        glowsheep$glow = SynchedEntityData.defineId(SheepMixin.class, EntityDataSerializers.BOOLEAN);
    }
}
