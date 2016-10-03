package xyz.rytix.roboTuptup.helper;

import java.util.ArrayList;
import java.util.List;

import scala.tools.nsc.doc.model.ModelFactory;
import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.Tuptup;
import xyz.rytix.roboTuptup.block.BlockBaseRobo;
import xyz.rytix.roboTuptup.block.BlockRobo;
import xyz.rytix.roboTuptup.entity.EntityMovingBlock;
import xyz.rytix.roboTuptup.itemBlock.ItemBlockBaseRobo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleSpell.MobFactory;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

public final class Initializer {
	public static final List<ObjectHolder<IForgeRegistryEntry<?>>> entrysBlockOrItemBlock = new ArrayList();
	//Objetos Criados Pelo MOD
	//Items
	public static ItemBlockBaseRobo ITEM_BLOCK_BASE_ROBO;
	//Blocos
	public static BlockBaseRobo BLOCK_BASE_ROBO;
	public static BlockRobo BLOCK_ROBO;
	
	public static final <K extends IForgeRegistryEntry<?>> void addToInitialization(
			K object, String registryName) throws Exception {
		for (ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrysBlockOrItemBlock) {
			if (entryItem.OBJECT.equals(object))
				throw new Exception("Objeto de registro duplicado");
		}
		entrysBlockOrItemBlock.add(new ObjectHolder<IForgeRegistryEntry<?>>(object,
				registryName));
	}

	public static final void preInitAll() {
		try {
			BLOCK_BASE_ROBO = new BlockBaseRobo(Material.ROCK);
			BLOCK_ROBO = new BlockRobo();

			ITEM_BLOCK_BASE_ROBO = new ItemBlockBaseRobo(BLOCK_BASE_ROBO);
			
			Initializer.addToInitialization(ITEM_BLOCK_BASE_ROBO, "baseRobo");
			Initializer.addToInitialization(BLOCK_BASE_ROBO, "baseRobo");
			Initializer.addToInitialization(BLOCK_ROBO, "robo");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void registerBlocksAndItemBlocks() {
		for (ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrysBlockOrItemBlock) {
			GameRegistry.register(entryItem.OBJECT, new ResourceLocation(
					Config.MOD_ID, entryItem.REGISTRY_NAME));
		}
	}

	public static final void registerBlocksAndItemBlocksRenders() {
		for (ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrysBlockOrItemBlock) {
			if (entryItem.OBJECT instanceof Item)
				Minecraft
						.getMinecraft()
						.getRenderItem()
						.getItemModelMesher()
						.register(
								(Item) entryItem.OBJECT,
								0,
								new ModelResourceLocation(Config.MOD_ID + ":"
										+ entryItem.REGISTRY_NAME, "inventory"));
		}
	}
	
	static final class ObjectHolder<K extends IForgeRegistryEntry<?>> {
		public final K OBJECT;
		public final String REGISTRY_NAME;

		public ObjectHolder(K object, String registryName) {
			OBJECT = object;
			REGISTRY_NAME = registryName;
		}
	}
}
