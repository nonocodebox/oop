package clids.ex1.data_structures;

/**
 * This class is an implementation to HashTable.
 *
 */
public class MyHashSet {

	private int capacity;
	private double upperLoadFactor;
	private double lowerLoadFactor;
	private StringLinkedList [] hashTable;
	private  int size;

	/**
	* A default constructor.
	* Constructs a new, empty table with default initial capacity (16)
	* and upper factor (0.75) and lower load factor (0.25).
	*/
	public MyHashSet() {
		this(16, 0.75, 0.25);
	}

	/*
	 * Initializes array of StringLinkedList.
	 */
	private void initializeTable(int size) {
		this.hashTable = new StringLinkedList [size];
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new StringLinkedList();
		}
	}

	/**
	* Constructs a new, empty table with the specified initial capacity
	* and the specified load factors.
	* @param initialCapacity - the initial capacity of the hash table.
	* @param upperLoadFactor - the upper load factor of the hash table.
	* @param lowerLoadFactor - the lower load factor of the hash table.
	*/
	public MyHashSet(int initialCapacity, double upperLoadFactor,
			double lowerLoadFactor) {
		this.capacity = initialCapacity;
		initializeTable(capacity);
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
		this.size = 0;
	}

	/**
	* Data constructor - builds the hash set by adding the elements
	* into the input array one-by-one.
	* If the same value appears twice (or more) in the list, it is
	* ignored.
	* The new table has the default values of initial capacity
	* (16), upper load factor (0.75), and lower load factor (0.25).
	* @param data Values to add to the set.
	*/
	public MyHashSet(String[] data) {
		this();

		for (int i = 0; i < data.length; i++) {
			if (!contains(data[i])) {
				this.add(data[i]);
			}
		}
	}

	/*
	 * Hash code method.
	 */
	private int hash(String data) {
		return Math.abs(data.hashCode() % hashTable.length);
	}

	/**
	* Add a new element with value newValue into the table.
	* @param newValue new value to add to the table.
	* @return false iff newValue already exists in the table.
	*/
	public boolean add(String newValue) {
		return this.add(newValue, true);
	}

	/*
	 * Helper adding value method.
	 *
	 * checkLimits - whether we should consider resizing.
	 */
	private boolean add(String newValue, boolean checkLimits) {
		if (newValue == null) {
			return false;
		}
		if (!contains(newValue)) {
			size++;

			int index = hash(newValue);
			hashTable[index].addLast(newValue);

			if (checkLimits) {
				double loadFactor = (double)size/(double)capacity;
				if (loadFactor > upperLoadFactor) {
					rehashing(capacity*2);
				}
			}

			return true;
		}

		return false;
	}

	/*
	 * Rehashes : Resizes the table and adds old values
	 * (insertion includes the use of re-calculating hash codes for each value)
	 *
	 * newSize - the amount of cells.
	 */
	private void rehashing(int newSize) {
		StringLinkedList newList = new StringLinkedList();
		for (int i = 0; i < hashTable.length; i++) {
			for (int j = hashTable[i].size() - 1; j >= 0; j--) {
				newList.add((String)hashTable[i].poll());
			}
		}

		initializeTable(newSize);
		this.size = 0;
		// flushes the values down to the hash table;
		for (int i = newList.size(); i > 0; i++) {
			this.add(newList.poll(), false);
		}

		capacity = newSize;
	}

	/**
	* Look for an input value in the table.
	* @param searchVal value to search for.
	* @return true iff searchVal is found in the table.
	*/
	public boolean contains(String searchVal) {
		if (searchVal == null) {
			return false;
		}
		int index = hash(searchVal);
		return this.hashTable[index].contains(searchVal);

	}

	/**
	* Remove the input element form the table.
	* @param toDelete value to delete.
	* @return true iff toDelete is found and deleted.
	*/
	public boolean delete(String toDelete) {
		if (!contains(toDelete)) {
			return false;
		}

		size--;
		double loadFactor = (double)size/(double)capacity;
		int index = hash(toDelete);
		this.hashTable[index].remove(toDelete);

		if (loadFactor < lowerLoadFactor) {
			rehashing(capacity/2);
		}
		return true;
	}
	/**
	* @return the number of elements in the table.
	*/
	public int size() {
		return size;
	}
	/**
	* @return the capacity of the table.
	*/
	public int capacity() {
		return capacity;
	}

}
