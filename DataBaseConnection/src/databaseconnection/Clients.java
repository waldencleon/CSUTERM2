/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.sql.*;
import java.util.*;
/**
 *
 * @author cawalden
 */
public class Clients implements Tables {
    
    public void displayClients() throws SQLException{
    Scanner scanner = new Scanner(System.in);
     String input1 = null;
     String input2 = null;
     
     System.out.println("CLIENT MENU");
        System.out.println("----------");
        System.out.println(
                "search_client - Find Client by Name\n" +
                "addclient - Add Client to Database\n" +
                "listclients - List all Clients in Database\n" +
                "removeclient - Remove Client From Database \n" +
                "quit - Quit BlockBlaster Menu \n" +
                "\n" +
                "Input You Selection Below:");
                
        input1 = scanner.nextLine();
        
        OUTER:
        while (!input1.equals("quit")) {
            switch (input1) {
                case "listclients":
                    listall();
                    break;
                case "addclient":
                    
        System.out.println("Enter the Client Name (Example -- MaryBrown):");
        input1 = scanner.nextLine();
        
        System.out.println("Enter Client ID (ensure id is in 001 format):");
        
        input2 = scanner.nextLine();
        
         create(input1, Integer.parseInt(input2) , false);
         
                    break;
                case "removeclient":
                    break;
                 case "search_client":
                    break; 
                case "quit":
                    break OUTER;
            }
            break;
        }
    
    
    }
    
   
 //List All Clients
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
          myRs = mystmt.executeQuery("Select * from clients");
       //PRint results
          while(myRs.next()){
              System.out.println(myRs.getString("name") + " -- " + myRs.getString("client_id") + " -- " + myRs.getString("deleted"));
          }
      }
      catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
         
     }
    
    
    public static void create(String name, Integer client_id, Boolean deleted){
      String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "zxcasdQWE!@#*";
          
      
  try{ 
       //Get Connection
          Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
          
          ResultSet myRs;
          PreparedStatement ps = null;
         String query = "INSERT INTO clients(\n" +
                 "	name, client_id, deleted)\n" +
                 "	VALUES (?, ?, ?)";
          
          ps = myConn.prepareStatement(query);
          ps.setString(1, name);
          ps.setInt(2, client_id);
          ps.setBoolean(3, deleted);
          
         myRs = ps.executeQuery();
         
        
 
  }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
      
      
  }
    
    
    
}
