package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class ExpWardBlockEntity extends BlockEntity implements Tickable
{	
	static int expRadius = WardBlocks.getConfig().EXP.expRadius;
	static int expLevel = WardBlocks.getConfig().EXP.expLevel;
	
	public ExpWardBlockEntity()
	{
		super(BlockInit.EXP_WARD_BLOCK_ENTITY);
	}
   
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			WardBlockEffects.giveExp(world, pos, expRadius, expLevel);
		}
	}
}