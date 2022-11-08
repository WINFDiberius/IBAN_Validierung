public class IbanValidationService {
    private String iBan;
    private int calculatedCheckSum;

    public IbanValidationService(String iBan) {
        this.iBan = iBan;
        this.calculatedCheckSum = -1;
    }

    public String getCountrycode(){
        return iBan.substring(0,2);
    }

    public int getCheckSum(){
        String part = iBan.substring(2,4);
        this.calculatedCheckSum = Integer.valueOf(part);
        return this.calculatedCheckSum;
    }

    public String getAccountNumber(){
        return iBan.substring(4,iBan.length());
    }

    public String getCountryCodeNumber(){

        char checkNumber = '7';

        int intA = iBan.charAt(0) - checkNumber;
        int intB = iBan.charAt(1) - checkNumber;
        String countryCodeNumber = "" + intA + intB * 100;
        return countryCodeNumber;
    }

    public String getFullCode(){
        return getAccountNumber() + getCountryCodeNumber();
    }

    public int[] buildArray(){
        int[] fullCode = new int[getFullCode().length()];

        for (int i = 0; i < getFullCode().length(); i++) {
            fullCode[i] = getFullCode().charAt(i) - '0';
        }

        return fullCode;
    }

    public int calcCheckSum(){

        int checkNumber = 0;
        int sum = 0;

        for (int i = 0; i < getFullCode().length(); i++) {

            checkNumber = ((checkNumber * 10) + buildArray()[i]) % 97;


        }
        checkNumber = 98 - (checkNumber % 97);
        return checkNumber;
    }

    public boolean isIbanCorrect(){
        return calcCheckSum() == getCheckSum();
    }
}
