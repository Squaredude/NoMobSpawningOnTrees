package com.mmyzd.nmsot.rule;

import java.util.LinkedList;

import com.mmyzd.nmsot.IntegerRange;
import com.mmyzd.nmsot.SpawningEntry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class RuleBlock extends Rule {

	private Block block = Blocks.AIR;
	private int lhs = 0, rhs = OreDictionary.WILDCARD_VALUE - 1;

	public RuleBlock(LinkedList<Character> s) throws Exception {
		RuleSet.nextPart(s);
		String modid = RuleSet.getToken(s);
		RuleSet.nextPart(s);
		String bname = RuleSet.getToken(s);
		if (RuleSet.getTokenEqualsIgnoreCase(s, ":")) {
			IntegerRange range = new IntegerRange(s);
			lhs = range.lhs;
			rhs = range.rhs;
		}
		block = Block.REGISTRY.getObject(new ResourceLocation(modid, bname));
	}

	@Override
	public boolean apply(SpawningEntry entry) {
		return entry.block == block && entry.damage >= lhs && entry.damage <= rhs;
	}

}
