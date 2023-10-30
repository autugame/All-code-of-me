package com.guava;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

public class GuavaDemo {
	
	@Test
	public void testGuava9() {
		Table<String, String, Integer> table = HashBasedTable.create();
		table.put("jeck", "java", 80);
		table.put("tom", "php", 60);
		table.put("bin", "java", 59);
		table.put("lily", "ui", 99);
		
		Set<Cell<String, String, Integer>> cells= table.cellSet();
		for (Cell c : cells) {
			System.out.println(c.getRowKey() + "-" + c.getColumnKey() + "-" + c.getValue());
		}
	}
	
	@Test
	public void testGuava8() {
		BiMap<String, String> map = HashBiMap.create();
		map.put("finally_test", "0158339248");
		map.put("bin", "18239382184");
		String name = map.inverse().get("0158339248");
		System.out.println(name);
	}
	
	@Test
	public void testGuava7() {
		Map<String, String> map = new HashMap<>();
		map.put("java从入门到精通", "bin");
		map.put("android从入门到精通", "bin");
		map.put("php从入门到精通", "jack");
		map.put("笑看人生", "vince");
		
		Multimap<String, String> mmap = ArrayListMultimap.create();
		Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			mmap.put(entry.getValue(), entry.getKey());
		}
		Set<String> keyset = mmap.keySet();
		for (String key : keyset) {
			Collection<String> values = mmap.get(key);
			System.out.println(key + "->" + values);
		}
	}
	@Test
	public void testGuava6() {
		String s = "good good study day day up";
		String[] ss = s.split(" ");
		HashMultiset<String> set = HashMultiset.create();
		for (String str : ss) {
			set.add(str);
		}
		
		Set<String> set2 = set.elementSet();
		for (String str : set2) {
			System.out.println(str + ":" + set.count(str));
		}
	}
	
	@Test
	public void testGuava5() {
		Set<Integer> set1 = Sets.newHashSet(1, 2, 3);
		Set<Integer> set2 = Sets.newHashSet(3, 4, 5);
		
		//SetView<Integer> v1 = Sets.intersection(set1, set2);
		//v1.forEach(System.out::println);
		
		SetView<Integer> v2 = Sets.difference(set1, set2);
		v2.forEach(System.out::println);
		
		SetView<Integer> v3 = Sets.union(set1, set2);
		v3.forEach(System.out::println);
	}
	
	@Test
	public void testGuava4() {
		List<String> list = Lists.newArrayList("java", "php", "c++", "c", "h5", "javascript");
		Function<String, String> f1 = new Function<String, String>() {

			@Override
			public String apply(String t) {
				return t.length() > 4 ? t.substring(0, 4) : t;
			}
			
		};
		Function<String, String> f2 = new Function<String, String>() {
			
			@Override
			public String apply(String t) {
				return t.toUpperCase();
			}
			
		};
		
		Function<String, String> f = Functions.compose(f1, f2);
		Collection<String> coll = Collections2.transform(list, f);
		
		coll.forEach(System.out::println);
	}
	
	@Test
	public void testGuava3() {
		Set<Long> timeSet = Sets.newHashSet(20121212L, 20220818L, 20160808L);
		Collection<String> timeCollect = Collections2.transform(timeSet, (e) -> new SimpleDateFormat("yyyy-MM-dd").format(e));
		timeCollect.forEach(System.out::println);
	}
	
	@Test
	public void testGuava2() {
		List<String> list = Lists.newArrayList("java", "php", "c++", "c", "h5", "javascript");
		Collection<String> result = Collections2.filter(list, (e) -> e.startsWith("j"));
		result.forEach(System.out::println);
	}
	
	
	@Test
	public void testGuava() {
		System.out.println("test guava");
		//List<String> list = Arrays.asList("jack", "tom", "lily", "bin");
		List<String> list = new ArrayList<>();
		list.add("jack");
		list.add("tom");
		list.add("lily");
		list.add("bin");
		//
		//List<String> readList = Collections.unmodifiableList(list);
		//readList.add("this");
		ImmutableList<String> iList =  ImmutableList.of("jack", "tom", "lily", "bin");
		
	}
	
}
