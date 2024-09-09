# Food Ordering System with GUI

A simple Java-based food ordering system that allows users to browse a menu, place orders, and receive an itemized receipt. This application is designed with a user-friendly graphical user interface (GUI) using Java Swing, making it ideal for small-scale restaurant simulations or educational purposes.

## Features

- **Interactive GUI**: Enables users to view menu items, specify quantities, and place orders effortlessly.
- **Generated Receipts**: Automatically generates a detailed receipt displaying items ordered, quantity, subtotal, tax, and total price.
- **File Handling**: Loads menu data from an external text file, making it easy to modify or expand the available menu.
- **Event-Driven Programming**: Processes real-time user inputs to update order details and calculate totals.

## Project Structure

- **`Cashier.java`**: Handles order-taking logic and user interaction through the console.
- **`Order.java`**: Manages the order data, including items and quantities.
- **`OrderPanel.java`**: Implements the GUI panel for item selection and display of order totals.
- **`TakeOutGUI.java`**: Executes the main graphical user interface and loads the menu from an external file.
