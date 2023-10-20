package net.sievert.jolcraft.entity.variant;


import com.mojang.serialization.Codec;
import java.util.function.IntFunction;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

    public enum DwarfVariant implements StringRepresentable {
        DEFAULT(0, "default"),
        RED(1, "red"),
        GREEN(2, "green"),
        BLUE(3, "blue"),
        PINK(4, "pink"),
        PURPLE(5, "purple"),
        WHITE(6, "white"),
        YELLOW(7, "yellow");

        public static final Codec<DwarfVariant> CODEC = StringRepresentable.fromEnum(DwarfVariant::values);
        private static final IntFunction<DwarfVariant> BY_ID = ByIdMap.continuous(DwarfVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
        private final int id;
        private final String name;

        private DwarfVariant(int p_262580_, String p_262591_) {
            this.id = p_262580_;
            this.name = p_262591_;
        }

        public int getId() {
            return this.id;
        }

        public static DwarfVariant byId(int p_30987_) {
            return BY_ID.apply(p_30987_);
        }
        public String getSerializedName() {
            return this.name;
        }
    }