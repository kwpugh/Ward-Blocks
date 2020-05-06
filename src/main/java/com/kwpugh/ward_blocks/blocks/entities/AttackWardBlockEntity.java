package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class AttackWardBlockEntity extends BlockEntity implements Tickable
{		
	public AttackWardBlockEntity()
	{
		super(BlockInit.ATTACK_WARD_BLOCK_ENTITY);
	}
   
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			WardBlockEffects.attackMobs(world, pos, 16.0D, 3.0F);
		}
	}
}