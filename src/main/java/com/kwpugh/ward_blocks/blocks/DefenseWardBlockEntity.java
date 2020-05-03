package com.kwpugh.ward_blocks.blocks;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class DefenseWardBlockEntity extends BlockEntity implements Tickable
{		
	public DefenseWardBlockEntity()
	{
		super(BlockInit.DEFENSE_WARD_BLOCK_ENTITY);
	}
   
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			WardBlockEffects.giveDefense(world, pos, 12.0D, 0);
		}
	}
}