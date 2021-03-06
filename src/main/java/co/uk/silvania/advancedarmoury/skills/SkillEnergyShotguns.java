package co.uk.silvania.advancedarmoury.skills;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillEnergyShotguns extends SkillLevelBase implements IExtendedEntityProperties {
	
	public static String staticSkillId;
	
	public SkillEnergyShotguns(EntityPlayer player, String skillID) {
		super(skillID);
		staticSkillId = skillID;
		this.xp = 0;
	}
	
	@Override
	public void addXP(float xpAdd, EntityPlayer player) {
		SkillLevelBase firearms = (SkillLevelBase) SkillFirearms.get(player, SkillFirearms.staticSkillId);
		firearms.forceAddXP(xpAdd/2, player);
		super.addXP(xpAdd, player);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat(skillId + "xp", xp);
		compound.setTag(skillId, nbt);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = (NBTTagCompound) compound.getTag(skillId);
		xp = nbt.getFloat(skillId + "xp");
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(SkillEnergyShotguns.staticSkillId, new SkillEnergyShotguns(player, staticSkillId));
	}

	@Override public void init(Entity entity, World world) {}
	@Override public void activateSkill(EntityPlayer player, World world) {}
	@Override public boolean hasGui() { return false; }
	@Override public int iconX() { return 90; }
	@Override public int iconZ() { return 30; }
	@Override public void openGui() {}
	@Override public String shortName() { return "E-STGN"; }
	@Override public String skillName() { return "Energy Shotguns"; }
	@Override public String nameFormat() { return "\u00A71"; }
	@Override public int xpBarColour() { return 170; }
	
	@Override
	public void addDescription() {
		description.add("Proficiency with Energy Shotguns.");
		description.add("Level up to unlock higher power weapons,");
		description.add("as well as a general Mobility/Damage boost.");
	}

	@Override
	public ResourceLocation skillIcon() {
		return new ResourceLocation(AdvancedArmoury.modid, "textures/gui/skillIcons.png");
	}

}
