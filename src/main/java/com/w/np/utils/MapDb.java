package com.w.np.utils;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class MapDb {

	public static void main(String[] args) {
		ConcurrentNavigableMap<Integer, String> treeMap = DBMaker.newTempTreeMap();
		treeMap.put(111,"some value");
		
		System.out.println(treeMap.get(111));
		
		
		DB db = DBMaker.newFileDB(new File("testdb"))
	               .closeOnJvmShutdown()
	               .encryptionEnable("password")
	               .make();

	// open existing an collection (or create new)
	ConcurrentNavigableMap<Integer,String> map = db.getTreeMap("collectionName");

	map.put(1, "one");
	map.put(2, "two");
	// map.keySet() is now [1,2]

	db.commit();  //persist changes into disk

	map.put(3, "three");
	// map.keySet() is now [1,2,3]
	db.rollback(); //revert recent changes
	// map.keySet() is now [1,2]

	db.close();

	}

}
