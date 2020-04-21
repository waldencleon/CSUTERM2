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
        String input1 = "";
       String input2 = "";
       
       
        System.out.println("----------Welcome to BlockBlaster Movies--------");
        System.out.println("Please choose from the selection below:");
        
        
        System.out.println(" BLOCKBLASTER ADMIN MENU");
        System.out.println("----------");
        System.out.println("clients - VIEW CLIENT MENU \n" +
                "movies - VIEW MOVIE MENU\n" +
                "rentalinfo - VIEW RENALT_INFO MENU\n" +              
                "quit - QUIT Menu\n");
        
        
        
        
        //CHECK FOR CORRECT INPUT
        
      while(!input1.equals("clients") || !input1.equals("movies") || !input1.equals("rentalinfo") || !input1.equals("quit")){
          
          System.out.println("Input Your Selection Below:");
          input1 = scanner.nextLine();
          
          if(input1.equals("clients") || input1.equals("movies") || input1.equals("rentalinfo") || input1.equals("quit")){break;}
          
      }
        
      //Determine Menu Selection
        
      if(!input1.equals("quit")){
          OUTER:
          while(!input1.equals("quit")){
              switch (input1) {
                  case "movies":
                      movies.displayMovieMenu();
                      break;
                  case "clients":
                      clients.displayClientMenu();
                      break;
                  case "rentalinfo":
                      break;
              }
              break;
          }
      }else{
          System.out.println("Leaving Menu");
      }
      
        
        
    }
    
}
