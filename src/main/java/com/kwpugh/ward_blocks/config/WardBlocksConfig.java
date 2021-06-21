package com.kwpugh.ward_blocks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "ward_blocks")
public class WardBlocksConfig extends PartitioningSerializer.GlobalData
{
	public General GENERAL = new General();

	@Config(name = "general")
	public static class General implements ConfigData {
		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nEnable-Disable Blocks"
				+ "\n***********************")
		public boolean enableGrowthBlock = true;
		public boolean enableHealthBlock = true;
		public boolean enableDefenseBlock = true;
		public boolean enableExpBlock = true;
		public boolean enableAttackBlock = true;
		public boolean enableLootBlock = true;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nExp Ward"
				+ "\n***********************")
		public int expRadius = 12;
		public int expLevel = 1;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nGrowth Ward"
				+ "\n***********************")
		public int growthHoriz = 10;
		public int growthHeight = 5;
		public int baseTickDelay = 90;
		public int cactusTickDelay = 20;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nHealth Ward"
				+ "\n***********************")
		public int healthRadius = 12;
		public int healthLevel = 0;
		public boolean enableYellowHearts = true;
		public float healthYellowHearts = 20;
		public int effectTickInterval = 8;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nDefense Ward"
				+ "\n***********************")
		public int defenseRadius = 12;
		public int defenseLevel = 0;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nAttack Ward"
				+ "\n***********************")
		public int attackRadius = 16;
		public int damageAmount = 3;

		@Comment("\n"
				+ "\n"
				+ "***********************"
				+ "\nLoot Ward"
				+ "\n***********************")
		public int lootRadius = 16;


	}
}