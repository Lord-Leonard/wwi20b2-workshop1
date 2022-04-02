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


    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;


        Enumeration<Rental> rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            totalAmount += each.amountCalculation(each);
           
            frequentRenterPoints += each.getRenterPoints();

            output(each.getThisAmount(), each, true);

        }

    
        return output(totalAmount,null, false);
    }

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String output(double amount, Rental each, Boolean value) {
        if (output.equals("")) 
            output = "Rental Record for " + getName() + "\n";

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
