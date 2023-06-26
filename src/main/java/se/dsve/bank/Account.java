// Skapa ett litet program och använda TDD med JUnit för att säkerställa att det fungerar som förväntat.
//
//Skapa en class som heter Bank och i den ska du ha följande metoder
//Constructor (initialCash, Accountnr)
//deposit(cash)
//widthdraw(cash)
//getBalance()
//getAccountNr()
//Skapa nödvändiga tester för att försäkra dig om att kontot inte får felaktiga inmatningar och inte övertrasseras

package bank;

public class Account {
    private double balance;
    private int accountNr;

    final private double maxTransaction = 10000.0;
    final private double minTransaction = 1.0;

    public Account(double initialCash, int accountNr) {
        if (initialCash < 0) {
            throw new IllegalArgumentException("You can't deposit a negative amount");
        }
        if (accountNr < 0) {
            throw new IllegalArgumentException("You can't have a negative account number");
        }
        this.balance = initialCash;
        this.accountNr = accountNr;
    }

    public void deposit(double cash) {
        if (cash < minTransaction){
            throw new IllegalArgumentException("You can't deposit less than " + minTransaction);
        }
        if (cash > maxTransaction) {
            throw new IllegalArgumentException("You can't deposit more than " + maxTransaction);
        }
        if (Double.isNaN(cash)){
            throw new IllegalArgumentException("Invalid deposit amount");
        }
        balance += cash;
        System.out.println("You deposited " + cash + " to account " + accountNr);
        System.out.println("Your new balance is " + balance);
    }

    public void withdraw(double cash) {
        if (cash < minTransaction){
            throw new IllegalArgumentException("You can't withdraw less than " + minTransaction);
        }
        if (cash > maxTransaction) {
            throw new IllegalArgumentException("You can't withdraw more than " + maxTransaction);
        }
        if (Double.isNaN(cash)){
            throw new IllegalArgumentException("Invalid withdraw amount");
        }
        if (cash > balance){
            throw new IllegalArgumentException("You can't withdraw more than you have");
        }
        balance -= cash;
        System.out.println("You withdrew " + cash + " from account " + accountNr);
        System.out.println("Your new balance is " + balance);
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNr() {
        return accountNr;
    }
}
