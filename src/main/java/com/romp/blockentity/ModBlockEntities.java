package com.romp.blockentity;

import com.romp.PonmaruMod;
import com.romp.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;



// 登録専用クラス。BonsaiBlockEntity をゲームに登録するだけの場所。複数BlockEntityをまとめて登録する
public class ModBlockEntities {

    public static BlockEntityType<BonsaiBlockEntity> BONSAI_BLOCK_ENTITY;

    public static void register() {

        BONSAI_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.tryParse("ponmaru_mod:bonsai_block_entity"),
                FabricBlockEntityTypeBuilder.create(
                        BonsaiBlockEntity::new,
                        ModBlocks.BONSAI_POT
                ).build()
        );
    }


    public static void initialize() {
        //　ログに出力して、Modが正しく初期化されたことを確認する
        PonmaruMod.LOGGER.info("ModBlockEntitiesクラス：BlockEntityの登録 " + PonmaruMod.MOD_ID);

    }

}
