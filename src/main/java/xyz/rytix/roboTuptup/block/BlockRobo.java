package xyz.rytix.roboTuptup.block;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

import xyz.rytix.roboTuptup.Config;
import xyz.rytix.roboTuptup.entity.TileEntityRobo;
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
import net.minecraft.util.ITickable;
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

public class BlockRobo extends Block implements ITileEntityProvider{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public enum Move{
		UP,DOWN,FRONT,BACK,LEFT,RIGHT,CLOCK,ANTICLOCK,TELEPORT
	}
	
	public static String REGISTRY_NAME = "robo";
	
	public BlockRobo() {
		super(Material.CLOTH);
		setBlockUnbreakable();
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
		
	}
		
	public BlockPos moveRobot(World worldIn, BlockPos pos, Move move){
		if(worldIn.isRemote){
			return null;
		}
		
		
		BlockPos old = pos;
		EnumFacing facing = (EnumFacing) worldIn.getBlockState(pos).getProperties().get(FACING);
		TileEntityRobo entity = (TileEntityRobo) worldIn.getTileEntity(pos);
		
		switch(move){
			case TELEPORT:
				facing = EnumFacing.NORTH;
				pos = new BlockPos(entity.getBase().getX(),entity.getBase().getY(),entity.getBase().getZ());
				break;
			case CLOCK:
				facing = facing.rotateY();
				break;
			case ANTICLOCK:
				facing = facing.rotateYCCW();
				break;
			case UP:
				pos = pos.up();
				break;
			case DOWN:
				pos = pos.down();
				break;
			case FRONT:
				switch(facing){
				case NORTH:
					pos = pos.north();
					break;
				case SOUTH:
					pos = pos.south();
					break;
				case EAST:
					pos = pos.east();
					break;
				case WEST:
					pos = pos.west();
					break;
				default:
					break;
				}
				break;
			case BACK:
				switch(facing){
				case NORTH:
					pos = pos.south();
					break;
				case SOUTH:
					pos = pos.north();
					break;
				case EAST:
					pos = pos.west();
					break;
				case WEST:
					pos = pos.east();
					break;
				default:
					break;
				}
				break;
			case LEFT:
				switch(facing){
				case NORTH:
					pos = pos.west();
					break;
				case SOUTH:
					pos = pos.east();
					break;
				case EAST:
					pos = pos.north();
					break;
				case WEST:
					pos = pos.south();
					break;
				default:
					break;
				}
				break;
			case RIGHT:
				switch(facing){
				case NORTH:
					pos = pos.east();
					break;
				case SOUTH:
					pos = pos.west();
					break;
				case EAST:
					pos = pos.south();
					break;
				case WEST:
					pos = pos.north();
					break;
				default:
					break;
				}
				break;
			default:
				break;
		
		}
		if(worldIn.isAirBlock(pos) || old == pos){
			//TODO Find a Better way to do this logic.
			BlockPos base = ((TileEntityRobo)worldIn.getTileEntity(old)).getBase();
			worldIn.setBlockToAir(old);
			worldIn.setBlockState(pos, getDefaultState().withProperty(FACING, facing));
			((TileEntityRobo)worldIn.getTileEntity(pos)).setBase(base);
			return pos;
		}else{
			return old;
		}
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer player) {
		
		if(worldIn.isRemote){
			return;
		}
		worldIn.setBlockToAir(((TileEntityRobo)worldIn.getTileEntity(pos)).getBase());
		
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY,
			float hitZ) {
		if(worldIn.isRemote){
			return true;
		}		
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem,
				side, hitX, hitY, hitZ);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state,
			Random rand) {
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

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityRobo();
	}
}