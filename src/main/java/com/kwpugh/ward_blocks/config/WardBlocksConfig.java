package com.kwpugh.ward_blocks.config;

import com.kwpugh.ward_blocks.WardBlocks;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name=WardBlocks.MOD_ID)
public class WardBlocksConfig implements ConfigData
{
	public Blocks BLOCKS = new Blocks();
	public Growth GROWTH = new Growth();
	public Health HEALTH = new Health();
	public Defense DEFENSE = new Defense();
	public Exp EXP = new Exp();
	public Attack ATTACK = new Attack();
	public Loot LOOT = new Loot();
	
	public static class Blocks
	{
		public boolean enableGrowthBlock = true;
		public boolean enableHealthBlock = true;
		public boolean enableDefenseBlock = true;
		public boolean enableExpBlock = true;
		public boolean enableAttackBlock = true;
		public boolean enableLootBlock = true;
	}
	
	public static class Growth
	{
		public int growthRange = 10;
	}
	
	public static class Health
	{
		public int healthRadius = 12;
		public int healthLevel = 0;
		public float healthYellowHearts = 20;
	}
	
	public static class Defense
	{
		public int defenseRadius = 12;
		public int defenseLevel = 0;
	}
	
	public static class Exp
	{
		public int expRadius = 12;
		public int expLevel = 1;
	}
	
	public static class Attack
	{
		public int attackRadius = 16;
		public int damageAmount = 3;
	}
	
	public static class Loot
	{
		public int lootRadius = 16;
	}
}