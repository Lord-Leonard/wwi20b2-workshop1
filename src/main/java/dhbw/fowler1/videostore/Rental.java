package dhbw.fowler1.videostore;

public class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public int getRenterPoints(){
        int frequentRenterPoints = 1;
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
        getDaysRented() > 1) frequentRenterPoints ++;

        return frequentRenterPoints;
    }
}
