package holiday.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import holiday.entity.HeartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherEntity.class)
public abstract class WitherEntityMixin extends HostileEntity {

    protected WitherEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    protected abstract double getHeadZ(int headIndex);

    @Shadow
    protected abstract double getHeadX(int headIndex);

    @Shadow
    protected abstract double getHeadY(int headIndex);

    @Inject(
        method = "shootSkullAt(IDDDZ)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private void shootSkullAtMixin(int headIndex, double targetX, double targetY, double targetZ, boolean charged, CallbackInfo ci) {
        if (!this.isSilent()) {
            this.getEntityWorld().syncWorldEvent(null, WorldEvents.WITHER_SHOOTS, this.getBlockPos(), 0);
        }

        double d = this.getHeadX(headIndex);
        double e = this.getHeadY(headIndex);
        double f = this.getHeadZ(headIndex);
        double g = targetX - d;
        double h = targetY - e;
        double i = targetZ - f;

        Vec3d vec3d = new Vec3d(g, h, i);

        if (this.getEntityWorld().getDimensionEntry().matchesKey(DimensionTypes.OVERWORLD)) {
            HeartEntity heartEntity = new HeartEntity(this.getEntityWorld(), this, vec3d.normalize());
            //heartEntity.setOwner(this);
            heartEntity.setPosition(d, e, f);
            this.getEntityWorld().spawnEntity(heartEntity);

        } else {
            WitherSkullEntity witherSkullEntity = new WitherSkullEntity(this.getEntityWorld(), this, vec3d.normalize());
            witherSkullEntity.setOwner(this);
            if (charged) {
                witherSkullEntity.setCharged(true);
            }

            witherSkullEntity.setPosition(d, e, f);
            this.getEntityWorld().spawnEntity(witherSkullEntity);
        }

        ci.cancel();
    }

}
