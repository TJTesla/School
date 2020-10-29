
/**
 * Write a description of class Stack here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stack<ContentType>
{
    private class Node {
        private ContentType content;
        private Node previous;
        
        public Node(ContentType pContent) {
            this.content = pContent;
            previous = null;
        }
        
        public void setPrevious(Node prev) {
            this.previous = prev;
        }
        
        public Node getPrevious() {
            return this.previous;
        }
        
        public ContentType getContent() {
            return this.content;
        }
    }
    
    private Node head;
    
    public Stack() {
        head = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void push(ContentType pContent) {
        if (pContent != null) {
            Node tempNode = new Node(pContent);
            if (this.isEmpty()) {
                head = tempNode;
            } else {
                tempNode.setPrevious(head);
                head = tempNode;
            }
        }
    }
    
    public void pop() {
        if (!this.isEmpty()) {
            head = head.getPrevious();
        }
    }
    
    public ContentType top() {
        if (!this.isEmpty()) { 
            return head.getContent();
        } else {
            return null;
        }
    }
    
}










