package dhbw.fowler1.videostore;

public class Rental {
    private Movie _movie;
    private int _daysRented;
    private double thisAmount = 0;

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

    public double getThisAmount(){
        return thisAmount;
    }

    public int getRenterPoints(){
        int frequentRenterPoints = 1;
        // add bonus for a two day new release rental
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
        getDaysRented() > 1) frequentRenterPoints ++;

        return frequentRenterPoints;
    }

    public double amountCalculation(Rental rental) {
        // determine amount for each line
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                
                break;
        }
        return thisAmount;
    }
}
