package com.lothrazar.blocklayering.registry;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import com.lothrazar.blocklayering.ModAbsentBD;
import com.lothrazar.blocklayering.block.BlockLayering;
import com.lothrazar.blocklayering.block.ItemLayering;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LayeringRegistry {

  public static List<Item> itemList = new ArrayList<Item>();
  public static List<Block> blocks = new ArrayList<Block>();

  @SubscribeEvent
  public void onRegistryEvent(RegistryEvent.Register<Block> event) {
    event.getRegistry().registerAll(blocks.toArray(new Block[0]));
  }

  @SubscribeEvent
  public void registerItems(RegistryEvent.Register<Item> event) {
    for (Item item : itemList) {
      event.getRegistry().register(item);
      //     OreDictionary.registerOre(string , item);
    }
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void registerModels(ModelRegistryEvent event) {
    String name;
    Item item;
    for (Block block : blocks) {
      item = Item.getItemFromBlock(block);
      name = ModAbsentBD.MODID + ":" + block.getUnlocalizedName().replaceAll("tile.", "");
      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, "inventory"));
      ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name));
    }
  }


  public void createLayer(Material mat, Item drop, String name) {
    BlockLayering block = new BlockLayering(mat, drop);
    registerBlock(block, "layer_" + name, new ItemLayering(block));
  }

  private Block registerBlock(Block block, String name) {
    return this.registerBlock(block, name, null);
  }

  private Block registerBlock(Block block, String name, @Nullable ItemBlock itemblock) {
    block.setCreativeTab(ModAbsentBD.tab);
    block.setRegistryName(new ResourceLocation(ModAbsentBD.MODID, name));
    block.setUnlocalizedName(name);
    blocks.add(block);
    ItemBlock ib;
    if (itemblock == null) {
      ib = new ItemBlock(block);
    }
    else {
      ib = itemblock;
    }
    ib.setRegistryName(block.getRegistryName()); // ok good this should work yes? yes! http://mcforge.readthedocs.io/en/latest/blocks/blocks/#registering-a-block
    itemList.add(ib);
    return block;
  }

}
