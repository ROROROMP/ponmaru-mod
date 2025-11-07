package com.romp.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {

    public static final FoodComponent ONIGIRI = new FoodComponent.Builder()
            .alwaysEdible()             // 空腹でなくても食べられる
            .nutrition(5)               // 満腹度（パンと同じ）
            .saturationModifier(1.2f)   // 満腹効率（パンの倍）
            .build();
}




