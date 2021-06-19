package com.kwpugh.ward_blocks.blocks.entities;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DefenseWardBlockEntity extends BlockEntity
{
	static int defenseRadius = WardBlocks.CONFIG.GENERAL.defenseRadius;
	static int defenseLevel = WardBlocks.CONFIG.GENERAL.defenseLevel;

    public DefenseWardBlockEntity(BlockPos pos, BlockState state)
    {
        super(BlockInit.DEFENSE_WARD_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity be)
    {
        if(!world.isClient && world.isReceivingRedstonePower(pos))
        {
            WardBlockEffects.giveDefense(world, pos, defenseRadius, defenseLevel);
        }
    }
}