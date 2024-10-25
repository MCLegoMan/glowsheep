/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.mixin;

import com.mclegoman.glowsheep.common.entity.Glow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SheepFurLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepFurLayer.class)
public abstract class SheepFurLayerMixin extends RenderLayer<Sheep, SheepModel<Sheep>> {
    @Shadow @Final private static ResourceLocation SHEEP_FUR_LOCATION;
    @Shadow @Final private SheepFurModel<Sheep> model;
    public SheepFurLayerMixin(RenderLayerParent<Sheep, SheepModel<Sheep>> renderLayer) {
        super(renderLayer);
    }
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/animal/Sheep;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/SheepFurLayer;coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/resources/ResourceLocation;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFFFFF)V", shift = At.Shift.AFTER))
    private void glowsheep$render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Sheep sheep, float limbAngle, float limbDistance, float tickDelta, float age, float headYaw, float headPitch, CallbackInfo ci) {
        if (((Glow)sheep).glowsheep$isGlow()) {
            float red;
            float green;
            float blue;
            if (sheep.hasCustomName() && "jeb_".equals(sheep.getName().getString())) {
                int n = sheep.tickCount / 25 + sheep.getId();
                int o = DyeColor.values().length;
                int p = n % o;
                int q = (n + 1) % o;
                float r = ((float)(sheep.tickCount % 25) + tickDelta) / 25.0F;
                float[] fs = Sheep.getColorArray(DyeColor.byId(p));
                float[] gs = Sheep.getColorArray(DyeColor.byId(q));
                red = fs[0] * (1.0F - r) + gs[0] * r;
                green = fs[1] * (1.0F - r) + gs[1] * r;
                blue = fs[2] * (1.0F - r) + gs[2] * r;
            } else {
                float[] hs = Sheep.getColorArray(sheep.getColor());
                red = hs[0];
                green = hs[1];
                blue = hs[2];
            }
            if (!sheep.isInvisible()) {
                this.getParentModel().copyPropertiesTo(model);
                model.prepareMobModel(sheep, limbAngle, limbDistance, tickDelta);
                model.setupAnim(sheep, limbAngle, limbDistance, age, headYaw, headPitch);
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.eyes(SHEEP_FUR_LOCATION));
                model.renderToBuffer(poseStack, vertexConsumer, 15728640, LivingEntityRenderer.getOverlayCoords(sheep, 0.0F), red, green, blue, 1.0F);
            }
        }
    }
}
