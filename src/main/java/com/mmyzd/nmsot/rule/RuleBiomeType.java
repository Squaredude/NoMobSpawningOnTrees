package com.mmyzd.nmsot.rule;

import java.util.HashSet;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.mmyzd.nmsot.NoMobSpawningOnTrees;
import com.mmyzd.nmsot.SpawningEntry;

public class RuleBiomeType extends Rule {

	public HashSet<Integer> biomeIDs = new HashSet<Integer>();
	
	public RuleBiomeType(LinkedList<Character> s) throws Exception {
		RuleSet.nextPart(s);
		String token = RuleSet.getToken(s).toUpperCase();
		Type type = BiomeDictionary.Type.getType(token);
		BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(type);
		for (BiomeGenBase biome: biomes) biomeIDs.add(BiomeGenBase.getIdForBiome(biome));
		if (biomeIDs.isEmpty()) {
			LogManager.getLogger(NoMobSpawningOnTrees.MODID).warn("No biome in biome type: " + token);
		}
		
	}
	
	@Override
	public boolean apply(SpawningEntry entry) {
		return biomeIDs.contains(BiomeGenBase.getIdForBiome(entry.world.getBiomeGenForCoords(entry.pos)));
	}

}
