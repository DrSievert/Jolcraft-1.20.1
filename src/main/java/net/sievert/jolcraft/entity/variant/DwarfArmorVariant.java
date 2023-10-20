package net.sievert.jolcraft.entity.variant;


import com.mojang.serialization.Codec;
import java.util.function.IntFunction;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

public enum DwarfArmorVariant implements StringRepresentable {
    DEFAULT(0, "default"),
    JEWELRY (1, "jewelry"),
    LEATHER(2, "leather"),
    CHAIN(3, "chain"),
    IRON(4, "iron"),
    DIAMOND(5, "diamond"),
    NETHERITE(6, "netherite");


    public static final Codec<DwarfArmorVariant> CODEC = StringRepresentable.fromEnum(DwarfArmorVariant::values);
    private static final IntFunction<DwarfArmorVariant> BY_ID = ByIdMap.continuous(DwarfArmorVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String name;

    private DwarfArmorVariant(int p_262580_, String p_262591_) {
        this.id = p_262580_;
        this.name = p_262591_;
    }

    public int getId() {
        return this.id;
    }

    public static DwarfArmorVariant byId(int p_30987_) {
        return BY_ID.apply(p_30987_);
    }
    public String getSerializedName() {
        return this.name;
    }
}
