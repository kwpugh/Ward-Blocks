package com.kwpugh.ward_blocks.blocks.blockentities;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

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
				WardBlockEffects.giveHealth(world, pos, 12.0D, 0, 20.0F);
			}
		}		
	}
}
