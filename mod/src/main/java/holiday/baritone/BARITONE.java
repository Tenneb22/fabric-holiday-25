package holiday.baritone;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.LoggerFactory;

public class BARITONE extends Item {
    public BARITONE(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World світ, PlayerEntity користувач, Hand рука) {
        if(світ.isClient())
        {
            return ActionResult.PASS;
        }
        var позиція = користувач.getEntityPos();
        var висота = NoteInfo.отриматиНотуЗаВисотоюГолови(
                користувач.getPitch()
        ).висота;
        світ.playSound(
                null,
                BlockPos.ofFloored(позиція),
                BaritoneInit.звукБаритону,
                SoundCategory.PLAYERS,
                1,
                висота
                );
        LoggerFactory.getLogger("Baritone").info("{} {}", висота, користувач.getPitch());
        return ActionResult.SUCCESS;
    }
}
