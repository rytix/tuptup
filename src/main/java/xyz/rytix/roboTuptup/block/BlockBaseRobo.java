package xyz.rytix.roboTuptup.block;

import xyz.rytix.roboTuptup.entity.TileEntityBaseRobo;
import xyz.rytix.roboTuptup.helper.Initializer;
import akka.io.Tcp.Register;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBaseRobo extends Block implements ITileEntityProvider{
	
	public BlockBaseRobo(Material materialIn) {
		super(materialIn);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			ItemStack heldItem, EnumFacing side, float hitX, float hitY,
			float hitZ) {
		if(worldIn.isRemote){
			return true;
		}
		
		//ILockableContainer ilockablecontainer = this.getLockableContainer(worldIn, pos);

//        if (ilockablecontainer != null)
//        {
//            playerIn.displayGUIChest(ilockablecontainer);
//        }
		
		TileEntityBaseRobo tileEntity = (TileEntityBaseRobo)worldIn.getTileEntity(pos);
		playerIn.displayGUIChest(tileEntity);
		//tileEntity.onBlockActivated(pos);
		// TODO Auto-generated method stub
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem,
				side, hitX, hitY, hitZ);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer player) {
		onBlockBeforeDestroyed(worldIn, pos);
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	@Override
	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
		onBlockBeforeDestroyed(world, pos);
		super.onBlockExploded(world, pos, explosion);
	}
	
	private void onBlockBeforeDestroyed(World worldIn, BlockPos pos){
		if(worldIn.isRemote){
			return;
		}
		worldIn.setBlockToAir(((TileEntityBaseRobo)worldIn.getTileEntity(pos)).getPos_robo());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state,
			EntityLivingBase placer, ItemStack stack) {
		TileEntityBaseRobo tileEntity = (TileEntityBaseRobo)worldIn.getTileEntity(pos);
		tileEntity.onBlockPlacedBy(pos);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return isAir(worldIn.getBlockState(pos.up()), worldIn, pos.up()) ? super.canPlaceBlockAt(worldIn, pos) : false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityBaseRobo();
	}
}
