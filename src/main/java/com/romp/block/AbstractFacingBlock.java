package com.romp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

import java.util.Objects;

// 向きの処理をまとめた抽象クラス
// BlockWithEntityベースの "向き付き抽象ブロック"
public class AbstractFacingBlock extends Block {

    // コンストラクタ
    public AbstractFacingBlock(Settings settings) {
        super(settings);

        //ブロックの "向き" を初期化
        setDefaultState(getDefaultState()
                .with(Properties.FACING, Direction.NORTH)
         );
    }

    // このブロックは向きを持つ ということをブロックの状態として追加しています
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.FACING);
    }

    // 配置時にプレイヤーの向きを取得して BlockState に反映
    // プレイヤーに向くようにブロックを設置する
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return Objects.requireNonNull(super.getPlacementState(ctx))
                .with(Properties.FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }



}
