/**
 * Node (a.k.a. link) for the doubly linked list. Holds one HurricaneRowData
 * plus references to the next and previous Nodes.
 */
public class Node
{
    private HurricaneRowData value;
    private Node next;
    private Node previous;

    public Node(HurricaneRowData value)
    {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public HurricaneRowData getValue()          { return value; }
    public void setValue(HurricaneRowData v)    { value = v; }

    public boolean hasNext()                    { return next != null; }
    public Node getNext()                       { return next; }
    public void setNext(Node next)              { this.next = next; }

    public boolean hasPrevious()                { return previous != null; }
    public Node getPrevious()                   { return previous; }
    public void setPrevious(Node previous)      { this.previous = previous; }

    //One line per node: year, ACE, tropical storms, hurricanes, major hurricanes
    @Override
    public String toString()
    {
        return String.format("%10d%11.0f%11d%11d%11d",
                value.getYear(), value.getAce(), value.getTropicalStorms(),
                value.getHurricanes(), value.getMajorHurricanes());
    }
}
