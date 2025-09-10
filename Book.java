public class Book{
    private String title;
    private String author; 
    private int releaseYear;
    private double rating;

    public Book(String title, String author, int releaseYear, double rating){
        this.title=title;
        this.author=author;
        this.releaseYear=releaseYear;
        this.rating=rating;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public void setReleaseYear(int releaseYear){
        this.releaseYear=releaseYear;
    }

    public void setRating(double rating){
        this.rating=rating;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public int getReleaseYear(){
        return this.releaseYear;
    }

    public double getRating(){
        return this.rating;
    }
}
