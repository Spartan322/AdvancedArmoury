package co.uk.silvania.advancedarmoury.items.modifiers;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.RarityRegistry;
import co.uk.silvania.advancedarmoury.items.EnumRarity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ModifierCoreBase extends Item implements IModifierCore {
	
	EnumRarity rarity;
	
	public ModifierCoreBase(EnumRarity rarity) {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(AdvancedArmoury.tabModifiers);
		this.rarity = rarity;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + this.getUnlocalizedName().substring(5));
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack item) {
		return (RarityRegistry.getColorFromRarity(rarity) + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(item) + ".name")).trim();
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean holding) {
		list.add(EnumChatFormatting.ITALIC + "Modifier Core");
		list.add(RarityRegistry.getRarityTag(rarity));
	}

	@Override public void onBlockShot(ItemStack item, EntityPlayer player, Block block) {}
	@Override public void onEntityShot(ItemStack item, EntityPlayer player, Entity entity) {}
	@Override public void onEntityKilled(ItemStack item, EntityPlayer player, Entity entity) {}
	@Override public void onFireWeapon(ItemStack item, EntityPlayer player) {}
	@Override public void onReload(ItemStack item, EntityPlayer player) {}
	@Override public void passiveModifier(ItemStack item, EntityPlayer player) {}

}
