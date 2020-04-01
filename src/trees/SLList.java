package trees;

public class SLList<E> {

	private static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;
		// Constructor
		
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}
		public Node(F data) {
			super();
			this.data = data;
		}
		
	}
	// data fields
	private Node<E> head;
	private int size;
	
    // Constructor
	SLList() {
		head=null;
		size=0;
	}
	
	// Methods
	
	public E addFirst(E item) {
		head = new Node<E>(item,head);
		size++;
		return item;
	}

	
	public E addLast(E item) {
		if (head==null) {
			return this.addFirst(item);
		}
		Node<E> current = head;
		while (current.next!=null) {
			current=current.next;
		}
		
		current.next = new Node<>(item);
		size++;
		return item;
	}
	

	
	private Node<E> addLastRHelper(E item, Node<E> current) {
		if (current==null) {
			return new Node<E>(item);
		} else {
			current.next = addLastRHelper(item,current.next);
			return current;
		}
	}
	
	public E addLastR(E item) {
		head = addLastRHelper(item,head);
		size++;
		return item;
	}

	
	private Node<E> addRHelper(int index,E item, Node<E> current) {
		if (index==0) {
			return new Node<E>(item,current);
		} else {
			current.next = addRHelper(index-1,item,current.next);
			return current;
		}
	}
	
	public E addR(int index,E item) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException();
		}
		head = addRHelper(index,item,head);
		size++;
		return item;
	}

	private Node<E> cloneRHelper(Node<E> current) {
		if (current==null) {
			return null;
		} else {
			return new Node<E>(current.data,cloneRHelper(current.next));
		}
	}
	
	public SLList<E> cloneR() {
		SLList<E> result = new SLList<E>();
		result.head = cloneRHelper(head);
		result.size = size;
		return result;		
	}
	

	private Node<E> takeRHelper(int n, Node<E> current) {
		if (n==0) {
			return null;
		} else {
			current.next = takeRHelper(n-1,current.next);
			return current;
		}
	}
	
	public void takeR(int n) {
		if (n<0) {
			throw new IllegalArgumentException();
		}
		if (n>=size) {
			return;
		}
		//  0 <= n < size 
		head = takeRHelper(n,head);
		size = n;
	}
	
	public void dropR(int n) {
		
	}
	
	public SLList<Pair<E,E>> zipR(SLList<E> l2) {
		return null;	
	}
	
	public E add(int index, E item) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException();
		}
		if (index==0) {
			return this.addFirst(item);
		}
		
		Node<E> current = head;
		for (int i=0; i<index-1; i++) {
			current=current.next;
		}
		
		current.next = new Node<>(item,current.next);
		size++;
		return item;
 	}
	
	public E removeFirst(E item) {
		if (head==null) {
			throw new IllegalStateException("List is empty");
		}
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
		
	}
	
	public E removeLast(E item) {
		return null;
	}
	
	public E remove(int index, E item) {
		return null;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		Node<E> current = head;
		s.append("[");
		while (current!=null) {
			s.append(current.data.toString()+",");
			current = current.next;
		}
		s.append("]");
		return s.toString();
	
	}
	
	public SLList<E> clone() {
		
		Node<E> current = head;
		Node<E> last = new Node<>(null);
		Node<E> newHead = last;
		
		while (current!=null) {
			last.next = new Node<E>(current.data);
			current = current.next;
			last = last.next;
		}
		
		SLList<E> result = new SLList<E>();
		result.head = newHead.next;
		result.size = size;
		return result;
	}
	
	public void take(int n) {
		
		if (n==0) {
			head=null;
			size=0;
			return;
		}
	    
		Node<E> current = head;
		int i=0;
		
		while (current!=null & i<n-1) {
			current = current.next;
			i++;
		}
		
		if (current!=null) {
			current.next=null;
			size = i+1;
		}
	
	}
	
	public void drop(int n) {
		
		if (n>=size) {
			head = null;
			size = 0;
			return;
		}
		Node<E> current = head;
		int i=0;
		
		while (current!=null & i<n) {
			current = current.next;
			i++;
		}
		
		if (current!=null) {
			head = current;
			size = size-i;
		}
	
	}
	
	public Pair<SLList<E>,SLList<E>> splitAt(int n) {
		
		SLList<E> lc1 = this.clone();
		SLList<E> lc2 = this.clone();
		
		lc1.take(n);
		lc2.drop(n);
		return new Pair<SLList<E>,SLList<E>>(lc1,lc2);
	}
	
	
	public boolean member(E item, Node<E> localHead) {
		Node<E> current = localHead;
		
		while (current!=null && !item.equals(current.data)) {
			current = current.next;
		}
		
		return current!=null;
	}
	
	public boolean repetitions() {
		
		Node<E> current = head;
		
		while (current!=null && !member(current.data,current.next)) {
			current=current.next;
		}
		
		return current!=null;
	}
	
	public void removeAll(E elem) {
		
		while (head!=null && head.data.equals(elem)) {
			head=head.next;
			size--;
		}
		
		if (head==null) {
			return;
		}
		
		// list is non-empty and its first element is not elem
		
		Node<E> current = head;
		
		while (current.next!=null) {
			if (current.next.data.equals(elem)) {
				current.next = current.next.next;
				size--; 
			} else {
				current = current.next;
			}
		}
	}
	
	public void reverse() {
		if (head==null || head.next==null) {
			return;
		}
		
		Node<E> current = head.next;
		Node<E> last = head;
		Node<E> old_head = head;
		
		while (current.next!=null) {
			Node<E> temp = current.next;
			current.next = last;
			// slide window
			last = current;
			current = temp;
		}
		
		current.next=last;
		old_head.next=null;
		head=current;
		
	}
	
	public void rad() {
		
		if (head==null || head.next==null) {
			return;
		}
		
		Node<E> current = head;
		while (current.next!=null) {
			if (current.data.equals(current.next.data)) {
				current.next = current.next.next;
				size--;
			} else {
				current = current.next;
			}
		}
		
		
	}
	
	public E get(int i) {
		
		Node<E> current= head;
		int n=0;
		while (current!=null && n<i) {
			current=current.next;
			n++;
		}
		
		if (current!=null) {
			return current.data;
		}
		return null;
	}
	
	public void stutter() {
		Node<E> current = head;
		
		while (current!=null) {
			current.next = new Node<E>(current.data,current.next);
			current = current.next.next;
		}
		size=size*2;
		
	}
	
	public Boolean hasCycle() {
		
		if (head==null || head.next==null) {
			return false;
		}
		
		Node<E> slow = head;
		Node<E> fast = head.next.next;
		
		while (fast!=null && fast.next!=null & slow!=fast) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow==fast;
		
	}
	
	
