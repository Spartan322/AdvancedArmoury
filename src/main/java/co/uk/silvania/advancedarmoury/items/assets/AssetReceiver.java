package co.uk.silvania.advancedarmoury.items.assets;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items.generic.ReceiverCasing;
import cpw.mods.fml.common.registry.GameRegistry;

public class AssetReceiver extends ReceiverCasing {
	
	public int magId = 0;
	public boolean topRail = false;
	
	public AssetReceiver(ComponentReceiver cpt) {
		super(cpt.componentName,
				cpt.displayName, 
				cpt.gunType, 
				cpt.xSize,
				cpt.ySize, 
				cpt.zSize, 
				cpt.frontEndX,
				cpt.frontEndY,
				cpt.frontEndZ,
				cpt.stockX,
				cpt.stockY,
				cpt.stockZ,
				cpt.magX,
				cpt.magY,
				cpt.magZ,
				cpt.attachmentX,
				cpt.attachmentY,
				cpt.attachmentZ,
				cpt.magId, 
				cpt.topRail,
				cpt.modelName, 
				cpt.modelTexture);
		
		GameRegistry.registerItem(this, cpt.unlocalizedName, AdvancedArmoury.modid);
	}
}
