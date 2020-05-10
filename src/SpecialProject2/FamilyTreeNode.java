package SpecialProject2;

import java.util.ArrayList;
import java.util.List;

public class FamilyTreeNode {
	private String lastName;
	private List<Person> members;
	public FamilyTreeNode left;
	public FamilyTreeNode right;
	
	/**
     	* Constructor: instantializes a new FamilyTreeNode
     	* given a lastName
     	*/
	public FamilyTreeNode(String lastName) {
		if(lastName == null) {
			throw new IllegalArgumentException("Parameter is invalid.");
		}
        
		this.lastName = lastName;
		members = new ArrayList<Person>();
	}

	/**
     	* Returns the last name of the FamilyTreeNode
     	*/
	public String getLastName() {
		return lastName;
	}

	/**
     	* Returns the arraylist of members in the FamilyTreeNode
     	*/
	public List<Person> getMembers() {
		if(members == null) {
			throw new NullPointerException("List of members is null");
		}
		
		return members;
	}

	/*
	 * Returns true if there is an instance of Person in the FamilyTreeNode that has
	 * the same first and last name provided Return false otherwise
	 */
	public boolean doesFamilyMemberExist(String lastName, String firstName) {
		if(lastName == null || firstName == null) {
			throw new IllegalArgumentException("At least one parameter is invalid.");
		}
        	for(Person x: members) {
        		if(x.getFirstName().equals(firstName)) {
        			if(x.getLastName().equals(lastName)) {
        				return true;
        			}
        		}
        	}
        	return false;
	}

	/**
	 * Returns true if there is an instance of Person in the FamilyTreeNode whose
	 * phone number matches the one provided Returns false otherwise
	 */
	public boolean doesNumberExist(String phoneNumber) {
		if(phoneNumber == null || phoneNumber.length() != 10) {
			throw new IllegalArgumentException("Parameter is invalid.");
		}
        	for(Person x: members) {
        		if(x.getPhoneNumber().equals(phoneNumber)) {
        			return true;
        		}
        	}
        	return false;
	}

	/*
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(String lastName, String firstName, String phoneNumber) {
		//The null and invalid parameters check happens in the other "addFamilyMember" function
		Person temp = new Person(lastName, firstName, phoneNumber);
		addFamilyMember(temp);
	}

	/**
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(Person person) {
		if(person.getLastName() == null || person.getFirstName() == null || person.getPhoneNumber() == null || !person.getLastName().equals(this.lastName)) {
			throw new IllegalArgumentException("At least one parameter is invalid.");
		}
		
		members.add(person);
	}

	/*
	 * Returns the phone number of the person in the family with the given phone
	 * number Returns "Does not exist." if not found
	 */
	public String getPhoneNumberOfFamilyMember(String lastName, String firstName) {
		if(lastName == null || firstName == null) {
			throw new IllegalArgumentException("At least one parameter is invalid.");
		}
        for(Person x: members) {
        	if(x.getFirstName().equals(firstName)) {
        		if(x.getLastName().equals(lastName)) {
        			return x.getPhoneNumber();
        		}
        	}
        }
        
        return "Does not exist.";
	}

	/*
	 * toString method Ex: [] [John Smith (5551234567), May Smith (5551234568),
	 * April Smith (5551234569), August Smith (5551234570)]
	 */
	public String toString() {
		String temp = "[";
		for(int i = 0; i < members.size(); i++) {
			temp += members.get(i).toString();
			
			if(i != members.size() -1) {
				temp += ", ";
			}
		}
		return temp + "]";
	}
	
	public static void main(String[] args) {
//		FamilyTreeNode test1 = new FamilyTreeNode("Escamilla");
//		test1.addFamilyMember("Escamilla", "William", "6313343068");
//		test1.addFamilyMember("Escamilla", "Tom", "6313343068");
//		test1.addFamilyMember("Escamilla", "Chris", "6313343068");
//		test1.addFamilyMember("Escamilla", "Bob", "6313343068");
//		
//		System.out.println(test1.getLastName());
//		
//		test1.getMembers();
//		
//		System.out.println(test1.doesFamilyMemberExist("Martinez", "William"));
//		System.out.println(test1.doesNumberExist("0000000000"));
//		System.out.println(test1.getPhoneNumberOfFamilyMember("Escamilla", "William"));
//		
//		System.out.println(test1);
	}
}

