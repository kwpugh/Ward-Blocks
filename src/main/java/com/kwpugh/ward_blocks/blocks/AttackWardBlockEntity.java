package com.kwpugh.ward_blocks.blocks;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.AttackUtil;

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
//			BlockPos posUp = pos.up();		
//			BlockState flaming = Blocks.FIRE.getDefaultState();
//			world.setBlockState(posUp, flaming, 11);
			
			AttackUtil.attack(world, pos);
		}
	}
}