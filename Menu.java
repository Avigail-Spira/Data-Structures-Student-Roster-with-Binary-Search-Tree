/* Author:   Avigail Spira
 * Project 1 Menu Class
*/

import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;


public class Menu {
	private BST studentBST = new BST();
	private static int index = 0;
	private LinkedQueue waitlist = new LinkedQueue(); 
	private Student[] sortedArray = new Student[20];
   
   public Menu() {
   }

   
   public static void main(String[] args) {
      Menu menu = new Menu();
      menu.readFromFile(); 
      menu.displayMenu();      
   }

   
   public void displayMenu() {
      String[] menuOptions = {
    		  //"Load Roster",
    		  "Add Student",
    		  "Remove Student",
    		  "Search Student by Name",
    		  "Search Student by ID", 
    		  "Save",
    		  //"Save Changes",
    		  };
      
      for (int option=0; option<menuOptions.length; option++) {
         option = JOptionPane.showOptionDialog(
        	null, 
            "Select a Menu Option", 
            "Menu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE, 
            null,
            menuOptions,
            menuOptions[0]); 
         switch (option) {
            /*case 0:
               try {
            	  this.readFromFile();
               } 
               catch (Exception exception) {
            	   System.out.println("Error in reading from File: " + exception);
               }
               break; */
            case 0:
            	this.addStudent();
            	break;
            case 1:
            	this.removeStudent();
            	break;
            case 2:
            	this.searchByName();
            	break;
            case 3:
            	this.searchByID();
            	break;
            case 4:
			try {
				this.save();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            	break;
            /*case 6:
            	try {
            		this.saveChanges();
            	}
            	catch (Exception exception) {
            		System.out.println("Error in saving to File: " + exception);
            	}
            	break;  */
            case -1: 
            	System.exit(0);
               
         }
      }
   }

   public void readFromFile()  {
	   File file = new File("SaveRoster.txt");
       Scanner input = null;
       
       try {
    	   input = new Scanner(file);
       } catch (FileNotFoundException e) {
    	   e.printStackTrace();
       }


      String firstName = "";
      String lastName = "";
      String IDNo = "";
      String line = input.nextLine();
      while (input.hasNextLine()) {
         line = input.nextLine();
         Scanner lineInput = new Scanner(line).useDelimiter(" ");
         firstName = lineInput.next();
         lastName = lineInput.next();
         IDNo = lineInput.next();
         Student student = new Student(firstName, lastName, IDNo);
         if (index < 20) { 
        	 studentBST.Insert(student);
        	 index++;
         } else
        	 waitlist.enQ(student);
         lineInput.close();
      }
      input.close(); 
   }

	private void addStudent() {
		String firstName = JOptionPane.showInputDialog("Enter Student First Name:");
		String lastName = JOptionPane.showInputDialog("Enter Student Last Name:");
		String IDNo = JOptionPane.showInputDialog("Enter Student ID Number:");
		Student newStudent = new Student(firstName, lastName, IDNo);
		if (index < 20) {
			studentBST.Insert(newStudent);
			index++;
		} else {
			waitlist.enQ(newStudent);
			JOptionPane.showMessageDialog(null, "The student has been added to the waitlist.");
		}     
	}
	
	private void removeStudent() {
		String IDNo = JOptionPane.showInputDialog("Enter ID Number of student to delete:");
		Student studentToDelete = new Student(null, null, IDNo);
		if (! studentBST.isEmpty()) {
			studentBST.Delete(studentToDelete);
			index--;
			
			if (! waitlist.isEmpty()) {
				studentBST.Insert(waitlist.deQ());
				index++;
			}
		}
	}
	  
	
	private void searchByName() {
		String firstName = JOptionPane.showInputDialog("Search by Student First Name:");
		String lastName = JOptionPane.showInputDialog("Search by Student Last Name:");
		Student searchStudent = new Student(firstName, lastName, null);
		JOptionPane.showMessageDialog(null, studentBST.Search(searchStudent));
		}
	
	private void searchByID() {
		String id_number = JOptionPane.showInputDialog("Search by Student ID Number:");
		Student searchStudent = new Student(null, null, id_number);
		JOptionPane.showMessageDialog(null, studentBST.SearchByID(searchStudent)); 
	}
	
	
	private void save() throws FileNotFoundException {	
		//save the BST into array by doing inorder traversal
		studentBST.InOrderToArray(sortedArray);
		
		
		File file = new File("./Roster.txt");
		PrintWriter fileWriter = new PrintWriter(file);
		fileWriter.print("FirstName, LastName, IDNo");
		
		if (index != 0) {
			for (int i=0; i<index; i++)
				fileWriter.print(sortedArray[i]);
		} 
		
		
		if (! waitlist.isEmpty()) {
			File queueFile = new File("./WaitList.txt");
			PrintWriter fileWriter2 = new PrintWriter(queueFile);
			fileWriter2.print("FirstName, LastName, IDNo");
			StudentNode front = waitlist.getRear().next; //starts at the front of the queue
			while (front != waitlist.getRear()){ //while its not at the end of the queue
				fileWriter2.println(front.data);
				front =front.next;
			} 
			fileWriter2.println(" " + front.data);
			fileWriter2.println();
			} 
		
		fileWriter.close();
	    
	}


}
