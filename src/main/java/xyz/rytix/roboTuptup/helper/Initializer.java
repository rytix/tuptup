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
	public static final List<ObjectHolder<IForgeRegistryEntry<?>>> entrys = new ArrayList();
	//Objetos Criados Pelo MOD
	//Items
	public static ItemBlockBaseRobo ITEM_BLOCK_BASE_ROBO;
	//Blocos
	public static BlockBaseRobo BLOCK_BASE_ROBO;
	public static BlockRobo BLOCK_ROBO;
	
	public static final <K extends IForgeRegistryEntry<?>> void addToInitialization(
			K object, String registryName) throws Exception {
		for (ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrys) {
			if (entryItem.OBJECT.equals(object))
				throw new Exception("Objeto de registro duplicado");
		}
		entrys.add(new ObjectHolder<IForgeRegistryEntry<?>>(object,
				registryName));
	}

	public static final void preInitAll() {
		try {
			/// Criação da base do robo
			//Bloco
			BLOCK_BASE_ROBO = new BlockBaseRobo(Material.CLOTH);
			Initializer.addToInitialization(BLOCK_BASE_ROBO, "baseRobo");
			//Item
			ITEM_BLOCK_BASE_ROBO = new ItemBlockBaseRobo(BLOCK_BASE_ROBO);
			Initializer.addToInitialization(ITEM_BLOCK_BASE_ROBO, "baseRobo");
			
			//Criação do robo
			BLOCK_ROBO = new BlockRobo();
			Initializer.addToInitialization(BLOCK_ROBO, "robo");
			
			//Criação da entidade que se move
			createEntity(EntityMovingBlock.class, "Tupzin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void initAll() {
		for (ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrys) {
			GameRegistry.register(entryItem.OBJECT, new ResourceLocation(
					Config.MOD_ID, entryItem.REGISTRY_NAME));
		}
	}

	public static final void registerAllRenders() {
		for (ObjectHolder<IForgeRegistryEntry<?>> entryItem : entrys) {
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

	public static final void createEntity(Class <? extends Entity> entityClass, String entityName){
		
		EntityRegistry.registerModEntity(entityClass, entityName, 0, Tuptup.instance, 64, 20, true);
		EntityRegistry.registerEgg(entityClass, 655523, 144568);
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
