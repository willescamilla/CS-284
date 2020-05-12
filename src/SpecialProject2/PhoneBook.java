package SpecialProject2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
	public Map<Character, BSFamilyTree> directory;

	/**
     	* Creates a new phone book with an empty directory.
     	*/
	public PhoneBook() {
		Character[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		directory = new HashMap<Character, BSFamilyTree>();
		for(int i = 0; i < 26; i++) 
		{
			directory.put(letters[i], new BSFamilyTree());
		}
	}

	/*
	 * Returns the instance of BSFamilyTree at the indicated letter
	 * Must accept lowercase letters as well as uppercase letters
	 */
	public BSFamilyTree getFamilyTree(char letter) {
		letter = Character.toUpperCase(letter);
		
		if((int)letter < 65 || (int)letter > 90) {
				throw new IllegalArgumentException("Invalid Character");
		}
		
		return directory.get(letter);
	}

	/*
	 * Adds a FamilyTreeNode to the PhoneBook
	 */
	public void addFamily(String lastName) {
		char target = Character.toUpperCase(lastName.charAt(0));
		getFamilyTree(target).addFamilyTreeNode(lastName);
	}

	/*
	 * Adds a Person to the PhoneBook
	 * If a FamilyTreeNode with the given last name doesn't currently exist, create the FamilyTreeNode
	 */
	public void addPerson(String lastName, String firstName, String phoneNumber) {
		char target = Character.toUpperCase(lastName.charAt(0));
		if( !(getFamilyTree(target).doesFamilyExist(lastName))) {
			getFamilyTree(target).addFamilyTreeNode(lastName);
		}
		
		for(int i = 65; i < 91; i++) {
			if(getFamilyTree((char)i).doesNumberExist(phoneNumber) || getFamilyTree((char)i).doesFamilyMemberExist(lastName, firstName)) {
				throw new IllegalArgumentException("Either phone number or family member already exists");
			}
		}
		
		getFamilyTree(target).getFamilyTreeNode(lastName).addFamilyMember(lastName, firstName, phoneNumber);
	}

	/*
	 * Finds the phone number of a person
	 * Returns 'Does not exist.' if not found.
	 */
	public String getPhoneNumber(String lastName, String firstName) {
		char target = Character.toUpperCase(lastName.charAt(0));
		return getFamilyTree(target).getFamilyTreeNode(lastName).getPhoneNumberOfFamilyMember(lastName, firstName);
	}

    	/**
     	* String representation of PhoneBook
     	*/
	public String toString() {
    	String temp = "";
    	
    	for(int i = 65; i < 91; i++) {
    		temp += (char)i + "\n" + getFamilyTree((char)i).toString();
    	}
    	
    	return temp;
    }
	
	public static void main(String[] args) {
//		PhoneBook test1 = new PhoneBook();
//		test1.addPerson("Escamilla", "William","6313343068");
//		test1.addPerson("Escamilla", "Bob","6313343068");
//		test1.addPerson("Escamilla", "Jeff","6313343068");
//		test1.addPerson("Eli", "Joe","6313343068");
//		test1.addPerson("Eli", "Schmo","6313343068");
//		test1.addPerson("Ludwig", "Jackson","6313343068");
//		test1.addPerson("Ludwig", "Samuel","6313343068");
//		test1.addPerson("Lzwrence", "Ryan","6313343068");
//		test1.addPerson("Lzwrence", "Katherine","6313343068");
//		test1.addPerson("Lzwrence", "Anthony","6313343068");
//		test1.addFamily("PoopyHead");
//		
//		System.out.println(test1);
	}
    
}

