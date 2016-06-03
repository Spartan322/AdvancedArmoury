//CLONED FROM FLENIXCITIES Walkway Renderer, then modified.
//Taken with permission, as it's my own damn mod. :D
package co.uk.silvania.advancedarmoury.client.renderers.blocks;

import co.uk.silvania.advancedarmoury.blocks.decorative.RailingMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.RailingStairsMilitaryBase;
import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class RailingRenderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {
	
	boolean renderSnow;
	boolean renderLeaves;
	
	public RailingRenderer(boolean snowy, boolean leaves) {
		renderSnow = snowy;
		renderLeaves = leaves;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if (metadata <= 3) {
			renderBlock(0.0D, 0.875D, 0.0D, 0.125D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0);
			
			renderBlock(0.03175D, 0.0D, 0.0D,    0.09325D, 0.9325D, 0.0625D, false, renderer, block, 0, 0, 0, 0); //Left bar-l
			renderBlock(0.03175D, 0.0D, 0.9375D, 0.09325D, 0.9325D, 1.0D,    false, renderer, block, 0, 0, 0, 0); //Left bar-r
			
			renderBlock(0.03175D, 0.46875D, 0.0675D, 0.09325D, 0.53125D, 0.9375D, false, renderer, block, 0, 0, 0, 0); //Left Middle bar
			
			if (renderSnow) {
				renderSnowBlock(0.0D,   1.0D, 0.0D,   0.125D, 1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left
			}
			if (renderLeaves) {
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Outer
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     0, 0, 1, 1, false, renderer, block, 0, 0, 0, 0); //Left Inner
				renderLeavesBlock(0.125, -0.05, -0.05,  0.125, 1.05, 1.05, 1.05, 1.05,  1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Top
			}
		} else if (metadata <= 7) {
			renderBlock(0.0D,   0.875D, 0.0D, 0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.875D, 0.0D, 1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top
			
			renderBlock(0.03175D, 0.0D, 0.0D,    0.09325D, 0.9325D, 0.0625D, false, renderer, block, 0, 0, 0, 0); //Left bar-l
			renderBlock(0.03175D, 0.0D, 0.9375D, 0.09325D, 0.9325D, 1.0D,    false, renderer, block, 0, 0, 0, 0); //Left bar-r
			renderBlock(0.0D,     0.0D, 0.03175D, 0.0625D,  0.9325D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back bar-l
			renderBlock(0.9375D,  0.0D, 0.03175D, 1.0D,     0.9325D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back bar-r
			
			renderBlock(0.03175D, 0.46875D, 0.0675D, 0.09325D, 0.53125D, 0.9375D, false, renderer, block, 0, 0, 0, 0); //Left Middle bar
			renderBlock(0.0675D,  0.46875D, 0.03175D, 0.9375D,  0.53125D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back Middle bar
			
			if (renderSnow) {
				renderSnowBlock(0.0D,   1.0D, 0.0D,   1.0D,   1.0625D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back
				renderSnowBlock(0.0D,   1.0D, 0.0D,   0.125D, 1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left
			}
			if (renderLeaves) {
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Outer
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     0, 0, 1, 1, false, renderer, block, 0, 0, 0, 0); //Left Inner
				renderLeavesBlock(0.125, -0.05, -0.05,  0.125, 1.05, 1.05, 1.05, 1.05,  1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Top
				
				renderLeavesBlock(1, 1, 0, 0,   1,    0,    0,    1,     -0.05,  -0.05, -0.05, -0.05,  false, renderer, block, 0, 0, 0, 0); //Back Outer
				renderLeavesBlock(0, 0, 1, 1,   1,    0,    0,    1,     -0.05,  -0.05, -0.05, -0.05,  false, renderer, block, 0, 0, 0, 0); //Back Inner
				renderLeavesBlock(1, 1, 0, 0,   1.05, 1.05, 1.05, 1.05,   0.125, -0.05, -0.05,  0.125, false, renderer, block, 0, 0, 0, 0); //Back Top
			}
		} else if (metadata <= 11) {
			renderBlock(0.0D,   0.875D, 0.0D, 0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.875D, 0.0D, 1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top
			renderBlock(0.875D, 0.875D, 0.0D, 1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right top
			
			renderBlock(0.03175D, 0.0D, 0.0D,     0.09325D, 0.9325D, 0.0625D,  false, renderer, block, 0, 0, 0, 0); //Left bar-l
			renderBlock(0.03175D, 0.0D, 0.9375D,  0.09325D, 0.9325D, 1.0D,     false, renderer, block, 0, 0, 0, 0); //Left bar-r
			renderBlock(0.0D,     0.0D, 0.03175D, 0.0625D,  0.9325D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back bar-l
			renderBlock(0.9375D,  0.0D, 0.03175D, 1.0D,     0.9325D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back bar-r
			renderBlock(0.90675D, 0.0D, 0.0D,     0.96825D, 0.9325D, 0.0625D,  false, renderer, block, 0, 0, 0, 0); //Right bar-l
			renderBlock(0.90675D, 0.0D, 0.9375D,  0.96825D, 0.9325D, 1.0D,     false, renderer, block, 0, 0, 0, 0); //Right bar-r
			
			renderBlock(0.03175D, 0.46875D, 0.0675D,  0.09325D, 0.53125D, 0.9375D,  false, renderer, block, 0, 0, 0, 0); //Left Middle bar
			renderBlock(0.0675D,  0.46875D, 0.03175D, 0.9375D,  0.53125D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back Middle bar
			renderBlock(0.90675D, 0.46875D, 0.0675D,  0.96825D, 0.53125D, 0.9375D,  false, renderer, block, 0, 0, 0, 0); //Right Middle bar
			
			if (renderSnow) {
				renderSnowBlock(0.0D,   1.0D, 0.0D,   1.0D,   1.0625D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back
				renderSnowBlock(0.0D,   1.0D, 0.0D,   0.125D, 1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left
				renderSnowBlock(0.875D, 1.0D, 0.0D,   1.0D,   1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right
			}
			if (renderLeaves) {
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Outer
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     0, 0, 1, 1, false, renderer, block, 0, 0, 0, 0); //Left Inner
				renderLeavesBlock(0.125, -0.05, -0.05,  0.125, 1.05, 1.05, 1.05, 1.05,  1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Top
				
				renderLeavesBlock(1, 1, 0, 0,   1,    0,    0,    1,     -0.05,  -0.05, -0.05, -0.05,  false, renderer, block, 0, 0, 0, 0); //Back Outer
				renderLeavesBlock(0, 0, 1, 1,   1,    0,    0,    1,     -0.05,  -0.05, -0.05, -0.05,  false, renderer, block, 0, 0, 0, 0); //Back Inner
				renderLeavesBlock(1, 1, 0, 0,   1.05, 1.05, 1.05, 1.05,   0.125, -0.05, -0.05,  0.125, false, renderer, block, 0, 0, 0, 0); //Back Top
				
				renderLeavesBlock(1.05,  1.05, 1.05, 1.05,  1,    0,    0,    1,     1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Right Outer
				renderLeavesBlock(1.05,  1.05, 1.05, 1.05,  1,    0,    0,    1,     0, 0, 1, 1, false, renderer, block, 0, 0, 0, 0); //Right Inner
				renderLeavesBlock(0.875, 1.05, 1.05, 0.875, 1.05, 1.05, 1.05, 1.05,  1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Right Top
			}
		} else if (metadata <= 13) {
			renderBlock(0.0D,   0.875D, 0.0D, 0.125D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.875D, 0.875D, 0.0D, 1.0D,   1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Right top
			
			renderBlock(0.03175D, 0.0D, 0.0D,    0.09325D, 0.9325D, 0.0625D, false, renderer, block, 0, 0, 0, 0); //Left bar-l
			renderBlock(0.03175D, 0.0D, 0.9375D, 0.09325D, 0.9325D, 1.0D,    false, renderer, block, 0, 0, 0, 0); //Left bar-r
			renderBlock(0.90675D, 0.0D, 0.0D,    0.96825D, 0.9325D, 0.0625D, false, renderer, block, 0, 0, 0, 0); //Right bar-l
			renderBlock(0.90675D, 0.0D, 0.9375D, 0.96825D, 0.9325D, 1.0D,    false, renderer, block, 0, 0, 0, 0); //Right bar-r
			
			renderBlock(0.03175D, 0.46875D, 0.0675D, 0.09325D, 0.53125D, 0.9375D, false, renderer, block, 0, 0, 0, 0); //Left Middle bar
			renderBlock(0.90675D, 0.46875D, 0.0675D, 0.96825D, 0.53125D, 0.9375D, false, renderer, block, 0, 0, 0, 0); //Right Middle bar
			
			if (renderSnow) {
				renderSnowBlock(0.0D,   1.0D, 0.0D,   0.125D, 1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left
				renderSnowBlock(0.875D, 1.0D, 0.0D,   1.0D,   1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right
			}
			if (renderLeaves) {
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Outer
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     0, 0, 1, 1, false, renderer, block, 0, 0, 0, 0); //Left Inner
				renderLeavesBlock(0.125, -0.05, -0.05,  0.125, 1.05, 1.05, 1.05, 1.05,  1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Top
				
				renderLeavesBlock(1.05,  1.05, 1.05, 1.05,  1,    0,    0,    1,     1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Right Outer
				renderLeavesBlock(1.05,  1.05, 1.05, 1.05,  1,    0,    0,    1,     0, 0, 1, 1, false, renderer, block, 0, 0, 0, 0); //Right Inner
				renderLeavesBlock(0.875, 1.05, 1.05, 0.875, 1.05, 1.05, 1.05, 1.05,  1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Right Top
			}
		} else {
			renderBlock(0.0D,   0.875D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.875D, 0.0D,   1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top
			renderBlock(0.875D, 0.875D, 0.0D,   1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right top
			renderBlock(0.0D,   0.875D, 0.875D, 1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Front top
			
			renderBlock(0.03175D, 0.0D, 0.0D,     0.09325D, 0.9325D, 0.0625D,  false, renderer, block, 0, 0, 0, 0); //Left bar-l
			renderBlock(0.03175D, 0.0D, 0.9375D,  0.09325D, 0.9325D, 1.0D,     false, renderer, block, 0, 0, 0, 0); //Left bar-r
			renderBlock(0.0D,     0.0D, 0.03175D, 0.0625D,  0.9325D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back bar-l
			renderBlock(0.9375D,  0.0D, 0.03175D, 1.0D,     0.9325D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back bar-r
			renderBlock(0.90675D, 0.0D, 0.0D,     0.96825D, 0.9325D, 0.0625D,  false, renderer, block, 0, 0, 0, 0); //Right bar-l
			renderBlock(0.90675D, 0.0D, 0.9375D,  0.96825D, 0.9325D, 1.0D,     false, renderer, block, 0, 0, 0, 0); //Right bar-r
			renderBlock(0.0D,     0.0D, 0.96825D, 0.0625D,  0.9325D, 0.90675D, false, renderer, block, 0, 0, 0, 0); //Front bar-l
			renderBlock(0.9375D,  0.0D, 0.96825D, 1.0D,     0.9325D, 0.90675D, false, renderer, block, 0, 0, 0, 0); //Front bar-r
			
			renderBlock(0.03175D, 0.46875D, 0.0675D,  0.09325D, 0.53125D, 0.9375D,  false, renderer, block, 0, 0, 0, 0); //Left Middle bar
			renderBlock(0.0675D,  0.46875D, 0.03175D, 0.9375D,  0.53125D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back Middle bar
			renderBlock(0.90675D, 0.46875D, 0.0675D,  0.96825D, 0.53125D, 0.9375D,  false, renderer, block, 0, 0, 0, 0); //Right Middle bar
			renderBlock(0.0675D,  0.46875D, 0.96825D, 0.9375D,  0.53125D, 0.90675D, false, renderer, block, 0, 0, 0, 0); //Front Middle bar
			
			if (renderSnow) {
				renderSnowBlock(0.0D,   1.0D, 0.0D,   1.0D,   1.0625D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back
				renderSnowBlock(0.0D,   1.0D, 0.0D,   0.125D, 1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left
				renderSnowBlock(0.875D, 1.0D, 0.0D,   1.0D,   1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right
				renderSnowBlock(0.0D,   1.0D, 0.875D, 1.0D,   1.0625D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Front
			}
			if (renderLeaves) {
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Outer
				renderLeavesBlock(-0.05, -0.05, -0.05, -0.05,  1,    0,    0,    1,     0, 0, 1, 1, false, renderer, block, 0, 0, 0, 0); //Left Inner
				renderLeavesBlock(0.125, -0.05, -0.05,  0.125, 1.05, 1.05, 1.05, 1.05,  1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Left Top
				
				renderLeavesBlock(1, 1, 0, 0,   1,    0,    0,    1,     -0.05,  -0.05, -0.05, -0.05,  false, renderer, block, 0, 0, 0, 0); //Back Outer
				renderLeavesBlock(0, 0, 1, 1,   1,    0,    0,    1,     -0.05,  -0.05, -0.05, -0.05,  false, renderer, block, 0, 0, 0, 0); //Back Inner
				renderLeavesBlock(1, 1, 0, 0,   1.05, 1.05, 1.05, 1.05,   0.125, -0.05, -0.05,  0.125, false, renderer, block, 0, 0, 0, 0); //Back Top
				
				renderLeavesBlock(1.05,  1.05, 1.05, 1.05,  1,    0,    0,    1,     1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Right Outer
				renderLeavesBlock(1.05,  1.05, 1.05, 1.05,  1,    0,    0,    1,     0, 0, 1, 1, false, renderer, block, 0, 0, 0, 0); //Right Inner
				renderLeavesBlock(0.875, 1.05, 1.05, 0.875, 1.05, 1.05, 1.05, 1.05,  1, 1, 0, 0, false, renderer, block, 0, 0, 0, 0); //Right Top
				
				renderLeavesBlock(1, 1, 0, 0,   1,    0,    0,    1,      1.05,  1.05, 1.05, 1.05,  false, renderer, block, 0, 0, 0, 0); //Front Outer
				renderLeavesBlock(0, 0, 1, 1,   1,    0,    0,    1,      1.05,  1.05, 1.05, 1.05,  false, renderer, block, 0, 0, 0, 0); //Front Inner
				renderLeavesBlock(1, 1, 0, 0,   1.05, 1.05, 1.05, 1.05,   0.875, 1.05, 1.05, 0.875, false, renderer, block, 0, 0, 0, 0); //Front Top
			}
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth = checkConnections(world, x, y, z-1, 0, meta, RailingMilitaryBase.class, RailingStairsMilitaryBase.class);
		boolean connectEast =  checkConnections(world, x+1, y, z, 1, meta, RailingMilitaryBase.class, RailingStairsMilitaryBase.class);
		boolean connectSouth = checkConnections(world, x, y, z+1, 0, meta, RailingMilitaryBase.class, RailingStairsMilitaryBase.class);
		boolean connectWest =  checkConnections(world, x-1, y, z, 1, meta, RailingMilitaryBase.class, RailingStairsMilitaryBase.class);
		
		boolean connectNorthEast = checkConnections(world, x+1, y, z-1, -1, meta, RailingMilitaryBase.class, RailingStairsMilitaryBase.class);
		boolean connectNorthWest = checkConnections(world, x-1, y, z-1, -1, meta, RailingMilitaryBase.class, RailingStairsMilitaryBase.class);
		boolean connectSouthEast = checkConnections(world, x+1, y, z+1, -1, meta, RailingMilitaryBase.class, RailingStairsMilitaryBase.class);
		boolean connectSouthWest = checkConnections(world, x-1, y, z+1, -1, meta, RailingMilitaryBase.class, RailingStairsMilitaryBase.class);
		
		boolean walkwayAbove = world.getBlock(x, y+1, z) instanceof RailingMilitaryBase;
		
		if (renderSnow) {
			if (world.getBlock(x, y-1, z).isSideSolid(world, x, y-1, z, ForgeDirection.UP)) {
				renderSnowBlock(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D, true, renderer, block, x, y, z, meta);
			}
		}
		
		boolean renderNorth = false; //X 0-1,  Z 0
		boolean renderEast  = false; //X 1,    Z 0-1
		boolean renderSouth = false; //X 0-1,  Z 1
		boolean renderWest  = false; //X 0,    Z 0-1
		
		if (meta == 0)  { renderNorth = true; }
		if (meta == 1)  { renderEast  = true; }
		if (meta == 2)  { renderSouth = true; }
		if (meta == 3)  { renderWest  = true; }
		if (meta == 4)  { renderNorth = renderEast  = true; }
		if (meta == 5)  { renderSouth = renderEast  = true; }
		if (meta == 6)  { renderSouth = renderWest  = true; }
		if (meta == 7)  { renderNorth = renderWest  = true; }
		if (meta == 8)  { renderNorth = renderEast  = renderSouth = true;}
		if (meta == 9)  { renderEast  = renderSouth = renderWest  = true;}
		if (meta == 10) { renderSouth = renderWest  = renderNorth = true;}
		if (meta == 11) { renderWest  = renderNorth = renderEast  = true;}
		if (meta == 12) { renderNorth = renderSouth = true; }
		if (meta == 13) { renderEast  = renderWest  = true; }
		if (meta == 14) { renderNorth = renderEast  = renderSouth = renderWest = true; }
		if (meta == 15) {
			if (!connectEast)  { renderEast = true; }
			if (!connectWest)  { renderWest = true; }
			if (!connectNorth) { renderNorth = true; }
			if (!connectSouth) { renderSouth = true; }
		}
		
		boolean renderNorthEast = (renderNorth && renderEast) ? true : false;
		boolean renderNorthWest = (renderNorth && renderWest) ? true : false;
		boolean renderSouthEast = (renderSouth && renderEast) ? true : false;
		boolean renderSouthWest = (renderSouth && renderWest) ? true : false;

		if (renderNorth) {
			if (!walkwayAbove) {
				//Alter sizes based on connections.
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderEast) { cnct1 = 0.125D; }
				if (renderWest) { cnct2 = 0.125D; }
				
				renderBlock(0.0D + cnct2, 0.875D, 0.0D, 1.0D - cnct1, 1.0D, 0.125D, true, renderer, block, x, y, z, meta);
				
				if (renderSnow) {
					renderSnowBlock(0.0D + cnct2, 1.0D, 0.0D, 1.0D - cnct1, 1.0625D, 0.125D, true, renderer, block, x, y, z, meta);
				}
				if (renderLeaves) {
					renderLeavesBlock(1, 1, 0, 0, 1, 0, 0, 1, -0.05, -0.05, -0.05, -0.05, true, renderer, block, x, y, z, meta); //Outer
					renderLeavesBlock(0, 0, 1, 1, 1, 0, 0, 1, -0.05, -0.05, -0.05, -0.05, true, renderer, block, x, y, z, meta); //Inner
					renderLeavesBlock(1, 1, 0, 0, 1.05, 1.05, 1.05, 1.05, 0.125, -0.05, -0.05, 0.125, true, renderer, block, x, y, z, meta); //Top
				}
			}
			renderBlock(0.0D,    0.0D, 0.03175D, 0.0625D, 0.9325D, 0.09325D, true, renderer, block, x, y, z, meta);
			renderBlock(0.9375D, 0.0D, 0.03175D, 1.0D,    0.9325D, 0.09325D, true, renderer, block, x, y, z, meta);
			
			renderBlock(0.0625D, 0.46875D, 0.03175D, 0.9375D, 0.53125D, 0.09325D, true, renderer, block, x, y, z, meta); //Middle bar
		}
		
		if (renderSouth) {
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderEast) { cnct1 = 0.125D; }
				if (renderWest) { cnct2 = 0.125D; }
				
				renderBlock(0.0D + cnct2, 0.875D, 0.875D, 1.0D - cnct1, 1.0D, 1.0D, true, renderer, block, x, y, z, meta);
				
				if (renderSnow) {
					renderSnowBlock(0.0D + cnct2, 1.0D, 0.875D, 1.0D - cnct1, 1.0625D, 1.0D, true, renderer, block, x, y, z, meta);
				}
				if (renderLeaves) {
					renderLeavesBlock(0, 0, 1, 1, 1, 0, 0, 1, 1.05, 1.05, 1.05, 1.05, true, renderer, block, x, y, z, meta); //Outer
					renderLeavesBlock(1, 1, 0, 0, 1, 0, 0, 1, 1.05, 1.05, 1.05, 1.05, true, renderer, block, x, y, z, meta); //Inner
					renderLeavesBlock(1, 1, 0, 0, 1.05, 1.05, 1.05, 1.05, 1.05, 0.875, 0.875, 1.05, true, renderer, block, x, y, z, meta); //Top
				}
			}
			
			renderBlock(0.0D,    0.0D, 0.90675D, 0.0625D, 0.9325D, 0.96825D, true, renderer, block, x, y, z, meta);
			renderBlock(0.9375D, 0.0D, 0.90675D, 1.0D,    0.9325D, 0.96825D, true, renderer, block, x, y, z, meta);
			
			renderBlock(0.0625D, 0.46875D, 0.90675D, 0.9375D, 0.53125D, 0.96825D, true, renderer, block, x, y, z, meta); //Middle bar
		}
		
		if (renderEast) {
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderNorth) { cnct1 = 0.125D; }
				if (renderSouth) { cnct2 = 0.125D; }
				
				renderBlock(0.875D, 0.875D, 0.0D + cnct1, 1.0D, 1.0D, 1.0D - cnct2, true, renderer, block, x, y, z, meta);
				
				if (renderSnow) {
					renderSnowBlock(0.875D, 1.0D, 0.0D + cnct1, 1.0D, 1.0625D, 1.0D - cnct2, true, renderer, block, x, y, z, meta);
				}
				if (renderLeaves) {
					renderLeavesBlock(1.05, 1.05, 1.05, 1.05, 0, 1, 1, 0, 0, 0, 1, 1, true, renderer, block, x, y, z, meta); //Outer
					renderLeavesBlock(1.05, 1.05, 1.05, 1.05, 1, 1, 0, 0, 1, 0, 0, 1, true, renderer, block, x, y, z, meta); //Inner
					renderLeavesBlock(1.05, 1.05, 0.875, 0.875, 1.05, 1.05, 1.05, 1.05, 1, 0, 0, 1, true, renderer, block, x, y, z, meta); //Top
				}
			}			
			renderBlock(0.90675D, 0.0D, 0.0D,    0.96825D, 0.9325D, 0.0625, true, renderer, block, x, y, z, meta);
			renderBlock(0.90675D, 0.0D, 0.9375D, 0.96825D, 0.9325D, 1.0D,   true, renderer, block, x, y, z, meta);
			
			renderBlock(0.90675D, 0.46875D, 0.0625D, 0.96825D, 0.53125D, 0.9375D, true, renderer, block, x, y, z, meta); //Middle bar
		}
		
		if (renderWest) { //-0.625D 1.0625D
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderNorth) { cnct1 = 0.125D; }
				if (renderSouth) { cnct2 = 0.125D; }
				
				renderBlock(0.0D, 0.875D, 0.0D + cnct1, 0.125D, 1.0D, 1.0D - cnct2, true, renderer, block, x, y, z, meta);
				
				if (renderSnow) {
					renderSnowBlock(0.0D, 1.0D, 0.0D + cnct1, 0.125D, 1.0625D, 1.0D - cnct2, true, renderer, block, x, y, z, meta);
				}
				
				if (renderLeaves) {
					renderLeavesBlock(-0.05, -0.05, -0.05, -0.05, 1, 1, 0, 0, 1, 0, 0, 1, true, renderer, block, x, y, z, meta); //Outer
					renderLeavesBlock(-0.05, -0.05, -0.05, -0.05, 0, 1, 1, 0, 0, 0, 1, 1, true, renderer, block, x, y, z, meta); //Inner
					renderLeavesBlock(0.125, 0.125, -0.05, -0.05, 1.05, 1.05, 1.05, 1.05, 1, 0, 0, 1, true, renderer, block, x, y, z, meta); //Top
				}
			}
			renderBlock(0.03175D, 0.0D, 0.0D,    0.09325D, 0.9325D, 0.0625, true, renderer, block, x, y, z, meta);
			renderBlock(0.03175D, 0.0D, 0.9375D, 0.09325D, 0.9325D, 1.0D,   true, renderer, block, x, y, z, meta);
			
			renderBlock(0.03175D, 0.46875D, 0.0625D, 0.09325D, 0.53125D, 0.9375D, true, renderer, block, x, y, z, meta); //Middle bar
		}
		
		//Notches in the corners.
		if (!walkwayAbove) {
			if (renderNorthEast) {
				renderBlock(0.875D, 0.875D, 0.0D, 1.0D, 1.0D, 0.125D, true, renderer, block, x, y, z, meta);
				if (renderSnow) {
					renderSnowBlock(0.875D, 1.0D, 0.0D, 1.0D, 1.0625D,  0.125D, true, renderer, block, x, y, z, meta);
				}
			}
			
			if (renderNorthWest) {
				renderBlock(0.0D, 0.875D, 0.0D, 0.125D, 1.0D, 0.125D, true, renderer, block, x, y, z, meta);
				if (renderSnow) {
					renderSnowBlock(0.0D, 1.0D, 0.0D, 0.125D, 1.0625D,  0.125D, true, renderer, block, x, y, z, meta);
				}
			}
			
			if (renderSouthEast) {
				renderBlock(0.875D, 0.875D, 0.875D, 1.0D, 1.0D, 1.0D, true, renderer, block, x, y, z, meta);
				if (renderSnow) {
					renderSnowBlock(0.875D, 1.0D, 0.875D, 1.0D, 1.0625D,  1.0D, true, renderer, block, x, y, z, meta);
				}
			}
			
			if (renderSouthWest) {
				renderBlock(0.0D, 0.875D, 0.875D, 0.125D, 1.0D, 1.0D, true, renderer, block, x, y, z, meta);
				if (renderSnow) {
					renderSnowBlock(0.0D, 1.0D, 0.875D, 0.125D, 1.0625D,  1.0D, true, renderer, block, x, y, z, meta);
				}
			}
		}
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.walkwayFenceRenderID;
	}
}