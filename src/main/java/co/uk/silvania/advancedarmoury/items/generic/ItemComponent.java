package co.uk.silvania.advancedarmoury.items.generic;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.RarityRegistry;
import co.uk.silvania.advancedarmoury.config.ComponentGenerator;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items.EnumRarity;
import co.uk.silvania.advancedarmoury.items.MaterialRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemComponent extends Item {

	protected MaterialStats stats;
	public String componentName;
	public String displayName;
	public String identifier;
	
	public boolean accuracyEnabled;
	public boolean powerEnabled;
	public boolean rangeEnabled;
	public boolean cosmeticEnabled;
	public boolean fireRateEnabled;
	public boolean calibreEnabled;
	
	public double size;
	
	ComponentGenerator compGen;
	
	//Only used by asset components
	public String modelName = "m4receiver";
	public String modelTexture = "m4receiver";
	public String iconTexture = "m4receiver";
	public String weaponType;
	public float xSize = 0;
	public float ySize = 0;
	public float zSize = 0;
	
	public boolean assetComponent = false;
	
	public ItemComponent(String componentName, String displayName, String identifier, double size, boolean acc, boolean pwr, boolean rng, boolean cos, boolean frt, boolean cal) {
		this.componentName = componentName;
		this.displayName = displayName;
		this.identifier = identifier;
		this.size = size;
		
		this.accuracyEnabled = acc;
		this.powerEnabled 	 = pwr;
		this.rangeEnabled 	 = rng;
		this.cosmeticEnabled = cos;
		this.fireRateEnabled = frt;
		this.calibreEnabled  = cal;
	}
	
	//For decorative-only stuff, such as assets, trigger etc
	public ItemComponent(String componentName, String displayName, String identifier, double size) {
		this(componentName, displayName, identifier, size, false, false, false, true, false, false);
	}
	
	@Override
    public String getItemStackDisplayName(ItemStack item) {
        return getMaterial(item) + " " + displayName;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + componentName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack item, int par2) {
		if (item.stackTagCompound != null) {
			return item.stackTagCompound.getInteger("itemCol");
		}
		return 16777215;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < MaterialRegistry.materials.size(); i++) {
			ItemStack itemComponent = new ItemStack(item);
			
			String[] splitter = MaterialRegistry.materials.get(i).split(",");
			String materialName = splitter[0].trim();
			double durability = compGen.parseDouble(splitter[1].trim());
			int weight = compGen.parseInt(splitter[2].trim());
			float accuracy = compGen.parseFloat(splitter[3].trim());
			String textCol = splitter[4].trim();
			int rgb = compGen.parseInt(splitter[5].trim());
			int fireRate = compGen.parseInt(splitter[6].trim());
			String oreDict = splitter[7].trim();
			int power = compGen.parseInt(splitter[8].trim());
			int range = compGen.parseInt(splitter[9].trim());
			String rarity = splitter[10].trim();
			
			String cosmetic = materialName;
				
			itemComponent.stackTagCompound = new NBTTagCompound();
			
			itemComponent.stackTagCompound.setString("componentName", componentName);
			itemComponent.stackTagCompound.setString("materialName", materialName);
			itemComponent.stackTagCompound.setString("textCol", textCol);
			itemComponent.stackTagCompound.setInteger("itemCol", rgb);
			itemComponent.stackTagCompound.setString("oreDict", oreDict);
			
			itemComponent.stackTagCompound.setDouble("durability", durability*size*50);
			itemComponent.stackTagCompound.setInteger("weight", (int) Math.round(weight*size));
			
			itemComponent.stackTagCompound.setInteger("cost", (int) Math.round((size*getWeight(itemComponent))/20));
			itemComponent.stackTagCompound.setInteger("buildTime", (int) Math.round(((size*getDurability(itemComponent))*3)/20));
			
			if (RarityRegistry.getEnumRarity(rarity) != null) {
				itemComponent.stackTagCompound.setString("rarity", rarity);
			}
			
			if (!this.accuracyEnabled)	{ accuracy = 0; }
			if (!this.powerEnabled)		{ power = 0; }
			if (!this.rangeEnabled)	 	{ range = 0; }
			if (!this.cosmeticEnabled)	{ cosmetic = ""; }
			if (!this.fireRateEnabled)	{ fireRate = 0; }
			
			itemComponent.stackTagCompound.setFloat("accuracy", accuracy);
			itemComponent.stackTagCompound.setInteger("power", power);
			itemComponent.stackTagCompound.setInteger("range", range);
			itemComponent.stackTagCompound.setString("cosmetic", cosmetic);
			itemComponent.stackTagCompound.setInteger("fireRate", fireRate);
			
			if (componentName.toLowerCase().contains("assault")) { itemComponent.stackTagCompound.setString("gunType", "Assault"); }
			if (componentName.toLowerCase().contains("rifle"))	 { itemComponent.stackTagCompound.setString("gunType", "Rifle"); }
			if (componentName.toLowerCase().contains("lmg"))	 { itemComponent.stackTagCompound.setString("gunType", "LMG"); }
			if (componentName.toLowerCase().contains("smg")) 	 { itemComponent.stackTagCompound.setString("gunType", "SMG"); }
			if (componentName.toLowerCase().contains("pistol"))  { itemComponent.stackTagCompound.setString("gunType", "Pistol"); }
			if (componentName.toLowerCase().contains("shotgun")) { itemComponent.stackTagCompound.setString("gunType", "Shotgun"); }

			if (assetComponent) {
				itemComponent.stackTagCompound.setString("modelName", modelName);
				itemComponent.stackTagCompound.setString("modelTexture", modelTexture);
				itemComponent.stackTagCompound.setString("iconTexture", iconTexture);
			}
				
			list.add(itemComponent);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		if (item.stackTagCompound != null) {
			if (item.stackTagCompound.getString("rarity").length() > 1 && item.stackTagCompound.getBoolean("generated")) {
				EnumRarity rarity = RarityRegistry.getEnumRarity(item.stackTagCompound.getString("rarity"));
				list.add(RarityRegistry.getRarityTag(rarity));
			}
			if (identifier.length() > 0) {
				list.add(identifier);
				list.add("");
			}
			list.add(item.stackTagCompound.getString("textCol") + "Material: " + getMaterial(item));
			list.add("");
			list.add(accDisplay(getAccuracy(item)));
			list.add(fireRateDisplay(item, getFireRate(item)));
			list.add(powerDisplay(item, getPower(item)));
			list.add(weightDisplay(item, getWeight(item)));
			list.add(durabilityDisplay(item, getDurability(item)));
			list.add("");
			list.add("Cost (Parts): " + getCost(item));
			list.add("Build Time: " + getBuildTime(item));
			list.add("");
			if (item.stackTagCompound != null) {
				if (item.stackTagCompound.getInteger("length") > 0) {
					list.add("Length: " + item.stackTagCompound.getInteger("length") + "\"");
				}
				if (item.stackTagCompound.getDouble("calibre") > 0) {
					list.add("Calibre: " + item.stackTagCompound.getDouble("calibre") + "mm");
				}
			}
			list.add("Hold shift for more information");
		} else {
			list.add("ITEM GENERATION HAS FAILED.");
			list.add("This item is useless. I suggest bathing it in lava.");
			list.add("This can only happen via bad item spawning methods;");
			list.add("/give and NEI WILL NOT WORK for my stuff! (Sorry!)");
			list.add("Use the creative menu, or when implemented, the");
			list.add("Creative Component Creation Centre.");
			list.add("");
			list.add("If you somehow got this in survival,");
			list.add("Please tell Flenix EXACTLY what you did.");
		}
	}
	
	public String accDisplay(float acc) {
		if (acc >= 0) {
			if (acc >= 0.6) { return "\u00A74" + "Accuracy: " + acc; }
			else if (acc >= 0.5) { return "\u00A7c" + "Accuracy: " + acc; }
			else if (acc >= 0.4) { return "\u00A7e" + "Accuracy: " + acc; }
			else if (acc >= 0.35) { return "\u00A7a" + "Accuracy: " + acc; }
			else return "\u00A72" + "Accuracy: " + acc;
		}
		return "\u00A78" + "Accuracy: N/A";
	}
	
	public String fireRateDisplay(ItemStack item, int fireRate) {
		if (fireRate > 0 && tag(item)) {
			return "Fire Rate: " + fireRate + " RPM";
		}
		return "\u00A78" + "Fire Rate: N/A";
	}
	
	public String powerDisplay(ItemStack item, int power) {
		//return "vulgar"; ;)
		if (power > 0) {
			int matWeight = stats.getWeight(getMaterial(item)); //TODO make better
			if (matWeight >= 1601) { return "\u00A72" + "Power: " + power; }
			else if (matWeight >= 1201) { return "\u00A7a" + "Power: " + power; }
			else if (matWeight >= 801) { return "\u00A7e" + "Power: " + power; }
			else if (matWeight >= 401) { return "\u00A7c" + "Power: " + power; }
			else return "\u00A74" + "Power: " + power;
		}
		return "\u00A78" + "Power: N/A";
	}
	
	public String weightDisplay(ItemStack item, int weight) {
		int matWeight = stats.getWeight(getMaterial(item)); //TODO make better
		if (matWeight >= 1601) { return "\u00A74" + "Weight: " + weight; }
		else if (matWeight >= 1201) { return "\u00A7c" + "Weight: " + weight; }
		else if (matWeight >= 801) { return "\u00A7e" + "Weight: " + weight; }
		else if (matWeight >= 401) { return "\u00A7e" + "Weight: " + weight; }
		else return "\u00A72" + "Weight: " + weight;

	}
	
	public String durabilityDisplay(ItemStack item, double durability) {
		double dura = stats.getDurability(getMaterial(item)); //TODO make better
		if (dura <= 0.6) { return "\u00A74" + "Durability: " + (int) durability; }
		else if (dura <= 2.0) { return "\u00A7c" + "Durability: " + (int) durability; }
		else if (dura <= 3.5) { return "\u00A7e" + "Durability: " + (int) durability; }
		else if (dura <= 6.5) { return "\u00A7a" + "Durability: " + (int) durability; }
		else return "\u00A72" + "Durability: " + (int) durability;
	}
	
	public int getDurability(ItemStack item)		{ return tag(item) ? item.stackTagCompound.getInteger("durability") : 0; } //Calculated part size/material/crafter skill
	public int getWeight(ItemStack item)			{ return tag(item) ? item.stackTagCompound.getInteger("weight") : 0; } //Calculated part size/material
	public String getComponentName(ItemStack item) 	{ return tag(item) ? item.stackTagCompound.getString("componentName") : null; }
	public String getMaterial(ItemStack item) 		{ return tag(item) ? item.stackTagCompound.getString("materialName") : null; }
	public String getTextCol(ItemStack item) 		{ return tag(item) ? item.stackTagCompound.getString("textCol") : null; }
	public int getItemCol(ItemStack item) 			{ return tag(item) ? item.stackTagCompound.getInteger("itemCol") : null; }
	public String getOreDict(ItemStack item) 		{ return tag(item) ? item.stackTagCompound.getString("oreDict") : null; }
	public int getCost(ItemStack item)				{ return tag(item) ? item.stackTagCompound.getInteger("cost") : null; }
	public int getBuildTime(ItemStack item)			{ return tag(item) ? item.stackTagCompound.getInteger("buildTime") : null; }
	
	public float getAccuracy(ItemStack item)	{ return tag(item) && accuracyEnabled ? item.stackTagCompound.getFloat("accuracy") : 0; }
	public int getPower(ItemStack item)			{ return tag(item) && powerEnabled    ? item.stackTagCompound.getInteger("power") : 0; }
	public int getRange(ItemStack item)			{ return tag(item) && rangeEnabled    ? item.stackTagCompound.getInteger("range") : 0; }
	public String getTexture(ItemStack item)	{ return tag(item) && cosmeticEnabled ? item.stackTagCompound.getString("cosmetic") : ""; }
	public int getFireRate(ItemStack item)		{ return tag(item) && fireRateEnabled ? item.stackTagCompound.getInteger("fireRate") : 0; }
	public double getCalibre(ItemStack item)	{ return tag(item) && calibreEnabled  ? item.stackTagCompound.getDouble("calibre") : 0; }
	
	public boolean tag(ItemStack item) { return item.stackTagCompound != null ? true : false; }
	

}
