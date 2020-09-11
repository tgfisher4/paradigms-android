import java.io.*;
import java.util.Scanner;
import org.json.simple.*; // this import is happening from a local file called org.json.jar remember to have the file in the folder
import org.json.simple.parser.*;

// Note: some parts of this program can be taken from the previous Daily

class Movie {
    //TODO class definition

    public String toString() {
        String s = "Title: " + title + "\tRelease Year: " + release + "\tGenre: " + genre;
        return s;
    }
} // end of Movie class

public class JSONReadDaily {
	public static Scanner stdin = new Scanner(System.in);

    public static void print(Movie[] movies, int i, boolean flag) {
        //TODO function definition
    } // end of print

    public static void printall(Movie[] movies) {
        //TODO function definition
    }// end of printall

    public static Movie[] parseJsonMovies(JSONArray a){
        // this function is fully done for you.
        return movies;
    } // end of parseJsonMovies

    public static void main(String[]args) throws IOException{
        // main is only partially done for you
        // TODO: Read json object from mini-movies.json file, you would have to keep exception handling in mind.
        
        Movie[] movies = parseJsonMovies(a);

		String getInput = "Available actions:\n  printall       \tPrints all movies in plain text.\n  print <integer>\tPrints the movie at the JSON array index specified by the integer option.\n  exit\n";
        System.out.println(getInput);
		
        //TODO get user input in a loop and have the program behave accordingly
	
    }// end of main
} // end of class
