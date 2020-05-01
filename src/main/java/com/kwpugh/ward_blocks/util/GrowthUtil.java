package com.kwpugh.ward_blocks.util;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.FungusBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GrowthUtil
{
	public static void growCrops(World world, BlockPos pos, int baseTickDelay, int radius)
	{
		ServerWorld serverWorld = (ServerWorld) world;
	
		BlockPos growthBlock = pos;
	
		for (BlockPos target : BlockPos.iterateOutwards(growthBlock, radius, 5, radius))
		{
			BlockState blockstate = world.getBlockState(target);
			Block block = blockstate.getBlock();

			if (   (block instanceof CropBlock) ||  //Beets Carrots Potatoes
					block instanceof BambooSaplingBlock ||
					block instanceof BambooBlock ||
					block instanceof CocoaBlock ||
					block instanceof StemBlock ||
					block instanceof SweetBerryBushBlock ||
					block instanceof FungusBlock ||
					block instanceof SaplingBlock ||
					block instanceof SeaPickleBlock 
					)
			{
				Fertilizable fertilizable = (Fertilizable)blockstate.getBlock();
				if (fertilizable.isFertilizable(world, target, blockstate, world.isClient))
				{
					if (fertilizable.canGrow(world, world.random, target, blockstate))
					{
						if (world.getTime() % (baseTickDelay) == 0)
						{	
							fertilizable.grow((ServerWorld)world, world.random, target, blockstate);
						} 
					}
				}
			}
		}
		
		for (BlockPos tickTarget : BlockPos.iterateOutwards(growthBlock, radius, 5, radius))
		{
			BlockState blockstate2 = world.getBlockState(tickTarget);
			Block blockToTick = blockstate2.getBlock();
				
			if(blockToTick instanceof SugarCaneBlock || 
					blockToTick instanceof CactusBlock ||
					blockToTick instanceof ChorusFlowerBlock)   
			{
				if (world.getTime() % (baseTickDelay / 16) == 0)
				{
					blockToTick.randomTick(blockstate2, (ServerWorld) world, tickTarget, world.random);
				}				
			}
		}
	}
}