package com.kwpugh.ward_blocks;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.init.ItemInit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class WardBlocks implements ModInitializer
{	
	public static final String MOD_ID = "ward_blocks";
	public static final ItemGroup WARD_BLOCKS_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "ward_blocks_group"), () -> new ItemStack(BlockInit.DEFENSE_WARD_BLOCK));
	
	public static final WardBlocks INSTANCE = new WardBlocks();
	
    @Override
    public void onInitialize()
    {
    	BlockInit.registerBlocks();
    	BlockInit.registerBlockItems();
    	BlockInit.registerBlockEntities();
    	
    	ItemInit.registerItems();
    }
}