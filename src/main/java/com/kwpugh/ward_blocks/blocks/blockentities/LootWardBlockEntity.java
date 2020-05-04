package com.kwpugh.ward_blocks.blocks.blockentities;

import java.util.List;
import java.util.stream.IntStream;

import org.eclipse.jdt.annotation.Nullable;

import com.kwpugh.ward_blocks.init.BlockInit;
import com.kwpugh.ward_blocks.util.WardBlockEffects;
import com.kwpugh.ward_blocks.util.WardBlockInterface;
import com.kwpugh.ward_blocks.util.WardBlockScreenHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class LootWardBlockEntity extends LootableContainerBlockEntity implements WardBlockInterface, Tickable
{
	private DefaultedList<ItemStack> inventory;
	private int transferCooldown;
	private long lastTickTime;
	
	public LootWardBlockEntity()
	{
		super(BlockInit.LOOT_WARD_BLOCK_ENTITY);
	    this.inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
	    this.transferCooldown = -1;
	}
   
	@Override
	public void tick()
	{
		if(!world.isClient && world.isReceivingRedstonePower(this.pos))
		{
			WardBlockEffects.giveLoot(world, pos, 16);
			
			if(world.getTime() / 180 == 0)
			{
		
			}
		}
	}

	
	public void fromTag(BlockState state, CompoundTag tag)
	{
		super.fromTag(state, tag);
		this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
		if (!this.deserializeLootTable(tag))
		{
			Inventories.fromTag(tag, this.inventory);
		}

		this.transferCooldown = tag.getInt("TransferCooldown");
	}

	public CompoundTag toTag(CompoundTag tag)
	{
		super.toTag(tag);
		if (!this.serializeLootTable(tag))
		{
			Inventories.toTag(tag, this.inventory);
		}

		tag.putInt("TransferCooldown", this.transferCooldown);
		
		return tag;
	}		   
	  
	public ItemStack removeStack(int slot, int amount)
	{
		this.checkLootInteraction((PlayerEntity)null);
	    return Inventories.splitStack(this.getInvStackList(), slot, amount);
	}

	public void setStack(int slot, ItemStack stack)
	{
		this.checkLootInteraction((PlayerEntity)null);
		this.getInvStackList().set(slot, stack);
		if (stack.getCount() > this.getMaxCountPerStack())
		{
			stack.setCount(this.getMaxCountPerStack());
		}
	}
		 
	private boolean insert()
	{
		Inventory inventory = this.getOutputInventory();
	    if (inventory == null)
	    {
	    	return false;
	    }
	    else
	    {
	    	Direction direction = ((Direction)this.getCachedState().get(HopperBlock.FACING)).getOpposite();
	        if (this.isInventoryFull(inventory, direction))
	        {
	        	return false;
	        }
	        else
	        {
	        	for(int i = 0; i < this.size(); ++i)
	            {
	        		if (!this.getStack(i).isEmpty())
	        		{
	        			ItemStack itemStack = this.getStack(i).copy();
	        			ItemStack itemStack2 = transfer(this, inventory, this.removeStack(i, 1), direction);
	        			if (itemStack2.isEmpty())
	        			{
	        				inventory.markDirty();
	        				
	        				return true;
	        			}

	        			this.setStack(i, itemStack);
	        		}
	            }

	            return false;
	        }
	    }
	}
	
	   public static ItemStack transfer(@Nullable Inventory from, Inventory to, ItemStack stack, int slot, @Nullable Direction direction)
	   {
		      ItemStack itemStack = to.getStack(slot);
		      if (canInsert(to, stack, slot, direction)) {
		         boolean bl = false;
		         boolean bl2 = to.isEmpty();
		         if (itemStack.isEmpty()) {
		            to.setStack(slot, stack);
		            stack = ItemStack.EMPTY;
		            bl = true;
		         } else if (canMergeItems(itemStack, stack)) {
		            int i = stack.getMaxCount() - itemStack.getCount();
		            int j = Math.min(stack.getCount(), i);
		            stack.decrement(j);
		            itemStack.increment(j);
		            bl = j > 0;
		         }

		         if (bl) {
		            if (bl2 && to instanceof LootWardBlockEntity) {
		               LootWardBlockEntity lootWardBlockEntity = (LootWardBlockEntity)to;
		               if (!lootWardBlockEntity.isDisabled()) {
		                  int k = 0;
		                  if (from instanceof LootWardBlockEntity) {
		                	  LootWardBlockEntity lootWardBlockEntity2 = (LootWardBlockEntity)from;
		                     if (lootWardBlockEntity.lastTickTime >= lootWardBlockEntity2.lastTickTime) {
		                        k = 1;
		                     }
		                  }

		                  lootWardBlockEntity.setCooldown(8 - k);
		               }
		            }

		            to.markDirty();
		         }
		      }

		      return stack;
		   }
	 
	   public static ItemStack transfer(@Nullable Inventory from, Inventory to, ItemStack stack, @Nullable Direction side) {
		      if (to instanceof SidedInventory && side != null) {
		         SidedInventory sidedInventory = (SidedInventory)to;
		         int[] is = sidedInventory.getAvailableSlots(side);

		         for(int i = 0; i < is.length && !stack.isEmpty(); ++i) {
		            stack = transfer(from, to, stack, is[i], side);
		         }
		      } else {
		         int j = to.size();

		         for(int k = 0; k < j && !stack.isEmpty(); ++k) {
		            stack = transfer(from, to, stack, k, side);
		         }
		      }

		      return stack;
		   }
	   
	   private static boolean canInsert(Inventory inventory, ItemStack stack, int slot, @Nullable Direction side) {
		      if (!inventory.isValid(slot, stack)) {
		         return false;
		      } else {
		         return !(inventory instanceof SidedInventory) || ((SidedInventory)inventory).canInsert(slot, stack, side);
		      }
		   }
	   
	   @Nullable
	   private Inventory getOutputInventory()
	   {
	      Direction direction = (Direction)this.getCachedState().get(HopperBlock.FACING);
	      return getInventoryAt(this.getWorld(), this.pos.offset(direction));
	   }
	  
	   @Nullable
	   public static Inventory getInputInventory(WardBlockInterface wardblockinterface) {
	      return getInventoryAt(wardblockinterface.getWorld(), wardblockinterface.getWardBlockX(), wardblockinterface.getWardBlockY() + 1.0D, wardblockinterface.getWardBlockZ());
	   }
	   
	   @Nullable
	   public static Inventory getInventoryAt(World world, BlockPos blockPos)
	   {
	      return getInventoryAt(world, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D);
	   }
	
	   private boolean isInventoryFull(Inventory inv, Direction direction) {
		      return getAvailableSlots(inv, direction).allMatch((i) -> {
		         ItemStack itemStack = inv.getStack(i);
		         return itemStack.getCount() >= itemStack.getMaxCount();
		      });
		   }

	   private static boolean isInventoryEmpty(Inventory inv, Direction facing) {
	      return getAvailableSlots(inv, facing).allMatch((i) -> {
	         return inv.getStack(i).isEmpty();
	      });
	   }
	
	   private static IntStream getAvailableSlots(Inventory inventory, Direction side) {
	      return inventory instanceof SidedInventory ? IntStream.of(((SidedInventory)inventory).getAvailableSlots(side)) : IntStream.range(0, inventory.size());
	   }
	 
	   @Nullable
	   public static Inventory getInventoryAt(World world, double x, double y, double z) {
	      Inventory inventory = null;
	      BlockPos blockPos = new BlockPos(x, y, z);
	      BlockState blockState = world.getBlockState(blockPos);
	      Block block = blockState.getBlock();
	      if (block instanceof InventoryProvider) {
	         inventory = ((InventoryProvider)block).getInventory(blockState, world, blockPos);
	      } else if (block.hasBlockEntity()) {
	         BlockEntity blockEntity = world.getBlockEntity(blockPos);
	         if (blockEntity instanceof Inventory) {
	            inventory = (Inventory)blockEntity;
	            if (inventory instanceof ChestBlockEntity && block instanceof ChestBlock) {
	               inventory = ChestBlock.getInventory((ChestBlock)block, blockState, world, blockPos, true);
	            }
	         }
	      }

	      if (inventory == null) {
	         List<Entity> list = world.getEntities((Entity)null, new Box(x - 0.5D, y - 0.5D, z - 0.5D, x + 0.5D, y + 0.5D, z + 0.5D), EntityPredicates.VALID_INVENTORIES);
	         if (!list.isEmpty()) {
	            inventory = (Inventory)list.get(world.random.nextInt(list.size()));
	         }
	      }

	      return (Inventory)inventory;
	   }
   
	   private static boolean canMergeItems(ItemStack first, ItemStack second) {
		      if (first.getItem() != second.getItem()) {
		         return false;
		      } else if (first.getDamage() != second.getDamage()) {
		         return false;
		      } else if (first.getCount() > first.getMaxCount()) {
		         return false;
		      } else {
		         return ItemStack.areTagsEqual(first, second);
		      }
		   }
	   
	@Override
	public int size()
	{
		return this.inventory.size();
	}

	@Override
	protected DefaultedList<ItemStack> getInvStackList()
	{
		return this.inventory;
	}

	@Override
	protected void setInvStackList(DefaultedList<ItemStack> list)
	{
		this.inventory = list;		
	}

	@Override
	protected Text getContainerName()
	{
		return new TranslatableText("Loot Ward Block");
	}

	@Override
	protected ScreenHandler createContainer(int i, PlayerInventory playerInventory)
	{
		return new WardBlockScreenHandler(i, playerInventory, this);
	}

	   private void setCooldown(int cooldown) {
		      this.transferCooldown = cooldown;
		   }

		   private boolean needsCooldown() {
		      return this.transferCooldown > 0;
		   }

		   private boolean isDisabled() {
		      return this.transferCooldown > 8;
		   }
		   
	@Override
	public double getWardBlockX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWardBlockY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWardBlockZ() {
		// TODO Auto-generated method stub
		return 0;
	}
}