package com.kwpugh.ward_blocks.util;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;
import com.kwpugh.ward_blocks.init.BlockInit;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class WardBlocksLootTables
{
	private static final List<LootTableInsert> INSERTS = Lists.newArrayList();


	public static void init()
	{
		FabricLootPoolBuilder GROWTH_WARD_BLOCK = FabricLootPoolBuilder.builder()
				.withRolls(ConstantLootTableRange.create(1))
				.withEntry(ItemEntry.builder(BlockInit.GROWTH_WARD_BLOCK))
				.withCondition(RandomChanceLootCondition.builder(0.10F).build());

		insert(new LootTableInsert(GROWTH_WARD_BLOCK,
				new Identifier("minecraft", "chests/buried_treasure")
		));
		
		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
			INSERTS.forEach(i->{
				if(ArrayUtils.contains(i.tables, identifier))
				{
					i.insert(supplier);
				}
			});
		}));
		
		FabricLootPoolBuilder HEALTH_WARD_BLOCK = FabricLootPoolBuilder.builder()
				.withRolls(ConstantLootTableRange.create(1))
				.withEntry(ItemEntry.builder(BlockInit.HEALTH_WARD_BLOCK))
				.withCondition(RandomChanceLootCondition.builder(0.10F).build());

		insert(new LootTableInsert(HEALTH_WARD_BLOCK,
				new Identifier("minecraft", "chests/buried_treasure")
		));
		
		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
			INSERTS.forEach(i->{
				if(ArrayUtils.contains(i.tables, identifier))
				{
					i.insert(supplier);
				}
			});
		}));
		
		FabricLootPoolBuilder DEFENSE_WARD_BLOCK = FabricLootPoolBuilder.builder()
				.withRolls(ConstantLootTableRange.create(1))
				.withEntry(ItemEntry.builder(BlockInit.DEFENSE_WARD_BLOCK))
				.withCondition(RandomChanceLootCondition.builder(0.10F).build());

		insert(new LootTableInsert(DEFENSE_WARD_BLOCK,
				new Identifier("minecraft", "chests/pillager_outpost")
		));
		
		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
			INSERTS.forEach(i->{
				if(ArrayUtils.contains(i.tables, identifier))
				{
					i.insert(supplier);
				}
			});
		}));
		
		FabricLootPoolBuilder EXP_WARD_BLOCK = FabricLootPoolBuilder.builder()
				.withRolls(ConstantLootTableRange.create(1))
				.withEntry(ItemEntry.builder(BlockInit.EXP_WARD_BLOCK))
				.withCondition(RandomChanceLootCondition.builder(0.10F).build());

		insert(new LootTableInsert(EXP_WARD_BLOCK,
				new Identifier("minecraft", "chests/stronghold_library")
		));
		
		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
			INSERTS.forEach(i->{
				if(ArrayUtils.contains(i.tables, identifier))
				{
					i.insert(supplier);
				}
			});
		}));

		FabricLootPoolBuilder ATTACK_WARD_BLOCK = FabricLootPoolBuilder.builder()
				.withRolls(ConstantLootTableRange.create(1))
				.withEntry(ItemEntry.builder(BlockInit.ATTACK_WARD_BLOCK))
				.withCondition(RandomChanceLootCondition.builder(0.10F).build());

		insert(new LootTableInsert(ATTACK_WARD_BLOCK,
				new Identifier("minecraft", "chests/bastion_treasure")
		));
		
		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
			INSERTS.forEach(i->{
				if(ArrayUtils.contains(i.tables, identifier))
				{
					i.insert(supplier);
				}
			});
		}));

		FabricLootPoolBuilder LOOT_WARD_BLOCK = FabricLootPoolBuilder.builder()
				.withRolls(ConstantLootTableRange.create(1))
				.withEntry(ItemEntry.builder(BlockInit.LOOT_WARD_BLOCK))
				.withCondition(RandomChanceLootCondition.builder(0.10F).build());

		insert(new LootTableInsert(LOOT_WARD_BLOCK,
				new Identifier("minecraft", "chests/end_city_treasure")
		));
		
		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, supplier, lootTableSetter) -> {
			INSERTS.forEach(i->{
				if(ArrayUtils.contains(i.tables, identifier))
				{
					i.insert(supplier);
				}
			});
		}));
		
	}

	public static void insert(LootTableInsert insert)
	{
		INSERTS.add(insert);
	}

	public static class LootTableInsert
	{
		public final Identifier[] tables;
		public final FabricLootPoolBuilder lootPool;

		public LootTableInsert(FabricLootPoolBuilder lootPool, Identifier... tables)
		{
			this.tables = tables;
			this.lootPool = lootPool;
		}

		public void insert(FabricLootSupplierBuilder supplier)
		{
			supplier.withPool(lootPool);
		}
	}
}