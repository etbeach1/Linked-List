
public interface DoublyLinkedSortedListInterface
{
	//Return a reference to the first Node in the list
	public Node getFirst();
	
	//Return a reference to the last Node in the list
	public Node getLast();
	
	//Remove the Node that has toRemove as its value
	public Node remove(HurricaneRowData toRemove);
	
	//Insert a new Node with the given newValue into the list in order.
	public void insert(HurricaneRowData newValue);
	
	//Return the entire list as a multi-line String
	public String toString();
}