package com.romp.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class OffsetBlockEntity extends BlockEntity {

    public OffsetBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OFFSET_BLOCK_ENTITY, pos, state);
    }


}
