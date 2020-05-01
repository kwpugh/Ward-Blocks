package com.kwpugh.ward_blocks.blocks;

import java.util.Iterator;
import java.util.List;

import com.kwpugh.ward_blocks.init.BlockInit;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;

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
			{
				// Scan for hostile mobs
				Box mobBox = (new Box(this.pos)).expand(12.0D, 10.0D, 12.0D);
				List<Entity> list2 = world.getNonSpectatingEntities(Entity.class, mobBox);
				Iterator iterator2 = list2.iterator();
				
				Entity targetEntity;
				
				// Cycle through and damage entities
				while(iterator2.hasNext())
				{
					targetEntity = (Entity)iterator2.next();
					if(targetEntity instanceof HostileEntity)
					{
						targetEntity.damage(DamageSource.FALL, 5);
						world.spawnEntity(new ExperienceOrbEntity(world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 1));
					}
				}
			}
		}
	}
}