public Boolean hasCycle2() {
		
		
		Node<E> slow = head;
		Node<E> fast = head;
		
		while (fast!=null && fast.next!=null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow==fast) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public static void main(String[] args) {
		SLList<Integer> l = new SLList<>();
		
		l.addFirst(14);
		l.addFirst(7);
		l.addFirst(3);
		
		System.out.println(l);
		
		l.addLastR(12);
		System.out.println(l);
	
		
		l.addR(2, 17);
		System.out.println(l);
		
		SLList<Integer> l2;
		l2 = l.cloneR();
		
		System.out.println("Clone is: "+ l2);
		l2.addLastR(122);
		System.out.println("After adding 122 to l2 value of l is "+ l);
	
		System.out.println(l2);
		l2.takeR(0);
		System.out.println(l2);
//		
 ////		l.reverse();
////		System.out.println(l);
//
//		l.addLast(12);
//		l.addLast(3);
//		l.add(4, 3);
//		l.addFirst(3);
////	
////		System.out.println(l);
////		l.stutter();
//		System.out.println(l);
//
//		
//		l.removeAll(3);
//		l.rad();
//		l.reverse();
//     	System.out.println(l);
//		
//		SLList<Integer> l2 = l.clone();
//		System.out.println(l2);
//
//		l2.drop(5);
//		System.out.println(l2);
//		System.out.println(l2.size);
     	
//        SLList<Integer> cl = new SLList<>();
//        Node<Integer> n3 = new Node<>(3);
//        Node<Integer> n2 = new Node<>(2,n3);
//        Node<Integer> n1 = new Node<>(1,n2);
//        cl.head = n1;
//        n3.next = cl.head;
//
//        System.out.println(cl.get(2000));
//     	System.out.println(cl.hasCycle());
//
//        SLList<Integer> cl2 = new SLList<>();
//        Node<Integer> n4 = new Node<>(3);
//        cl2.head = n4;
//        n4.next = cl2.head;
//
//        
//        System.out.println(cl2.get(2000));
//     	System.out.println(cl2.hasCycle());
//

		
		
	}
}
