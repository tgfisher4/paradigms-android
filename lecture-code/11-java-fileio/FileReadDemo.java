import java.util.Scanner;
import java.io.File;

public class FileReadDemo{

    public static void main(String[] args) throws Exception{
        File myFile = new File("scores.txt");
        
        // option 1
        Scanner in = new Scanner(myFile);
        //optional  Scanner in = new Scanner(new File("scores.txt"));
        
       
        while(in.hasNext()){
            String firstName = in.next();
            String lastName = in.next();
            int score       = in.nextInt();

            System.out.println(firstName + " " + lastName + " got a score of " + score);
        } // end of while
        in.close();

        // option 2
        Scanner in2 = new Scanner(myFile);
        while(in2.hasNext()){
            String line = in2.nextLine();
            String[] tokens = line.split(" ");

            for(String token : tokens){
                System.out.print(token + ", ");
                if(token.matches("Dwight")){
                    System.out.print("Beets, ");
                }
            }
        } // end of while
    
    } // end of main
}
