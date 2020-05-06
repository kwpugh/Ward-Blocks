package com.kwpugh.ward_blocks.blocks;

import com.kwpugh.ward_blocks.blocks.entities.AttackWardBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class AttackWardBlock extends Block implements BlockEntityProvider
{
	public AttackWardBlock(Settings settings)
	{
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView)
	{
		return new AttackWardBlockEntity();
	}
}