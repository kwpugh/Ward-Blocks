package com.kwpugh.ward_blocks.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class HealthUtil
{
	public static void Health(World world, BlockPos pos, double x, double y, double z)
	{
		// Scan for players in range
		Box playerBox = (new Box(pos)).expand(x, y, z);
		List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerBox);
		Iterator iterator1 = list.iterator();
		
		ServerPlayerEntity targetPlayer;
		
		// Cycle through list and give effects to players				
		while(iterator1.hasNext())
		{
			targetPlayer = (ServerPlayerEntity)iterator1.next();
			
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.REGENERATION, 8, 0, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.SATURATION, 8, 0, false, false);

			targetPlayer.addStatusEffect(effect);
			targetPlayer.addStatusEffect(effect2);
			
			giveGreaterExtraHearts(world, targetPlayer);
		}
	}
	
	//Faster increase of yellow hearts on tick update
		public static void giveGreaterExtraHearts(World world, LivingEntity player)
		{
			if(!world.isClient)
			{
				float current = player.getAbsorptionAmount();
			
				if(player.getHealth() < 20 || current >= 20)
				{
					return;
				}
			
				if(current >= 19)
				{
					if (player.age % 20 == 0)
					{
						player.setAbsorptionAmount(20);
					} 
					
					return;
				}
				if(current < 19)
				{
					if (player.age % 20 == 0)
					{
						player.setAbsorptionAmount(current + 1.0F);
					} 
		
					return;
				}
			
				return;
			}	
		}
}
