package com.kwpugh.ward_blocks.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

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
				+ "\nBlock Settings"
				+ "\n***********************")
		public int growthRange = 10;
		public int healthRadius = 12;
		public int healthLevel = 0;
		public float healthYellowHearts = 20;
		public int defenseRadius = 12;
		public int defenseLevel = 0;
		public int expRadius = 12;
		public int expLevel = 1;
		public int attackRadius = 16;
		public int damageAmount = 3;
		public int lootRadius = 16;
	}
}