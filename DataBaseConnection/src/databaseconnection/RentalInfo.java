/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.sql.*;


/**
 *
 * @author cawalden
 */
public class RentalInfo {
    

    public void updateRentalInfo(Integer client_id, Integer date, Integer movie_id , Integer rental_id) throws SQLException{
    String url = "jdbc:postgresql://localhost:5432/Term2";
    String user = "postgres";
    String password = "zxcasdQWE!@#*";
 
 
 
    Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
    Statement mystmt = myConn.createStatement();
    ResultSet myRs;
    
    String query = "INSERT INTO public.rentalinfo(\n" +
"	client_id, date_out, movie_id, rental_id)\n" +
"	VALUES (?, ?, ?, ?) ";
    
    try{
         
        PreparedStatement ps = myConn.prepareStatement(query);
          
          ps.setInt(1, client_id);
          ps.setInt(2,date);
          ps.setInt(3, movie_id);
          ps.setInt(4, rental_id);
                  
          
         myRs = ps.executeQuery();
          
          
      }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
 
 
        
    }
    
    public void updateReturnDate(Integer returnDate, Integer movie_id) throws SQLException{
        
        String url = "jdbc:postgresql://localhost:5432/Term2";
    String user = "postgres";
    String password = "zxcasdQWE!@#*";
 
 
 
    Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
    Statement mystmt = myConn.createStatement();
    ResultSet myRs;
        
    String query = "UPDATE public.rentalinfo\n" +
"	SET  date_in=?\n" +
"	WHERE movie_id =?";
    
    try{
         
        PreparedStatement ps = myConn.prepareStatement(query);
          
          ps.setInt(1, returnDate);
          ps.setInt(2,movie_id);
                  
          
         myRs = ps.executeQuery();
          
          
      }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
        
        
        
    }
    
    
    
    
}
