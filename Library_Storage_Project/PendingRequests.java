import Includes.*;

public class PendingRequests {
    private int length = 0;
    private Node<RequestData> front;
    private Node<RequestData> back;

    public boolean insert(Node<RequestData> insnode) {
        /*
         * Your code here.
         */
        if(front==null&&back==null){
            front=insnode;
            back=front;
        }
        else{
            back.next=insnode;
            insnode.previous=back;
            back=insnode;
        }
        length++;
        return true;
    }

    public boolean delete(Node<RequestData> delnode) {
        /*
         * Your code here.
         */
        if(front==null&&back==null){
            return false;
        }
        if (delnode.data.ISBN == front.data.ISBN) {
            if(front.next!=null){
                front.next.previous = null;
                front = front.next;
            }
            else{
                front=null;

            }
            length--;
            return true;
        
            
        }
        if (delnode.data.ISBN == back.data.ISBN) {
            back.previous.next = null;
            back = back.previous;
            length--;
            return true;
        }
        
        else{
            Node<RequestData>curr=front.next;
            Node<RequestData>prev=front;
            while(curr!=null){
                if(curr.data.ISBN==delnode.data.ISBN){
                    prev.next=curr.next;
                    curr.next.previous=prev;
                    curr.next=null;
                    curr.previous=null;
                    break;
                }
                else{
                    prev=curr;
                    curr=curr.next;
                }
            }
        }
        length--;

        return true;
    }

    public Node<RequestData> find(int ISBN) {
        /*
         * Your code here.
         */
        if(front==null){
            return null;
        }
        Node<RequestData> nrd = front;
        while(nrd.data.ISBN!=ISBN){
            nrd=nrd.next;
            if(nrd==null){
                break;
            }
        }
        
        return nrd;
    }

    public String toString(){
        Node<RequestData> temp = front;
        String s = "Length: " + length + "\n";
        while(temp != null){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
    //to check functioning
    public static void main(String[] args) {
        PendingRequests p=new PendingRequests();
        Node<RequestData>n=new Node<>();
        RequestData r1=new RequestData();
        
        r1.ISBN=123;
        r1.UserID=1234;
        n.data=r1;
        p.insert(n);
        Node<RequestData>n2=new Node<>();
        RequestData r2=new RequestData();
        
        r2.ISBN=13;
        r2.UserID=134;
        Node<RequestData>n3=new Node<>();
        n2.data=r2;

        p.insert(n2);
        RequestData r3=new RequestData();
        
        r3.ISBN=11113;
        r3.UserID=11134;
        n3.data=r3;
        p.insert(n3);
        System.out.println(p);
        p.delete(n2);
        p.delete(n);
        p.delete(n3);

        System.out.println("\n");

        System.out.println(p);
        System.out.println("\n");

        System.out.println(p.find(11113));
    }
}
