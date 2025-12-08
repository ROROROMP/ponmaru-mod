package com.romp.block;

import com.romp.api.IFacing;
import com.romp.api.ISlabAwareBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;

// ランタンのブロックとしてまとめる
public class LanternBlock extends Block implements IFacing, ISlabAwareBlock {

    public static final BooleanProperty ON_SLAB = BooleanProperty.of("on_slab");

    // コンストラクタ(MODロード時（レジストリ登録時） に実行)
    public LanternBlock(Settings settings) {
        super(settings);
    }

    // プロパティを追加
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ON_SLAB);
    }

    // 配置時
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState below = ctx.getWorld().getBlockState(pos.down());

        // FACING の反映
        BlockState state = withPlacementFacing(this.getDefaultState(), ctx);

        // ON_SLAB の反映
        boolean onSlab = isOnBottomSlab(below);
        state = state.with(ON_SLAB, onSlab);

        // 動いているかコンソールに出して確認
        // System.out.println("ランタンブロックチェック: FACING = " + state.get(FACING));
        // System.out.println("ランタンブロックチェック: ON_SLAB  = " + onSlab);

        return state;
        
    }


}