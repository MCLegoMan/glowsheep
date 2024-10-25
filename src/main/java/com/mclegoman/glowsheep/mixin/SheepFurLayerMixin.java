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
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SheepFurLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
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
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/animal/Sheep;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/SheepFurLayer;coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/client/model/EntityModel;Lnet/minecraft/resources/ResourceLocation;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFFI)V", shift = At.Shift.AFTER))
    private void glowsheep$render(PoseStack p_117421_, MultiBufferSource p_117422_, int p_117423_, Sheep sheep, float p_117425_, float p_117426_, float p_117427_, float p_117428_, float p_117429_, float p_117430_, CallbackInfo ci) {
        if (((Glow)sheep).glowsheep$isGlow()) {
            int color;
            if (sheep.hasCustomName() && "jeb_".equals(sheep.getName().getString())) {
                int k = sheep.tickCount / 25 + sheep.getId();
                int l = DyeColor.values().length;
                int i1 = k % l;
                int j1 = (k + 1) % l;
                float f = ((float)(sheep.tickCount % 25) + p_117427_) / 25.0F;
                int k1 = Sheep.getColor(DyeColor.byId(i1));
                int l1 = Sheep.getColor(DyeColor.byId(j1));
                color = FastColor.ARGB32.lerp(f, k1, l1);
            } else {
                color = Sheep.getColor(sheep.getColor());
            }
            if (!sheep.isInvisible()) {
                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(sheep, p_117425_, p_117426_, p_117427_);
                this.model.setupAnim(sheep, p_117425_, p_117426_, p_117428_, p_117429_, p_117430_);
                VertexConsumer vertexconsumer = p_117422_.getBuffer(RenderType.EYES.apply(SHEEP_FUR_LOCATION, RenderStateShard.NO_TRANSPARENCY));
                this.model.renderToBuffer(p_117421_, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, color);
            }
        }
    }
}
