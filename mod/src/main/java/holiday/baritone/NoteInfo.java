package holiday.baritone;

import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

public enum NoteInfo {
    Fs(0,"F#", 0xffff0000),
    G(1,"G", 0xffff8000),
    Gs(2,"G#", 0xffffff00),
    A(3,"A", 0xff80ff00),
    As(4,"A#", 0xff00ff00),
    B(5,"B", 0xff00ff80),
    C(6,"C", 0xff00ffff),
    Cs(7,"C#", 0xff0080ff),
    D(8,"D", 0xff0000ff),
    Ds(9,"D#", 0xff8000ff),
    E(10,"E", 0xffff00ff),
    F(11,"F", 0xffff0080),
    Fs1(12,"F#+1", 0xffff0000);

    public final float висота;
    public final String назва;
    public final int колір;

    NoteInfo(int Нота, String Назва, int колір){
        this.висота = ВисотаНоти(Нота);
        this.назва =Назва;
        this.колір = колір;
    }
    public static float ВисотаНоти(int Нота){
      return (float) Math.pow(2,((float)Нота)/12f);
    };
    public static float мінімум;
    public static float максимум;
    public static NoteInfo отриматиНотуЗаВисотоюГолови(float висотаГолови){
        мінімум=-90;
        максимум=90;
        var списокНот = NoteInfo.values();
        var кількістьНот = Arrays.stream(списокНот).count();
        var нота = 12-(int) ((висотаГолови-мінімум)/(максимум-мінімум)*кількістьНот);
        return списокНот[(int) MathHelper.clamp(нота,0,кількістьНот-1)];
    };
}

