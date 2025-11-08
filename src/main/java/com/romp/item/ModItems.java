package com.romp.item;

import com.romp.PonmaruMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import static net.minecraft.item.Items.register;

public class ModItems {

    // アイテムを追加
    //public static final Item ONIGIRI = register("onigiri", Item::new, new Item.Settings());

    // 今回食料として追加しなおしたところ
    // Itemに対して、FoodComponentを設定する
    public static final Item ONIGIRI = register("onigiri", Item::new, new Item.Settings().food(ModFoodComponents.ONIGIRI));

    // レジストリキーを設定
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(PonmaruMod.MOD_ID, "item_group"));
    // アイテムグループを作成
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ONIGIRI))
            .displayName(Text.translatable("item_group.ponmaru_mod"))
            .build();

    // カスタムアイテムグループを登録
    public static final ItemGroup PONMARU_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

    public static void initialize() {
        //　ログに出力して、Modが正しく初期化されたことを確認する
        PonmaruMod.LOGGER.info("MODアイテムの登録 " + PonmaruMod.MOD_ID);

        // 前回クリエイティブタブに追加（FOOD_AND_DRINKが「食べ物を飲み物」のタブ)
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
//                .register(entries -> {
//                    entries.add(ONIGIRI);
//                });

        // カスタムアイテムグループにアイテムを追加
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).
                register(itemGroup -> {
                    itemGroup.add(ModItems.ONIGIRI);
                });


    }
}

