/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author cawalden
 */
public class Movies implements Tables {
    
 public void dispalyMovieMenu() throws SQLException{
     Scanner scanner = new Scanner(System.in);
     String input1 = null;
     String input2 = null;
     
     System.out.println("MOVIE MENU");
        System.out.println("----------");
        System.out.println("checkout - Checkout a Movie\n" +
                "checkmovie - Determine If Movie is Checked Out\n" +
                "addmovie - Add Movie to Database\n" +
                "listmovies - List all Movies in Database\n" +
                "removemovie - Remove Movie From Database \n" +
                "quit - Quit BlockBlaster Menu \n" +
                "\n" +
                "Input You Selection Below:");
                
        input1 = scanner.nextLine();
        
        OUTER:
        while (!input1.equals("quit")) {
            switch (input1) {
                case "listmovies":
                    listall();
                    break;
                case "addmovie":
       
        System.out.println("Enter the Movie Name:");
        input1 = scanner.nextLine();
        
        System.out.println("Enter movie_id:");
        input2 = scanner.nextLine();
        
                    create(input1, Integer.parseInt(input2), false);
                    break;
                case "checkmovie":
                    determineCheckOut();
                    break;
                 case "checkout":
                    break; 
                     case "removemovie":
                    break;
                case "quit":
                    break OUTER;
            }
            break;
        }
        
     
 }
    
    //List ALL MOVIES
  public static void listall() throws SQLException{
         
           String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "zxcasdQWE!@#*";
 
      try{
          //Get Connection
          Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
          Statement mystmt = myConn.createStatement();
          ResultSet myRs;
          myRs = mystmt.executeQuery("Select * from movies");
       //PRint results
          while(myRs.next()){
              System.out.println(myRs.getString("title") + " -- " + myRs.getInt("movie_id") + " -- " + myRs.getString("rented"));
          }
      }
      catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
         
     }  
  
  //Create Movie
  public static void create(String title, Integer movie_id, Boolean rented){
      String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "zxcasdQWE!@#*";
          
     
  try{ 
       //Get Connection
          Connection myConn = DriverManager.getConnection(url, user, password);
         
//Create Result 
         ResultSet myRs; 
         
          PreparedStatement ps = null;
         String query = "INSERT INTO public.movies(\n" +
                 "	title, movie_id, rented)\n" +
                 "	VALUES (?, ?, ?)";
          
          ps = myConn.prepareStatement(query);
          ps.setString(1, title);
          ps.setInt(2, movie_id);
          ps.setBoolean(3, rented);
          
         myRs = ps.executeQuery();
         
        
 
  }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
      
      
  }
  
  
  
  // Determine Checked out
  public static void determineCheckOut() throws SQLException{
     
 Scanner scanner = new Scanner(System.in);  
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "zxcasdQWE!@#*";
 
 Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
 Statement mystmt = myConn.createStatement();
 ResultSet myRs;
     
        System.out.println("Enter a movie name to detrmine if it is checked out below.");
        System.out.println("Enter a Movie Name: ");
       String input = scanner.nextLine();
       
 
         String query = "select * from movies where title = ? ";
         PreparedStatement ps = null;
          
          ps = myConn.prepareStatement(query);
          ps.setString(1, input);
          
         myRs = ps.executeQuery();
                  
       

//PRint results; the name and email are the columns that will print the query strings results
        while(myRs.next()){
            
              System.out.println(myRs.getString("title") + " -- " + myRs.getString("rented"));
              if(myRs.getString("rented").equals("f")){
                  System.out.println("Movie is available for purchase");
              }
          
      }
      
      
      
      
  }// end of determine function
    
    
    
}
