package food_ordering_system;

import java.util.Scanner;

public class Cashier {

    // Attributes
    private Menu menu = null;

    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }

    /**
     * Prints the menu.
     */
    private void printCommands() {
	System.out.println("\nMenu:");
	System.out.println(menu.toString());
	System.out.println("Press 0 when done.");
	System.out.println("Press any other key to see the menu again.\n");
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
	System.out.println("Welcome to WLU Foodorama!\n");

	Order order = new Order(); 
	
	this.printCommands();
	
	Scanner input = new Scanner(System.in); 
	
	int command = 0;
	while (true) 
	{
	    System.out.print("Command: ");
	    if(input.hasNextInt()) 
	    {
		command = input.nextInt();
	    }
	    else
	    {
		System.out.println("Not a valid number");
		this.printCommands();
		input.next(); 
		continue;
	    }
	    
	    if(command == 0) 
	    {
		break; 
	    }
	    else if(command > 0 && command < menu.size()+1) 
	    {
		//Get amt of item 
		int amt = 0; 
		System.out.print("How many do you want? ");
		if(input.hasNextInt()) 
		{
		    amt = input.nextInt();		
		}
		else
		{
		    System.out.println("Not a valid number");
		    input.next(); //End current input call
		    continue;
		}
		order.add(menu.getItem(command), amt);
	    }
	    else  //Command is out of range 0-menu.size
	    {
		// Print menu options again
		this.printCommands();
	    }
	    
	}
	
	System.out.println("----------------------------------------");
	System.out.println(order.toString());
	
	input.close(); 
	
	return null;
    }
}