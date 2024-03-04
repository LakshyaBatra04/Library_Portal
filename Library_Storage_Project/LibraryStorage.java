import java.util.*;

import Includes.*;

public class LibraryStorage {
    // HashMap
    // process return
    private HashMap<Integer, BookData> storage;
    private RequestQueue rqQueue;
    private PendingRequests prLinkedList;

    public LibraryStorage() {
        storage = new HashMap<Integer, BookData>();
        for (int i=100001; i<100011; i++) {
            BookData book = new BookData();
            MyDate dateor = new MyDate();
            dateor.month = 0;
            dateor.year = 0;
            book.BorrowedStatus = false;
            book.BorrowedByUserID = 0;
            book.ISBN = i;
            book.Publisher = "publisher";
            book.Author = "author";
            book.DateOfReturn = dateor;
            storage.put(i, book);
        }

        rqQueue = new RequestQueue();
        prLinkedList = new PendingRequests();
    }
    public BookData giveBookData(int ISBN){
        if(storage.get(ISBN)!=null){
            return storage.get(ISBN);
        }
        else{
            return null;
        }
    }
    public void push(int ISBN, int UserID) {
        rqQueue.push(ISBN, UserID);
        return;
    }

    public boolean processQueue() {
        /*
         * Your code here.
         */
        RequestData temp=rqQueue.getFront();
        if(temp==null){
            return true;
        }
        int ISBN=temp.ISBN;

        BookData b=storage.get(ISBN);
        rqQueue.pop();

        if(b!=null){
            if(b.BorrowedStatus==false){
                b.BorrowedStatus=true;
                b.BorrowedByUserID=temp.UserID;
                MyDate Date=new MyDate();
                if(temp.RequestDate!=null){
                    Date.month=temp.RequestDate.month+01;
                    Date.year=temp.RequestDate.year;
                }
                
                if(Date.month==13){
                    Date.month=01;
                    Date.year=Date.year+1;
                }
                b.DateOfReturn=Date;
                return true;
            }
            else{
                //Book already borrowed , so put the request in pending requests
                Node<RequestData>n=new Node<>();
                RequestData r1=new RequestData();
                
                r1.ISBN=b.ISBN;
                r1.UserID=temp.UserID;
                n.data=r1;
                

                prLinkedList.insert(n);
                return false;
            }
        }
        return false; //Book not in storage
    }


    public boolean processReturn(BookData book) {          // I have assumed this takes BookData object as argument, can also work with ISBN perhaps
        /*
         * Your code here.
         */
        int ISBN=book.ISBN;
        Node<RequestData>temp=prLinkedList.find(ISBN);

        if(temp!=null){
            RequestData rq=temp.data;


            book.BorrowedByUserID=rq.UserID;
            book.BorrowedStatus=true;
            MyDate Date=new MyDate();
            Date.month=book.DateOfReturn.month+01;
            Date.year=book.DateOfReturn.year;
            if(book.DateOfReturn.month==13){
                Date.month=01;
                Date.year=book.DateOfReturn.year+1;
            }
            book.DateOfReturn=Date;   
            prLinkedList.delete(temp);
            return true;
        }
        
        else{
            book.BorrowedStatus=false;
            return false;
        }
    }

    public String rqQueueToString(){
        return rqQueue.toString();
    }

    public String prLinkedListToString(){
        return prLinkedList.toString();
    }
    
}
