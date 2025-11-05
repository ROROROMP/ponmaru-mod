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
    // 大文字の ONIGIRI は Java コード内で使う定数名（変数名）
    // 小文字の "onigiri" はアイテムID（ゲーム内で使われる名前）
    public static final Item ONIGIRI = registerItem("onigiri", Item::new);


    // アイテムを「名前＋設定付きで登録する」ための汎用メソッド
    // name（アイテム名）と、function（アイテムをどう作るか）を渡すと、自動的にRegistryに登録して、そのアイテムを返してくれる
    private static Item registerItem(String name, Function<Item.Settings, Item> function) {

        // Registry.register()を使ってゲームにアイテムを登録する
        return Registry.register(
                Registries.ITEM,
                Identifier.of(PonmaruMod.MOD_ID, name),
                function.apply(new Item.Settings()
                        .registryKey(RegistryKey.of(
                                        RegistryKeys.ITEM, Identifier.of(PonmaruMod.MOD_ID, name)
                                )
                        )
                )
        );
    }

    public static void registerModItems() {

        //　ログに出力して、Modが正しく初期化されたことを確認する
        PonmaruMod.LOGGER.info("Registering Mod Items for " + PonmaruMod.MOD_ID);

        // クリエイティブタブに追加
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(entries -> {
                    entries.add(ONIGIRI); // ONIGIRI（変数）を追加します
                });
    }

}

