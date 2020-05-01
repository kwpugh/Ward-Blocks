package com.kwpugh.ward_blocks.init;


import com.kwpugh.ward_blocks.WardBlocks;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit
{
	public static final Item CARBONADO = new Item((new Item.Settings()).group(WardBlocks.WARD_BLOCKS_GROUP));
	
	public static void registerItems()
	{		 
		Registry.register(Registry.ITEM, new Identifier("ward_blocks", "carbonado"), CARBONADO);
	}
}
