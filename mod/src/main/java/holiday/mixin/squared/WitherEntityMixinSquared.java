package holiday.mixin.squared;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import holiday.idkwheretoputthis.WitherEntityExtension;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(value = WitherEntity.class, priority = 1500)
public abstract class WitherEntityMixinSquared extends HostileEntity {
    private WitherEntityMixinSquared(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /*
        ChainStyleWither compatibility
     */

    @Dynamic
    @SuppressWarnings("all")
    @TargetHandler(
        mixin = "dev.louis.chainstylewither.mixin.WitherBossMixin",
        name = "customServerAiStep"
    )
    @WrapMethod(
        method = "@MixinSquared:Handler",
        remap = false
    )
    private void wrapCustomServerAiStep(ServerWorld world, CallbackInfo ci, Operation<Void> original) {
        if (!((WitherEntityExtension)(Object)this).fabric_holiday_25$isInOverWorld()) {
            original.call(world, ci);
        }
    }

    /**
     * @author Tenneb22
     * @reason Remove explosion on death from ChainStyleWither mod
     */
    @SuppressWarnings("all")
    @Overwrite
    protected void updatePostDeath() {
        super.updatePostDeath();
    }
}
