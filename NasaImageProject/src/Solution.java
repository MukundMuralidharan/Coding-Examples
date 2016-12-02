import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.net.*;

import org.json.simple.*;

import java.text.*;


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

@SuppressWarnings("unused")
class Solution {
  public static void main(String[] args) throws Exception {
    
    String token="9Jz6tLIeJ0yY9vjbEUWaH9fsXA930J9hspPchute";
    double lat; double lon;
    
    Solution solution= new Solution();
    
    // Sending Latitude and longitude to the function as it does not read from console.
    lat= 1.5;
    lon= 100.75;
    
    String result=solution.flyby(lat, lon, token);
    System.out.println("Next fly by date:"+result);
    
  }
  
  public String flyby(double lat , double lon, String token) throws Exception{
    
    // URL links  
    
         String urlVal= "https://api.nasa.gov/planetary/earth/assets?lon="+ URLEncoder.encode(String.valueOf(lon), "UTF-8")+"&lat="+ URLEncoder.encode(String.valueOf(lat), "UTF-8")+"&api_key="+ URLEncoder.encode(token, "UTF-8");
    
    // This take care of connecting to url to get the response Json value.
           
          URL url= new URL(urlVal);
          HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
         httpURLConnection.setRequestMethod("GET"); 
         httpURLConnection.connect();  
       
    BufferedReader in= new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
    
    //StringBuffer response= new StringBuffer();
    String dateVal;
    String tempDate;
    List<Date> dates= new ArrayList<Date>(); 
    
    double totalTime=0.0;
    String getDate;
    double stdtime;
    Double avgTime; 
    Double totDelta=0.0;
    Double avgTimeDelta=0.0;
    Double stdTot=0.0;
    Double stdTimeDelta=0.0;
    Double finalDate=0.0;
    
    Date latestDate = new Date();
    
    // this has the date format
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    // This take care of reading the Json response and parse the result and then further 
    //parse the date .
    
    
    while((dateVal=in.readLine())!=null){
      Object obj=JSONValue.parse(dateVal);
      JSONObject jobj=(JSONObject)obj;
      JSONArray results= (JSONArray)jobj.get("results"); 
      for (int i=0; i< results.size(); i++){
        dateVal=(((JSONObject)results.get(i)).get("date")).toString();
        Date date = formatter.parse(dateVal);
        dates.add(date);
      }
      
      //This gets the latest Date 
      
      latestDate = dates.get(0); 
      for(int i=0;i<dates.size();i++){
        if(dates.get(i).after(latestDate)){
          latestDate=dates.get(i);
        }     
      }
      
      //this get the avg time delta and standard deviation delta 
      
      for(int i=0;i<dates.size()-1;i++){
         double dateDifference = Math.abs(((dates.get(i).getTime())/1000L)-(dates.get(i+1).getTime()/1000L));
        
          totDelta += dateDifference;
          stdTot += dateDifference * dateDifference;
      }
      
        avgTimeDelta = totDelta/dates.size();
        stdTimeDelta = Math.sqrt(stdTot/dates.size());
    }

    finalDate = (latestDate.getTime()/1000L) + (avgTimeDelta + stdTimeDelta);
    

    return new Date(Math.round(finalDate*1000)).toString();
  }
 
}


