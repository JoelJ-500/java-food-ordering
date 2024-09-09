package food_ordering_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 */
public class Menu {

    // Attributes.
    // define a List of MenuItem objects
    // Note that this must be a *List* of some flavour
    // @See
    // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html

    // your code here
    private List<MenuItem> items = new ArrayList<MenuItem>(); 

    /**
     * Creates a new Menu from an existing List of MenuItems. MenuItems are copied
     * into the Menu List.
     *
     * @param items an existing List of MenuItems.
     */
    public Menu(List<MenuItem> items) {

	this.items = items; 
    }

    /**
     * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
     * corresponds to a MenuItem. You have to read the Scanner line by line and add
     * each MenuItem to the List of items.
     *
     * @param fileScanner A Scanner accessing MenuItem String data.
     */
    public Menu(Scanner fileScanner) {

	while(fileScanner.hasNextLine()) 
	{
	    String line = fileScanner.nextLine();
	    
	    // Extract string entry and double cost
	    String info[] = line.split(" ", 2);
	    String entry = info[1].trim();
	    double cost = Double.parseDouble(info[0].trim());
	    
	    // Create menuitem obj to append to list
	    MenuItem item = new MenuItem(entry, cost);
	    items.add(item);
	}

    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {

	return items.get(i);
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	return items.size();
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {
	String menu_items = "";
	
	int i = 0; 
	for(MenuItem item: items) 
	{
	    i++; 
	    menu_items += String.format("%2d) ", i) + item.toString() + "\n"; 
	}

	return menu_items;
    }
}