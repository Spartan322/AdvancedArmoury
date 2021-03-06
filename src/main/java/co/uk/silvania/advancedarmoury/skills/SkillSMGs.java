package co.uk.silvania.advancedarmoury.skills;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class SkillSMGs extends SkillLevelBase implements IExtendedEntityProperties {
	
	public static String staticSkillId;
	
	public SkillSMGs(EntityPlayer player, String skillID) {
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
		player.registerExtendedProperties(SkillSMGs.staticSkillId, new SkillSMGs(player, staticSkillId));
	}

	@Override public void init(Entity entity, World world) {}
	@Override public void activateSkill(EntityPlayer player, World world) {}
	@Override public boolean hasGui() { return false; }
	@Override public int iconX() { return 60; }
	@Override public int iconZ() { return 0; }
	@Override public void openGui() {}
	@Override public String shortName() { return "SMG"; }
	@Override public String skillName() { return "Sub-Machine Guns"; }
	@Override public String nameFormat() { return "\u00A75"; }
	@Override public int xpBarColour() { return 11141290; }
	
	@Override
	public void addDescription() {
		description.add("Proficiency with Sub-Machine Guns");
		description.add("Level up to unlock higher power weapons,");
		description.add("as well as a general Mobility boost.");
	}

	@Override
	public ResourceLocation skillIcon() {
		return new ResourceLocation(AdvancedArmoury.modid, "textures/gui/skillIcons.png");
	}

}
