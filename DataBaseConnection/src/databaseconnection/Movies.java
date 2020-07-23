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
    
 public Integer rentalIDOffSet = 1;
 
 public String menu(){
 Scanner scanner = new Scanner(System.in);
     String input1 = "";
        
     
//Movie Menu
     System.out.println("");      
        System.out.println("----------");
     System.out.println("MOVIE MENU");
        System.out.println("----------");
        System.out.println("checkout - Checkout a Movie\n" +
                "checkmovie - Determine If Movie is Checked Out\n" +
                "addmovie - Add Movie to Database\n" +
                "listmovies - List all Movies in Database\n" +
                "removemovie - Remove Movie From Database \n" +
                "returnmovie -- Return Movie to Database \n" +
                "quit - Quit BlockBlaster Menu \n");
                
        
        
        //Validate Input
        while(!input1.equals("checkout") || !input1.equals("addmovie") || !input1.equals("listmovies") || !input1.equals("quit") || !input1.equals("removemovie") || !input1.equals("checkmovie") || !input1.equals("returnmovie")){
          
          System.out.println("Input Your Selection Below:");
          input1 =scanner.nextLine();
          
          if(input1.equals("checkout") || input1.equals("addmovie") || input1.equals("listmovies") || input1.equals("quit") || input1.equals("removemovie") || input1.equals("checkmovie")|| input1.equals("returnmovie")){break;}
          
      }
        return input1;
 }
    
 public void displayMovieMenu() throws SQLException{
     Scanner scanner = new Scanner(System.in);
     String input1 = "";
     String input2 = "";
     
     //Movie Menu
     System.out.println("");      
        System.out.println("----------");
     System.out.println("MOVIE MENU");
        System.out.println("----------");
        System.out.println("checkout - Checkout a Movie\n" +
                "checkmovie - Determine If Movie is Checked Out\n" +
                "addmovie - Add Movie to Database\n" +
                "listmovies - List all Movies in Database\n" +
                "removemovie - Remove Movie From Database \n" +
                "returnmovie -- Return Movie to Database \n" +
                "quit - Quit BlockBlaster Menu \n");
                
        
        
        //Validate Input
        while(!input1.equals("checkout") || !input1.equals("addmovie") || !input1.equals("listmovies") || !input1.equals("quit") || !input1.equals("removemovie") || !input1.equals("checkmovie") || !input1.equals("returnmovie")){
          
          System.out.println("Input Your Selection Below:");
          input1 =scanner.nextLine();
          
          if(input1.equals("checkout") || input1.equals("addmovie") || input1.equals("listmovies") || input1.equals("quit") || input1.equals("removemovie") || input1.equals("checkmovie")|| input1.equals("returnmovie")){break;}
          
      }
        
        
        //Menu Loop
        if(!input1.equals("quit")){
       
          OUTER:
        while (!input1.equals("quit")) {
            switch (input1) {
                
                case "listmovies":
                    listall();
                    break;
                    
                case "addmovie":
       
        System.out.println("Enter the Movie Name:");
        input1 = scanner.next();
        
        System.out.println("Enter movie_id(Example 1000):");
        input2 = scanner.next();
        
                    create(input1, Integer.parseInt(input2), false);
                    break;
                    
                case "checkmovie":
                    determineCheckOut();
                    break;
                 case "checkout":
                
                 
                 String available = determineCheckOut();
                  
                  if(available.equals("f")){
                      checkoutMovie();
                  }else{
                      System.out.println("Movie is Unavailable");
                  }
                     
                    break;
                                  
                 case "returnmovie":
                     
                     returnMovie();
                     
                     
                     break;
                     
                     case "removemovie":
                         removeMovie();
                    break;
                                        
                  
            }
          input1 = menu();
          
        }
      }else{
          System.out.println("Leaving Menu");
      }
        
        
        
     
 }
    
    //List ALL MOVIES
  public void listall() throws SQLException{
         
           String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "<your password here>";
 
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
  public void create(String title, Integer movie_id, Boolean rented){
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
  public String determineCheckOut() throws SQLException{
     
 Scanner scanner = new Scanner(System.in);  
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "<your password here>";
 
 String checkedOut; //Retuend for determining if user can checkmovie out
     checkedOut = "";
     
 Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
 Statement mystmt = myConn.createStatement();
 ResultSet myRs;
     
        System.out.println("Enter a movie name to detrmine if it is checked out below.");
        
        System.out.println("Enter a Movie Name: ");
       String input = scanner.nextLine();
       
 
         String query = "Select * from movies where title = ? ";
         PreparedStatement ps = null;
          
          ps = myConn.prepareStatement(query);
          ps.setString(1, input);
          
         myRs = ps.executeQuery();
                  
       while(myRs.next()){
            
         System.out.println(myRs.getString("title") + " -- " + myRs.getString("rented"));
              
        if(myRs.getString("rented").equals("f")){
            System.out.println("Movie is Available for Purchase");
            
            checkedOut = myRs.getString("rented");
        }else{
            System.out.println("Movie is currently being rented.");
        }

         }
      return checkedOut;
  }// end of determine function
  
  
    
  public void removeMovie() throws SQLException{
      
 Scanner scanner = new Scanner(System.in);  
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "<your password here>";
      
 Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
 Statement mystmt = myConn.createStatement();
 ResultSet myRs;
 
 
 System.out.println("Enter a movie name to remove a movie below.");
        System.out.println("Enter a Movie Name: ");
       String input = scanner.next();
    
       //Test Validity
         try{ 
       
      
        String query = "DELETE FROM movies WHERE title =? ";
          
         PreparedStatement ps = null;
          
          ps = myConn.prepareStatement(query);
          ps.setString(1, input);
          
         myRs = ps.executeQuery();
         
         System.out.println("Movie Deleted");
        
 
  }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
         
         
      
  }// End of RemoveMovie
  
  //Returns Movie_ID
 public Integer getMovieID(String title) throws SQLException {
      
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "<your password here>";
 PreparedStatement ps = null;
 Integer movie_id = 0;
 Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
 Statement mystmt = myConn.createStatement();
 ResultSet myRs;
     
   
       
 
         String query = "select movie_id from movies where title = ? ";
         
        
          ps = myConn.prepareStatement(query);
          ps.setString(1, title);
          
         myRs = ps.executeQuery();
         
    while(myRs.next()){
        
       movie_id = Integer.parseInt(myRs.getString("movie_id"));
    }
       
     return movie_id;
 }
 
 
 
 
 
 
 //Generate Rental ID
 public Integer generateRentalID() throws SQLException{
       
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "<your password here>";
 PreparedStatement ps = null;
 Integer sum =0;
 
 Connection myConn = DriverManager.getConnection(url, user, password);
 Statement mystmt = myConn.createStatement();
 ResultSet myRs;
     
       
 
         String query = "select rental_id from rentalinfo";
         
          
          ps = myConn.prepareStatement(query);
          
          
         myRs = ps.executeQuery();
                  

        while(myRs.next()){
           
           sum += myRs.getInt("rental_id");
              
    }
    
        return sum;
     
 }
  
  public void checkoutMovie() throws SQLException{
 Clients client = new Clients();
 RentalInfo rentalinfo = new RentalInfo();
 Scanner scanner = new Scanner(System.in);  
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "<your password here>";
 
 
 
 Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
 Statement mystmt = myConn.createStatement();
 ResultSet myRs;
 
 Integer rentalcount = client.getRentalCount();
 
      //Determine if User is above rental count
      if(rentalcount >= 3){
          System.out.println("You cannot rent a Movie, You must return your movie.");
      }else{
          
          // code for execution
          
          System.out.println("Enter Movie Again to Finalize Checkout:");
      String input = scanner.next();
      
      String query1 = "UPDATE movies\n" +
"	SET  rented= true \n" +
"	WHERE title = ? ";
       PreparedStatement ps = null;
       
       
      try{
         
          ps = myConn.prepareStatement(query1);
          
          ps.setString(1, input);
          
         myRs = ps.executeQuery();
          
          
      }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
      
      
      //Get Date
      System.out.println("Enter the Date (Example May 1st -- 050120:");
      Integer date = Integer.parseInt(scanner.next());
      
      //Get Client_id
      
     Integer client_id = client.searchClient();
  
     //Update Rental_Count for User
     client.addRentalCount(client_id);
     
      
   // Get Movie_id
    Integer movie_id = getMovieID(input);
    
            
    //Get Rental Id
    Integer rental_id;
    
    rental_id = rentalIDOffSet + generateRentalID();
    
    
    
   rentalinfo.updateRentalInfo(client_id, date, movie_id, rental_id);
      
      }//end of else clause
 
        
  }
    
    
  public void returnMovie() throws SQLException{
      Clients client = new Clients();
 RentalInfo rentalinfo = new RentalInfo();
 Scanner scanner = new Scanner(System.in);  
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "<your password here>";
      
      
 
 //Update Movie Checkout Status
      System.out.println("Enter Movie that is being returned:");
      String movie = scanner.nextLine();
      update_movie_return_status(movie);
 
 Integer movie_id = getMovieID(movie);
 
 //Update Client's Rental Count
 System.out.println("Enter Client ID");
  
 Integer client_id = scanner.nextInt();
 client.subtractRentalCount(client_id);
 
 
 
 //Update RentalInfo Return Date
      System.out.println("Enter the Return Date (Example 050220):");
      Integer returndate = scanner.nextInt();
      
      rentalinfo.updateReturnDate(returndate, movie_id);
      
 
      System.out.println("Movie returned");
      
      
      
      
  }
  
  public void update_movie_return_status(String movie) throws SQLException{
      
      
      String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "<your password here>";
 PreparedStatement ps = null;
      
   Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
 Statement mystmt = myConn.createStatement();
 ResultSet myRs;
 
 
 
 String query = "UPDATE movies\n" +
"	SET  rented = false\n" +
"	WHERE title = ?";
      
 try{
 ps = myConn.prepareStatement(query);
          
          ps.setString(1, movie);
          
         myRs = ps.executeQuery();
          
          
      }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
 
      
  }
  
  
  
} // end of Movie Class
