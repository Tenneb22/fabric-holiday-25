package holiday.baritone;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;

import java.util.Arrays;

public class BaritoneHud {
    public static void старт() {
        HudRenderCallback.EVENT.register(BaritoneHud::рендер);
    }

    private static void рендер(DrawContext контекст, RenderTickCounter рахування) {
        if(MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof BARITONE
                ||MinecraftClient.getInstance().player.getOffHandStack().getItem() instanceof BARITONE ) {
            контекст.getMatrices().pushMatrix();
            var ШиринаВікна = MinecraftClient.getInstance().getWindow().getScaledWidth();
            var ВисотаВікна = MinecraftClient.getInstance().getWindow().getScaledHeight();
            var ВисотаПогляду = MinecraftClient.getInstance().player.getPitch();
            var Нота = NoteInfo.отриматиНотуЗаВисотоюГолови(ВисотаПогляду);
            контекст.drawCenteredTextWithShadow(
                    MinecraftClient.getInstance().textRenderer,
                    Нота.назва,
                    ШиринаВікна/2,
                    ВисотаВікна/2+3,
                    Нота.колір
            );
            var КількістьЧастинок = Arrays.stream(NoteInfo.values()).count();
            var РозмірЧастинки = (int) (ВисотаВікна/2/КількістьЧастинок);

            контекст.getMatrices().translate(0,ВисотаВікна/2*(-ВисотаПогляду/(NoteInfo.максимум-NoteInfo.мінімум)));
            for(int i=0;i<КількістьЧастинок;i++) {
                int номерНоти = (int) (КількістьЧастинок-1-i);
                контекст.fill(ШиринаВікна / 2 + 8, ВисотаВікна / 4 + РозмірЧастинки * i + 1, ШиринаВікна / 2 + 24, ВисотаВікна / 4 + РозмірЧастинки * (i + 1) - 1, NoteInfo.values()[номерНоти].колір);
                контекст.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, Text.literal(NoteInfo.values()[номерНоти].назва),
                        ШиринаВікна / 2 + 26,(int) (ВисотаВікна / 4 + РозмірЧастинки * (i+0.3)), NoteInfo.values()[номерНоти].колір);
            }
            контекст.getMatrices().popMatrix();
        }
    }
}
