// Skapa ett litet program och använda TDD med JUnit för att säkerställa att det fungerar som förväntat.
//
//Skapa en class som heter Bank och i den ska du ha följande metoder
//Constructor (initialCash, Accountnr)
//deposit(cash)
//widthdraw(cash)
//getBalance()
//getAccountNr()
//Skapa nödvändiga tester för att försäkra dig om att kontot inte får felaktiga inmatningar och inte övertrasseras

package se.dsve.bank;

public class Account {
    private double balance;
    private final int accountNr;

    final private double maxTransaction = 10000.0;
    final private double minTransaction = 1.0;

    public Account(double initialCash, int accountNr) {
        if (initialCashIsNegative(initialCash)) {
            throw new IllegalInitialCashException("You can't deposit a negative amount");
        }
        if (accountNrIsLessThanOne(accountNr)) {
            throw new IllegalAccountNrException("You can't have a negative account number");
        }
        this.balance = initialCash;
        this.accountNr = accountNr;
    }

    protected boolean initialCashIsNegative(double initialCash) {
        return initialCash < 0;
    }

    protected boolean accountNrIsLessThanOne(int accountNr) {
        return accountNr < 1;
    }

    public void deposit(double cash) {
        if (cashIsLessThanMinTransaction(cash)) {
            throw new IllegalCashIsLessThanMinTransactionException("You can't deposit less than " + minTransaction);
        }
        if (cashIsMoreThanMaxTransaction(cash)) {
            throw new IllegalCashIsMoreThanMaxTransactionException("You can't deposit more than " + maxTransaction);
        }
        balance += cash;
//        System.out.println("You deposited " + cash + " to account " + accountNr);
        printToConsole(transactionAction(cash, new String[]{"deposited", "to"}, accountNr));
        newBalanceIs();
    }

    protected void printToConsole(String message) {
        System.out.println(message);
    }

    protected boolean cashIsLessThanMinTransaction(double cash) {
        return cash < minTransaction;
    }

    protected boolean cashIsMoreThanMaxTransaction(double cash) {
        return cash > maxTransaction;
    }

    protected String transactionAction(double cash, String[] action, int accountNr) {
        return "You " + action[0] + " " + cash + " " + action[1] + " account " + accountNr;
    }

    private void newBalanceIs() {
        printToConsole("Your new balance is " + getBalance());
    }

    public void withdraw(double cash) {
        if (cashIsLessThanMinTransaction(cash)) {
            throw new IllegalCashIsLessThanMinTransactionException("You can't withdraw less than " + minTransaction);
        }
        if (cashIsMoreThanMaxTransaction(cash)) {
            throw new IllegalCashIsMoreThanMaxTransactionException("You can't withdraw more than " + maxTransaction);
        }
        if (cashIsMoreThanBalance(cash)) {
            throw new IllegalCashIsMoreThanBalanceException("You can't withdraw more than you have");
        }
        balance -= cash;
//        System.out.println("You withdrew " + cash + " from account " + accountNr);
        printToConsole(transactionAction(cash, new String[]{"withdrew", "from"}, accountNr));
        newBalanceIs();
    }



    protected boolean cashIsMoreThanBalance(double cash) {
        return cash > balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNr() {
        return accountNr;
    }
}
