/**
 * A doubly linked list that keeps its values sorted by ACE, largest first,
 * at all times. Every insert places the new Node in its correct position.
 *
 * Tie rule: a new value goes BEFORE existing values with an equal ACE.
 */
public class DoublyLinkedSortedList implements DoublyLinkedSortedListInterface
{
    private Node first;
    private Node last;
    private int size;

    public DoublyLinkedSortedList()
    {
        first = null;
        last = null;
        size = 0;
    }

    public Node getFirst() { return first; }
    public Node getLast()  { return last; }
    public int size()      { return size; }

    //Inserts newValue in sorted (descending ACE) order
    public void insert(HurricaneRowData newValue)
    {
        Node newNode = new Node(newValue);

        //Walk forward to the first node whose ACE is <= the new ACE.
        Node current = first;
        while (current != null && current.getValue().getAce() > newValue.getAce())
        {
            current = current.getNext();
        }

        if (current == null)
        {
            //Reached the end (or list is empty): append as the new last node
            newNode.setPrevious(last);
            if (last != null)
            {
                last.setNext(newNode);
            }
            else
            {
                first = newNode;      //list was empty
            }
            last = newNode;
        }
        else
        {
            //Insert newNode just before current
            Node before = current.getPrevious();
            newNode.setNext(current);
            newNode.setPrevious(before);
            current.setPrevious(newNode);
            if (before != null)
            {
                before.setNext(newNode);
            }
            else
            {
                first = newNode;      //new node is the new head
            }
        }
        size++;
    }

    /**
     *Removes the node whose value is toRemove (same object/data)
     *Returns the removed Node, or null if it wasn't found
     */
    public Node remove(HurricaneRowData toRemove)
    {
        Node target = findNode(toRemove);
        if (target == null)
        {
            return null;
        }

        Node before = target.getPrevious();
        Node after = target.getNext();

        if (before != null) { before.setNext(after); } else { first = after; }
        if (after != null)  { after.setPrevious(before); } else { last = before; }

        target.setNext(null);
        target.setPrevious(null);
        size--;
        return target;
    }

    //True if the list contains the given value
    public boolean contains(HurricaneRowData value)
    {
        return findNode(value) != null;
    }

    //Returns the node whose value matches (null if none)
    public Node getByValue(HurricaneRowData value)
    {
        return findNode(value);
    }

    //Two rows match if they are the same object or all five fields are equal
    private Node findNode(HurricaneRowData value)
    {
        Node current = first;
        while (current != null)
        {
            HurricaneRowData v = current.getValue();
            if (v == value
                || (v.getYear() == value.getYear()
                    && v.getAce() == value.getAce()
                    && v.getTropicalStorms() == value.getTropicalStorms()
                    && v.getHurricanes() == value.getHurricanes()
                    && v.getMajorHurricanes() == value.getMajorHurricanes()))
            {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    //The entire list, one node per line
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Node current = first;
        while (current != null)
        {
            sb.append(current);
            if (current.hasNext())
            {
                sb.append("\n");
            }
            current = current.getNext();
        }
        return sb.toString();
    }
}
