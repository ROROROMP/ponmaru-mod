package com.romp.entity;

import com.romp.PonmaruMod;
import com.romp.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModBlockEntities {


    public static final BlockEntityType<?> OFFSET_BLOCK_ENTITY =
            FabricBlockEntityTypeBuilder.create(OffsetBlockEntity::new,

                    // ここにこのBlockEntityを使うブロックを並べる
                    ModBlocks.DARK_OAK_LANTERN

            ).build(null);

    // ② 登録用メソッド
    public static void registerAll() {
        Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(PonmaruMod.MOD_ID, "offset_block_entity"), // ←ここを変更
                OFFSET_BLOCK_ENTITY
        );
    }

}
