package com.kwpugh.ward_blocks.blocks;

import java.util.Iterator;
import java.util.List;

import com.kwpugh.ward_blocks.init.BlockInit;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class HealthWardBlockEntity extends BlockEntity implements Tickable
{		
	public HealthWardBlockEntity()
	{
		super(BlockInit.HEALTH_WARD_BLOCK_ENTITY);
	}
   
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			{
				// Scan for players in range
				Box playerBox = (new Box(this.pos)).expand(32.0D, 5.0D, 32.0D);
				List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerBox);
				Iterator iterator1 = list.iterator();
				
				ServerPlayerEntity targetPlayer;

				
				// Cycle through list and give effects to players				
				while(iterator1.hasNext())
				{
					targetPlayer = (ServerPlayerEntity)iterator1.next();
					
					StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.REGENERATION, 8, 1, false, false);
					StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.SATURATION, 8, 1, false, false);

					targetPlayer.addStatusEffect(effect);
					targetPlayer.addStatusEffect(effect2);
					
					giveGreaterExtraHearts(world, targetPlayer, null);
				}
			}
		}
	}
	      
	      
	//Faster increase of yellow hearts on tick update
	public static void giveGreaterExtraHearts(World world, LivingEntity player, ItemStack itemstack)
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