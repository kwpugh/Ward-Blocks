package com.kwpugh.ward_blocks.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class WardBlockEffects
{		
	// Removes most mobs and spawns defined loot above block
	public static void giveLoot(World world, BlockPos pos, int radius)
	{
		// Scan for hostile mobs
		Box mobBox = (new Box(pos)).expand(radius, 10.0D, radius);
		List<Entity> list2 = world.getNonSpectatingEntities(Entity.class, mobBox);
		Iterator iterator2 = list2.iterator();
		
		Entity targetEntity;
		
		// Cycle through and spawn loot and XP
		while(iterator2.hasNext())
		{
			targetEntity = (Entity)iterator2.next();
			if(targetEntity instanceof HostileEntity)
			{				
				targetEntity.remove(Entity.RemovalReason.KILLED);
				((HostileEntity) targetEntity).playSpawnEffects();
				
				ItemStack drop;
				
				if(targetEntity instanceof ZombieEntity)
				{
					drop = new ItemStack(Items.GOLD_INGOT);				
				}
				else if(targetEntity instanceof SpiderEntity)
				{
					drop = new ItemStack(Items.STRING);				
				}
				else if(targetEntity instanceof SkeletonEntity)
				{
					drop = new ItemStack(Items.BONE);				
				}
				else if(targetEntity instanceof PatrolEntity)
				{
					drop = new ItemStack(Items.IRON_INGOT);				
				}
				else if(targetEntity instanceof CreeperEntity)
				{
					drop = new ItemStack(Items.GUNPOWDER);				
				}				
				else if(targetEntity instanceof SlimeEntity)
				{
					drop = new ItemStack(Items.SLIME_BALL);				
				}
				else if(targetEntity instanceof WitchEntity)
				{
					drop = new ItemStack(Items.EMERALD);				
				}
				else if(targetEntity instanceof EndermanEntity)
				{
					drop = new ItemStack(Items.ENDER_PEARL);				
				}
				else if(targetEntity instanceof BlazeEntity)
				{
					drop = new ItemStack(Items.BLAZE_ROD);				
				}
				else if(targetEntity instanceof WitherSkeletonEntity)
				{
					drop = new ItemStack(Items.WITHER_SKELETON_SKULL);				
				}
				else if(targetEntity instanceof PhantomEntity)
				{
					drop = new ItemStack(Items.PHANTOM_MEMBRANE);				
				}
				else
				{
					drop = new ItemStack(Items.IRON_NUGGET);				
				}
				
				world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY()+1, pos.getZ(), drop));
			}
		}
	}
	
	// Removes most mobs and spawns XP by above block
	public static void giveExp(World world, BlockPos pos, int radius, int orbAmount)
	{
		// Scan for hostile mobs
		Box mobBox = (new Box(pos)).expand(radius, 10.0D, radius);
		List<Entity> list2 = world.getNonSpectatingEntities(Entity.class, mobBox);
		Iterator iterator2 = list2.iterator();
		
		Entity targetEntity;
		
		// Cycle through and spawn XP
		while(iterator2.hasNext())
		{
			targetEntity = (Entity)iterator2.next();
			if(targetEntity instanceof HostileEntity)
			{				
				targetEntity.remove(Entity.RemovalReason.KILLED);
				((HostileEntity) targetEntity).playSpawnEffects();
				world.spawnEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY()+1, pos.getZ(), 1));
			}
		}
	}
	
	// Cause mobs to target themselves, disable AI on creepers and skeleton 
	public static void attackMobs(World world, BlockPos pos, int radius, float damageAmount)
	{
		// Scan for hostile mobs
		Box mobBox = (new Box(pos)).expand(radius, 10.0D, radius);
		List<Entity> list2 = world.getNonSpectatingEntities(Entity.class, mobBox);
		Iterator iterator2 = list2.iterator();
		
		Entity targetEntity;
		
		// Cycle through and effect entities
		while(iterator2.hasNext())
		{
			targetEntity = (Entity)iterator2.next();
			if(targetEntity instanceof HostileEntity)
			{				
				targetEntity.damage(DamageSource.GENERIC, damageAmount);
				((HostileEntity) targetEntity).setTarget((LivingEntity) targetEntity);
				if(targetEntity instanceof CreeperEntity || targetEntity instanceof SkeletonEntity)
				{
					((HostileEntity) targetEntity).setAiDisabled(true);
				}
			}
		}
	}
	
	// Gives player strength and resistance
	public static void giveDefense(World world, BlockPos pos, int radius, int effectLevel, int effectTickInterval)
	{
		// Scan for players in range
		Box playerBox = (new Box(pos)).expand(radius, 8.0D, radius);
		List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerBox);
		Iterator iterator1 = list.iterator();
		
		ServerPlayerEntity targetPlayer;

		// Cycle through list and give effects to players				
		while(iterator1.hasNext())
		{
			targetPlayer = (ServerPlayerEntity)iterator1.next();

			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.RESISTANCE, effectTickInterval, effectLevel, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.STRENGTH, effectTickInterval, effectLevel, false, false);

			targetPlayer.addStatusEffect(effect);
			targetPlayer.addStatusEffect(effect2);
		}
	}
	
	//  Accelerates growth in area of effect
	public static void growCrops(World world, BlockPos pos, int baseTickDelay, int cactusTickDelay, int radius, int height)
	{
		ServerWorld serverWorld = (ServerWorld) world;

		BlockPos growthBlock = pos;

		// For blocks that use a grow() method and implement Fertilizable
		for (BlockPos target : BlockPos.iterateOutwards(growthBlock, radius, height, radius))
		{
			BlockState blockstate = world.getBlockState(target);
			Block block = blockstate.getBlock();

			if (   (block instanceof CropBlock) ||  //Beets Carrots Potatoes
					block instanceof BambooSaplingBlock ||
					block instanceof BambooBlock ||
					block instanceof CocoaBlock ||
					block instanceof StemBlock ||
					block instanceof SweetBerryBushBlock ||
					block instanceof FungusBlock ||
					block instanceof SaplingBlock ||
					block instanceof SeaPickleBlock ||
					block instanceof KelpBlock ||
					block instanceof KelpPlantBlock
					)
			{
				Fertilizable fertilizable = (Fertilizable)blockstate.getBlock();
				if (fertilizable.isFertilizable(world, target, blockstate, world.isClient))
				{
					if (fertilizable.canGrow(world, world.random, target, blockstate))
					{
						if (world.getTime() % (baseTickDelay) == 0)
						{	
							fertilizable.grow((ServerWorld)world, world.random, target, blockstate);
						} 
					}
				}
			}
		}

		// For those blocks that lack grow() we fall back to randomTick()
		for (BlockPos tickTarget : BlockPos.iterateOutwards(growthBlock, radius, height, radius))
		{
			BlockState blockstate2 = world.getBlockState(tickTarget);
			Block blockToTick = blockstate2.getBlock();
				
			if(blockToTick instanceof SugarCaneBlock || 
					blockToTick instanceof CactusBlock ||
					blockToTick instanceof ChorusFlowerBlock
					)
			{
				if (world.getTime() % (cactusTickDelay) == 0)
				{
					blockToTick.randomTick(blockstate2, (ServerWorld) world, tickTarget, world.random);
				}				
			}
		}
	}
	
	// Gives player heal, regeneration, and yellow hearts
	public static void giveHealth(World world, BlockPos pos, int radius, int effectLevel, int effectTickInterval, boolean enableExtraHearts, float yellowHearts)
	{
		// Scan for players in range
		Box playerBox = (new Box(pos)).expand(radius, 4, radius);
		List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerBox);
		Iterator iterator1 = list.iterator();
		
		ServerPlayerEntity targetPlayer;
		
		// Cycle through list and give effects to players				
		while(iterator1.hasNext())
		{
			targetPlayer = (ServerPlayerEntity)iterator1.next();
			
			StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.REGENERATION, effectTickInterval, effectLevel, false, false);
			StatusEffectInstance effect2 = new StatusEffectInstance(StatusEffects.SATURATION, effectTickInterval, effectLevel, false, false);

			targetPlayer.addStatusEffect(effect);
			targetPlayer.addStatusEffect(effect2);

			if(enableExtraHearts)
			{
				giveGreaterExtraHearts(world, targetPlayer, yellowHearts);
			}
		}
	}
	
	//Increase of yellow hearts on tick update
	public static void giveGreaterExtraHearts(World world, LivingEntity player, float yellowHearts)
	{
		if(!world.isClient)
		{
			float current = player.getAbsorptionAmount();
		
			if(player.getHealth() < yellowHearts || current >= yellowHearts)
			{
				return;
			}
		
			if(current >= (yellowHearts - 1.0F))
			{
				if (player.age % 20 == 0)
				{
					player.setAbsorptionAmount(yellowHearts);
				} 
				
				return;
			}
			if(current < (yellowHearts - 1.0F))
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
