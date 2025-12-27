package holiday.baritone;

import holiday.CommonEntrypoint;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;import net.minecraft.registry.RegistryKeys;import net.minecraft.util.Identifier;import java.util.function.Function;

public class fabric_register_thing_stolen_from_tutorial {
    public static <T extends Item> T зареєструвати(String ідентифікатор, Function<Item.Settings, T> factory) {
        Identifier айді = Identifier.of(CommonEntrypoint.MOD_ID, ідентифікатор);
        RegistryKey<Item> ключ = RegistryKey.of(RegistryKeys.ITEM, айді);

        Item.Settings налаштування = new Item.Settings().registryKey(ключ).maxCount(1);
        T предмет = factory.apply(налаштування);

        return Registry.register(Registries.ITEM, ключ, предмет);
    }
}
