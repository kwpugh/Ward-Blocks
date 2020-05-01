package com.kwpugh.ward_blocks.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class DefenseUtil
{
	public static void Defense(World world, BlockPos pos)
	{
		// Scan for players in range
		Box playerBox = (new Box(pos)).expand(8.0D, 8.0D, 8.0D);
		List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerBox);
		Iterator iterator1 = list.iterator();
		
		ServerPlayerEntity targetPlayer;

		
		// Cycle through list and give effects to players				
		while(iterator1.hasNext())
		{
			targetPlayer = (ServerPlayerEntity)iterator1.next();
			
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.RESISTANCE, 8, 0, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.STRENGTH, 8, 0, false, false);

			targetPlayer.addStatusEffect(effect);
			targetPlayer.addStatusEffect(effect2);
		}
	}
}
