package HW4;
import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private Random priorityGenerator;
	private Node<E> root;
	

	private static class Node<E>{
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E > left;
		public Node <E > right;
		
		/**
		 * Creates a new Node object with given data and priority 
		 * @param data the data stored in the node
		 * @param priority the priority of the node
		 */
		public Node(E data, int priority){
			if(data == null){
				throw new NullPointerException();
 			}
			this.data = data;
			this.priority = priority;
			right = null;
			left = null;
		}
		
		/**
		 * Right tree rotation to maintain BST properties
		 */
		public Node<E> rotateRight(){ 
			Node<E> top = this.left;
			Node<E> left  = top.right;
			top.right = this;
			this.left = left;
			return top;
			}
		
		/**
		 * Left tree rotation to maintain BST properties 
		 */
		public Node<E> rotateLeft(){
			Node<E> top = this.right;
			Node<E> right = top.left;
			top.left = this;
			this.right = right;
			return top;
			}
		
		/**
		 * Creates and returns a String representation of the Node
		 */
		public String toString(){
			return "(key=" + this.data + ", priority=" + this.priority + ")";
		}
	}
	
	
	/**
	 * Creates a new empty Treap 
	 */
	public Treap(){
		root = null;
		priorityGenerator = new Random();
	}
	
	/**
	 * Creates a new empty Treap with a null root and a random priority generator based on the given seed
	 * @param seed seed for the priority generator
	 */
	public Treap(long seed){
		root = null;
		priorityGenerator = new Random(seed);
	}
	
	/**
	 * Add a key to the trap with random priority
	 * @param key key to be added to treap
	 * @return true if successfully added
	 */
	public boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}
	
	
	public boolean add(E key, int priority){
		if(key == null){
			return false;
		}
		if(find(key)){
			return false;
		} else {
			if(root == null){
				root = new Node<E>(key, priority);
				return true;
			}else{ 
				Node<E> curr = root;
				Stack<Node<E>> nodeStack = new Stack<Node<E>>();
				while(curr != null){
					nodeStack.push(curr);
					if(curr.data.compareTo(key) < 0){
						curr = curr.right;
					} else {
						curr = curr.left;
					}
				}
				curr = new Node<E>(key, priority);
				if(nodeStack.peek().data.compareTo(key) > 0){
					nodeStack.peek().left = curr;
				}else{
					nodeStack.peek().right = curr;
				}
				
				while(!nodeStack.empty() && nodeStack.peek().priority < curr.priority){
					Node<E> parentNode = nodeStack.pop();
					if(parentNode == root){
						if(root.left == curr){
							root.rotateRight();
							root = curr;
							return true;
						} else{
							root.rotateLeft();
							root = curr;
							return true;
						}
					}
					else if(curr == parentNode.left){
						parentNode.rotateRight();
						if(nodeStack.peek().left == parentNode){
							nodeStack.peek().left = curr;
						} else{
							nodeStack.peek().right = curr;
						}
					} else{
						parentNode.rotateLeft();
						if(nodeStack.peek().left == parentNode){
							nodeStack.peek().left = curr;
						} else{
							nodeStack.peek().right = curr;
						}
					}
				}
			} return true;
		}
	}
	
	/**
	 * Removes the node containing the key
	 * @param key data in the node to be deleted
	 * @return true if node is successfully deleted
	 */
	public boolean delete(E key){
		if(key == null){
			return false;
		}
		if(!find(key)){
			return false;
		} else{
			Node<E> curr = root;
			Node<E> parent = root;
			while(curr.data.compareTo(key) != 0){
				parent = curr;
				if(curr.data.compareTo(key) < 0){
					curr = curr.right;
				} else {
					curr = curr.left;
				}
			}
			while(curr.right != null || curr.left != null){
				if(parent.right == curr){
					if(curr.left == null){
						parent.right = curr.right;
						parent = curr.right;
						curr.rotateLeft();
					} else if(curr.right == null){
						parent.right = curr.left;
						parent = curr.left;
						curr.rotateRight();
					}else if(curr.left.priority > curr.right.priority){
						parent.right = curr.left;
						parent = curr.left;
						curr.rotateRight();
					} else { 
						parent.right = curr.right;
						parent = curr.right;
						curr.rotateLeft();
					}
				} else{ 
					if(curr.left == null){
						parent.left = curr.right;
						parent = curr.right;
						curr.rotateLeft();
					} else if(curr.right == null){
						parent.left = curr.left;
						parent = curr.left;
						curr.rotateRight();
					} else if(curr.left.priority > curr.right.priority){
						parent.left = curr.left;
						parent = curr.left;
						curr.rotateRight();
					} else {
						parent.left = curr.right;
						parent = curr.right;
						curr.rotateLeft();
					}
				}
			}
			if(parent.right == null){
				parent.left = null;
			} else if(parent.left == null){
				parent.right = null;
			} else {
				if(parent.right.data == key){
					parent.right = null;
				} else{
					parent.left = null;
				}
			}
			return true;
		}
		
	}
	
	
	private boolean find(Node<E> root, E key){
		if(root == null) {
			return false;
		}
		Node<E> curr = root;
		while(curr.data != key){ 
			if(curr.data.compareTo(key) > 0){
				if(curr.left == null){
					return false;
				} else {
					curr = curr.left;
				}
			} else {
				if(curr.right == null){
					return false;
				} else {
					curr = curr.right;
				}
			}
		}
		return true;
	}
	
	/**
	 * Finds if a node containing the given key is in the Treap or not
	 * @param key data contained in node 
	 * @return the key if found, null if not
	 */
	public boolean find(E key){
		if(key == null){
			throw new IllegalArgumentException();
		} else{
			return find(root, key);
		}
	}
	

	
	/**
	 * Creates and returns a string representation of itself
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		format(root, 1, sb);
		return sb.toString();
	}
	
	
	private void format(Node<E> node, int h, StringBuilder sb) {
		for (int i = 1; i < h; i++) { 
			sb.append(" ");
		} if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			format(node.left, h + 1, sb);
			format(node.right, h + 1, sb);
		}
	}
	public static void main(String args[]){
//		Treap<Integer> t = new Treap<Integer>();
//		t.add(4,19);
//		t.add(2,31);
//		t.add(6,70);
//		t.add(3,12);
//		t.add(1,84);
//		t.add(5,83);
//		t.add(7,26);
//		System.out.println(t);
	}

}
