# DVD Collection Console Application

## Overview
This project is a Java-based console application that manages a collection of DVDs. The application allows users to add, modify, remove, and view DVDs in the collection. The collection is stored in an array and maintained in alphabetical order based on DVD titles.

### Provided Files
- **DVD.java**: Models a single DVD with a title, rating, and total running time.
- **DVDCollection.java**: Manages a collection of DVDs stored in an array.
- **DVDUserInterface.java**: Interface defining required operations for any user interface to interact with the DVD collection.
- **DVDGUI.java**: Implements a graphical user interface (GUI) for the DVD collection.
- **DVDConsoleUI.java**: Implements a console user interface (CUI) for the DVD collection.
- **DVDManager.java**: Contains the `main` method that launches either the GUI or CUI based on user input.

### DVDCollection Class
- **toString**: Returns a formatted string listing all DVDs in the collection, along with the number of DVDs and the array length for debugging purposes.
- **addOrModifyDVD**: Adds a new DVD to the collection in alphabetical order or modifies the rating and running time of an existing DVD.
- **removeDVD**: Removes a DVD from the collection by its title.
- **getDVDsByRating**: Retrieves all DVDs with a specified rating.
- **getTotalRunningTime**: Calculates and returns the total running time of all DVDs in the collection.
- **loadData**: Loads DVD data from a file and populates the collection.
- **save**: Saves the current DVD collection to the specified file.

## How to Run
1. Compile all the provided `.java` files.
2. Use the `DVDManager` class to launch the console or graphical interface for interacting with the DVD collection.
3. Based on user input, choose either the **console** or **graphical** interface for managing the DVD collection.
