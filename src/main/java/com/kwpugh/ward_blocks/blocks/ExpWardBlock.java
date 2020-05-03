package com.kwpugh.ward_blocks.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class ExpWardBlock extends Block implements BlockEntityProvider
{
	public ExpWardBlock(Settings settings)
	{
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView)
	{
		return new ExpWardBlockEntity();
	}
}