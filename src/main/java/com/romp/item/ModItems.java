package com.romp.item;

import com.romp.PonmaruMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;

public class ModItems {

    // アイテムを追加
    //public static final Item ONIGIRI = register("onigiri", Item::new, new Item.Settings());

    // 今回食料として追加しなおしたところ
    // Itemに対して、FoodComponentを設定する
    public static final Item ONIGIRI = register("onigiri",Item::new, new Item.Settings().food(ModFoodComponents.ONIGIRI));


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
        PonmaruMod.LOGGER.info("MODアイテムの登録 " + PonmaruMod.MOD_ID);

        // クリエイティブタブに追加（FOOD_AND_DRINKが「食べ物を飲み物」のタブ)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(entries -> {
                    entries.add(ONIGIRI);
                });
    }

}

