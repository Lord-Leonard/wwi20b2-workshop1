// TEST
package dhbw.fowler1.videostore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<Rental>();
    private String output;
    private double totalAmount;
    private int frequentRenterPoints;

    public Customer(String name) {
        _name = name;
        output = "";
    }

    public double amountCalculation(Rental rental) {
        // determine amount for each line
        double thisAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                output(thisAmount, rental);
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                output(thisAmount, rental);
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                output(thisAmount, rental);
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
        totalAmount = 0;
        frequentRenterPoints = 0;

        String result = "Rental Record for " + getName() + "\n";

        Enumeration<Rental> rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

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

    public String output(double amount, Rental each) {
        if (output.equals("")) 
            output = "Rental Record for" + getName() + "\n";
        output += "\t" + each.getMovie().getTitle()+ "\t" +
                String.valueOf(amount) + "\n";
        if (each.hasMoreElements != true) {
            output += "Amount owed is " + String.valueOf(totalAmount) + "\n";
            output += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points"; 
        }

    }
}
