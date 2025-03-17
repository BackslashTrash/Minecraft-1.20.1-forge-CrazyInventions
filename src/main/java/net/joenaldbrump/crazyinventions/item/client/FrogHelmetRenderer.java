package net.joenaldbrump.crazyinventions.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.item.custom.FrogHelmet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FrogHelmetRenderer extends GeoArmorRenderer<FrogHelmet> {
    public FrogHelmetRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(CrazyInventions.MOD_ID,"armor/frog_helmet")));
    }

    @Override
    public void actuallyRender(PoseStack poseStack, FrogHelmet animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        Entity entity = getCurrentEntity();
        GeoBone bone = getHeadBone();
        if (bone != null) {
            poseStack.mulPose(Axis.YP.rotation(entity.getYRot()));
            poseStack.mulPose(Axis.XP.rotation(entity.getXRot()));
            //Make sure the helmet is always facing the direction of the player's head
        }
    }

}
