package SpecialProject;
import java.util.ArrayList;
import java.util.List;

public class ListCollection<E> {
  private int nodeCount;
  protected List<SingleLL<E>> collection;

  /**
   * Base constructor, initializes an empty ListCollection.
   */
  public ListCollection() {
	  this(0);
  }

  /**
   * Initializes a ListCollection with `numLists` lists.
   * 
   * @param numLists
   */
  public ListCollection(int numLists) 
  {
	  if(numLists < 0) {
		  throw new IllegalArgumentException("Parameter is invalid.");
	  }
	  collection = new ArrayList<SingleLL<E>>();
	  nodeCount = 0;
	  
	  for(int i = 0; i < numLists; i++) {
		  collection.add(new SingleLL<E>());
	  }
  }

  /**
   * @return The size of the `ListCollection`
   */
  public int size() {
	  return collection.size();
  }

  /**
   * Sets the nodeCount
   * 
   * @param nodeCount
   */
  public void setNodeCount(int nodeCount) {
	  this.nodeCount = nodeCount;
  }

  /**
   * @return the nodeCount
   */
  public int getNodeCount() {
	  return nodeCount;
  }

  /**
   * Adds the specified `SingleLL` to the end of the `ListCollection`
   * 
   * @param list
   */
  public void addList(SingleLL<E> list) 
  {
	  collection.add(list);
	  nodeCount += list.size();
  }

  /**
   * Adds the specified `List` to the `ListCollection`
   * 
   * @param list
   * @complexity Your big-o and supporting explanation here
   * Complexity = O(n) because the function runs straight through every element in "list" once so the complexity is the size of that list
   */
  public void addList(List<E> list) 
  {
	  SingleLL<E> bum = new SingleLL<E>();
	  
	  for(E node: list) {
		  bum.append(node);
	  }
	  
	  collection.add(bum);
	  nodeCount += bum.size();
  }

  /**
   * Returns the list at the specified index
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @return the list
   */
  public SingleLL<E> getList(int listIndex) 
  {
	  if (listIndex < 0 || listIndex >= collection.size()) {
	      throw new IllegalArgumentException("Index is out of bounds.");
	  }
	  
	  return collection.get(listIndex);
  }

  /**
   * Adds an element to the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   * @complexity Your big-o and supporting explanation here
   * Complexity = O(n) because the function runs once through the SingleLL in which we are inserting the element.
   * It uses two similar "get" functions, however the .get() function for ArrayLists has a runtime of O(1)
   */
  public void addElem(int listIndex, int elemIndex, E elem) 
  {
	  
	  getList(listIndex).insert(elemIndex, elem);
	  nodeCount++;
  }

  /**
   * Sets the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @param elem
   */
  public void setElem(int listIndex, int elemIndex, E elem) 
  {
	  getList(listIndex).set(elemIndex, elem);
  }

  /**
   * Returns the element at the `elemIndex` position of the list at `listIndex`
   * 
   * @throws IllegalArgumentException when index out of bounds
   * @param listIndex
   * @param elemIndex
   * @return
   */
  public E getElem(int listIndex, int elemIndex) 
  {
	  return getList(listIndex).get(elemIndex);
  }

  /**
   * Returns the `ListCollection` in string form, following STRICTLY the rule of
   * "[LIST1, LIST2, LIST3, ... ]" Ex: [[0, 1, 2], [3, 4, 5], [6, 7, 8]]
   */
  
  public String toString() 
  {
	  StringBuilder sb = new StringBuilder();
	  sb.append("[");
	    
	    for (int i = 0; i < collection.size(); i++) 
	    {
	    	if (i == collection.size() - 1) {
	    		sb.append(collection.get(i).toString() + "");
	    	}
	    	else {
	    		sb.append(collection.get(i).toString() + ", ");
	    	}
	    }

	    sb.append("]");
	    return sb.toString();
  }
  
 /* public static void main(String[] args) {
	  ListCollection<Integer> test1 = new ListCollection<Integer>();
	  
	  SingleLL<Integer> bum1 = new SingleLL<Integer>();
	  bum1.append(1);
	  bum1.append(2);
	  bum1.append(3);
	  bum1.append(5);
	  SingleLL<Integer> bum2 = new SingleLL<Integer>();
	  bum2.append(551);
	  bum2.append(6);
	  bum2.append(7);
	  
	  test1.addList(bum1);
	  test1.addList(bum2);
	  System.out.println(test1);
	  
	  test1.addElem(0, 3, 4);
	  System.out.println(test1);
  } */
}
