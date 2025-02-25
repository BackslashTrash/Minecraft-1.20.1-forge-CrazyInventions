package net.joenaldbrump.crazyinventions.item.client;



import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.joenaldbrump.crazyinventions.CrazyInventions;
import net.joenaldbrump.crazyinventions.item.custom.PanHelmet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FryingPanArmorRenderer extends GeoArmorRenderer<PanHelmet> {
    public FryingPanArmorRenderer() {
        super(new DefaultedItemGeoModel<>(new ResourceLocation(CrazyInventions.MOD_ID,"armor/frying_pan")));
    }

    @Override
    public void actuallyRender(PoseStack poseStack, PanHelmet animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
//        if (getCurrentEntity() instanceof Player player) {
        Entity entity = getCurrentEntity();
        GeoBone bone = getHeadBone();
        if (bone != null) {
            poseStack.mulPose(Axis.YP.rotation(entity.getYRot()));
            poseStack.mulPose(Axis.XP.rotation(entity.getXRot()));
        }
        //}
    }
}
