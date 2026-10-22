/*Name: Ethan Beach
Assignment: Linked Lists (Hurricane Data)
Date: 10/19/2026-10/25/2026
Purpose: To read Hurricane data from a CSV file and store in a sorted linked list. 
Sources:https://www.w3schools.com/java/java_linkedlist.asp
https://www.geeksforgeeks.org/java/linked-list-in-java/
https://stackoverflow.com/questions/31544501/creating-a-node-class-in-java
Note: https://chatgpt.com/share/6ad95363-60b8-83e8-be7d-d044a9ecdacc Thought I would use ChatGPT to create a change log between the old and new main,
and had it export as a .txt file which I will upload to the github repo. 
*/
//Imported utilities for file reading and to handle file exceptions 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static final String INPUT_FILE = "ace.csv";

    public static void main(String[] args) {

        // The sorted list replaces the old ArrayList.
        DoublyLinkedSortedList data = readData(INPUT_FILE);

        if (data.getFirst() == null) {
            System.out.println("No data was read from " + INPUT_FILE 
                    + ". Check that the file is in the project folder.");
            return;
        }

        // The list is sorted by ACE (largest first), so the first node holds the max.
        Node link = data.getFirst();
        HurricaneRowData dat = link.getValue();
        int max_year = dat.getYear();
        System.out.println("Year of max ace: " + max_year);
        System.out.println("All data in order of Ace:");
        System.out.println(data);

        // Required tests
        System.out.println();
        testInsertAndSize();
        testSortedOrderAndLinks();
        testContainsAndRemove();
    }

    
    //Test 1: insert 3 items and check the length is 3
    
    private static void testInsertAndSize() {
        DoublyLinkedSortedList list = new DoublyLinkedSortedList();
        list.insert(new HurricaneRowData(2000, 100, 10, 5, 2));
        list.insert(new HurricaneRowData(2001, 200, 11, 6, 3));
        list.insert(new HurricaneRowData(2002, 150, 12, 7, 4));

        if (list.size() == 3) {
            System.out.println("Test 1 (insert 3 items -> size 3): PASSED");
        } else {
            System.out.println("Test 1 (insert 3 items -> size 3): FAILED, size was "
                    + list.size());
        }
    }

    
    //Test 2: Values inserted out of order end up sorted (largest ACE first,
    // smallest last) and the previous/next links agree in both directions
    
    private static void testSortedOrderAndLinks() {
        DoublyLinkedSortedList list = new DoublyLinkedSortedList();
        list.insert(new HurricaneRowData(2000, 100, 10, 5, 2));
        list.insert(new HurricaneRowData(2001, 200, 11, 6, 3));   // should become first
        list.insert(new HurricaneRowData(2002, 150, 12, 7, 4));   // goes in the middle
        list.insert(new HurricaneRowData(2003, 50, 13, 8, 5));    // should become last

        boolean ok = list.getFirst().getValue().getAce() == 200
                  && list.getLast().getValue().getAce() == 50
                  && list.getFirst().getNext().getValue().getAce() == 150
                  && list.getLast().getPrevious().getValue().getAce() == 100
                  && !list.getFirst().hasPrevious()
                  && !list.getLast().hasNext();

        System.out.println("Test 2 (sorted order and links): " + (ok ? "PASSED" : "FAILED"));
    }

    
    //Test 3 (extra): contains, getByValue, and remove
    
    private static void testContainsAndRemove() {
        DoublyLinkedSortedList list = new DoublyLinkedSortedList();
        HurricaneRowData a = new HurricaneRowData(2000, 100, 10, 5, 2);
        HurricaneRowData b = new HurricaneRowData(2001, 200, 11, 6, 3);
        list.insert(a);
        list.insert(b);

        boolean ok = list.contains(a)
                  && list.getByValue(b) == list.getFirst()
                  && list.remove(b) != null
                  && !list.contains(b)
                  && list.size() == 1
                  && list.getFirst() == list.getLast();

        System.out.println("Test 3 (contains/getByValue/remove): " + (ok ? "PASSED" : "FAILED"));
    }

    
    //Reads the CSV file and inserts each row into a DoublyLinkedSortedList
    
    private static DoublyLinkedSortedList readData(String fileName) {

        //Creates an empty sorted list; every insert() puts the row in order
        DoublyLinkedSortedList rows = new DoublyLinkedSortedList();

        //Creates a try and catch for file reading exceptions
        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            //hasNextLine() returns true as long as there is another line left
            
            while (fileScanner.hasNextLine()) {

                //nextLine() grabs the next line and advances the scanner
                
                String line = fileScanner.nextLine().trim();

                
                if (line.isEmpty()) {
                    continue;
                }

                //split(",") chops the line at every comma and hands back an array of the pieces
                
                String[] parts = line.split(",");

                //If a line is malformed and doesn't have all five columns,
                //skip it rather than crash with an index-out-of-bounds error
                if (parts.length < 5) {
                    continue;
                }

                //Converts text to numbers. Can fail so gets a try which skips instead of out of bounds erroring
                try {
                    //Integer.parseInt turns "1950" into the int 1950
                    //Double.parseDouble turns "243" into the double 243.0
                    //clean() (defined below) strips out junk characters first
                    int year = Integer.parseInt(clean(parts[0]));
                    double ace = Double.parseDouble(clean(parts[1]));
                    int tropicalStorms = Integer.parseInt(clean(parts[2]));
                    int hurricanes = Integer.parseInt(clean(parts[3]));
                    int majorHurricanes = Integer.parseInt(clean(parts[4]));

                    //Build one object from this row and append it to the list
                    //`new` calls the constructor over in HurricaneRowData
                    rows.insert(new HurricaneRowData(year, ace, tropicalStorms,
                            hurricanes, majorHurricanes));

                } catch (NumberFormatException e) {
                    //Skips any line that has a number that can't be parsed, rather than crashing the program
                    continue;
                }
            }

            //Closes the scanner
            fileScanner.close();

        } catch (FileNotFoundException e) {
            //Prints error instead of crashing if the file isn't found
            System.out.println("Could not find the file: " + fileName);
        }

        //Hands the finished list back to main
        return rows;
    }

    //Cleans up the string
    private static String clean(String field) {
        return field.replace("\"", "")   // remove any double-quote characters
                    .replace(" ", "")    // remove spaces
                    .replace("*", "")    // remove footnote asterisks
                    .trim();             // remove leftover whitespace and \r
    }
}
