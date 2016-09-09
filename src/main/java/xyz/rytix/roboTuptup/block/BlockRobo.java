package xyz.rytix.roboTuptup.block;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.helper.Initializer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRobo extends Block{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public enum Move{
		UP,DOWN,FRONT,BACK,LEFT,RIGHT
	}
	public static String REGISTRY_NAME = "robo";
	
	public BlockRobo() {
		super(Material.CLOTH);
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY,
			float hitZ) {
		side.rotateY();
		// TODO Auto-generated method stub
//		if(side == EnumFacing.SOUTH){
//			worldIn.setBlockToAir(pos);
//			worldIn.setBlockState(pos.add(0, 0, -1), getDefaultState().withProperty(FACING, EnumFacing.WEST));
//		}
		
		worldIn.scheduleBlockUpdate(pos, this, 200, 1);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem,
				side, hitX, hitY, hitZ);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state,
			Random rand) {
		worldIn.setBlockToAir(pos);
		System.out.println(state.getProperties().get(FACING));
		//worldIn.setBlockState(pos.add(0, 0, -1), getDefaultState().withProperty(FACING, state.getProperties()));
		// TODO Auto-generated method stub
		super.updateTick(worldIn, pos, state, rand);
	}
	
	/*
	 * Define quais propriedades este bloco terá, se não sobrescrever ele terá a default.
	 * \nA default não possui propriedades
	 * {
	 */
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] {FACING});
		
	}
	// }
	//Funções Necessárias para ler o modelo da textura {
	@Override
	public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
	// }
	// Função que faz as faces dos blocos próximos a este serem renderizados {
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	// }
}