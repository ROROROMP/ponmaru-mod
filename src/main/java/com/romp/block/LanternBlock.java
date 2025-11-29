package com.romp.block;

import com.romp.PonmaruMod;
import com.romp.api.IFacing;
import com.romp.util.Facing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;


// ランタンのブロックとしてまとめる
// implements IFacing → 「向きを持つブロック」であることを宣言
// IFacing を implements することで getFacing() と setFacing() を必ず実装する必要がある
public class LanternBlock extends Block implements IFacing {


    public static final BooleanProperty ON_SLAB = BooleanProperty.of("on_slab");

    // コンストラクタ
    public LanternBlock(Settings settings) {
        super(settings);

        // ブロックの向きを初期化
        this.setDefaultState(getStateManager().getDefaultState()
                .with(Facing.FACING, Direction.NORTH)
        );
    }

    // プロパティを追加
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        Facing.addFacingProperty(builder);
        builder.add(ON_SLAB);
    }

    // 現在の向きを取得
    @Override
    public Direction getFacing(BlockState state) {
        return state.get(Facing.FACING);
    }

    // 新しい向きの BlockState を返す
    @Override
    public BlockState setFacing(BlockState state, Direction direction) {
        return state.with(Facing.FACING, direction);
    }

    // 配置時の向きをプレイヤーに合わせる
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState below = ctx.getWorld().getBlockState(pos.down());

        boolean isBottomSlab =
                below.getBlock() instanceof SlabBlock &&
                        below.get(SlabBlock.TYPE) == SlabType.BOTTOM;

        // ブロックの種類を判別できるか、ログで確認
        PonmaruMod.LOGGER.info("LanternBlockでの下のブロック確認" + isBottomSlab);

        // ① まず向きだけ付けたブロックステートを作る
        BlockState state = Facing.withPlacementFacing(getDefaultState(), ctx);

        // ② その state に対して ON_SLAB を付けて返す
        return state.with(ON_SLAB, isBottomSlab);
    }

}