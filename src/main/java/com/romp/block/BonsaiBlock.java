package com.romp.block;

import com.romp.api.ISlabAwareBlock;
import com.romp.item.SaplingType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class BonsaiBlock extends Block implements ISlabAwareBlock {

    // ← ここにプロパティを宣言
    public static final BooleanProperty ON_SLAB = BooleanProperty.of("on_slab");
    public static final BooleanProperty EMPTY = BooleanProperty.of("empty");
    public static final EnumProperty<SaplingType> SAPLING = EnumProperty.of("sapling", SaplingType.class);

    // コンストラクタで設定を渡す
    public BonsaiBlock(AbstractBlock.Settings settings) {
        super(settings);

        // デフォルト状態を設定
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(EMPTY, true)
                .with(ON_SLAB, false)
                .with(SAPLING, SaplingType.CHERRY) // デフォルト値
        );
    }

    // プロパティを追加(まとめて登録)
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // BlockState に EMPTY SAPLING ON_SLABを登録
        builder.add(EMPTY, SAPLING, ON_SLAB);
    }

    // 配置時の向きをプレイヤーに合わせる
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState below = ctx.getWorld().getBlockState(pos.down());
        BlockState state = getDefaultState();

        // ON_SLAB の反映
        boolean onSlab = isOnBottomSlab(below);
        state = state.with(ON_SLAB, onSlab);

        return state;
    }

    // onUse メソッドをオーバーライドして、右クリック処理を記述します。
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        // クライアント側なら何もしない(クライアント側ではパーティクルや音など描画処理のみ)
        if (world.isClient()) return ActionResult.SUCCESS;

        // プレイヤーのメインハンドにあるアイテムスタックを取得する
        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

        // ここに右クリック時の処理を書く
        // EMPTY が true の場合 → 植える
        if (state.get(EMPTY)) {

            SaplingType sapling = SaplingType.fromItem(stack.getItem());

            // 手に苗木がなければ何もしない
            if (sapling == null) return ActionResult.PASS;

            world.setBlockState(pos, state.with(EMPTY, false).with(SAPLING, sapling));

            if (!player.isCreative()) {
                stack.decrement(1); // 苗木を1個消費
            }

            // 種類ごとに置き換えるブロックを決める
            switch (sapling) {
                case CHERRY, SPRUCE -> {
                    world.setBlockState(pos, state.with(EMPTY, false).with(SAPLING, sapling));
                }
            }

        }
        // EMPTY が true 以外の場合 → 何か植わってたら取り出せる
        else {
            // ここで植えられた苗木の種類を取得する
            SaplingType planted = state.get(SAPLING);

            // 植わってる種類に応じて返す苗木を決める
            ItemStack dropStack = switch (planted) {
                case CHERRY -> new ItemStack(Items.CHERRY_SAPLING);
                case SPRUCE -> new ItemStack(Items.SPRUCE_SAPLING);
            };

            if (!player.getInventory().insertStack(dropStack)) {
                player.dropItem(dropStack, false);
            }

            // ここでブロックを元に戻す
            BlockState below = world.getBlockState(pos.down());
            // ON_SLAB の反映
            boolean onSlab = isOnBottomSlab(below);

            BlockState newState = state.with(ON_SLAB, onSlab)
                    .with(EMPTY, true);
            world.setBlockState(pos, newState);

        }

        return ActionResult.SUCCESS;
    }

}
