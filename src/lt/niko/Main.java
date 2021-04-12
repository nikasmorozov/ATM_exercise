package lt.niko;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Account newAccount1 = new Account("Sąskaita 1", "generatedInClass", 0, 0000);
        Account newAccount2 = new Account("Sąskaita 2", "generatedInClass", 1000, 0000);

        System.out.println("Įveskite " + newAccount1.getName() + " (" + newAccount1.getNumber() + ") balansą");
        double providedBalance = scanner.nextDouble();

        while (providedBalance >= 1000000) {
            System.out.println("Viršijote limitą. Įveskite " + newAccount1.getName() + " (" + newAccount1.getNumber() + ") balansą iš naujo");
            providedBalance = scanner.nextDouble();
        }
        newAccount1.setBalance(providedBalance);

        System.out.println("Susikurkite " + newAccount1.getName() + " (" + newAccount1.getNumber() + ") pin kodą");
        newAccount1.setPinCode(scanner.nextInt());

        pinCodeChange(newAccount1, newAccount2, scanner);

        scanner.close();
    }

    public static void pinCodeChange(Account account1, Account account2, Scanner scanner) {
        Account accountToCheck;

        if (account1.getBalance() <= account2.getBalance()) {
            accountToCheck = account1;
        } else {
            accountToCheck = account2;
        }

        int pinEntryTries = 2;

            System.out.println("Turite pasikeisti " + accountToCheck.getName() + " PIN kodą. Įveskite esamą PIN kodą:");
            int providedPinCode = scanner.nextInt();

            while (providedPinCode != accountToCheck.getPinCode() && pinEntryTries > 0) {
                System.out.println("Neteisingas PIN kodas. Jums liko " + pinEntryTries + " bandymų");
                pinEntryTries--;
                providedPinCode = scanner.nextInt();
            }

            if (providedPinCode == accountToCheck.getPinCode()) {
                System.out.println("Įveskite naują PIN kodą");
                int newPinCode = scanner.nextInt();
                System.out.println("Pakartokite naują PIN kodą");
                int newPinCodeConfirm = scanner.nextInt();

                if(newPinCode != accountToCheck.getPinCode() && newPinCode == newPinCodeConfirm) {
                    accountToCheck.setPinCode(newPinCode);
                    withdrawal(account1, account2, scanner);
                } else {
                    System.out.println("Įvestas PIN kodas sutapo su senuoju arba įvesti PIN kodai nesutapo");
                }

            } else {
                System.out.println("Jūsų sąskaita užblokuota");
            }
    }

    public static void withdrawal(Account account1, Account account2, Scanner scanner) {
        System.out.println("Pasirinkite sąskaitą:");
        System.out.println("1: " + account1.getName() + ": " + account1.getNumber().substring(0, 2) + "xxxxxxxxxxxxx" + account1.getNumber().substring(15));
        System.out.println("2: " + account2.getName() + ": " + account2.getNumber().substring(0, 2) + "xxxxxxxxxxxxx" + account2.getNumber().substring(15));
        int selectedAccount = scanner.nextInt();
        Account accountToWithdraw = null;

        if (selectedAccount == 1) {
            accountToWithdraw = account1;
        } else if(selectedAccount == 2) {
            accountToWithdraw = account2;
        } else {
            withdrawal(account1, account2, scanner);
        }

        System.out.println("Kokią sumą norite išsiimti?:");
        double withdrawalAmount = scanner.nextDouble();

        if(withdrawalAmount <= accountToWithdraw.getBalance()) {
            accountToWithdraw.setBalance(accountToWithdraw.getBalance() - withdrawalAmount);
            String accountPreview = accountToWithdraw.getNumber().substring(0, 2) + "xxxxxxxxxxxxx" + accountToWithdraw.getNumber().substring(15);
            System.out.println(accountToWithdraw.getName() + " (" + accountPreview + ") balansas yra: " + accountToWithdraw.getBalance());
        } else {
            System.out.println("Balansas nepakankamas.");
            withdrawal(account1, account2, scanner);
        }
    }
}
