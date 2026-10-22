[change_log.txt](https://github.com/user-attachments/files/33793817/change_log.txt)
Change Log: MainOld.java -> Main.java

The main change is that the program moves from using an ArrayList to a custom doubly linked, sorted list. Instead of finding the maximum ACE value by searching through the data and writing a report to a text file, the updated program sorts the hurricane data as it is inserted, retrieves the maximum ACE year from the first node, and runs tests on the linked list.

DETAILED CHANGE LOG

Area: Data structure
Before (MainOld.java): Used ArrayList<HurricaneRowData>.
After (Main.java): Uses DoublyLinkedSortedList.

Area: Data insertion
Before: Added rows using rows.add().
After: Inserts rows using rows.insert() in sorted order.

Area: Sorting
Before: Data was not sorted when loaded.
After: Data is sorted by ACE, largest to smallest.

Area: Maximum ACE
Before: Used yearOfMaxAce() to search for the maximum.
After: Reads the first node, which contains the maximum ACE value.

Area: Output
Before: Printed the maximum ACE, its year, and the full row.
After: Prints the maximum ACE year and all data in sorted order.

Area: File output
Before: Wrote results to max_ace.txt.
After: No longer writes a report to a separate file.

Area: Testing
Before: No built-in tests.
After: Adds three tests for list functionality.

Area: CSV reading
Before: Parsed CSV rows into an ArrayList.
After: Parses CSV rows into the sorted linked list.

Area: Data cleaning
Before: Removed quotes, spaces, and asterisks.
After: Same cleaning behavior, with expanded comments.

NEW FUNCTIONALITY ADDED

1. Sorted insertion: Each hurricane record is inserted into its correct position based on ACE, with the largest value first.
2. List size test: Inserts three records and checks that the list contains three items.
3. Sorting and link test: Verifies the order of records and checks the previous/next links at both ends of the list.
4. Search and removal test: Tests contains(), getByValue(), and remove(), including checking the size and first/last nodes after removal.
5. Empty-list handling: Checks whether the list has a first node before attempting to retrieve the maximum ACE year.

METHODS REMOVED

- yearOfMaxAce() — no longer needed because the list is already sorted.
- rowForYear() — no longer needed to retrieve the maximum record.
- writeReport() — removed along with the PrintWriter import and OUTPUT_FILE constant.

METHODS RETAINED OR MODIFIED

- main() — now retrieves the maximum year from the first node and runs the three tests.
- readData() — now returns a DoublyLinkedSortedList and inserts each parsed record in sorted order.
- clean() — retains its original data-cleaning behavior.

SUMMARY

The updated Main.java changes the program from a straightforward CSV reader using an ArrayList into a program that demonstrates a sorted doubly linked list, including insertion, traversal, bidirectional links, searching, removal, and basic testing.

Important: Main.java now depends on the DoublyLinkedSortedList and Node classes, which must be present in the project along with HurricaneRowData for the program to compile. The change log compares the two supplied Main files only; it does not verify those supporting classes or test the program's execution.
