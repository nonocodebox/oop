package clids.ex2.filescript.orders;

import java.io.File;

/**
 * A decorator order class which orders by a primary and secondary order.
 * This class implements Order.

 */
public class CombinedOrder implements Order {
	private Order primaryOrder, secondaryOrder;
	
	/**
	 * CombinedOrder constructor
	 * @param primaryOrder The primary order to use.
	 * @param secondaryOrder The secondary order to use.
	 */
	public CombinedOrder(Order primaryOrder, Order secondaryOrder) {
		this.primaryOrder = primaryOrder;
		this.secondaryOrder = secondaryOrder;
	}
	
	/**
	 * Compares two files using the primary an secondary orders.
	 * @param f1 The first file.
	 * @param f2 The second file.
	 * @return negative if f1 < f2, positive if f1 > f2, 0 if f1 = f2.
	 */
	public int compare(File f1, File f2) {
		int result = primaryOrder.compare(f1, f2);
		
		if (result == 0) {
			result = secondaryOrder.compare(f1, f2);
		}
		
		return result;
	}
}
