// Exercise for Java101 Day 8.

package edu.ittc.training.addressbook;

import java.util.Scanner;

public class AddressBookApp {
	private static final char ADD_ENTRY      = '1';
	private static final char DELETE_ENTRY   = '2';
	private static final char VIEW_ALLENTRY  = '3';
	private static final char EDIT_ENTRY     = '4';
	private static final char SEARCH_BYNAME  = '5';
	private static final char SEARCH_BYADDR  = '6';
	private static final char SEARCH_BYREC   = '7';
	private static final char EXIT           = 'X';	
	
    private static Scanner dataReader;
    private static AddressBook book;
    

    public static void main(String[] args) {            
        book = new AddressBook(10);
        dataReader = new Scanner(System.in);
        
        boolean lContinue = true;
        while (lContinue) {
            switch (Character.toUpperCase(menu())) {
                case ADD_ENTRY    : addBookEntry(); break;                
                case DELETE_ENTRY : deleteEntry(); break;
                case VIEW_ALLENTRY: viewAllEntries(); break;
                case EDIT_ENTRY   : editEntry(); break;
                case SEARCH_BYNAME: searchEntryByName(); break; 
                case SEARCH_BYADDR: searchEntryByAddress(); break; 
                case SEARCH_BYREC : searchEntryByRecord(); break;
                case EXIT:
                    lContinue = false;
                    break;
                default:
                    System.out.println("\nInvalid Menu option");
            }            
        }
        System.out.println("\nEnd of program...");
    }

    

    public static char menu() {
        char choice;
        System.out.println("\nAddressBook Menu");
        System.out.println("1. Add Entry");        
        System.out.println("2. Delete Entry");
        System.out.println("3. View all Entries");
        System.out.println("4. Update an Entry");
        System.out.println("5. Search Entry By Name");
        System.out.println("6. Search Entry By Address");
        System.out.println("7. Search Entry By Record Number");
        System.out.println("X. Exit Program");
        System.out.print("\nSelect Menu Option: ");
        choice = dataReader.nextLine().charAt(0);
        return choice;
    }
    

    
    public static AddressBookEntry getEntryDetails(AddressBookEntry entry) {
        if( entry == null ) {
            entry = new AddressBookEntry();
        }
        System.out.print("\nName     : "); entry.setName(dataReader.nextLine());
        System.out.print("Address  : "); entry.setAddress(dataReader.nextLine());
        System.out.print("Phone No.: "); entry.setTelNo(dataReader.nextLine());
        System.out.print("Email    : "); entry.setEmailAdd(dataReader.nextLine());
        return entry;
    }
    

    public static void addBookEntry() {
        AddressBookEntry entry = getEntryDetails(null);
        if( entry != null ) {
            book.addAddressBookEntry(entry);
        }
    }


    
    public static void editEntry() {
    	System.out.println("Update record number: ");
        AddressBookEntry[] list= book.getAllEntries();
                int recnum = Integer.parseInt(dataReader.nextLine());
        
    	if (recnum > list.length){
    		System.out.println("not valid");
    	}
    	else 	
    	book.updateAddressBookEntry(recnum, getEntryDetails(null)); 
    	System.out.println("Record Number: " + recnum + " is updated.");
    	
  
    			
    			
        // TODO: edit and update a single record entry
        //System.out.println("\nUnder construction....");
    }

    
    
    public static void searchEntryByRecord() {
    	  
        	System.out.println("\n:Record Number to Search: ");
        	int num = Integer.parseInt(dataReader.nextLine());
        	AddressBookEntry[] list = book.getAllEntries();
        	
        	//if ( int i = 0; i < list.length; i++){
        	
        	if (num > list.length){
        		System.out.println("not valid");
        		//System.out.println( list.length);
        	}
        	else 	
        	displayEntry(list[num-1],num); 
        	
        
    	
        // TODO: search an entry using its record no.
        // display "record not found" if such record does not exist.
        // Display all its entry. 
        // Hint: use the method "findAddressBookEntryByRecordNo()" 
        //       from the AddressBook class
        //System.out.println("\nUnder construction....");
    }
    

    
    public static void deleteEntry() {
    	
    	System.out.println("Record Number to Delete: ");
        AddressBookEntry[] list = book.getAllEntries();
        
        int recnum = Integer.parseInt(dataReader.nextLine());
        
    	if (recnum > list.length){
    		System.out.println("not valid");
    	}
    	else 	
    	book.deleteAddressBookEntry(recnum); 
    	System.out.println("Record Number: " + recnum + " deleted");
    	
    	
        // TODO: delete an entry using its record no.
        // display "record not found" if such record does not exist.         
        // Hint: use the method "deleteAddressBookEntry()" 
        //       from the AddressBook class
    }

    
    
    // display a single record
    public static void displayEntry(AddressBookEntry entry, int recNo) {        
        System.out.println("\nRecord No. " + recNo);
        System.out.println("Name     : " + entry.getName());
        System.out.println("Address  : " + entry.getAddress());
        System.out.println("Phone No.: " + entry.getTelNo());
        System.out.println("Email    : " + entry.getEmailAdd());
    }

 
    // Search all entries containing name search criteria
    public static void searchEntryByName() {       
        System.out.print("\nName to search: "); 
        // ensure no extraneous space and search criteria all in lowercase 
        String name = dataReader.nextLine().trim().toLowerCase();   
        
        // get a reference to the Addressbook list
        AddressBookEntry[] list = book.getAllEntries();             
        for( int i = 0; i < list.length; i++ ) {   
            // compare search criteria with every entry 
            if(list[i]!= null && list[i].getName().toLowerCase().contains(name)) {
                 displayEntry(list[i],i+1); 
            }
        }
        System.out.println("No more records follow...");
    }


    
 // Search all entries containing name search criteria
    public static void searchEntryByAddress() {    
    	System.out.println("\nAddress to Search: ");
    	String address = dataReader.nextLine().trim().toLowerCase();
    	
    	AddressBookEntry[] list= book.getAllEntries();
    	for( int i = 0; i < list.length; i++ ) {   
            // compare search criteria with every entry 
            if(list[i]!=null && list[i].getAddress().toLowerCase().contains(address)) {
                 displayEntry(list[i],i+1); 
            }else
            	System.out.println("not valid");
            	break;
                 
    	
            }
    	}
        // TODO: search entry by address
    	// search criteria can be any string inside the 
    	// address string
    	//System.out.println("\nUnder construction....");
    
   

    
    public static void viewAllEntries() {
        int validRecords = 0;
        
        // get a reference to the Addressbook list
        AddressBookEntry[] list = book.getAllEntries();
        if( list.length == 0) {
            System.out.println("\nList empty...");
        }
        
        for( int i = 0; i < list.length; i++ ) {
            if( list[i] != null ) {
                displayEntry(list[i],++validRecords);                
            }    
        }
        //System.out.println("No more entries to follow...");
    }
}
