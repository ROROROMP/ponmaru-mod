package com.romp.registry;

import com.romp.PonmaruMod;

import com.romp.block.BonsaiBlock;
import com.romp.block.LanternBlock;
import com.romp.block.ZaisuBlock;
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

    // ブロックを追加
    public static final Block OAK_LANTERN = register("oak_lantern",
            settings -> new LanternBlock(settings
                    .strength(0.5f)
                    .luminance(state -> 10)
                    .nonOpaque()),
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD), true
    );

    public static final Block DARK_OAK_LANTERN = register("dark_oak_lantern",
            settings -> new LanternBlock(settings
                    .strength(0.5f)
                    .luminance(state -> 12)
                    .nonOpaque()),
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.WOOD), true
    );

    public static final Block BONSAI_POT = register("bonsai_pot",
            settings -> new BonsaiBlock(settings.strength(0.5f).luminance(state -> 8).nonOpaque()),
            AbstractBlock.Settings.create(), true
    );

    public static final Block ZAISU = register("zaisu",
            settings -> new ZaisuBlock(settings.strength(1.0f).nonOpaque()),
            AbstractBlock.Settings.create(), true
    );

    public static final Block HOTPOT = register("hotpot",
            settings -> new Block(settings.strength(1.0f).nonOpaque()),
            AbstractBlock.Settings.create(), true
    );


    // Blockオブジェクトを返す register メソッド
    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {

        // BlockのRegistryKey（登録キー）を作成
        RegistryKey<Block> blockKey = keyOfBlock(name);

        // Blockのインスタンスを作成
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Blockをアイテム登録するかしないかを設定。（作動中ピストンみたいにアイテム持たないブロックはfalseにする）
        if (shouldRegisterItem) {

            // ここに来るのは "shouldRegisterItem" が true のときだけ
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