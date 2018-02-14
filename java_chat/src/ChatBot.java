/*
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class ChatBot{
  private String file;
  public ArrayList<String> answers = new ArrayList<String>();
  private boolean isSilent = false;
  private String botname;
  
  ChatBot(){
    this.file = "en_text.txt";
    this.botname = " Bot: ";
	answersPath();
	
  }
  
  public void answersPath(){
	 try {
	 FileReader fr = new FileReader("src/" + file);
	 BufferedReader input = new BufferedReader(fr);
	 String line = null;
	 while ((line = input.readLine())!= null) {
	         answers.add (line);
	   }
	 input.close();
	 } catch (FileNotFoundException e) {
			e.printStackTrace();
	 } catch (IOException e) {
		 	e.printStackTrace();
	}
  }

  public String randomAnswer() {
	  Random rnd = new Random();
	  int randNum = rnd.nextInt(answers.size());
	  String randomLine = answers.get(randNum);
	  return randomLine;
  }
  public String getFile(){
	  return file;
  }
  public void setFile(String f){
	  this.file = f;
	  answers.clear();
	  answersPath();
  }
  public void setIsSilent(){
	  this.isSilent = true;
  }
  public String getUp() {
	  if (this.isSilent = true){
		  this.isSilent = false;
	  }
	  return this.botname + "Show me what you got.";
  }
  public String date() {
	  LocalDateTime now = LocalDateTime.now();
	  return botname + "Current date is " + DateTimeFormatter.ofPattern("MM-dd-yyy").format(now);
  }
  public String time() {
	  LocalDateTime now = LocalDateTime.now();
	  return botname + "Current time is " + DateTimeFormatter.ofPattern("HH:mm:ss").format(now);
  }
  
  public String dialog() throws IOException {
	  if (isSilent == false) {
		  return botname + randomAnswer();
	  }
	  else
	  {
		  return botname + "zzz";
	  }
  }
  
  public static void main(String[] args) throws IOException{

    ChatBot newbot = new ChatBot();
    Scanner in = new Scanner(System.in);
    while (true) {
      System.out.print(" You: ");
      String question = in.next();
      if (question.equals("setFile"))
      {
        question = in.next();
        newbot.setFile(question);
        System.out.println(" New destination: " + newbot.getFile());
      }
      if (question.equals("quit")) {
    	  break;
      }
      if(question.equals("silent")) {
    	  newbot.setIsSilent();
      }
      if (question.equals("getUp")) {
    	  System.out.println(newbot.getUp());
    	  continue;
      }
      if (question.equals("date")) {
    	  System.out.println(newbot.date());
    	  continue;
      }
      if (question.equals("time")) {
    	  System.out.println(newbot.time());
    	  continue;
      }
      System.out.println(newbot.dialog());
    }
    in.close();
  }
}
*/

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;
import java.io.*;

public class ChatBot{
  private String file;
  private ArrayList<String> answers = new ArrayList<String>();
  private boolean isSilent = false;
  private String botName;
  private String userName;
  
  ChatBot(){
    this.file = "en_text.txt";
    this.botName = " Bot: ";
    this.userName = " You: ";
	answersPath();
  }
  
  private void setUserName(String user) {
	  this.userName = user;
  }
  
  private void answersPath(){
	 /*
	Path path = Paths.get(this.file);
	this.answers = Files.readAllLines(path);
	*/
	 try {
	 FileReader fr = new FileReader("include/" + file);
	 BufferedReader input = new BufferedReader(fr);
	 String line = null;
	 while ((line = input.readLine())!= null) {
	         answers.add (line);
	   }
	 input.close();
	 } catch (FileNotFoundException e) {
		 	System.out.println(e);
	 } catch (IOException e) {
		 	System.out.println(e);
	}
  }

  private String randomAnswer() {
	  Random rnd = new Random();
	  int randNum = rnd.nextInt(answers.size());
	  String randomLine = answers.get(randNum);
	  return randomLine;
  }
  private String getFile(){
	  return file;
  }
  private void setFile(String f){
	this.file = f;
	answers.clear();
	answersPath();  
  }
  private void setIsSilent(){
	  this.isSilent = true;
  }
  private void getUp() {
	  if (this.isSilent = true){
		  this.isSilent = false;
	  }
  }
  private String date() {
	  LocalDateTime now = LocalDateTime.now();
	  return DateTimeFormatter.ofPattern("MM-dd-yyy").format(now);
  }
  private String time() {
	  LocalDateTime now = LocalDateTime.now();
	  return DateTimeFormatter.ofPattern("HH:mm:ss").format(now);
  }

  private void setName(Scanner in) {
	  System.out.print("\n Welcome to chatbot. Enter your name: ");
	  String name = in.nextLine();
	  setUserName(name);
	  System.out.println(" Hello, " + userName + ". What do you want to know?\n");
  }
  
  private void dialogVariants() {
	  	Scanner in = new Scanner(System.in);
	    while (true) {
	      System.out.print(userName);
	      String question = in.nextLine();
		  System.out.print(botName);
	      if (question.equals("quit")) {
	    	  System.out.print(" Bye!" +
	    			  "\n Session is over.");
	    	  break;
	      }
	      if (question.equals("")) {
	    	  System.out.println("Empty.");
	    	  continue;
	      }
	      if (question.equals("setName")){
	    	  setName(in);
	      }
	      if (question.equals("change"))
	      {
	    	System.out.print(" Enter new file or filepath: ");
		    String fileName = in.nextLine();
	    	Pattern p = Pattern.compile("(.+?)(\\.txt)$");
	      	Matcher m = p.matcher(fileName);
	      	if (m.find()) {
		    	setFile(fileName);
		    	System.out.println(botName + " New answerpack: " + getFile());
	      	}
	      	else {
	      		System.out.println(botName + " Wrong, text should be like 'text.txt'.");
	      	}
	        continue;
	      }
	      if(question.equals("silent")) { 
	    	  setIsSilent();
	      }
	      if (question.equals("getUp")) {
	    	  getUp();
	    	  System.out.println("Show me what you got.");
	    	  continue;
	      }
	      if (question.equals("date")) {
	    	  System.out.println("Current date is " +date());
	    	  continue;
	      }
	      if (question.equals("time")) {
	    	  System.out.println("Current time is " + time());
	    	  continue;
	      }
	      if (isSilent == false) {
			  System.out.println(randomAnswer());
		  }
		  else
		  {
			  System.out.println("zzz");
		  }
	    }
	    in.close();  
  }
  private void chatInfo() {
	  System.out.println(" Simple java chatbot by Slavvok.\n Available commands: " +
			  "silent, getUp, date, time, change, setName, quit.\n");
  }
  public void dialog() throws IOException {
	  chatInfo();
	  dialogVariants();
  }
  
  public static void main(String[] args) throws IOException{
    ChatBot newbot = new ChatBot();
    newbot.dialog();
  }
}