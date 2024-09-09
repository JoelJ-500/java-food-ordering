package food_ordering_system;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 */
public class Order implements Printable {

    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);

    // Attributes
    HashMap<MenuItem, Integer> order = null;

    public Order() 
    {
	order = new HashMap<MenuItem, Integer>();
    }

    /**
     * Increments the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(final MenuItem item, final int quantity) {

	if(this.order.containsKey(item)) //Increment quantity
	{
	    this.order.put(item, this.order.get(item) + quantity);
	}
	else 
	{
	    order.put(item, quantity);
	}
    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total price for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {

	BigDecimal subtotal = BigDecimal.ZERO;
	
	/*for(MenuItem item: this.order.keySet()) 
	{
	    BigDecimal ind_cost = item.getCost().multiply(new BigDecimal(this.order.get(item)));
	    System.out.println(ind_cost + " " +this.order.get(item));
	    subtotal = subtotal.add(ind_cost);
	}*/
	for (HashMap.Entry<MenuItem, Integer> entry : this.order.entrySet()) 
	{
		MenuItem item = entry.getKey();
		Integer value = entry.getValue();
		
		BigDecimal price = item.getCost();		
		price = price.multiply(new BigDecimal ((double)value));
		subtotal = subtotal.add(price);
	}

	return subtotal;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {

	BigDecimal subtotal = this.getSubTotal();
	BigDecimal taxes = subtotal.multiply(TAX_RATE);

	return taxes;
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {


	return this.getSubTotal().add(this.getTaxes());
    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
	    throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    final Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    // Now we perform our rendering
	    final String[] lines = this.toString().split("\n");
	    int y = 100;
	    final int inc = 12;

	    for (final String line : lines) {
		g2d.drawString(line, 100, y);
		y += inc;
	    }
	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {

	// %-12s $%5.2f  
	//hot dog      $ 1.25
	String format = "%-12s%2d@ $%5.2f = $6.2f\n";
	String order_list = ""; 
	/*
	for (MenuItem item: this.order.keySet()) 
	{
	    format += String.format(format, item.getEntry(),
		this.order.get(item), item.getCost().floatValue(),
		item.getCost().floatValue()*this.order.get(item));
	}*/
	for (Map.Entry<MenuItem, Integer> entry : this.order.entrySet()) {
		MenuItem item = entry.getKey();
		Integer value = entry.getValue();
		BigDecimal cost = item.getCost().multiply(new BigDecimal ((double) value));
		
		order_list = order_list + String.format("%-13s %d @ $%,6.2f = $%,6.2f\n", item.getEntry(), value, item.getCost(), cost);
	}
	
	order_list += "\n";
	
	order_list += String.format("%-29s$%6.2f\n", "Subtotal:"
		, this.getSubTotal().floatValue());
	order_list += String.format("%-29s$%6.2f\n", "Taxes:"
		, this.getTaxes().floatValue());
	order_list += String.format("%-29s$%6.2f\n", "Total:"
		, this.getTotal().floatValue());

	return order_list;
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item     The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(final MenuItem item, final int quantity) {

	if(quantity < 1) 
	{
	    this.order.remove(item);
	}
	else if(this.order.containsKey(item))  //update
	{
	    this.order.put(item, this.order.get(item)+ quantity);
	}
	else //insert new key, if not present
	{
	    this.order.put(item, quantity);
	}

    }
}