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
public class Main {
    
    
    
    
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Movies movies = new Movies();
        Clients clients = new Clients();
        String input1 = null;
       String input2 = null;
       
       
        System.out.println("----------Welcome to BlockBlaster Movies--------");
        System.out.println("Please choose from the selection below:");
        
        
        System.out.println(" BLOCKBLASTER ADMIN MENU");
        System.out.println("----------");
        System.out.println("clients - VIEW CLIENT MENU \n" +
                "movies - VIEW MOVIE MENU\n" +
                "rentalinfo - VIEW RENALT_INFO MENU\n" +              
                "\n" +
                "Input You Selection Below:");
        
        input1 = scanner.nextLine();
        
        
        //CHECK FOR CORRECT INPUT
        
      while(!input1.equals("clients") || !input1.equals("movies") || !input1.equals("rentalinfo") || !input1.equals("quit")){
          
          System.out.println("Sorry did recognize that input, please try again:");
          input1 =scanner.nextLine();
          
          if(input1.equals("clients") || input1.equals("movies") || input1.equals("rentalinfo") || input1.equals("quit")){break;}
          
      }
        
      //Determine Menu Selection
        OUTER:
        while (!input1.equals("quit")) {
            switch (input1) {
                case "movies":
                    movies.dispalyMovieMenu();
                    break;
                case "clients":
                    clients.displayClients();
                    break;
                case "rentalinfo":
                    break;
                case "quit":
                    System.out.println("Leaving Menu");
                    break OUTER;
            }
            break;
        }
            
            
            
        
        
        
       
        
       
        //movies.determineCheckOut();
        
//           System.out.println("");
//           clients.getClients();
//           
//          System.out.println("");
//          movies.getMovies();
          
       
       

        
        
    }
    
}
