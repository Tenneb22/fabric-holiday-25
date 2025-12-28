package holiday.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import holiday.item.HolidayServerItems;
import holiday.tag.HolidayServerBlockTags;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
    @WrapOperation(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BoneMealItem;useOnFertilizable(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"))
    private boolean wrapStoneMeal(ItemStack stack, World world, BlockPos pos, Operation<Boolean> original) {
        boolean isStoneMeal = stack.getItem() == HolidayServerItems.STONE_MEAL;
        if (world.getBlockState(pos).isIn(HolidayServerBlockTags.STONE_MEALABLE)) {
            if (isStoneMeal) {
                return original.call(stack, world, pos);
            }
            return false;
        }
        return !isStoneMeal && original.call(stack, world, pos);
    }

}
