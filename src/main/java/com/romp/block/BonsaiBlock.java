package com.romp.block;

import com.romp.item.SaplingType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
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

public class BonsaiBlock extends Block {

    // ← ここにプロパティを宣言
    public static final BooleanProperty EMPTY = BooleanProperty.of("empty");
    public static final EnumProperty<SaplingType> SAPLING = EnumProperty.of("sapling", SaplingType.class);

    // コンストラクタで設定を渡す
    public BonsaiBlock(AbstractBlock.Settings settings) {
        super(settings);

        // デフォルト状態を EMPTY=true に設定
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(EMPTY, true)
                //.with(SAPLING, SaplingType.CHERRY) // デフォルトはとりあえず CHERRY
        );
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // BlockState に EMPTY を登録
        builder.add(EMPTY, SAPLING);
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

            // 手に苗木がなければ何もしない
            SaplingType sapling = SaplingType.fromItem(stack.getItem());
            if (sapling == null) return ActionResult.PASS;

            // 種類ごとに置き換えるブロックを決める
            switch (sapling) {
                case CHERRY, SPRUCE -> {
                    world.setBlockState(pos, state.with(EMPTY, false).with(SAPLING, sapling));
                }
            }

            if (!player.isCreative()) {
                stack.decrement(1); // 苗木を1個消費
            }

        }
        // EMPTY が true 以外の場合 → 何か植わってたら取り出せる
        else {

            // ここで植えられた苗木の種類を取得する
            SaplingType planted = state.get(SAPLING);

            // アイテムとしてプレイヤーに返す
            ItemStack dropStack = ItemStack.EMPTY;

            // 植わってる種類に応じて返す苗木を決める
            switch (planted) {
                case CHERRY -> dropStack = new ItemStack(Items.CHERRY_SAPLING);
                case SPRUCE -> dropStack = new ItemStack(Items.SPRUCE_SAPLING);
            }

            if (!player.getInventory().insertStack(dropStack)) {
                player.dropItem(dropStack, false);
            }

            // ここでブロックを元に戻す
            world.setBlockState(pos, this.getDefaultState().with(EMPTY, true));


        }

        return ActionResult.SUCCESS;
    }

}
