package com.romp.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

// 向きの処理をまとめたヘルパークラス
public class Facing {

    // ブロックの向きを表すプロパティを定数としてまとめる（FACINGはブロックの向きを表す EnumProperty 定数）
    public static final EnumProperty<Direction> FACING = Properties.FACING;

    // ブロックの向きをNORTHに初期化する
    public static BlockState withDefaultFacing(BlockState state) {
        return state.with(Properties.FACING, Direction.NORTH);
    }

    // このブロックは向きを持つ ということをブロックの状態として追加する
    public static void addFacingProperty(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.FACING);
    }

    // 配置時にプレイヤーの向きを反映するメソッド
    // ItemPlacementContext にはプレイヤーの向き情報が入っている
    // .getHorizontalPlayerFacing() → プレイヤーが向いている方向（N, S, E, W）
    // .getOpposite() → ブロックは プレイヤーと向かい合う方向になるのが普通
    // これも BlockState は不変なので、新しい BlockState を返す
    public static BlockState withPlacementFacing(BlockState state, ItemPlacementContext ctx) {
        return state.with(Properties.FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }


}
