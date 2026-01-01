package holiday.screen;

import net.minecraft.screen.Property;

public final class LongPropertyPair {
    protected final Property high = Property.create();
    protected final Property low = Property.create();

    public long get() {
        return ((long) this.high.get()) << 32 | (this.low.get() & 0xFFFFFFFFL);
    }

    public void set(long value) {
        this.high.set((int) ((value & 0xFFFFFFFF00000000L) >> 32));
        this.low.set((int) (value & 0xFFFFFFFFL));
    }
}
