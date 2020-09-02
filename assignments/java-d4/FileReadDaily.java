import java.io.*;
import java.util.Scanner;

// starter code - incomplete

class Movie {
    //TODO class definition

    public String toString() {
        String s = "Title: " + title + "\tRelease Year: " + release + "\tGenre: " + genre;
        return s;
    } // end of toString()
} // end of Movie class

public class FileReadDaily {
	public static Scanner stdin = new Scanner(System.in);

    public static void print(Movie[] movies, int i, boolean flag) {
        //TODO function definition
    } // end of print function

    public static void printall(Movie[] movies) {
        //TODO function definition
    } // end of printall function


    public static void main(String[]args) throws IOException{
        Movie[] movies = new Movie[4096];
        //TODO read from mini-movies.dat and create movies

		String message = "Available actions:\n  printall       \tPrints all movies in plain text.\n  print <integer>\tPrints the movie at the array index specified by the integer option.\n exit\n";
        System.out.println(message);
        //TODO prompt user for choice, accept user choice and have the program behave accordingly
        return;
	} // end of main
} // end of class Daily
