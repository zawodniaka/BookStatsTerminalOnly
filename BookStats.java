//A simple project that reads a file that contains lines of book entries.
//These entries are in the order: Title, Author, Release Year, and rating (0-5 stars)
//The program will read and separate these entries and provide stats based upon the release year
//This will include average rating for a year and how many DNFs (did not finish) there are for
//the year.

//Allison Zawodniak, September 10th 2025

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class BookStats {
    File bookFile=new File("092025BookData.txt");
    //A tree map is chosen to display the years in order
    TreeMap<Integer, List<Book>> bookMap=new TreeMap<>();

    public static void main(String[] args) {
        BookStats stats=new BookStats();
        stats.readFile();
        stats.printStats();
    }

    /*
     * Reads the file by using a scanner and checking for if a next line exists. Reads in the 
     * next line and splits the contents to create the book objects.
     * Books are sorted by their release years.
     */
    public void readFile(){
        try {
            Scanner scanner=new Scanner(bookFile);
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                String[] parts=line.split(",");
                Book book=new Book(parts[0].trim(), parts[1].trim(), Integer.parseInt(parts[2].trim()), 
                    Double.parseDouble(parts[3].trim()));

                //check if the map has the key of year
                //if it does, add the new entry to its list
                int year=book.getReleaseYear();
                if(bookMap.containsKey(year)){
                    bookMap.get(year).add(book);
                }

                //if not, create new list and key for it
                else{
                    ArrayList<Book> newList=new ArrayList<>();
                    newList.add(book);
                    bookMap.put(year, newList);
                }
                
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
    }//end of readFile method

    
    /*
     * Print the stats for each year contained in the bookMap. 
     * Starts with the earliest year and prints all books read that were released that year.
     * Keeps track of the number of DNFs (Did Not Finish) and the total rating for the books.
     * Lastly, a line is printed providing the user with the average rating and number of DNFs.
     */
    public void printStats(){
        for (Integer key : bookMap.keySet()) {
            System.out.println(key);
            double total=0;
            int dnfs=0;

            //sorts the array by rating in descending order
            bookMap.get(key).sort((book1, book2)->Double.compare(book2.getRating(), book1.getRating()));

            for (Book book : bookMap.get(key)) {
                double rating=book.getRating();
                System.out.printf("%s by %s %.2f %n", book.getTitle(), 
                    book.getAuthor(), rating);
                if(rating==0){
                    dnfs+=1;
                }else{
                    total+=rating;
                }
            }

            System.out.printf("FINAL %d STATS -- Average rating: %.2f  Total DNFs: %d %n%n", 
                key, total/(bookMap.get(key).size()-dnfs), dnfs);
        }
    }//end of printStats method
}
