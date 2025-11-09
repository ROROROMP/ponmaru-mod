package com.romp.item;

import com.romp.PonmaruMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {


    // レジストリキーを設定
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY =
            RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(PonmaruMod.MOD_ID, "item_group"));

    // アイテムグループを作成
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.APPLE))
            .displayName(Text.translatable("item_group.ponmaru_mod"))
            .build();

    // カスタムアイテムグループを登録
    public static final ItemGroup PONMARU_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);



public static void registerItemGroups() {

    PonmaruMod.LOGGER.info("ModItemGroupsクラス：アイテムグループの登録 " + PonmaruMod.MOD_ID);

    // カスタムアイテムグループにアイテムを追加
    ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).
            register(itemGroup -> {
                itemGroup.add(ModItems.ONIGIRI);
            });

}


}