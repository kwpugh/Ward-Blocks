package com.kwpugh.ward_blocks.init;

import com.kwpugh.ward_blocks.WardBlocks;
import com.kwpugh.ward_blocks.blocks.AttackWardBlock;
import com.kwpugh.ward_blocks.blocks.AttackWardBlockEntity;
import com.kwpugh.ward_blocks.blocks.DefenseWardBlock;
import com.kwpugh.ward_blocks.blocks.DefenseWardBlockEntity;
import com.kwpugh.ward_blocks.blocks.GrowthWardBlock;
import com.kwpugh.ward_blocks.blocks.GrowthWardBlockEntity;
import com.kwpugh.ward_blocks.blocks.HealthWardBlock;
import com.kwpugh.ward_blocks.blocks.HealthWardBlockEntity;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit
{
	public static BlockEntityType<HealthWardBlockEntity> HEALTH_WARD_BLOCK_ENTITY;
	public static BlockEntityType<AttackWardBlockEntity> ATTACK_WARD_BLOCK_ENTITY;
	public static BlockEntityType<GrowthWardBlockEntity> GROWTH_WARD_BLOCK_ENTITY;
	public static BlockEntityType<DefenseWardBlockEntity> DEFENSE_WARD_BLOCK_ENTITY;
	
	public static final Block HEALTH_WARD_BLOCK = new HealthWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F).build());
	public static final Block ATTACK_WARD_BLOCK = new AttackWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F).build());
	public static final Block GROWTH_WARD_BLOCK = new GrowthWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F).build());
	public static final Block DEFENSE_WARD_BLOCK = new DefenseWardBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F).build());
	
	public static void registerBlocks()
	{
		Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "health_ward_block"), HEALTH_WARD_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "attack_ward_block"), ATTACK_WARD_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "growth_ward_block"), GROWTH_WARD_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier("ward_blocks", "defense_ward_block"), DEFENSE_WARD_BLOCK);
	}
	
	public static void registerBlockItems()
	{
		Registry.register(Registry.ITEM, new Identifier("ward_blocks", "health_ward_block"), new BlockItem(HEALTH_WARD_BLOCK, new Item.Settings().group(WardBlocks.WARD_BLOCKS_GROUP)));
		Registry.register(Registry.ITEM, new Identifier("ward_blocks", "attack_ward_block"), new BlockItem(ATTACK_WARD_BLOCK, new Item.Settings().group(WardBlocks.WARD_BLOCKS_GROUP)));
		Registry.register(Registry.ITEM, new Identifier("ward_blocks", "growth_ward_block"), new BlockItem(GROWTH_WARD_BLOCK, new Item.Settings().group(WardBlocks.WARD_BLOCKS_GROUP)));
		Registry.register(Registry.ITEM, new Identifier("ward_blocks", "defense_ward_block"), new BlockItem(DEFENSE_WARD_BLOCK, new Item.Settings().group(WardBlocks.WARD_BLOCKS_GROUP)));
	}
	
	public static void registerBlockEntities()
	{
		HEALTH_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:health_ward_block", BlockEntityType.Builder.create(HealthWardBlockEntity::new, HEALTH_WARD_BLOCK).build(null));
		ATTACK_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:attack_ward_block", BlockEntityType.Builder.create(AttackWardBlockEntity::new, ATTACK_WARD_BLOCK).build(null));
		GROWTH_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:growth_ward_block", BlockEntityType.Builder.create(GrowthWardBlockEntity::new, GROWTH_WARD_BLOCK).build(null));
		DEFENSE_WARD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "ward_blocks:defense_ward_block", BlockEntityType.Builder.create(DefenseWardBlockEntity::new, DEFENSE_WARD_BLOCK).build(null));
	}
}