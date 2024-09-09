package food_ordering_system;

import java.math.BigDecimal;

public class MenuItem {

    // Attributes
    private static final String itemFormat = "%-12s $%5.2f";
    private String entry = null;
    private BigDecimal cost = null;

    /**
     * Constructor. Must set cost to 2 decimal points for calculations.
     *
     * @param entry Entry of the menu item.
     * @param cost  Cost of the menu item.
     */
    public MenuItem(final String entry, final BigDecimal cost) {

	this.entry = entry;
	this.cost = cost;

    }

    /**
     * Alternate constructor. Converts a double cost to BigDecimal.
     *
     * @param entry Entry of the menu item.
     * @param cost  Cost of the menu item.
     */
    public MenuItem(final String entry, final double cost) {

	this.entry = entry; 
	this.cost = BigDecimal.valueOf(cost);
    }

    /**
     * entry getter
     *
     * @return Entry of the menu item.
     */
    public String getEntry() {
	return this.entry;
    }

    /**
     * cost getter
     *
     * @return Cost of the menu item.
     */
    public BigDecimal getCost() {
	return this.cost;
    }

    /**
     * Returns a MenuItem as a String in the format:
     *
     * <pre>
    hot dog      $ 1.25
    pizza        $10.00
     * </pre>
     */
    @Override
    public String toString() {

	// Convert Big Decimal to .2 precision float
	float costf = this.cost.floatValue();
	
	return String.format(itemFormat, this.entry, costf);
    }
}