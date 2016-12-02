/*
 * ReadMe:
 * -------
 *  DoctorAppEntry.java is the entry point for the application to find out similar doctors from the 
 *  list.
 * Package consist of 4 classes Doctor,DoctorInfo DoctorScoreCompare & DoctorAppEntry. I have the
 * Getters and Setters for the Doctors in Doctors class. 
 * 
 * DoctorInfo class has the methods addDocList which adds all the doctors information to HashMap.
 * Method getSimilarDocList will perform the task of getting the similar doctors in prioritize order.
 * 
 * The Order is determined by the Score in the code.
 * 
 *  The input of doctors are obtained from the CSV File called input .CSV in /etc.
 *  
 *  To simplify the running of the application, Doctor's name is obtained as input by scanner that
 *  in turn is passed to the method getSimilarDocList in DoctorInfo class to get the required doctor's
 *  names. 
 *  
 *  The prioritizing is done by comparing the score of the doctors in the HashMap. To get the score of
 *  each doctor the specialty is used for checking .
 *  
 *  TO organize i have moved my Junit test to Test Folder.
 *  
 *  
 *  Problem Statement
 * -----------------

    A site contains a listing of doctors. Users can browse for doctors given a specific specialty, area, review score etc. 

    Write a class which when given a doctor, provides a list of similar doctors, in a prioritized order. 

    You define what similar means and the result ordering, but clearly document any assumptions in your code. Please, include unit tests. 

    You can assume the entire directory of doctors fits into memory, and write in whatever language you are most comfortable with.
 * 
 *  
 * 
 */

package problem.doctor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;


public class DoctorAppEntry {

	public static void main(String[] args) throws IOException {
		
		DoctorInfo di1= new DoctorInfo();
		
		String name;
		String fileName= "./etc/input.csv";
		String line="";
		
		BufferedReader buffReader= new BufferedReader(new FileReader(fileName)); 
		Scanner scan= new Scanner(System.in);
		
		while((line=buffReader.readLine())!=null){
			if(line.contains("Name")){
				continue;
			}
			String[] docInput= line.split(",");
			
			String d_name=docInput[0].toLowerCase();
			String spec=docInput[1].toLowerCase();
			String area=docInput[2].toLowerCase();
			int score=Integer.parseInt(docInput[3]);
			
			Doctor d1=new Doctor(d_name,spec,area,score );
			di1.addDocList(d_name, d1);
		}
		
		System.out.print("Enter the Doctor's Name:");
		name=scan.next();
        
        
        Collection<Doctor> result= di1.getSimilarDocList(name);
        if(result.isEmpty()){
        	System.out.println("Doctor information is not present");
        }
        for (Doctor res: result){
        	
        	System.out.println(res.getName());
        	
        }
        scan.close();
        buffReader.close();
	}

}
