package com.kwpugh.ward_blocks.blocks;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.DefenseUtil;

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
			DefenseUtil.Defense(world, pos);
		}
	}
}