package com.romp;

import com.romp.block.ModBlocks;
import com.romp.item.ModItemGroups;
import com.romp.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PonmaruMod implements ModInitializer {
    public static final String MOD_ID = "ponmaru_mod";

    // ログを出力し、MODが動いてるか確認する
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize(); // ModItemsクラスをロード
        ModBlocks.initialize(); // ModBlocksクラスをロード
        ModItemGroups.registerItemGroups(); // ModItemGroupsクラスをロード
    }






}