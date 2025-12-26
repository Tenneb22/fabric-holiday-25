package holiday;

import holiday.block.HolidayServerBlocks;
import holiday.item.HolidayServerItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class HolidayServerModelProvider extends FabricModelProvider {
    public HolidayServerModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.registerSimpleCubeAll(HolidayServerBlocks.REDSTONE_SAND);
        generator.registerNorthDefaultHorizontalRotatable(HolidayServerBlocks.TINY_POTATO);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(HolidayServerItems.ABSOLUTELY_SAFE_ARMOR, Models.GENERATED);
        generator.register(HolidayServerItems.FABRIC_PATTERN_ITEM, Models.GENERATED);
        generator.register(HolidayServerItems.TATER_PATTERN_ITEM, Models.GENERATED);
    }
}
