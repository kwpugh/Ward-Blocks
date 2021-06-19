package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class GrowthWardBlockEntity extends BlockEntity implements Tickable
{	
	static int growthRadius = WardBlocks.CONFIG.GENERAL.growthRange;
	
	public GrowthWardBlockEntity()
	{
		super(BlockInit.GROWTH_WARD_BLOCK_ENTITY);
	}
   
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			WardBlockEffects.growCrops(world, pos, 360, growthRadius);
		}
	}
}