package com.mmyzd.nmsot.rule;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.Entity;

import com.mmyzd.nmsot.NoMobSpawningOnTrees;
import com.mmyzd.nmsot.SpawningEntry;

public class RuleMob extends Rule {
	
	public Class<? extends Entity> entityClass = null;
	public HashSet<Class<? extends Entity>> entityClasses = null;
	
	private static HashMap<String, HashSet<Class<? extends Entity>>> classNames = null, mobNames = null, mobFullNames;
	
	public RuleMob(LinkedList<Character> s) throws Exception {
		RuleSet.nextPart(s);
		if (classNames == null) {
			classNames = new HashMap<String, HashSet<Class<? extends Entity>>>();
			mobNames = new HashMap<String, HashSet<Class<? extends Entity>>>();
			mobFullNames = new HashMap<String, HashSet<Class<? extends Entity>>>();
			@SuppressWarnings("unchecked")
			Map<Class<? extends Entity>, String> entityList = EntityList.classToStringMapping;
			for (Entry<Class<? extends Entity>, String> e: entityList.entrySet()) {
				Class<? extends Entity> key = e.getKey();
				String value = e.getValue();
				value = value.toLowerCase().replaceAll("\\s", "");
				addMobLookup(mobFullNames, value, key);
				addMobLookup(mobNames, value.substring(value.indexOf('.') + 1), key);
				addMobLookup(classNames, key.getSimpleName(), key);
			}
		}
		String name = RuleSet.getToken(s).toLowerCase();
		HashSet<Class<? extends Entity>> classNameGroup = classNames.get(name);
		HashSet<Class<? extends Entity>> mobNameGroup = mobNames.get(name);
		HashSet<Class<? extends Entity>> mobFullNameGroup = mobFullNames.get(name);
		if (mobFullNameGroup != null && mobFullNameGroup.size() == 1) {
			entityClass = mobFullNameGroup.iterator().next();
		} else if (classNameGroup != null && classNameGroup.size() == 1) {
			entityClass = classNameGroup.iterator().next();
		} else if (mobNameGroup != null && mobNameGroup.size() == 1) {
			entityClass = mobNameGroup.iterator().next();
		} else if (mobFullNameGroup != null) {
			entityClasses = mobFullNameGroup;
		} else if (classNameGroup != null) {
			entityClasses = classNameGroup;
		} else if (mobNameGroup != null) {
			entityClasses = mobNameGroup;
		}
		if (entityClasses == null && entityClass == null) {
			LogManager.getLogger(NoMobSpawningOnTrees.MODID).warn("Can not find this mob: " + name);
		}
	}
	
	private void addMobLookup(HashMap<String, HashSet<Class<? extends Entity>>> lookup, String key, Class<? extends Entity> value) {
		HashSet<Class<? extends Entity>> group = lookup.get(key);
		if (group == null) lookup.put(key, group = new HashSet<Class<? extends Entity>>());
		group.add(value);
=======
import java.util.LinkedList;

import net.minecraft.entity.Entity;

import com.mmyzd.nmsot.SpawningEntry;

public class RuleMob extends Rule {

	String name, pfxn;
	Class<? extends Entity> ret;
	
	public RuleMob(LinkedList<Character> s) throws Exception {
		RuleSet.nextPart(s);
		name = RuleSet.getIdentifier(s, "mob class name");
		pfxn = "Entity" + name;
>>>>>>> fadfc75ef6f4b7654eb38d208a04d4320c9c61e3
	}
	
	@Override
	public boolean apply(SpawningEntry entry) {
<<<<<<< HEAD
		return entityClasses == null ? entityClass == entry.entity.getClass() : entityClasses.contains(entry.entity.getClass());
=======
		Class<? extends Entity> c = entry.entity.getClass();
		if (ret != null) return c == ret;
		String mob = c.getSimpleName();
		if (mob.equals(name) || mob.equalsIgnoreCase(pfxn)) {
			ret = c;
			return true;
		}
		return false;
>>>>>>> fadfc75ef6f4b7654eb38d208a04d4320c9c61e3
	}

}
