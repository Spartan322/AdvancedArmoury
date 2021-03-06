package co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault;

import co.uk.silvania.advancedarmoury.AAUtils;
import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.GuiHandler;
import co.uk.silvania.advancedarmoury.blocks.machines.BlockMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class AssaultAssemblyTable extends BlockMachine {

	public AssaultAssemblyTable() {
		super();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking() || AAUtils.isHoldingWeapon(player)) {
			return false;
		}
		if (world.getBlock(x + 1, y, z) == this) {
			player.openGui(AdvancedArmoury.instance, GuiHandler.assaultAssemblyGuiId, world, x, y, z);
		} else if (world.getBlock(x - 1, y, z) == this) {
			player.openGui(AdvancedArmoury.instance, GuiHandler.assaultAssemblyGuiId, world, x - 1, y, z);
		} else if (world.getBlock(x, y, z + 1) == this) {
			player.openGui(AdvancedArmoury.instance, GuiHandler.assaultAssemblyGuiId, world, x, y, z);
		} else if (world.getBlock(x, y, z - 1) == this) {
			player.openGui(AdvancedArmoury.instance, GuiHandler.assaultAssemblyGuiId, world, x, y, z - 1);
		} else {
			if (!world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText("You must have two blocks side by side for this machine to function."));
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new AssaultAssemblyTableEntity();
	}
}
