import java.io.*;
import java.util.*;




public class CityRecords {

	
	public void writeToFile(String[]person_info,String filename)  {	
		try{
			BufferedReader buff= new BufferedReader(new InputStreamReader(System.in)); 
			File personalInfo= new File(filename);
			FileWriter fw= new FileWriter(personalInfo);
			BufferedWriter bw= new BufferedWriter(fw);

			System.out.print("Enter the personal information with the City and First and Last name:");


			for(int i=0;i<person_info.length;i++){

				person_info[i]=buff.readLine();
				bw.write(person_info[i]);

				bw.newLine();
				bw.flush();

			}
			bw.close();
			
			residentCities(filename);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
public void residentCities(String filename) {
		
		String name;
		//Compare if the Cities exist in the file, if so arrange them in alphabetical order
		try{
		File f= new File(filename);
		BufferedReader fbuff=new BufferedReader(new FileReader(f));
		String str;
		HashMap<String,List<String>> hList=new HashMap<String,List<String>>();

		while((str=fbuff.readLine())!=null){
			
			String[] sub_string= str.split(", ");
			String l_name=sub_string[0];
			String f_name=sub_string[1];
			String city=sub_string[2];
			String state=sub_string[3];
			city=city + ", "+state;
			name= f_name+" "+l_name;
			
			if(!hList.containsKey(city)){
				List<String>arrList= new ArrayList<String>();

				arrList.add(name);
				Collections.sort(arrList);

				hList.put(city,arrList);

			}
			else{
				List<String> tmp_city=hList.get(city);
				boolean exists = false;
				for(String s: tmp_city){
					if(s.equals(name)){
						exists=true;
					}
				}
				if(!exists){
					tmp_city.add(name);
					hList.put(city,tmp_city);
				}
			}

		}
		
		fbuff.close();
		Map<String, List<String>>tmap=new TreeMap<String,List<String>>(hList);
		CitiesAndResidence.printResult(tmap);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

public String fileNameHelper(String filename){
	return filename;
}



}
