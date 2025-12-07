package com.romp.client;

import com.romp.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;


public class PonmaruModClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        // ブロックの一部を透明にする
        BlockRenderLayerMap.putBlock(ModBlocks.BONSAI_POT, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DARK_OAK_LANTERN, BlockRenderLayer.CUTOUT);


    }




}