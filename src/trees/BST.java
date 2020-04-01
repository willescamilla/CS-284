package trees;

public class BST<E extends Comparable<E>> extends BTree<E> {

	BST() {
		super();
	}
	
	BST(E item) {
		super(item);
	}
	
	BST(E item, BST<E> l, BST<E> r) {
		super(item,l,r);
	}
	
	
	private Boolean find(E key, Node<E> current) {
		if (current==null) {
			return false;
		} else {
			int c = key.compareTo(current.data);
			if (c==0) {
				return true;
			} else {
				if (c<0) {
					return find(key,current.left);
				} else {
					return find(key,current.right);
				}
			}
		}
	}
	
	public Boolean find(E key) {
		return find(key,root);
	}
	
	private E max(E item1, E item2) {
		return item1.compareTo(item2)>0 ? item1 : item2;
	}
	
	private E min(E item1, E item2) {
		return item1.compareTo(item2)<0 ? item1 : item2;
	}
	
	/** Returns the maximum element in the tree
	 * Assumption: tree is not empty
	 * @return
	 */
	private E max(Node<E> current) {
		if (current.is_leaf()) {
			return current.data;
		} else { // node has at least one child
			if (current.left==null) {
				return max(current.data, max(current.right));
			}
			if (current.right==null) {
				return max(current.data,max(current.left));
			}
			return max(max(current.data,max(current.left)),max(current.right));
		}
			
	}
	
	
	/** Returns the minimum element in the tree
	 * Assumption: tree is not empty
	 * @return
	 */
	private E min(Node<E> current) {
		if (current.is_leaf()) {
			return current.data;
		} else { // node has at least one child
			if (current.left==null) {
				return min(current.data, min(current.right));
			}
			if (current.right==null) {
				return min(current.data,min(current.left));
			}
			return min(min(current.data,min(current.left)),min(current.right));
		}
			
	}
	private Boolean is_bst(Node<E> current) {
		if (current==null || current.is_leaf()) {
			return true;
		} else { // non-empty and at least one child
			if (current.left==null) {
				return current.data.compareTo(min(current.right))<0 &&
						is_bst(current.right);
			}
			if (current.right==null) {
				return current.data.compareTo(max(current.left))>0 &&
						is_bst(current.left);
			}
			return current.data.compareTo(min(current.right))<0 &&
					is_bst(current.right) &&
					current.data.compareTo(max(current.left))>0 &&
					is_bst(current.left);	
		}
	}
	
	
	
	public Boolean is_bst() {
		return is_bst(root);
	}
	
	private Node<E> add(E item, Node<E> current) {
		if (current==null) {
			return new Node<E>(item);
		} else {
			int n = current.data.compareTo(item);
			if (n==0) {
				throw new IllegalStateException("Item already in tree");
			}
			if (n>0) {
				current.left = add(item,current.left);
				return current;
			}
			current.right = add(item,current.right);
			return current;
		}
	}
	
 	public E add(E item) {
		root = add(item,root);
		return item;
	}
	
	public static void main(String[] args) {
		BST<Integer> t10 = new BST<>(10,new BST<> (), new BST<>());
		BST<Integer> t24 = new BST<>(24,new BST<>(), new BST<>());
		BST<Integer> t12 = new BST<>(12,t10, t24);
		BST<Integer> t3 = new BST<>(3,new BST<> (), new BST<>());
		BST<Integer> t7 = new BST<>(7,t3, t12);

//		System.out.println(t7.find(12));
//		System.out.println(t7.find(33));
		System.out.println(t7);
		
		t7.add(9);

		System.out.println(t7);
//		System.out.println(t7.is_bst());
		
		
		
	}
}
