package com.romp.item;

import com.romp.PonmaruMod;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;


public class ModItems {

    // アイテムを追加
    public static final Item ONIGIRI = register("onigiri", Item::new, new Item.Settings().food(ModFoodComponents.ONIGIRI));
    public static final Item ONIGIRI_SHIO = register("onigiri_shio", Item::new, new Item.Settings().food(ModFoodComponents.ONIGIRI_SHIO));
    public static final Item ONIGIRI_UME = register("onigiri_ume", Item::new, new Item.Settings().food(ModFoodComponents.ONIGIRI_UME));
    public static final Item KIZUSHI = register("kizushi", Item::new, new Item.Settings().food(ModFoodComponents.KIZUSHI));
    public static final Item SANSYOKUDANGO = register("sansyokudango", Item::new, new Item.Settings().food(ModFoodComponents.SANSYOKUDANGO));

    // Item オブジェクトを返す register メソッド
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {

        // アイテムキーを作成
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PonmaruMod.MOD_ID, name));

        // アイテムインスタンスを作成
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // アイテムを登録
        Registry.register(Registries.ITEM, itemKey, item);
        return item;

    }



    public static void initialize() {

        //　ログに出力して、Modが正しく初期化されたことを確認する
        PonmaruMod.LOGGER.info("ModItemsクラス：アイテムの登録 " + PonmaruMod.MOD_ID);

    }




}

