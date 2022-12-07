import java.util.EmptyStackException;

/* Author:   Avigail Spira
 * Project 1 LinkedStudentList Class
*/

public class LinkedQueue {
	private StudentNode rear;
	//rear.next points to the front of the queue
	
	public LinkedQueue( ) {
        rear = null;
    }
	
	
	public StudentNode getRear() {
		return rear;
	}

	public void enQ(Student s){
	    if (isEmpty()){
            StudentNode temp= new StudentNode(s,null);
            rear=temp;
            temp.next=rear; 
         }
         else {
             StudentNode front=rear.next;
             rear.next= new StudentNode(s,front);
             rear=rear.next;
	    }
	}


	public Student deQ(){
	    if(isEmpty()){
	    	System.out.println("Queue is empty") ;
            return(null);
	    } 
	    Student oldFront = rear.next.data;
	    if(rear == rear.next){
	        rear = null;
	    }
	    else{
	        rear.next = rear.next.next;
	    }
	    return oldFront;
	}
	

	public boolean isEmpty() {
		return (rear == null);
	}
	
}


