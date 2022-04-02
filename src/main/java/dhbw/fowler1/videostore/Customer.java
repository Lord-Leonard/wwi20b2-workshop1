// TEST
package dhbw.fowler1.videostore;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector<Rental>();
    
    static String output;
    static int frequentRenterPoints;

    public Customer(String name) {
        _name = name;
        output = "Rental Record for " + getName() + "\n";
    }

/**
 * Diese Methode erstellt einen Outpustring mit dem Namen des Nutzers, den Ausgeliehenen Filmen, dem geschuldeten Betrag und den Treuepunkten.
 * @return Outputstring
 */
    public String statement() {
        double totalAmount = 0;

        totalAmount = Rental.totalAmount(_rentals.elements());
           
        // frequentRenterPoints += each.getRenterPoints();

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

    public static String output(double amount, Rental each, Boolean value) {
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
