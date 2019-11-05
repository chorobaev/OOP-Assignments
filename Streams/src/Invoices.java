import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Invoices {
    public static void main(String[] args) {
        Invoice[] invoices = {
            new Invoice("83", "Electric Sander", 7, 57.98),
            new Invoice("24", "Power saw", 18, 99.99),
            new Invoice("7", "Sledge hammer", 11, 21.50),
            new Invoice("77", "Hammer", 76, 11.99),
            new Invoice("39", "Lawn mower", 3, 79.50),
            new Invoice("68", "Screwdriver", 106, 6.99),
            new Invoice("68", "Screwdriver", 106, 6.99),
            new Invoice("56", "Jig saw", 21, 11.00),
            new Invoice("3", "Wrench", 34, 7.50),
        };

        System.out.println("Sorted by partDescription:");
        Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getPartDescription))
            .forEach(System.out::println);
        System.out.println("\nSorted by pricePerItem:");
        Arrays.stream(invoices).sorted(Comparator.comparing(Invoice::getPricePerItem))
            .forEach(System.out::println);
        System.out.println("\npartDescription and quantity, sorted by quantity:");
        Arrays.stream(invoices).map(item -> new Object() {
            String partDescription = item.getPartDescription();
            int quantity = item.getQuantity();

            @Override
            public String toString() {
                return "quantity=" + quantity +
                    ", partDescription='" + partDescription + '\'';
            }
        }).sorted(Comparator.comparingInt(i -> i.quantity))
            .forEach(System.out::println);

        System.out.println("\npartDescription and value, sorted by value:");
        Arrays.stream(invoices).map(item -> new Object() {
            String partDescription = item.getPartDescription();
            double value = item.getQuantity() * item.getPricePerItem();

            @Override
            public String toString() {
                return "value=" + value +
                    ", partDescription='" + partDescription + '\'';
            }
        }).sorted(Comparator.comparingDouble(i -> i.value))
            .forEach(System.out::println);

        System.out.println("\npartDescription and value, value in range $200 and $500, sorted by value:");
        Arrays.stream(invoices).map(item -> new Object() {
            String partDescription = item.getPartDescription();
            double value = item.getQuantity() * item.getPricePerItem();

            @Override
            public String toString() {
                return "value=" + value +
                    ", partDescription='" + partDescription + '\'';
            }
        }).filter(item -> item.value >= 200 && item.value <= 500)
            .sorted(Comparator.comparingDouble(i -> i.value))
            .forEach(System.out::println);

        System.out.println("\nInvoices in which the partDescription contains the word \"saw\".");
        Arrays.stream(invoices).filter(item -> Pattern.matches(".*saw.*", item.getPartDescription()))
            .forEach(System.out::println);
    }
}
