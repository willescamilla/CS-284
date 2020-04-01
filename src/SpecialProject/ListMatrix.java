package SpecialProject;

public class ListMatrix extends ListCollection<Integer> {
  private int rows;
  private int columns;

  /**
   * Initializes a `ListMatrix` with the specified number of rows and columns. By
   * default, ALL elements are set to 0.
   * 
   * @param rows
   * @param columns
   */
  public ListMatrix(int rows, int columns) 
  {
	  super();
	  this.rows = rows;
	  this.columns = columns;
	  
	  if(rows <= 0 || columns <= 0) {
		  throw new IllegalArgumentException("Parameter is invalid.");
	  }
	  
	  for(int i = 0; i < rows; i++) 
	  {
		  SingleLL<Integer> bum = new SingleLL<Integer>();
		  
		  for(int j = 0; j < columns; j++) 
		  {
			  bum.push(0);
		  }
		   addList(bum);
	  }
  }

  /**
   * @return the number of rows
   */
  public int numRows() {
    return this.rows;
  }

  /**
   * 
   * @return the number of columns
   */
  public int numColumns() {
    return this.columns;
  }

  /**
   * Adds the `ListMatrix` to `ListMatrix other`, storing the result in the caller
   * (this)
   * 
   * @throws IllegalArgumentException if dimensions do not peoperly coincide
   * @param other
   * @complexity Your big-o and supporting explanation here
   * Complexity = O(n*m^2) because traversing the function seems like O(n*m) but you have to take
   * into account the complexity of the getList() function, and how many times it is called, in the inner loop
   */
  public void add(ListMatrix other) 
  {
	  if (this.rows != other.numRows() || this.columns != other.numColumns()) 
	  {
	      throw new IllegalArgumentException("Dimensions do not properly coincide");
	  }
	  
	  int num = 0;
	  for(int i = 0; i < this.rows; i++) 
	  {
		  for(int j = 0; j < this.columns; j++) 
		  {
			  num = this.getElem(i, j) + other.getElem(i, j);
			  this.setElem(i, j, num);
		  }
	  }
  }
  


  /**
   * Returns the transpose of the matrix
   * 
   * @param matrix
   * @return matrix tranpose
   */
 
  public static ListMatrix transpose(ListMatrix matrix) 
  {
	  ListMatrix result = new ListMatrix(matrix.numColumns(), matrix.numRows());
	  
	  for(int i = 0; i < matrix.numRows(); i++) 
	  {
		  for(int j = 0; j < matrix.numColumns(); j++) 
		  {
			  result.setElem(j,  i, matrix.getElem(i, j));
		  }
	  }
	  
	  return result;
  }
  
  /**
   * Multiplies the `ListMatrix` with `ListMatrix other`, returning the result as
   * a new `ListMatrix.
   * 
   * @throws IllegalArgumentException if dimensions do not properly coincide
   * @param other
   * @return
   */
  
  public ListMatrix multiply(ListMatrix other) 
  {
	  if (this.rows != other.numColumns() || this.columns != other.numRows()) 
	  {
	      throw new IllegalArgumentException("Dimensions do not properly coincide");
	  }
	  
	  // With the way multiply works, the "result" matrix's column length will be the same as "other"
	  // And its row length will be the same as "this"
	  ListMatrix result = new ListMatrix(this.numRows(), other.numColumns());
	  
	  //Transpose the 2nd matrix to make it easier to parse through and multiply
	  other = transpose(other);
	  
	  // Initialize counter variables
	  int num = 0;
	  
	  // count1 will keep track of which row in "result" and "this" we are on
	  // count2 will keep track of which column in "result" we are at
	  int count1 = 0;
	  int count2 = 0;
	  
	  // Need to loop entirely through "other" for the amount of rows in "this". 
	  // That's why we need to nest the double for-loop inside of this while loop
	  // Similarly, we don't want to change the row of "this" and "result" until we have gotten 
	  // through all values in "other"... so count1 only updates after the double for-loop has ended
	  while(count1 < this.rows) 
	  {
		  for(int i = 0; i < other.numRows(); i++) 
		  {
			  for(int j = 0; j < other.numColumns(); j++) 
			  {
				  num += (this.getElem(count1, j) * other.getElem(i, j));
			  }
			  result.setElem(count1, count2, num);
			  count2++;
			  num = 0;
		  }
		  
	  	count1++;
	  }
	  
	  return result;
  }
  
  /*
  public static void main(String[] args) 
  {
	  ListMatrix test1 = new ListMatrix(2,3);
	  ListMatrix test2 = new ListMatrix(3,2);
	  
	  // Fill test1
	  for(int i = 0; i < test1.rows; i++) {
		  for(int j = 0; j < test1.columns; j++) {
			  test1.setElem(i, j, 2);
		  }
	  }
	  
	  // Fill test2
	  for(int i = 0; i < test2.rows; i++) {
		  for(int j = 0; j < test2.columns; j++) {
			  test2.setElem(i, j, 3);
		  }
	  }
	  
	  
	  
	  System.out.println(test1);
	  System.out.println(test2);
	  System.out.println(test2.multiply(test1));
	  //System.out.println(test1);
	  
  } */
}
