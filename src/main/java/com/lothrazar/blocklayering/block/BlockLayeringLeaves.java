package com.lothrazar.blocklayering.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockLayeringLeaves extends BlockLayering {

  public BlockLayeringLeaves(Block main, Properties props, String reg) {
    super(main, props, reg);
  }

  @Override
  public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return 255;
  }
}
