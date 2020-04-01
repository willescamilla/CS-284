package trees;

import queues.Queue;

public class BTree<E> {

	protected static class Node<F>{
		// data fields
		protected F data;
		protected Node<F> left;
		protected Node<F> right;
		
		// Constructors
		public Node(F data, Node<F> left, Node<F> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		public Node(F data) {
			super();
			this.data = data;
			this.left=null;
			this.right=null;
		}
		
		public Boolean is_leaf() {
			return left==null && right==null;
		}
		
		
	}
	// Data fields
	protected Node<E> root;
	protected int size;
	
	
	// Constructors
	BTree() {
		root=null;
		size=0;
	}
	
	BTree(E item) {
		root = new Node<E>(item);
		size = 1;
	}
	
	BTree(E item, BTree<E> left, BTree<E> right) {
		root = new Node<E>(item,left.root,right.root);
		size = 1 + left.size + right.size;
	}
	
	// Methods
	
	private int height_helper(Node<E> current) {
		if (current==null) {
			return 0;
		} else {
			return 1 + Math.max(height_helper(current.left), height_helper(current.right));
		}
	}
	
	public int height() {
		return height_helper(root);
	}
	
	private Boolean is_balanced_helper(Node<E> current) {
		if (current==null) {
			return true;
		} else {
			return Math.abs(height_helper(current.left)-height_helper(current.right))<=1 &&
					is_balanced_helper(current.left) &&
					is_balanced_helper(current.right);
		}
	}
	/** 
	 * A binary tree is said to be balanced if both of its subtrees are balanced and 
	 * the height of its left subtree differs from the height of its right subtree 
	 * by at most 1.
	 * @return true if the tree is balanced, false otherwise
	 */
	public Boolean is_balanced() {
		return is_balanced_helper(root);
	}
	
	private Boolean is_full_helper(Node<E> current) {
		if (current==null || current.is_leaf()) {
			return true;
		} else {
			return current.left!=null && current.right!=null && 
					is_full_helper(current.left) &&
					is_full_helper(current.right);
		}
		
	}
	
	/**
	 * A full binary tree is a binary tree where all nodes have 
	 * either 2 children or 0 children (the leaf nodes)
	 * @return
	 */
	public Boolean is_full() {
		return is_full_helper(root);
	}

	public Boolean is_perfect() {
		return size== Math.pow(2,height())-1;
	}
	
	private Boolean all_nulls(Queue<Node<E>> q) {
		
		while (!q.is_empty() && q.peek()==null) {
			q.poll();
		}
		
		return q.is_empty();
	}
	
	/** Hint: use a queue */
	public Boolean is_complete() {
		Queue<Node<E>> q = new Queue<>();
		
		q.offer(root);
		
		while (!q.is_empty()) {
			Node<E> n = q.poll();
			if (n==null) {
				return all_nulls(q);
			} else {
				q.offer(n.left);
				q.offer(n.right);
			}
			
		}
		// Never reached...
		return true;
	}
	
	
	private Boolean is_isomorphic(Node<E> current1, Node<E> current2) {
		return (current1==null && current2==null)
				||
			   (current1!=null && current2!=null && current1.data.equals(current2.data) 
				 &&
				 (is_isomorphic(current1.left,current2.left) && is_isomorphic(current1.right,current2.right)
				 || 
				 (is_isomorphic(current1.left,current2.right) && is_isomorphic(current1.right,current2.left))));
	}
	
	public Boolean is_isomorphic(BTree<E> t2) {
		return is_isomorphic(root,t2.root);
	}
	private StringBuilder toString(Node<E> current, int i) {
		StringBuilder r = new StringBuilder() ;
		for (int j=0; j<i; j++) {
			r.append("-");
		}
		
		if (current==null) {
			r.append("null\n");
		} else {
			r.append(current.data.toString()+"\n");
			r.append(toString(current.left,i+1));
			r.append(toString(current.right,i+1));
			
		}
		return r;
		
	}
	
	public String toString() {
		return toString(root,0).toString();
	}
	
//	public static void main(String[] args) {
////		BTree<Integer> t1 = new BTree<>(1);
////		BTree<Integer> t2 = new BTree<>(2,t1,new BTree<>());
////		
////		System.out.println(t1.is_balanced());
////		System.out.println(t2.is_balanced());
////		
////		BTree<Integer> t3 = new BTree<>(3, t1, t2);
////		
////		System.out.println(t1.is_full());
////		System.out.println(t2.is_full());
////		System.out.println(t3.is_full());
//		
//		BTree<Integer> t10 = new BTree<>(10,new BTree<> (), new BTree<>());
//		BTree<Integer> t14 = new BTree<>(14,new BTree<> (), new BTree<>());
//		BTree<Integer> t24 = new BTree<>(24,new BTree<>(), new BTree<>());
//		BTree<Integer> t12 = new BTree<>(12,t10, t24);
//		BTree<Integer> t3 = new BTree<>(3,new BTree<> (), new BTree<>());
//		BTree<Integer> t7 = new BTree<>(7,t3, t12);
//		
//		
//		BTree<Integer> t24i = new BTree<>(24,new BTree<>(),t14);
//		BTree<Integer> t12i = new BTree<>(12,t10, t24i);
//		BTree<Integer> t3i = new BTree<>(9,new BTree<> (), new BTree<>());
//		BTree<Integer> t7i = new BTree<>(7,t12i,t3i);
//		
//		BTree<Integer> s4 = new BTree<>(4,new BTree<>(),new BTree<>());
//		BTree<Integer> s0 = new BTree<>(0,new BTree<>(),new BTree<>());
//		BTree<Integer> s2 = new BTree<>(2,new BTree<>(),new BTree<>());
//		BTree<Integer> s1 = new BTree<>(1,s0,new BTree<>());
//		BTree<Integer> s5 = new BTree<>(5,s4, new BTree<>());
//		BTree<Integer> s3 = new BTree<>(3,s1,s5);
//		
//		BTree<Integer> r3 = new BTree<>(3,new BTree<>(),s4);
//		
//		
//		
//		
////	    System.out.println(t7.is_isomorphic(t7));
//		
//		System.out.println(r3.is_complete());
//	}
	
 }
