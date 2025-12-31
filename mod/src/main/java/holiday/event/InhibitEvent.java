package holiday.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;

public interface InhibitEvent {
    Event<InhibitEvent> EVENT = EventFactory.createArrayBacked(InhibitEvent.class,
            (listeners) -> (enderman) -> {
                for (InhibitEvent listener : listeners) {
                    if (!listener.canTeleport(enderman)) {
                        return false;
                    }
                }
                return true;
            });

    boolean canTeleport(LivingEntity entity);
}
