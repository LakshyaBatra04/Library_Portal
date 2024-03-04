import Includes.*;

public class RequestQueue {
    private Node<RequestData> front;
    private Node<RequestData> back;
    private int length = 0;

    
    public RequestData getFront() {
        return this.front.data;
    }
  
    public int getLength() {
        /*
         * Your code here.
         */
       
        return this.length;
    }

    public void push(int ISBN, int UserID) {
        /*
         * Your code here.
         */
        if(getLength()==0){
            back=new Node<RequestData>();
            RequestData t=new RequestData();
            t.ISBN=ISBN;
            t.UserID=UserID;

            this.back.data=t;
            
            this.front=this.back;
        }
        else{
            Node <RequestData> temp=new Node<RequestData>();
            RequestData t=new RequestData();
            t.ISBN=ISBN;
            t.UserID=UserID;
            temp.data=t;
            back.next=temp;
            temp.previous=back;
            back=temp;
        }
        length++;

        return;
    }

    public void pop() {      // processing needs to be done before popping, 
        /*
         * Your code here.
         */
        if(getLength()==0){
            return;
        }
        else if(getLength()==1){
            front=null;
            back=null;
        }
        else{
            Node<RequestData>temp=new Node<RequestData>();
            temp=front.next;
            temp.previous=null;
            front=temp;
        }
        length--;
        return;
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
    public static void main(String[] args) {
        RequestQueue q=new RequestQueue();
        q.push(1234,12);
        q.push(11, 011);
        q.push(124,1211);
        q.push(134,12111);

        System.out.println(q);

        System.out.println(q.getLength());

        q.pop();
        q.pop();
        System.out.println(q);
        System.out.println(q.getLength());


    }
}
