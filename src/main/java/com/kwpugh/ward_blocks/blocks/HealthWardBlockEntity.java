package com.kwpugh.ward_blocks.blocks;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.HealthUtil;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class HealthWardBlockEntity extends BlockEntity implements Tickable
{		
	public HealthWardBlockEntity()
	{
		super(BlockInit.HEALTH_WARD_BLOCK_ENTITY);
	}
	        
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			{
				HealthUtil.Health(world, pos, 8.0D, 8.0D, 8.0D);
			}
		}		
	}
}
