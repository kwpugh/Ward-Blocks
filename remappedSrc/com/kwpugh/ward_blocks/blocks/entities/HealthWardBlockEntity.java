package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class HealthWardBlockEntity extends BlockEntity implements Tickable
{
	static int healthRadius = WardBlocks.CONFIG.GENERAL.healthRadius;
	static int healthLevel = WardBlocks.CONFIG.GENERAL.healthLevel;
	static float healthYellowHearts = WardBlocks.CONFIG.GENERAL.healthYellowHearts;
	
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
				WardBlockEffects.giveHealth(world, pos, healthRadius, healthLevel, healthYellowHearts);
			}
		}		
	}
}
