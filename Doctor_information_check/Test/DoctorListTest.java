/*
 * ReadME:
 * ------
 * Just for the sake of showing the organization i have added the Junit test in a seperate Folder called 
 * Test.
 * 
 * Problem Statement
 * -----------------

    A site contains a listing of doctors. Users can browse for doctors given a specific specialty, area, review score etc. 

    Write a class which when given a doctor, provides a list of similar doctors, in a prioritized order. 

    You define what similar means and the result ordering, but clearly document any assumptions in your code. Please, include unit tests. 

    You can assume the entire directory of doctors fits into memory, and write in whatever language you are most comfortable with.
 * 
 */


package problem.doctor;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import problem.doctor.Doctor;


public class DoctorListTest {

	public static Doctor doct;
	public static DoctorInfo docinfo;
	
	@BeforeClass
	public static void initializeDoc() throws IOException{
		
		docinfo=new DoctorInfo();
		docinfo.docMap.clear();
		
		
		String fileName= "./etc/input.csv";
		String line="";
		
		BufferedReader buffReader= new BufferedReader(new FileReader(fileName)); 
		
		
		while((line=buffReader.readLine())!=null){
			if(line.contains("Name")){
				continue;
			}
			String[] docInput= line.split(",");
			
			String d_name=docInput[0].toLowerCase();
			String spec=docInput[1].toLowerCase();
			String area=docInput[2].toLowerCase();
			int score=Integer.parseInt(docInput[3]);
			
		    doct=new Doctor(d_name,spec,area,score );
			docinfo.addDocList(d_name, doct);
		}
		
	}
	
	//Test for correct List of Doctors how are similar displayed in Priority.
	
	@Test
	public void testDocList(){
		
		
		String [] testVal={"elvis","alan","dave","fleming"};
		String [] resArr= new String[10];
		int j=0;
		Collection<Doctor> result=docinfo.getSimilarDocList("fleming");
		
		for(Doctor d:result){
			resArr[j]=d.getName();
			j++;
		}
		try{
		   for(int i=0;i<resArr.length;i++){
			
			   assertEquals(testVal[i],resArr[i]);
		   }
		}catch(Exception e){
			fail("Error:"+e.getMessage());
			
		}
		System.out.println("Doctor List By Priority---"+"\t"+"Pass");
	}
	
	
	// Test for doctor who is not present in the list.
	
	@Test
	public void testDocListFailed(){
	
		
		boolean flag=true;
		int j=0;
		Collection<Doctor> result=docinfo.getSimilarDocList("harry");
		
		try{
		  If(flag=result.isEmpty()){
			 assertEquals(flag, true)
		}catch(Exception e){
			fail("Error:"+e.getMessage());
		}
		
		System.out.println("Doctor List By priority when given a Doctor not present in the list---"+"\t"+"Pass");
		
	}
	
	
		
}
	

