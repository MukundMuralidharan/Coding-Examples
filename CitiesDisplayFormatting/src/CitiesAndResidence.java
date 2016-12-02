import java.io.*;
import java.util.*;


public class CitiesAndResidence {

	
	public static void main(String[] args ) throws Exception{
		
		String[] person_info;
		int num_input;
		
		String tmp;
		
	    BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 

		System.out.print("Enter the number of input values:");
		tmp=br.readLine();
		num_input=Integer.parseInt(tmp);
		person_info= new String[num_input];
		
		String filename="../Information.txt";
		
		CityRecords cr= new CityRecords();
		cr.fileNameHelper(filename);
		cr.writeToFile(person_info,filename);
		
	}
	
		
	public static void printResult(Map<String, List<String>>tmap){

		for(  Map.Entry<String, List<String>> entry: tmap.entrySet()){
			System.out.println(entry.getKey());
			
			List<String> tmp_list=(List<String>)entry.getValue();
			for(String s: tmp_list){
				System.out.println("\t"+s);
			}

		}

	}

}
