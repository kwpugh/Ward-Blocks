package com.kwpugh.ward_blocks.blocks;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.GrowthUtil;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class GrowthWardBlockEntity extends BlockEntity implements Tickable
{		
	public GrowthWardBlockEntity()
	{
		super(BlockInit.GROWTH_WARD_BLOCK_ENTITY);
	}
   
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			GrowthUtil.growCrops(world, pos, 360, 10);
		}
	}
}