// TEST
package dhbw.fowler1.videostore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<Rental>();

    public Customer(String name) {
        _name = name;
    }

    public double amountCalculation(Rental rental) {
        // determine amount for each line
        double thisAmount = 0;
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

    public int frpCalculation(Rental rental){
        int frequentRenterPoints = 0;
        frequentRenterPoints ++;
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
        rental.getDaysRented() > 1) frequentRenterPoints ++;

        return frequentRenterPoints;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        String result = "Rental Record for " + getName() + "\n";
<<<<<<< Updated upstream
        double thisAmount = 0;
        Rental each = (Rental) rentals.nextElement();

        while (rentals.hasMoreElements()) {
            thisAmount = 0;
            each.nextElement();
=======

        Enumeration<Rental> rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
>>>>>>> Stashed changes

            totalAmount += amountCalculation(each);
            result += "\t" + each.getMovie().getTitle()+ "\t" +
                String.valueOf(amountCalculation(each)) + "\n";

            frequentRenterPoints += frpCalculation(each);

        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
        
        return result;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }
}
