package co.uk.silvania.advancedarmoury.gun.inventory.assault;

import co.uk.silvania.advancedarmoury.blocks.machines.SlotComponent;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotComponentOption;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotCore;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.SlotAttachment;
import net.minecraft.item.ItemStack;

public class AssaultIInventory extends ItemIInventory {

	public AssaultIInventory(ItemStack item) {
		super(item, 32);
		// TODO Auto-generated constructor stub
	}
	
	public ItemStack getBarrel() { 		return this.getStackInSlot(2); }
	public ItemStack getFrontEnd() { 	return this.getStackInSlot(3); }
	public ItemStack getReceiver() { 	return this.getStackInSlot(4); }
	public ItemStack getStock() { 		return this.getStackInSlot(5); }
	
	public ItemStack getModifierCore() { return this.getStackInSlot(6); }
}
