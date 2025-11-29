package com.romp.entity;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class OffsetBlockRenderer implements BlockEntityRenderer<OffsetBlockEntity, BlockEntityRenderState> {

    @Override
    public BlockEntityRenderState createRenderState() {
        return null;  // 今回は特別な状態を作らないので null
    }

    @Override
    public void render(BlockEntityRenderState state,
                       MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {



        // ここにモデル描画を追加する
        // 例: BlockRenderManager 経由で BlockState を描画

    }

}
