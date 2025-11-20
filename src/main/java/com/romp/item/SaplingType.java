package com.romp.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.StringIdentifiable;

//苗木のタイプのEnum を書く
public enum SaplingType implements StringIdentifiable {

    CHERRY("cherry", Items.CHERRY_SAPLING),
    SPRUCE("spruce", Items.SPRUCE_SAPLING);

    private final String name;
    private final Item item;

    SaplingType(String name, Item item) {
        this.name = name;
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    @Override
    public String asString() {
        return this.name;
    }

    // SaplingType に変換するメソッド
    public static SaplingType fromItem(Item item) {
        for (SaplingType type : values()) {
            if (type.item == item) {
                return type;
            }
        }
        return null;
    }
}