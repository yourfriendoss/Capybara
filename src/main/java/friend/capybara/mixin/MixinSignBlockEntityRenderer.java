package friend.capybara.mixin;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import friend.capybara.Capybara;
import friend.capybara.event.events.EventSignBlockEntityRender;

@Mixin(SignBlockEntityRenderer.class)
public class MixinSignBlockEntityRenderer {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(SignBlockEntity signBlockEntity_1, float float_1, MatrixStack matrixStack_1, VertexConsumerProvider vertexConsumerProvider_1, int int_1, int int_2, CallbackInfo ci) {
        EventSignBlockEntityRender event = new EventSignBlockEntityRender(signBlockEntity_1);
        Capybara.eventBus.post(event);
        if (event.isCancelled()) ci.cancel();
    }

}
