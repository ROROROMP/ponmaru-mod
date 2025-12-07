package com.romp.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;

public interface ISlabAwareBlock {

    // 配置時に下のブロックが下付きハーフかどうか判定
    default boolean isOnBottomSlab(BlockState stateBelow) {
        return stateBelow.getBlock() instanceof net.minecraft.block.SlabBlock
                && stateBelow.get(SlabBlock.TYPE) == SlabType.BOTTOM;
    }
}