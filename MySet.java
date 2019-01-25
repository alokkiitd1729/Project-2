

public class MySet<X>  { 
	public class Node {
		public X data;
		public Node next;
	}	
	 Node head;
	 Node headi = head;
	  MySet<X> c;
	 public boolean IsEmpty() {            // 1st method :- check whether set(linked list) is empty or not
		 if(head == null) {
			 return true;
		 }
		 return false ;
	 }
	 public  boolean IsMember(X o) {     // 2nd method :- checks whether object o is in the set(LL) or not.
		 Node travel = head;
	while(travel != null) {
			if(travel.data == o) {
				     //System.out.println("true");
                                   return true;
			                  } 
			travel = travel.next;
			}
			//System.out.println("false");
		return false;
		}
	 public void addElement(X o) {  // 3rd method :- insert a data of object type :)
		if(IsMember(o) == false) {
			 Node node1 = new Node();
			 
			 node1.data = o;
			 
			 node1.next = null;
			
			 if(head == null) {
				 head = node1;
				
			 }
			 else {
				 
				 node1.next = head;
				 head = node1;
				 
			 }
		}
		 
	 } 	 
	  public void swapconsicutive(int i) {
		 int x=0;
		 Node travel1 = head;
		 Node travel2 =head.next;
		 Node temp1 = null;
		 Node temp2 = travel2.next;
		 while(x<i) {
			 x++;
			temp1=travel1;
			travel1=travel1.next;
			travel2=travel2.next;
			temp2=temp2.next;
		 }
		 if(temp1!=null){
			 temp1.next=travel2;
		 }
		 travel2.next=travel1;
		 travel1.next=temp2;
	 }
	 public void Delete(X o) {                  // 4th method :- for deleting an object from set(LL)
		try {
			 if(IsMember(o) == true) {
				 Node travel = head;
				 Node temp1 = null;
				 Node temp2 = travel.next;
				 while(travel.data != o) {
					 temp1 = travel;
					 travel = travel.next;
					 temp2 = temp2.next;
				 }
				 if(temp1!=null){
					 temp1.next = temp2;
				 }
			 }
		}
		catch ( Exception e) {
			System.out.println("Given Object " +o+ " is not in the SET");
		}	 
      }
	 public X ithelement( int k) {
		 Node travel = head;
		 for(int i = 0; i<k; i++) {
			 travel = travel.next;
		 }
		 return travel.data;
	 }
	 public int length() {
		 Node travel = head;
			int x = 0;
			while(travel != null) {
				x++;
				travel = travel.next;
			}
			return x;
	 }
	 public MySet<X> reverse()
		{
			MySet<X> b=new MySet<X>();
			Node n=head;
			while(n!=null)
			{
				b.addElement(n.data);
				n=n.next;
			}
			return b;
		}
	 public MySet<X> Union(MySet<X> a) {
		 Node travel = head;
		 while(travel != null) {
				 a.addElement(travel.data);
			 travel = travel.next;
		 }
		 return a;
	 }
	 public  MySet<X> Intersection(MySet<X> a) {
		 Node travel = head;
		 while(travel != null) {
			 if(a.IsMember(travel.data) == true) {
				 c.addElement(travel.data);
			 travel = travel.next;
			 }
		 }
		 return c;
	 }
 }










