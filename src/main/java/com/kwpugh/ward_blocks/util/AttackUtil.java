package com.kwpugh.ward_blocks.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class AttackUtil
{
	public static void attack(World world, BlockPos pos)
	{
		// Scan for hostile mobs
		Box mobBox = (new Box(pos)).expand(16.0D, 10.0D, 16.0D);
		List<Entity> list2 = world.getNonSpectatingEntities(Entity.class, mobBox);
		Iterator iterator2 = list2.iterator();
		
		Entity targetEntity;
		
		// Cycle through and damage entities
		while(iterator2.hasNext())
		{
			targetEntity = (Entity)iterator2.next();
			if(targetEntity instanceof HostileEntity)
			{				
				targetEntity.damage(DamageSource.GENERIC, 2);
				((HostileEntity) targetEntity).setTarget((LivingEntity) targetEntity);
				if(targetEntity instanceof CreeperEntity || targetEntity instanceof SkeletonEntity)
				{
					((HostileEntity) targetEntity).setAiDisabled(true);
				}
			}
		}
	}
}