/* 
Your previous Plain Text content is preserved below:
jwk@delphix.com
===== Intro =====

Here at Delphix, we admire NASA’s engineering mission. But
beyond that, we can use data from NASA to make predictions
about the future. Solving global warming is unfortunately
outside the scope of an interview question, so your goal
is somewhat simpler: use NASA’s public HTTP APIs to create
a function which predicts the next time a satellite image
will be taken of a certain location. This can be handy if
you're trying to get your picture onto online mapping
applications like Google Maps. :-)

You should implement this in whatever language you're most
comfortable with -- just make sure your code is production
quality, well designed, and easy to read.

Also, please help us by keeping this question and your
answer secret so that every candidate has a fair chance in
future Delphix interviews.


===== Steps =====

1.  Choose the language you want to code in from the menu
    labeled "Plain Text" in the top right corner of the
    screen. You will see a "Run" button appear on the top
    left -- clicking this will send your code to a Linux
    server and compile / run it. Output will appear on the
    right side of the screen.
    
    For information about what libraries are available for
    your chosen language, see:

        https://coderpad.io/languages

2.  Pull up the documentation for the API you'll be using:

      https://api.nasa.gov/api.html

3.  You'll need an API key in order to query the data from
    NASA. You can use the one that we created:

        9Jz6tLIeJ0yY9vjbEUWaH9fsXA930J9hspPchute

4.  Implement a function flyby() whose method signature
    looks like this:

      flyby(double latitude, double longitude)

    The return value is a prediction for when the next
    picture will be taken. The prediction should include
    a date / time that the picture will happen given the
    average time between successive pictures, plus a
    standard deviation for that time so we know how long
    to sit around in order to make sure our picture will
    appear in Google Maps.
    
    next_date = \
        last_date + (avg_time_delta + std_dev_time_delta)

    If you want to change the function signature to deal
    with error conditions or some other complexity not
    captured by the one above, go for it! Just add a comment
    telling us what you changed and why.

5.  Add any tests for your code to the main() method of
    your program so that we can easily run them.


====== FAQs =====

Q:  How do I know if my solution is correct?
A:  Make sure you've read the prompt carefully and you're
    convinced your program does what you think it should
    in the common case. If your program does what the prompt 
    dictates, you will get full credit. We do not use an
    auto-grader, so we do not have any values for you to
    check correctness against.
    
Q:  How should my output be formatted?
A:  Your output should include a date and time. There are
    no other strict formatting constraints (we just
    inspect the output for correctness).
    Sample output might look like:
    
    flyby(36.098592, -112.097796) -> 2016-06-10 18:02:55
    
Q:  Any suggestions of fun locations I can test with?
A:  Fun location           Latitude    Longitude
    ---------------------  ----------  ------------
    Grand Canyon           36.098592   -112.097796
    Niagra Falls           43.078154   -79.075891
    Four Corners Monument  36.998979   -109.045183
    Delphix San Francisco  37.7937007  -122.4039064

Q:  If I need a clarification, who should I ask?
A:  Send all questions to the email address that sent you
    this document, and an engineer at Delphix will get
    back to you ASAP (we're pretty quick during normal
    business hours).

Q:  How long should this question take me?
A:  Approximately 1 hour, but it could take more or less
    depending on your experience with web APIs and the
    language you choose.

Q:  When is this due?
A:  We will begin grading your answer 24 hours after it is
    sent to you, so that is the deadline.

Q:  How do I turn in my solution?
A:  Anything you've typed into this document will be saved.
    Email us when you are done with your solution. We will
    respond confirming we've received the solution within
    24 hours.

Q:  Can I use any external resources to help me?
A:  Absolutely! Feel free to use any online resources you
    like, but please don't collaborate with anyone else.

Q:  Can I use my favorite library in my program?
A:  Unfortunately, there is no way to load external
    libraries into CoderPad, so you must stick to what
    they provide out of the box for your language:

        https://coderpad.io/languages

    If you really want to use something that's not
    available, email the address that sent you this link
    and we will work with you to find a solution.

Q:  Why does my program terminate unexpectedly in
    CoderPad, and why can't I read from stdin or pass
    arguments on the command line?
A:  CoderPad places a limit on the runtime and amount of
    output your code can use, but you should be able to
    make your code fit within those limits. You can hard
    code any arguments or inputs to the program in your
    main() method or in your tests.

Q:  I'm a Vim/Emacs fan -- is there any way to use those
    keybindings? What about changing the tab width? Font
    size?
A:  Yes! Hit the button at the bottom of the screen that
    looks like a keyboard.
 */