package holiday.mixin;

import holiday.event.InhibitEvent;
import net.minecraft.entity.mob.ShulkerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerEntity.class)
public class ShulkerEntityMixin {
    @Inject(method = "tryTeleport", at = @At("HEAD"), cancellable = true)
    private void stopTeleporting(CallbackInfoReturnable<Boolean> cir) {
        if(InhibitEvent.EVENT.invoker().canTeleport((ShulkerEntity) (Object) this))
            cir.setReturnValue(false);
    }
}
