package com.romp.api;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.state.property.BooleanProperty;

public interface ISlabAwareBlock {

    // 配置時に下がスラブかどうか判定
    default boolean isOnBottomSlab(BlockState stateBelow) {
        return stateBelow.getBlock() instanceof net.minecraft.block.SlabBlock
                //&& stateBelow.get(net.minecraft.block.SlabBlock.TYPE) == net.minecraft.block.enums.SlabType.BOTTOM;
                && stateBelow.get(SlabBlock.TYPE) == SlabType.BOTTOM;
    }

    // BlockState に任意の BooleanProperty を適用
    default BlockState applyOnSlabProperty(BlockState state, BooleanProperty property, boolean onSlab) {
        if (state.contains(property)) {
            return state.with(property, onSlab);
        }
        return state;
    }

    // 配置時に BooleanProperty を ON_SLAB 以外でも汎用的に適用
    default BlockState handlePlacement(BlockState defaultState, BlockState stateBelow, BooleanProperty slabProperty) {
        boolean onSlab = isOnBottomSlab(stateBelow);
        return applyOnSlabProperty(defaultState, slabProperty, onSlab);
    }
}
