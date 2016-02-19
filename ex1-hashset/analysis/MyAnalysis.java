package clids.ex1.analysis;

import java.util.Date;
import java.util.LinkedList;
import java.util.TreeSet;
import clids.ex1.data_structures.MyHashSet;

/**
 * This class analyzes different types of data structures. 
 *
 */
public class MyAnalysis {

	static final String CONTAINS_DATA1_TEST1 = "-13170890158";
	static final String CONTAINS_DATA1_TEST2 = "hi";
	static final String CONTAINS_DATA2_TEST1 = "23";
	static final String CONTAINS_DATA2_TEST2 = "hi";

	/**
	 * Analyzes different types of data structures adding items time from data files.
	 *
	 * @param file file path
	 * @param tree TreeSet to compare
	 * @param myHashSet myHashSet to compare
	 * @param list LinkedList to compare
	 * @return an array with timing results ordered the way received.
	 */
	private static long [] findAddTime(String file, TreeSet<String> tree, MyHashSet myHashSet,
			LinkedList<String> list) {

		long [] results = new long [3];
		String[] data1 = Ex1Utils.file2array(file);

		// Tree test
		long timeBefore = new Date().getTime();

		for (int i = 0; i < data1.length; i++) {
			tree.add(data1[i]);
		}

		long timeAfter = new Date().getTime();

		long treeTime = timeAfter - timeBefore;

		results[0] = treeTime;

		// MyHashSet test
		timeBefore = new Date().getTime();

		for (int i = 0; i < data1.length; i++) {
			myHashSet.add(data1[i]);
		}

		timeAfter = new Date().getTime();

		long hashTime = timeAfter - timeBefore;

		results[1] = hashTime;

		// LinkedList test
		timeBefore = new Date().getTime();

		for (int i = 0; i < data1.length; i++) {
			list.add(data1[i]);
		}

		timeAfter = new Date().getTime();

		long listTime = timeAfter - timeBefore;

		results[2] = listTime;

		return results;
	}

	/**
	 * Analyzes different types of data structures: whether an item is contained timing.
	 * @param tree TreeSet to compare
	 * @param myHashSet myHashSet to compare
	 * @param list LinkedList to compare
	 * @param check string to be checked.
	 * @return an array with timing results ordered the way received.
	 */
	private static long [] containsTime(TreeSet<String> tree, MyHashSet myHashSet,
			LinkedList<String> list, String check) {
		long [] results = new long [3];

		// MyHashSet test
		long timeBefore = new Date().getTime();

		tree.contains(check);

		long timeAfter = new Date().getTime();

		long treeTime = timeAfter - timeBefore;

		results[0] = treeTime;

		// MyHashSet test
		timeBefore = new Date().getTime();

		myHashSet.contains(check);

		timeAfter = new Date().getTime();

		long hashTime = timeAfter - timeBefore;

		results[1] = hashTime;

		// LinkedList test
		timeBefore = new Date().getTime();

		myHashSet.contains(check);

		timeAfter = new Date().getTime();

		long listTime = timeAfter - timeBefore;

		results[2] = listTime;

		return results;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String data1Path = "data1.txt";
		String data2Path = "data2.txt";

		TreeSet<String> tree = new TreeSet<String>();
		MyHashSet myHashSet = new MyHashSet();
		LinkedList<String> list = new LinkedList<String>();

		TreeSet<String> tree2 = new TreeSet<String>();
		MyHashSet myHashSet2 = new MyHashSet();
		LinkedList<String> list2 = new LinkedList<String>();

		System.out.println("Adding data");

		long [] data1Result = findAddTime(data1Path, tree, myHashSet, list);
		long [] data2Result = findAddTime(data2Path, tree2, myHashSet2, list2);

		// printing
		System.out.println("Data1 timing");
		System.out.println("Tree: "+ data1Result[0]);
		System.out.println("MyHashSet: "+ data1Result[1]);
		System.out.println("List: "+ data1Result[2]);

		System.out.println("Data2 timing");
		System.out.println("Tree: "+ data2Result[0]);
		System.out.println("MyHashSet: "+ data2Result[1]);
		System.out.println("List: "+ data2Result[2]);

		System.out.println("Comparing time between data1 and data2 timing");
		System.out.println("Tree: "+ Math.abs(data2Result[0] - data1Result[0]));
		System.out.println("MyHashSet: "+ Math.abs(data2Result[1] - data1Result[1]));
		System.out.println("List: "+ Math.abs(data2Result[2] - data2Result[2]));

		long [] containData1Test1 = containsTime(tree, myHashSet, list, CONTAINS_DATA1_TEST1);
		long [] containData1Test2 = containsTime(tree, myHashSet, list, CONTAINS_DATA1_TEST2);

		// printing
		System.out.println("Containing timing (data1): " + CONTAINS_DATA1_TEST1);
		System.out.println("Tree: "+ containData1Test1[0]);
		System.out.println("MyHashSet: "+ containData1Test1[1]);
		System.out.println("List: "+ containData1Test1[2]);

		System.out.println("Containing timing (data1): " + CONTAINS_DATA1_TEST2);
		System.out.println("Tree: "+ containData1Test2[0]);
		System.out.println("MyHashSet: "+ containData1Test2[1]);
		System.out.println("List: "+ containData1Test2[2]);

		System.out.println("Comparing time between " + CONTAINS_DATA1_TEST1 +  " and " + CONTAINS_DATA1_TEST2 +
						   " timing (data1)");
		System.out.println("Tree: "+ Math.abs(containData1Test2[0] - containData1Test1[0]));
		System.out.println("MyHashSet: "+ Math.abs(containData1Test2[1] - containData1Test1[1]));
		System.out.println("List: "+ Math.abs(containData1Test2[2] - containData1Test1[2]));

		long [] containData2Test1 = containsTime(tree2, myHashSet2, list2, CONTAINS_DATA2_TEST1);
		long [] containData2Test2 = containsTime(tree2, myHashSet2, list2, CONTAINS_DATA2_TEST2);

		// printing
		System.out.println("Containing timing (data2): " + CONTAINS_DATA2_TEST1);
		System.out.println("Tree: "+ containData2Test1[0]);
		System.out.println("MyHashSet: "+ containData2Test1[1]);
		System.out.println("List: "+ containData2Test1[2]);

		System.out.println("Containing timing (data2): " + CONTAINS_DATA2_TEST2);
		System.out.println("Tree: "+ containData1Test2[0]);
		System.out.println("MyHashSet: "+ containData2Test2[1]);
		System.out.println("List: "+ containData2Test2[2]);

		System.out.println("Comparing time between " + CONTAINS_DATA2_TEST1 +  " and " + CONTAINS_DATA2_TEST2 +
						   " timing (data2)");
		System.out.println("Tree: "+ Math.abs(containData2Test2[0] - containData2Test1[0]));
		System.out.println("MyHashSet: "+ Math.abs(containData2Test2[1] - containData2Test1[1]));
		System.out.println("List: "+ Math.abs(containData2Test2[2] - containData2Test1[2]));
	}

}
