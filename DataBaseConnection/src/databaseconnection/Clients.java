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
    
    public void displayClientMenu() throws SQLException{
    Scanner scanner = new Scanner(System.in);
     String input1 = "";
     String input2 = "";
     
     
        System.out.println("");
        System.out.println("----------");
     System.out.println("CLIENT MENU");
        System.out.println("----------");
        System.out.println(
                "search_client - Find Client by Name\n" +
                "addclient - Add Client to Database\n" +
                "listclients - List all Clients in Database\n" +
                "removeclient - Remove Client From Database \n" +
                "quit - Quit BlockBlaster Menu \n");
                
       
     
        //CHECK FOR CORRECT INPUT
        
      while(!input1.equals("search_client") || !input1.equals("addclient") || !input1.equals("listclients") || !input1.equals("removeclient") || !input1.equals("quit")){
          
          System.out.println("Input Your Selection Below:");
          input1 = scanner.nextLine();
        
          
          if(input1.equals("search_client") || input1.equals("addclient") ||input1.equals("listclients") || input1.equals("removeclient") || input1.equals("quit")){break;}
          
      }
        
       
        
        OUTER:
        while (!input1.equals("quit")) {
            switch (input1) {
                case "listclients"://List all clients
                    listall();
                    break;
                    
                case "addclient"://Creat Client
                 
                     create();
         
                    break;
                    
                case "removeclient":
                    removeClient();
                    
                    break;
                    
                 case "search_client":
                      searchClient();
                      
                    break; 
                     
                case "quit":
                    break OUTER;
            }
            displayClientMenu();
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
          System.out.println("NAME -- ID -- Disabled_Status -- Movies_rented");
          while(myRs.next()){
              System.out.println(myRs.getString("name") + " -- " + myRs.getString("client_id")+ " -- " + myRs.getString("deleted")+ " -- " +
                      myRs.getString("rental_count"));
          }
      }
      catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
         
     }
    
    
    public static void create(){
      String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "zxcasdQWE!@#*";
 Scanner scan = new Scanner(System.in);
 String username = null;
 Boolean status = false;
 Integer client_id = null;

 
        // Get Username
        System.out.println("Enter Username( Example MaryBrown ):");
        username = scan.nextLine();
        
        System.out.println("Enter Client_Id (Example 004): ");
        client_id = Integer.parseInt(scan.nextLine());
        
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
          ps.setString(1, username);
          ps.setInt(2, client_id);
          ps.setBoolean(3, status);
          
         myRs = ps.executeQuery();
         
        
         System.out.println("User Created");
  }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
      
      
  }
    
    public static void searchClient() throws SQLException{
        Scanner scanner = new Scanner(System.in);  
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "zxcasdQWE!@#*";
 PreparedStatement ps = null;
 
 Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
 Statement mystmt = myConn.createStatement();
 ResultSet myRs;
     
        System.out.println("Enter a user's name below to get account details.");
        System.out.println("Enter a User Name: ");
       String input = scanner.nextLine();
       
 
         String query = "select * from clients where name = ? ";
         
          
          ps = myConn.prepareStatement(query);
          ps.setString(1, input);
          
         myRs = ps.executeQuery();
                  
       

//PRint results; the name and email are the columns that will print the query strings results
        while(myRs.next()){
            System.out.println("");
            System.out.println("NAME -- ID -- Disabled_Status -- Movies_rented");
              System.out.println(myRs.getString("name") + " -- " + myRs.getString("client_id")+ " -- " + myRs.getString("deleted")+ " -- " +
                      myRs.getString("rental_count"));
              
    }
    
    
    
}
    
    public static void removeClient() throws SQLException{
        
        
        Scanner scanner = new Scanner(System.in);  
 String url = "jdbc:postgresql://localhost:5432/Term2";
 String user = "postgres";
 String password = "zxcasdQWE!@#*";
      
        Connection myConn = DriverManager.getConnection(url, user, password);
         //Create Statement
        Statement mystmt = myConn.createStatement();
        ResultSet myRs;
 
 
 System.out.println("Enter Clients Name Below:");
      
       String input = scanner.nextLine();
    
       //Test Validity
         try{ 
       
      
        String query = "UPDATE clients SET deleted = true  WHERE name = ?";
          
         PreparedStatement ps = null;
          
          ps = myConn.prepareStatement(query);
          ps.setString(1, input);
          
          
          myRs = ps.executeQuery();
         
         
        
 
  }catch (SQLException ex) {
           System.out.println(ex.getMessage());
      }
        
        
        
        
        
        
    }// end of removeClient
    
}
