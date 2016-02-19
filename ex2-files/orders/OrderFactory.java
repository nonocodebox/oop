package clids.ex2.filescript.orders;

import clids.ex2.filescript.WarningException;

/**
 * A factory class to create Order objects from order strings.

 */
public class OrderFactory {
	private static final String SIZE = "size";
	private static final String MOD = "mod";
	private static final String FILE = "file";
	private static final String ABS = "abs";

	/**
	 * Creates an order from an order string.
	 * @param orderString The order string.
	 * @return A new Order object.
	 * @throws WarningException An error occurred when creating the order.
	 */
	public static Order createOrder(String orderString) throws WarningException {
		Order order;
		
		switch (orderString) {
			case ABS:
				return new AbsolutePathOrder();
				
			case FILE:
				order = new FileNameOrder();
				break;
				
			case MOD:
				order = new LastModifiedOrder();
				break;
				
			case SIZE:
				order = new SizeOrder();
				break;
			
			default:
				throw new BadOrderNameException("Bad order name");
		}
		
		return new CombinedOrder(order, new AbsolutePathOrder());
	}
}
