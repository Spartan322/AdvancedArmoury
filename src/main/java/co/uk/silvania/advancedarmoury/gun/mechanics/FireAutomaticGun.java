package co.uk.silvania.advancedarmoury.gun.mechanics;

import java.util.List;
import java.util.Random;

import co.uk.silvania.advancedarmoury.AAItems;
import co.uk.silvania.advancedarmoury.DamageSourceShot;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultContainer;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.items.components.cores.IModifierCore;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemBarrel;
import co.uk.silvania.advancedarmoury.items.rounds.ItemRound;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class FireAutomaticGun {
	
	private Entity pointedEntity;
	
	public float getAccuracy(ItemStack item) {
		AssaultIInventory inventory = new AssaultIInventory(item);
		ItemStack barrel = inventory.getBarrel();
		if (barrel != null) {
			ItemBarrel itemBarrel = (ItemBarrel) barrel.getItem();
			float accuracy = itemBarrel.accuracy;
			int length = barrel.stackTagCompound.getInteger("length");
			double d0 = length / 100.0;
			if (d0 < 0.01) { d0 = 0.01; }
			float f0 = (float) (accuracy - d0);
			if (f0 < 0.0F) {
				f0 = 0.0F;
			}
			return f0;
		}
		return 0;
	}
	
	public int getRange(ItemStack item) {
		//TODO
		return 150;
	}
	
	public boolean isCompleteGun(ItemStack item, AssaultIInventory inv) {
		for (int i = 2; i < 20; i++) {
			if (i != 11 && inv.getStackInSlot(i) == null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasMoved(ItemStack item, EntityPlayer player) {
		if (item.stackTagCompound != null) {
			double prevX = item.stackTagCompound.getDouble("xPos");
			double prevY = item.stackTagCompound.getDouble("yPos");
			double prevZ = item.stackTagCompound.getDouble("zPos");
			if (prevX != player.posX) { return true; }
			if (prevY != player.posY) { return true; }
			if (prevZ != player.posZ) { return true; }
		}
		return false;
	}
	
	public void dryFire(World world, EntityPlayer player, ItemStack item) {
		world.playSoundAtEntity(player, "advancedarmoury:dryfire", 3.0F, 1.0F);
	}
	
	public void fire(World world, EntityPlayer player, ItemStack gun, ItemStack round, IModifierCore mod) {
		AssaultIInventory gunInv = new AssaultIInventory(player.getHeldItem());
		AssaultContainer container = new AssaultContainer(player, player.inventory, gunInv);
		Random rand = new Random();
		
		if (isCompleteGun(gun, gunInv)) {
			double chambreCal = gunInv.getStackInSlot(12).stackTagCompound.getDouble("calibre");
			double barrelCal  = gunInv.getStackInSlot(13).stackTagCompound.getDouble("calibre");
			
			if (round == null) {
				if (player.capabilities.isCreativeMode) {
					//Player is in creative, no round inserted. We'll create a simple one for them.
					round = new ItemStack(AAItems.itemRound);
					round.stackTagCompound = new NBTTagCompound();
					round.stackTagCompound.setDouble("calibre", chambreCal);
					round.stackTagCompound.setInteger("calibreId", 556);
					round.stackTagCompound.setString("bullet", "Lead");
					round.stackTagCompound.setString("case", "Brass");
				} else {
					return;
				}
			}
			
			double roundCal   = round.stackTagCompound.getDouble("calibre");
		
			if (!(chambreCal == barrelCal && barrelCal == roundCal)) {
				//damage gun
				return;
			}

			//Pull sound from round type, adjust pitch based on barrel length
			//If (silencer) { //silenced sound } else { //This vv }
			world.playSoundAtEntity(player, "advancedarmoury:m4a1_shoot", 5.0F, 1.0F);
			
			if (mod != null) {
				mod.onFireWeapon(gun, player);
			}
			
			int burst = gun.stackTagCompound.getInteger("burstCount");
			
			gun.stackTagCompound.setInteger("burstCount", burst + 1);
			
			//Calculate offsets for accuracy etc
			double accuracy = getAccuracy(gun)*10;
			double ADSacc = 0;
			double sneakAcc = 0;
			double moveAcc = 0;
			double fireAcc = 0;
			
			if (gun.stackTagCompound.getBoolean("aiming")) {
				ADSacc = accuracy/2;
			}
			
			if (gun.stackTagCompound.getBoolean("firing")) {
				if (burst > 3) { burst = 3; }
				fireAcc = burst / 20.0;
			}
			
			if (player.isSneaking()) {
				sneakAcc = accuracy/5;
			}
			
			if (hasMoved(gun, player)) {
				moveAcc = accuracy*2;
			}
			
			//System.out.println("Acc: " + accuracy + ", ADSacc: " + ADSacc + ", sneakAcc: " + sneakAcc + ", moveAcc: " + moveAcc + ", fireAcc: " + fireAcc);
			accuracy = accuracy - ADSacc - sneakAcc + moveAcc + fireAcc;
			double modifyX = ((rand.nextInt((int) Math.round(accuracy * 200)) - (int) accuracy*100)/7500D);
			double modifyY = ((rand.nextInt((int) Math.round(accuracy * 200)) - (int) accuracy*100)/7500D);
			double modifyZ = ((rand.nextInt((int) Math.round(accuracy * 200)) - (int) accuracy*100)/7500D);
			
			Vec3 playerPos = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
			Vec3 targetBlock = targetBlock(world, player, gun, modifyX, modifyY, modifyZ);
			Entity targetEntity = targetEntity(world, player, gun, modifyX, modifyY, modifyZ);
			
			double blockDistance = -1;
			double entityDistance = -1;
			
			if (targetBlock != null) {
				blockDistance = targetBlock.distanceTo(playerPos);
			}
			if (targetEntity != null) {
				entityDistance = targetEntity.getDistanceToEntity(player);
			}
			
			System.out.println("Block distance: " + blockDistance + ", entityDistance: " + entityDistance);
			
			if (blockDistance >= 0 && blockDistance < entityDistance) {
				shootBlock(targetBlock, player, gun, round, mod);
			} else if (entityDistance >= 0 && entityDistance < blockDistance) {
				shootEntity(targetEntity, player, gun, round, mod);
			}
			
			if (!player.capabilities.isCreativeMode) {
				//TODO Damage system
				//System.out.println("Item name: " + gunInv.getStackInSlot(2).getDisplayName());
				//System.out.println("Pre-damage: " + gunInv.getStackInSlot(2).getItemDamage());
				//ItemStack newStack = new ItemStack(gunInv.getStackInSlot(2).getItem(), gunInv.getStackInSlot(2).stackSize, gunInv.getStackInSlot(2).getItemDamage() + 1);
				gunInv.setInventorySlotContents(2, null);
				//System.out.println("Post-damage: " + gunInv.getStackInSlot(2).getItemDamage());
				
				gunInv.markDirty();
				container.detectAndSendChanges();
				player.inventory.markDirty();
				player.inventoryContainer.detectAndSendChanges();
				//gunInv.setInventorySlotContents(2, damageItem(gunInv.getStackInSlot(2)));
			}
		}
	}
	
	//Returns Vec3 of the block hit, or null if none found.
	public Vec3 targetBlock(World world, EntityPlayer player, ItemStack gun, double modifyX, double modifyY, double modifyZ) {
		System.out.println("FIND block.");
		double range = getRange(gun);
		
		if (!world.isRemote) {
			Vec3 headVec = getCorrectedHeadVec(world, player);
			Vec3 lookVec = player.getLook(1);
			//Apply the offsets
			lookVec.xCoord += modifyX;
			lookVec.yCoord += modifyY;
			lookVec.zCoord += modifyZ;
			
			Vec3 targetVec = headVec.addVector(lookVec.xCoord * range, lookVec.yCoord * range, lookVec.zCoord * range);

            MovingObjectPosition mopBlock = world.rayTraceBlocks(headVec, targetVec);

            if (mopBlock != null) {
            	int x = mopBlock.blockX;
            	int y = mopBlock.blockY;
            	int z = mopBlock.blockZ;
            	System.out.println("Hit block: " + world.getBlock(x, y, z).getLocalizedName());
            	
            	return Vec3.createVectorHelper(x, y, z);
            }
		}
		return null;
	}
	
	//Returns the first entity in-line.
	public Entity targetEntity(World world, EntityPlayer player, ItemStack gun, double modifyX, double modifyY, double modifyZ) {
		System.out.println("FIND entity.");
		double range = getRange(gun);
		
		if (!world.isRemote) {
			Vec3 lookVec = player.getLookVec();			
			lookVec.xCoord += modifyX;
			lookVec.yCoord += modifyY;
			lookVec.zCoord += modifyZ;
			
			Vec3 vec3 = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);//player.getPosition(1.0F);
			vec3.yCoord += player.getEyeHeight();

			Vec3 addedVector = vec3.addVector(lookVec.xCoord * range, lookVec.yCoord * range, lookVec.zCoord * range);
			
	        this.pointedEntity = null;
	        Vec3 vec33 = null;
	        List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(lookVec.xCoord * range, lookVec.yCoord * range, lookVec.zCoord * range).expand(1.0D, 1.0D, 1.0D));
	        double d2 = range + 1;
	        
	        for (int i = 0; i < list.size(); ++i) {
	            Entity entity = (Entity)list.get(i);

	            if (entity.canBeCollidedWith()) {
	                float f2 = entity.getCollisionBorderSize();
	                AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
	                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, addedVector);

	                if (axisalignedbb.isVecInside(vec3)) {
	                    if (0.0D < d2 || d2 == 0.0D) {
	                        this.pointedEntity = entity;
	                        System.out.println("Target aquired! " + entity.getCommandSenderName());
	                        vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
	                        d2 = 0.0D;
	                        return entity;
	                    }
	                } else if (movingobjectposition != null) {
	                    double d3 = vec3.distanceTo(movingobjectposition.hitVec);

	                    if (d3 < d2 || d2 == 0.0D) {
	                        if (!(entity == player.ridingEntity && !entity.canRiderInteract())) {
	                            this.pointedEntity = entity;
		                        System.out.println("Target aquired! " + entity.getCommandSenderName());
	                            vec33 = movingobjectposition.hitVec;
	                            d2 = d3;
	                            return entity;
	                        }
	                    }
	                }
	            }
	        }
		}
		return null;
	}
	
	public void shootBlock(Vec3 blockVec, EntityPlayer player, ItemStack gun, ItemStack round, IModifierCore mod) {
		Block block = player.worldObj.getBlock((int) blockVec.xCoord, (int) blockVec.yCoord, (int) blockVec.zCoord);
		ItemRound rnd = (ItemRound) round.getItem();
		System.out.println("Shoot block.");
		
		if (mod != null) {
			mod.onBlockShot(gun, player, block);
		}
    	rnd.onShotBlock(round, block);
	}
	
	public void shootEntity(Entity entity, EntityPlayer player, ItemStack gun, ItemStack round, IModifierCore mod) {
		ItemRound rnd = (ItemRound) round.getItem();
		System.out.println("Shoot entity.");
		
		int damage = 10;
		
		entity.attackEntityFrom(new DamageSourceShot(player), damage);
		
		if (mod != null) { mod.onEntityShot(gun, player, entity); }
        if (entity instanceof EntityLivingBase) {
        	rnd.onShotEntity(round, (EntityLivingBase) entity);
        }
	}
	
	private Vec3 getCorrectedHeadVec(World world, EntityPlayer player) {
	    Vec3 vec3 = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
        if(!world.isRemote){
            vec3.yCoord += player.getEyeHeight();
            if(player instanceof EntityPlayerMP && player.isSneaking()){
                vec3.yCoord -= 0.08;
            }
        }
        return vec3;
	}

}
