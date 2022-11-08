import java.util.Arrays;
import java.util.Scanner;

public class DemoIban {

    public static void main(String[] args) {


        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Eingabe von IBAN:");

        String ibanEntry = myObj.nextLine();  // Read user input

        IbanValidationService ibanValidationService = new IbanValidationService(ibanEntry);

        System.out.println("\nLändercode: " + ibanValidationService.getCountrycode() + " (" + ibanValidationService.getCountryCodeNumber() + ")");
        System.out.println("Prüfsumme: " + ibanValidationService.getCheckSum());
        System.out.println("Kontonummer: " + ibanValidationService.getAccountNumber());
        System.out.println("Prüfzahl: " + ibanValidationService.getFullCode());

        int[] checkNumberAsInt = ibanValidationService.buildArray();
        System.out.println("Prüfzahl als Zahl: " + Arrays.toString(checkNumberAsInt));

        System.out.print("Ist dieser IBAN korrekt? ");
        if(ibanValidationService.isIbanCorrect())
            System.out.print("Ja");
        else
            System.out.print("Nein");
        System.out.println(" (" + ibanValidationService.calcCheckSum() + ")");

    }

}
