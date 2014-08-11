package vswe.production;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.config.Configuration;
import vswe.production.block.ModBlocks;
import vswe.production.config.ConfigLoader;
import vswe.production.creativetab.CreativeTabProduction;
import vswe.production.gui.GuiHandler;
import vswe.production.item.ModItems;
import vswe.production.network.PacketHandler;



@Mod(modid = "StevesWorkshop", name = StevesProduction.NAME, version = "0.2.2")
public class StevesProduction {
    public static final String CHANNEL = "SWorkshop";
    public static final String NAME = "Steve's Workshop";

    public static FMLEventChannel packetHandler;

    @Mod.Instance("StevesWorkshop")
    public static StevesProduction instance;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigLoader.init(event.getSuggestedConfigurationFile());
        packetHandler = NetworkRegistry.INSTANCE.newEventDrivenChannel(CHANNEL);
        new CreativeTabProduction();
        ModItems.init();
        ModBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        packetHandler.register(new PacketHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}
