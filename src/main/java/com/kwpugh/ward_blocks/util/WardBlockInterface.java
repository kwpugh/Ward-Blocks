package com.kwpugh.ward_blocks.util;


import org.eclipse.jdt.annotation.Nullable;

import net.minecraft.inventory.Inventory;
import net.minecraft.world.World;

public interface WardBlockInterface extends Inventory
{
	   @Nullable
	   World getWorld();

	   double getWardBlockX();

	   double getWardBlockY();

	   double getWardBlockZ();
}
