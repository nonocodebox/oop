package clids.ex3.data_structures;

/**
 * An AVL tree is a self-balancing binary search tree. 
 * AVL trees maintain the property that for each node, 
 * the difference between the heights of both its sub-trees is at most 1.
 * This is done by re-balancing the tree after each insertion and deletion operation.
 * 

 *
 */
public class AvlTree{
	private static final int NOT_FOUND = -1;
	private TreeNode root;
	private int size;
	
	/*
	 * A class represents a tree node.
	 */
	private class TreeNode {
		private TreeNode parent;
		private TreeNode left;
		private TreeNode right;
		private int height;
		private int value;
		
		public TreeNode (TreeNode parent, TreeNode left, TreeNode right, 
				int height, int value) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.height = height;
			this.value = value;
		}
	}
	
	/*
	 * Describes what rotation should be performed.
	 */
	private enum ViolationSituation {
		NONE,
		RIGHT,
		LEFT,
		LR,
		RR,
		RL,
		LL
	}
	
	/*
	 * Holds a tree violation information.
	 * first - next node after the violationNode, 
	 * staring from violationNode to the longest route in the tree.
	 * second the next node after first, staring from violationNode 
	 * to the longest route in the tree.
	 */
	private class ViolationInfo {
		private ViolationSituation first;
		private ViolationSituation second;
		private TreeNode violationNode;
		
		public ViolationInfo(ViolationSituation first, 
				ViolationSituation second,
				TreeNode violationNode) {
			this.first = first;
			this.second = second;
			this.violationNode = violationNode;
		}
		public ViolationInfo() {
			this(ViolationSituation.NONE, ViolationSituation.NONE, null);
		}
		
		/*
		 * Checks whether a given cell has a violation,
		 * if so finds first and second (see explanation above)
		 */
		public boolean checkCellViolation(TreeNode current) {
			if (current == null) {
				return false;
			}
			
			int heightLeft = -1;
			int heightRight = -1;
			
			if (current.left != null) {
				heightLeft = current.left.height;
			}
			if (current.right != null) {
				heightRight = current.right.height;
			}
			
			if (Math.abs(heightLeft - heightRight) > 1) {
				this.violationNode = current;
				
				if (heightLeft > heightRight) {
					this.first = ViolationSituation.LEFT;
					
					heightLeft = -1;
					heightRight = -1;

					if (current.left.left != null) {
						heightLeft = current.left.left.height;
					}
					if (current.left.right != null) {
						heightRight = current.left.right.height;
					}
				}
				else {
					this.first = ViolationSituation.RIGHT;

					heightLeft = -1;
					heightRight = -1;

					if (current.right.left != null) {
						heightLeft = current.right.left.height;
					}
					if (current.right.right != null) {
						heightRight = current.right.right.height;
					}
				}
				
				if (heightLeft > heightRight) {
					this.second = ViolationSituation.LEFT;
				}
				else {
					this.second = ViolationSituation.RIGHT;
				}
				
				return true;
			}
			
			return false;
		}
	}
	
	/**
	* Does tree contain a given input value.
	*
	* @param searchVal value to search for.
	* @return if newValue is found in the tree, return the depth of its node(where 0 is the root).
	* Otherwise return -1.
	*/
	// We assume that the tree is an Avl tree therefore it takes O(log(n)).
	public int contains(int searchVal) {
		TreeNode x = root;
		return contains(x, searchVal);
	}
	
	private int contains(TreeNode x, int searchVal) {
		if (x == null) {
			return NOT_FOUND;
		}
		if (x.value == searchVal) {
			return level(x);
		}
		else if (x.value > searchVal) {
			return contains(x.left, searchVal);
		}
		else {
			return contains(x.right, searchVal);
		}
	}

	
	/**
	*Add a new node with key newValue into the tree.
	*
	* @param newValue new value to add to the tree.
	* @return false if newValue already exist in the tree
	*
	*/
	public boolean add(int newValue) {
		if (contains(newValue) != NOT_FOUND) {
			return false;
		}
		
		if (root == null) {
			root = new TreeNode(null, null, null, 0, newValue);
		}
		
		
		else {
			TreeNode next = root;
			TreeNode x = root;
			ViolationSituation side = ViolationSituation.NONE;
			
			do {
				x = next;
				if (newValue < next.value) { 
					next = next.left;
					side = ViolationSituation.LEFT;
				}
				else {
					next = next.right;
					side = ViolationSituation.RIGHT;
				}
			} while (next != null);
			
			TreeNode newLeaf = new TreeNode(x, null, null, 0, newValue);
			
			if (side == ViolationSituation.RIGHT) {
				x.right = newLeaf;
			}
			else {
				x.left = newLeaf;
			}
			updateHeightsFromNode(newLeaf);
			searchBalance(x);
		}
		
		size++;
		return true;
	}
	
	/*
	 * climbs up to the tree by iterating the parents,
	 * and balances the tree, if it finds a violation (O(log n)).
	 */
	private void searchBalance(TreeNode node) {
		ViolationInfo violation = new ViolationInfo();
		
		while(node != null) {
			if(violation.checkCellViolation(node)) {
				balance(violation);
			}
			
			node = node.parent;
		}
	}
	
	/*
	 * balances tree according to ViolationInfo (O(1)).
	 */
	private void balance(ViolationInfo info) {
		TreeNode first;
		TreeNode second;
		ViolationSituation state = ViolationSituation.NONE;
		
		if (info.first == ViolationSituation.RIGHT ) {
			first = info.violationNode.right;
			if (info.second == ViolationSituation.RIGHT) {
				second = first.right;
				state = ViolationSituation.RR;
			}
			else {
				second = first.left;
				state = ViolationSituation.RL;
			}
		}
		else {
			first = info.violationNode.left;
			if (info.second != null && info.second == ViolationSituation.RIGHT) {
				second = first.right;
				state = ViolationSituation.LR;
			}
			else {
				second = first.left;
				state = ViolationSituation.LL;
			}
		} 
		switch (state) {
		case RL:
			info.violationNode.right = second;
			second.parent = info.violationNode;
			first.left = second.right;
			if (second.right != null) {
				second.right.parent = first;
			}
			second.right = first;
			first.parent = second;
			
			updateNodeHeight(first);
			updateNodeHeight(second);
			updateNodeHeight(info.violationNode);
			
			TreeNode temp = first;
			first = second;
			second = temp;
		case RR:
			TreeNode head = info.violationNode.parent;
			
			info.violationNode.right = first.left;
			if (first.left != null) {
				first.left.parent = info.violationNode;
			}
			
			first.left = info.violationNode;
			info.violationNode.parent = first;
			
			if (info.violationNode == root) {
				root = first;
				first.parent = null;
			}
			else {
				if (head.value > first.value) {
					head.left = first;
				}
				else {
					head.right = first;
				}
				first.parent = head;
			}
			
			updateNodeHeight(info.violationNode);
			updateNodeHeight(second);
			updateNodeHeight(first);
			break;
		case LR:
			info.violationNode.left = second;
			second.parent = info.violationNode;
			first.right = second.left;
			if (second.left != null) {
				second.left.parent = first;
			}
			second.left = first;
			first.parent = second;

			updateNodeHeight(first);
			updateNodeHeight(second);
			updateNodeHeight(info.violationNode);
			
			temp = first;
			first = second;
			second = temp;
		case LL:
			head = info.violationNode.parent;
			info.violationNode.left = first.right;
			if (first.right != null) {
				first.right.parent = info.violationNode;
			}
			first.right = info.violationNode;
			info.violationNode.parent = first;
			
			if (info.violationNode == root) {
				root = first;
				first.parent = null;
			}
			else {
				if (head.value > first.value) {
					head.left = first;
				}
				else {
					head.right = first;
				}
				first.parent = head;
			}
			
			updateNodeHeight(info.violationNode);
			updateNodeHeight(second);
			updateNodeHeight(first);
			break;
		default:
			break;
		}
	}
	
	/*
	 * Updates nodes' heights starting from a given node upwards (O(log n)).
	 */
	private void updateHeightsFromNode(TreeNode node) {
		while (node != null) {
			updateNodeHeight(node);
			node = node.parent;
		}
	}
	
	/*
	 * Updates a single node's height (O(1)).
	 */
	private void updateNodeHeight(TreeNode node) {
		int leftHeight = -1;
		int rightHeight = -1;
		
		if (node.left != null) {
			leftHeight = node.left.height;
		}
		
		if (node.right != null) {
			rightHeight = node.right.height;
		}
		
		node.height = Math.max(leftHeight, rightHeight) + 1;
	}
	
	/**
	*Remove a node from the tree,if it exists.
	*
	* @param toDelete value to delete
	* @return true if toDelete is found and deleted
	*/
	public boolean delete(int toDelete) {
		if (root == null) {
			return false;
		}
		
		return delete(treeSearch(root, toDelete));
	}
	
	/*
	 * delete helper.
	 */
	private boolean delete(TreeNode found) {
		if (found == null) {
			return false;
		}
		
		TreeNode potentialViolationCell = found.parent;
		
		if (found.left == null && found.right == null) {
			// not the root has to have a parent
			
			if (found.parent == null) {
				root = null;
			}
			else if (found.parent.value < found.value) {
				found.parent.right = null;
			}
			else {
				found.parent.left = null;
			}
		}
		else if (found.left == null || found.right == null) {
			TreeNode child;
			if (found.left != null) {
				child = found.left;
			}
			else {
				child = found.right;
			}
			
			if (found.parent == null) {
				root = child;
			}
			else if (found.parent.value > child.value) {
				found.parent.left = child;
			}
			else {
				found.parent.right = child;
			}
			child.parent = found.parent;
		}
		else { // two children doesnt create any voilation 
			TreeNode successor = successor(found);
			int temp = successor.value;
			successor.value = found.value;
			found.value = temp;
			delete(successor);
			return true;
		}
		
		size--;
		updateHeightsFromNode(potentialViolationCell);
		searchBalance(potentialViolationCell);
		
		return true;
	}
	
	/*
	 * finds a node's successor.
	 */
	private TreeNode successor(TreeNode x) {
		if (x == null || root == null) {
			return null;
		}
		if (x.right != null) {
			return findMin(x.right);
		}
		else if (x.value == (findMax(root)).value) {
			return null;
		}
		else {
			TreeNode parent = x.parent;
			while (parent != null && parent.left == null) {
				parent = parent.parent;
			}
			return parent;
		}
	}
	
	/*
	 * finds the minimal node in a subtree.
	 */
	private TreeNode findMin(TreeNode x) {
		if (root == null) {
			return null;
		}
		
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	/*
	 * finds the maximal node in a subtree.
	 */
	private TreeNode findMax(TreeNode x) {
		if (root == null) {
			return null;
		}
		
		while (x.right != null) {
			x = x.right;
		}
		return x;
	}
	
	/*
	 * searches a node in a subtree by value.
	 */
	private TreeNode treeSearch(TreeNode x,int value) {
		if (root == null){
			return null;
		}
		if (x == null) {
			return null;
		}
		
		else if (x.value == value) {
			return x;
		}
		else if (x.value > value) {
			return treeSearch(x.left, value);
		}
		else {
			return treeSearch(x.right, value);
		}
	}
	
	/**
	* @return number of nodes in the tree
	*/
	public int size() {
		return size;
	}
	
	/*
	 * returns a node's level.
	 */
	private int level(TreeNode node) {
		if (root == null || node == null) {
			return -1;
		}
		
		int i = 0;
		TreeNode parent = node.parent;
		while (parent != null) {
			i++;
			parent = parent.parent;
		}
		
		return i;
	}
	
	/**
	* A default constructor.
	*/
	public AvlTree() {
		
	}
	/**
	* A data constructor
	* a constructor that builds the tree by adding the elements in the input array one-by-one.
	* If the same value appears twice (or more) in the list, it is ignored.
	*
	@param data values to add to tree
	*/
	public AvlTree(int[] data) {
		for (int i = 0; i < data.length; i++) {
			if (this.contains(data[i]) == NOT_FOUND) {
				this.add(data[i]);
			}
		}
	}
}
