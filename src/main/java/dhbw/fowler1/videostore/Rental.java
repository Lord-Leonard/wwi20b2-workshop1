package dhbw.fowler1.videostore;

import java.util.Enumeration;

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

    /***
     * Berechnet den gesamten zu zahlenden Betrag angand der Liste an ausgeliehenen Filmen
     * @param rentals alle ausgeliehenen Filme
     * @return Gesamten zu zahlenden Betrag (double)
     */
    public static double totalAmount(Enumeration<Rental> rentals){
        double totalAmount = 0;
        while (rentals.hasMoreElements()){
            Rental each = rentals.nextElement();
            totalAmount += each.amountCalculation();
            Customer.output(each.getThisAmount(), each, true);
        }
        return totalAmount;
    }

    /***
     * Berechnet die Kosten für einen Film anhand der Ausleihdauer
     * @return Preis
     */
    public double amountCalculation() {
        // determine amount for each line
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (getDaysRented() > 2)
                    thisAmount += (getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (getDaysRented() > 3)
                    thisAmount += (getDaysRented() - 3) * 1.5;
                
                break;
        }
        return thisAmount;
    }
}
