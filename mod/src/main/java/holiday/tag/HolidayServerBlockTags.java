package holiday.tag;

import holiday.CommonEntrypoint;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public final class HolidayServerBlockTags {
    public static final TagKey<Block> CRYING_IMPACTORS = of("crying_impactors");
    public static final TagKey<Block> STONE_MEALABLE = of("stone_mealable");

    private HolidayServerBlockTags() {
    }

    public static TagKey<Block> of(String path) {
        return TagKey.of(RegistryKeys.BLOCK, CommonEntrypoint.identifier(path));
    }
}
