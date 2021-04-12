package lt.niko;

public class Account {
    private String name;
    private String number;
    private double balance;
    private int pinCode;

    Account(String name, String number, double balance, int pinCode) {
        this.setName(name);
        this.setNumber(number);
        this.setBalance(balance);
        this.setPinCode(pinCode);
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        long leftLimit = 0;
        long rightLimit = 1000000000000000000L;
        long numberWithoutPrefix = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        number = String.valueOf(numberWithoutPrefix);
        this.number = "LT" + number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

}
