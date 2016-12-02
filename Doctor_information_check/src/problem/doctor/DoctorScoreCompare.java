/*
 * Comparator class here compares each doctor's score to prioritize the doctors.While it returns int.
 * With the result provided the doctor's are arranged with Highest score to Lower.
 */

package problem.doctor;

import java.util.Comparator;

public class DoctorScoreCompare implements Comparator<Doctor> {

	public int compare(Doctor d1, Doctor d2){
	  
		if(d1.getScore()>d2.getScore()){
			return -1;
		}else if(d1.getScore()==d2.getScore()){
			return 0;
		}else{
			return 1;
		}
	}
}
