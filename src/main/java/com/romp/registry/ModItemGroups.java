package com.romp.registry;

import com.romp.PonmaruMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {

    // レジストリキーを設定
    public static final RegistryKey<ItemGroup> PONMARU_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(PonmaruMod.MOD_ID, "item_group"));

    // カスタムアイテムグループを作成
    public static final ItemGroup PONMARU_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.ONIGIRI_UME))
            .displayName(Text.translatable("item_group.ponmaru_mod"))
            .build();

    // カスタムアイテムグループを登録
    public static final ItemGroup PONMARU_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, PONMARU_GROUP_KEY, PONMARU_GROUP);


    public static void registerItemGroups() {

        //　ログに出力して、ModItemGroupsクラスが正しく初期化されたことを確認する
        PonmaruMod.LOGGER.info("ModItemGroupsクラス：アイテムグループの登録 " + PonmaruMod.MOD_ID);

        // カスタムアイテムグループにアイテムを追加
        ItemGroupEvents.modifyEntriesEvent(PONMARU_GROUP_KEY).
                register(itemGroup -> {
                    itemGroup.add(ModItems.ONIGIRI_SHIO);
                    itemGroup.add(ModItems.ONIGIRI);
                    itemGroup.add(ModItems.ONIGIRI_UME);
                    itemGroup.add(ModItems.KIZUSHI);
                    itemGroup.add(ModItems.SANSYOKUDANGO);

                    itemGroup.add(ModBlocks.OAK_LANTERN);
                    itemGroup.add(ModBlocks.DARK_OAK_LANTERN);
                    itemGroup.add(ModBlocks.BONSAI_POT);
                    itemGroup.add(ModBlocks.ZAISU);
                });

    }


}