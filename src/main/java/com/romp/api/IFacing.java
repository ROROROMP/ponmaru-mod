package com.romp.api;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;


// 向きがあることを示すインターフェース
public interface IFacing {

    // ブロックの向きを表すプロパティを定数としてまとめる（FACINGはブロックの向きを表す EnumProperty 定数）
    public static final EnumProperty<Direction> FACING = Properties.FACING;

    // 配置時にプレイヤーの向きを反映するメソッド
    // ItemPlacementContext にはプレイヤーの向き情報が入っている
    // .getHorizontalPlayerFacing() → プレイヤーが向いている方向（N, S, E, W）
    // .getOpposite() → ブロックは プレイヤーと向かい合う方向になるのが普通
    // 新しい BlockState を返す
    default BlockState withPlacementFacing(BlockState state, ItemPlacementContext ctx) {
        return state.with(Properties.FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
}