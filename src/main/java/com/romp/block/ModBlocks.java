package com.romp.block;

import com.romp.PonmaruMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;


public class ModBlocks {

    public static final Block ANDON = register(
            "andon",
            settings -> new Block(settings
                    .strength(1.5f)           // 硬さ
                    .luminance(state -> 15)   // 光源レベル 0～15
                    .nonOpaque() // 透過部分を描画
            ),
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD),
            true
    );


    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // ブロックの RegistryKey（登録キー）を作成
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // ブロックのインスタンスを作成
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // 場合によっては、アイテムを登録しないこともある。
        // 例: `minecraft:moving_piston` や `minecraft:end_gateway` のような
        //     テクニカルブロックはアイテムを持たない。
        if (shouldRegisterItem) {
            // アイテムはブロックとは別の Registry に登録する必要があるが、
            // 登録 ID（名前）は同じでよい。
            RegistryKey<Item> itemKey = keyOfItem(name);

            // ブロックに対応するアイテムを作成
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(PonmaruMod.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(PonmaruMod.MOD_ID, name));
    }


    public static void initialize() {
        //　ログに出力して、Modが正しく初期化されたことを確認する
        PonmaruMod.LOGGER.info("ModBlocksクラス：ブロックの登録 " + PonmaruMod.MOD_ID);

    }

}