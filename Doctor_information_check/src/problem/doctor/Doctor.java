/*
 * Doctor is the class that provides the doctor information by doing Getters and setters.
 */


package problem.doctor;

public class Doctor {
 

	private String d_Name;
    private String specialty;
    private String area;
    private int score;

    
 public Doctor(String d_Name, String specialty, String area, int score){
	  this.d_Name = d_Name;
	  this.specialty = specialty;
	  this.area = area;
	  this.score = score;
	  
  }
 public String getName(){
	 return d_Name;
 }
 
 public String getSpecialty(){
	 return specialty;
 }
 
 public String getArea(){
	 return area;
	 
 }
 
 public int getScore(){
	 return score;
 }
 
 public void setName(String d_Name){
	this.d_Name=d_Name;
 }
 public void setSpecialty(String specialty){
	 this.specialty=specialty;
 }
 public void setArea(String area){
	 this.area=area;
 }
 public void setScore(int score){
	 this.score=score;
 }

 
}
