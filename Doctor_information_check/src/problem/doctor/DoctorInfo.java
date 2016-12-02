/*
 * ReadME:
 * ------
 * 
 *    DoctorInfo has methods such as addDoclist that accepts the name of the doctor as key and
 *    Doctor object as values to add the information to the HashMap.
 *    
 *    getSmiliarDocList gives the solution for the problem stated. Here we are calling a comparator class
 *    to compare the scores of each doctor as we want to prioritize the Doctors. 
 */


package problem.doctor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DoctorInfo {
 
	HashMap <String, Doctor> docMap = new HashMap<String, Doctor>();
	
	
	public void addDocList(String name,Doctor doc ){
		
		docMap.put(name, doc);
	}
	
	public List<Doctor> getSimilarDocList(String doctor){
		
		String speciality=null;
		List<Doctor> finalDocList= new ArrayList<Doctor>(); 
		if(docMap.containsKey(doctor)){
		
			speciality= docMap.get(doctor).getSpecialty();
		}
		Collection<Doctor> docVal= docMap.values();
		for (Doctor d:docVal){
			 if(d.getSpecialty().equals(speciality)){
				 finalDocList.add(d);
			 }
		}
		Collections.sort(finalDocList, new DoctorScoreCompare());
		return finalDocList;
		
	}
	
}
