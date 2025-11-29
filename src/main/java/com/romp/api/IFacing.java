package com.romp.api;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

/*
向きがあることを示すインターフェース
「このクラスは回転（向き）機能を持っています」という契約を表します。
ブロック側がこれを implements すると、向きの取得や設定ができることが保証される。
直接インスタンスを作ることはできません。
役割は機能の“仕様”を定義するだけで、実際の処理はFacingのヘルパークラスを呼ぶ
 */
public interface IFacing {

    // このメソッドは ブロックの現在の向きを取得するためのもの。
    // BlockState は Minecraft のブロックの状態を表すオブジェクト
    // Direction は Minecraft の enum で、NORTH, SOUTH, EAST, WEST, UP, DOWN のいずれか
    Direction getFacing(BlockState state);

    // ブロックの向きを更新するためのメソッド
    // 戻り値として新しい BlockState を返す
    BlockState setFacing(BlockState state, Direction direction);


}
