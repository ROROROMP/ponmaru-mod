package com.romp.blockentity;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

// 実際の BlockEntity クラス。苗木を保持したり、NBT保存/読み込みする処理を書く場所。
public class BonsaiBlockEntity extends BlockEntity {

    // コンストラクタ
    public BonsaiBlockEntity (BlockPos pos, BlockState state) {
        super(ModBlockEntities.BONSAI_BLOCK_ENTITY, pos, state);
    }
}
