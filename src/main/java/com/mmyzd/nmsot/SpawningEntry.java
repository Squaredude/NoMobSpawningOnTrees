package com.mmyzd.nmsot;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class SpawningEntry {
	
	public BlockPos pos;
	public int x, y, z, damage;
	public World world;
	public Block block;
	public Entity entity;
	
	public void init(LivingSpawnEvent.CheckSpawn event) {
		x = (int)Math.floor(event.x);
		y = (int)event.y - 1;
		z = (int)Math.floor(event.z);
		pos = new BlockPos(x, y, z);
		world  = event.world;
		block  = world.getBlockState(pos).getBlock();
		damage = block.getDamageValue(world, pos);
		entity = event.entity;
	}
	
}