

/**
 * Project completion Date 28 October 2018,
 *    Author@_Alok_Kumar
 *    Indian Institute of Technology(IIT) Delhi
 *
 *    To Understand the project in enough detail please go to ReadMe file of Project 2
 *     and Visit under the section under the name of this class.
 */


public class MyLinkedList<X> {
		public X data;
		public MyLinkedList<X> next;
		
		MyLinkedList<X> head;
		 MyLinkedList<X> headi = head;
		
		 public boolean IsEmpty() {            // 1st method :- check whether set(linked list) is empty or not
			 if(head == null) {
				 return true;
			 }
			 return false ;
		 }
		 public X ithelement( int k) {
			 MyLinkedList<X> travel = head;
			 for(int i = 0; i<k; i++) {
				 travel = travel.next;
			 }
			 return travel.data;
		 }
		 public int length() { 
			 MyLinkedList<X> travel = head;
				int x = 0;
				
				while(travel!= null) {
					travel=travel.next;
					
					x++;				
				}
				return x;
		 }
		 public  boolean IsMember(X o) {     // 2nd method :- checks whether object o is in the set(LL) or not.
			 MyLinkedList<X> travel = head;
		while(travel != null) {
				if(travel.data == o) {
					     System.out.println("true");
	                                   return true;
				                  } 
				travel = travel.next;
				}
				System.out.println("false");
			return false;
			}
		 
		 public void addElement(X o) {  // 3rd method :- insert a data of object type :)
			if(IsMember(o) == false) {
				 MyLinkedList<X> k = new MyLinkedList<X>();
				 k.data = o;
				 k.next = null;
				 if(head == null) {
					 head = k;
				 }
				 else {
					 k.next = head;
					 head = k;
				 }
			}
		 } 	 
		 
		 
		 public void Delete(X o) {                  // 4th method :- for deleting an object from set(LL)
			try {
				 if(IsMember(o) == true) {
					 MyLinkedList<X> travel = head;
					 MyLinkedList<X> temp1 = null;
					 MyLinkedList<X> temp2 = travel.next;
					 while(travel.data != o) {
						 temp1 = travel;
						 travel = travel.next;
						 temp2 = temp2.next;
					 }
					 temp1.next = temp2;
				 }
			}
			catch ( Exception e) {
				System.out.println("Given Object " +o+ " is not in the SET");
			}	 
	      }
}
