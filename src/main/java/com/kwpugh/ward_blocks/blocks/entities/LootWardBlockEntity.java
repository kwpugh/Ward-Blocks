package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class LootWardBlockEntity extends BlockEntity implements Tickable
{
	public LootWardBlockEntity()
	{
		super(BlockInit.LOOT_WARD_BLOCK_ENTITY);
	}
   
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			WardBlockEffects.giveLoot(world, pos, 16);
		}
	}
}