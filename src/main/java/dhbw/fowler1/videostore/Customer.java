// TEST
package dhbw.fowler1.videostore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<Rental>();
    private String output;
    
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
                output(thisAmount, rental, true);
                break;
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                output(thisAmount, rental, true);
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                output(thisAmount, rental, true);
                break;
        }
        return thisAmount;
    }


    public String statement() {
        double totalAmount = 0;


        Enumeration<Rental> rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            totalAmount += amountCalculation(each);

        }

    
        return output(totalAmount,null, false);
    }

    public int getTotalRenterPoints() {
        int total = 0;
        Enumeration<Rental> rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            total += each.getRenterPoints();
        }
        return total;
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String output(double amount, Rental each, Boolean value) {
        if (output.equals("")) 
            output = "Rental Record for" + getName() + "\n";

        if (value){
            output += "\t" + each.getMovie().getTitle()+ "\t" +
                String.valueOf(amount) + "\n";
        }else {
            output += "Amount owed is " + String.valueOf(amount) + "\n";
            output += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points"; 
        }

        return output;

    }
}
