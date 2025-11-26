package com.romp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Direction;

import java.util.Objects;

public class ZaisuBlock extends Block {

    // バニラ階段方式：EnumProperty で向き管理
    public static final EnumProperty<Direction> FACING = EnumProperty.of("facing", Direction.class,
            Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

    // 1. 木材用 EnumProperty
    //public static final EnumProperty<WoodType> WOOD = EnumProperty.of("wood", WoodType.class);

    // 2. カーペット色用 EnumProperty
    public static final EnumProperty<DyeColor> CARPET = EnumProperty.of("carpet", DyeColor.class);


    // コンストラクタ
    public ZaisuBlock(Settings settings) {
        super(settings);
        //ブロックの "向き" を初期化
        setDefaultState(getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
                //.with(WOOD, WoodType.OAK)           // 初期値
                .with(CARPET, DyeColor.WHITE));     // 初期値
        // );
    }

    // このブロックは向きを持つ ということをブロックの状態として追加しています
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, CARPET);
        //builder.add(Properties.HORIZONTAL_FACING, WOOD, CARPET);
    }

    // 配置時にプレイヤーの向きを取得して BlockState に反映
    // プレイヤーに向くようにブロックを設置する
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return Objects.requireNonNull(super.getPlacementState(ctx))
                .with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                //.with(WOOD, WoodType.OAK)           // 初期木材
                .with(CARPET, DyeColor.WHITE);      // 初期カーペット

    }


    // ブロッククラスで onUse をオーバーライド
//    @Override
//    public  ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
//
//        System.out.println("座椅子が右クリックされました"); // ← これでコンソールに出力される
//
//        if (!world.isClient()) {
//            BlockEntity be = world.getBlockEntity(pos);
//            if (be instanceof ZaisuBlockEntity zaisu) {
//
//                // 右クリックした手に染料を持っているか確認
//                ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
//                if (stack.getItem() instanceof DyeItem dye) {
//                    int color = dye.getColor().ordinal(); // 0〜15 の色番号
//
//                    System.out.println("FabricColor: " + dye.getColor().ordinal());
//
//                    zaisu.setFabricColor(color);
//
//                    if (!player.isCreative()) {
//                        stack.decrement(1); // 染料を1個消費
//                    }
//
//                    world.updateListeners(pos, state, state, 3); // 見た目更新
//                    return ActionResult.SUCCESS;
//                }
//            }
//        }
//        return ActionResult.PASS;
//    }


}

