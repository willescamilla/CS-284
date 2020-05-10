package SpecialProject2;

import java.util.ArrayList;

/**
 * BSFamilyTree creates a tree for specific families. 
 */
public class BSFamilyTree {
    private FamilyTreeNode root;

    /**
     * Constructor: constructs an empty BSFamilyTree
     */
    public BSFamilyTree() {
        root = null;
    }

    /**
     * takes in the last name and returns true if there
     * is a FamilyTreeNode with the given last name.
     */
    public boolean doesFamilyExist(String lastName) {
    	if(lastName == null) {
    		throw new IllegalArgumentException("Invalid Parameter.");
    	}
    	
    	FamilyTreeNode temp = root;
        return check(temp, lastName);
    }
    
    
    public boolean check(FamilyTreeNode node, String lastName) {
    	if(node == null) {
    		return false;
    	}
    	else if(node.getLastName().compareTo(lastName) < 0) {
        	return check(node.right, lastName);
        }
        else if(node.getLastName().compareTo(lastName) > 0) {
        	return check(node.left, lastName);
        }
    	
    	//If we get here then we found the right lastname
    	return true;
    }
    

    /**
     * Takes in a last name and creates a new instance of
     * FamilyTreeNode and adds it to the BSFamilyTree.
     */
    public void addFamilyTreeNode(String lastName) {
    	if(lastName == null) {
    		throw new IllegalArgumentException("Invalid Parameter.");
    	}
    	
        root = findSpot(root, lastName);
    }
    
    public FamilyTreeNode findSpot(FamilyTreeNode node, String lastName) {
    	if(node == null) {
    		return new FamilyTreeNode(lastName);
    	}
    	else if(node.getLastName().compareTo(lastName) < 0) {
        	node.right = findSpot(node.right, lastName);
        	return node;
        }
        else if(node.getLastName().compareTo(lastName) > 0) {
        	node.left = findSpot(node.left, lastName);
        	return node;
        }
    	
    	//If we get here then we found the right lastname
    	throw new IllegalArgumentException("Node already in tree");
    }

    /**
     * Takes a last name and then finds that specific
     * family tree and then returns that FamilyTreeNode
     * If last name is not in tree, throws an exception.
     */
    public FamilyTreeNode getFamilyTreeNode(String lastName) {
    	if(lastName == null) {
    		throw new IllegalArgumentException("Invalid Parameter.");
    	}
    	
    	FamilyTreeNode temp = root;
    	return findNode(temp, lastName);
    }
    
    public FamilyTreeNode findNode(FamilyTreeNode node, String lastName) {
    	if(node == null) {
    		throw new IllegalArgumentException("Node not in tree");
    	}
    	else if(node.getLastName().compareTo(lastName) < 0) {
        	return findNode(node.right, lastName);
        }
        else if(node.getLastName().compareTo(lastName) > 0) {
        	return findNode(node.left, lastName);
        }
    	
    	//If we get here then we found the right lastname
    	return node;
    }

    /**
     * Returns true if the input phone number exists in the BSFamilyTree
     * false otherwise.
     */
    public boolean doesNumberExist(String phoneNumber) {
    	if(root == null) {
    		return false;
    	}
    	
    	FamilyTreeNode temp = root;
    	return findNumber(temp, phoneNumber);
    }
    
    public boolean findNumber(FamilyTreeNode node, String phoneNumber) {
    	if(node.doesNumberExist(phoneNumber)) {
    		return true;
    	}
    	else {
    		if(node.right != null) {
    			findNumber(node.right, phoneNumber);
    		}
    		if(node.left != null) {
    			findNumber(node.left, phoneNumber);
    		}
    	}
    	
    	return false;
    }
    
    public boolean doesFamilyMemberExist(String lastName, String firstName) {
    	if(root == null) {
    		return false;
    	}
    	
    	FamilyTreeNode temp = root;
    	return findMember(temp, lastName, firstName);
    }
    
    public boolean findMember(FamilyTreeNode node, String lastName, String firstName) {
    	if(node.doesFamilyMemberExist(lastName, firstName)) {
    		return true;
    	}
    	else {
    		if(node.right != null) {
    			findMember(node.right, lastName, firstName);
    		}
    		if(node.left != null) {
    			findMember(node.left, lastName, firstName);
    		}
    	}
    	
    	return false;
    }

    /**
     * Returns the string representation of the BSFamilyTree
     */
    public String toString() {
    	return toString(root,0).toString();
    }
    
    private StringBuilder toString(FamilyTreeNode node, int i) {
		StringBuilder r = new StringBuilder() ;
		for (int j=0; j<i; j++) {
			r.append(" ");
		}
		
		if (node==null) {
			r.append("null\n");
		} else {
			r.append(node.toString()+"\n");
			r.append(toString(node.left,i+1));
			r.append(toString(node.right,i+1));
		}
		return r;
	}
    
    public static void main(String[] args) {
//    	BSFamilyTree test1 = new BSFamilyTree();
//    	test1.addFamilyTreeNode("Escamilla");
//    	test1.addFamilyTreeNode("Martinez");
//    	test1.addFamilyTreeNode("Duchatelier");
//    	
//    	test1.getFamilyTreeNode("Escamilla").addFamilyMember("Escamilla", "William","6313343068");
//    	test1.getFamilyTreeNode("Escamilla").addFamilyMember("Escamilla", "Bob","6313343068");
//    	test1.getFamilyTreeNode("Escamilla").addFamilyMember("Escamilla", "Jeff","6313343068");
//    	
//    	test1.getFamilyTreeNode("Martinez").addFamilyMember("Martinez", "Sara","6313343068");
//    	test1.getFamilyTreeNode("Martinez").addFamilyMember("Martinez", "John","6313343068");
//    	test1.getFamilyTreeNode("Martinez").addFamilyMember("Martinez", "Andrew","6313343068");
//    	
//    	test1.getFamilyTreeNode("Duchatelier").addFamilyMember("Duchatelier", "Tyrone","6313343068");
//    	test1.getFamilyTreeNode("Duchatelier").addFamilyMember("Duchatelier", "Joseph","6313343068");
//    	test1.getFamilyTreeNode("Duchatelier").addFamilyMember("Duchatelier", "Anthony","6313343068");
//    	
//    	System.out.println(test1.doesNumberExist("6313343068"));
//    	
//    	System.out.println(test1);
    	
    }
}
