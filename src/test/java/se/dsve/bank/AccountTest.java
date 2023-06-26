package se.dsve.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AccountTest {
    private Account account;
    private double initialCash;
    private int accountNr;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        initialCash = 100;
        accountNr = 1234;
        account = new Account(initialCash, accountNr);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        initialCash = 0;
        accountNr = 0;
        account = null;
    }

    @ParameterizedTest
    @CsvSource({
            "1,1234,1",
            "100,2345, 100",
            "100.1,3456,100.1",
    })
    void createAccountPass(double initialCash, int accountNr, double output) {
        Account account = new Account(initialCash, accountNr);
        double result = account.getBalance();
        assertEquals(output, result);
    }

    @ParameterizedTest
    @CsvSource({
            "13,0",
            "13,-100",
    })
    void createAccountFailAccountNr(double initialCash, int accountNr) {
        assertThrows(IllegalAccountNrException.class, () -> {
            Account account = new Account(initialCash, accountNr);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "-1,13",
            "-100,13",
    })
    void createAccountFailInitialCash(double initialCash, int accountNr) {
        assertThrows(IllegalInitialCashException.class, () -> {
            Account account = new Account(initialCash, accountNr);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-100",
            "-10000",
            "-10001",
    })
    void initialCashIsNegativePass(double input) {
        assertTrue(account.initialCashIsNegative(input));
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "1",
            "100",
            "10000",
            "10001",
    })
    void initialCashIsNegativeFail(double input) {
        assertFalse(account.initialCashIsNegative(input));
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "-1",
    })
    void accountNrIsLessThanOnePass(int input) {
        assertTrue(account.accountNrIsLessThanOne(input));
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "100",
            "10000",
    })
    void accountNrIsLessThanOneFail(int input) {
        assertFalse(account.accountNrIsLessThanOne(input));
    }

    @ParameterizedTest
    @CsvSource({
            "1,101",
            "100,200",
            "100.1,200.1",
            "10000,10100"
    })
    void depositPass(double input, double output) {
        account.deposit(input);
        double result = account.getBalance();
        assertEquals(output, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "-100",
            "0.9",
            "-0.1",
    })
    void depositFailMin(double input) {
        Assertions.assertThrows(IllegalCashIsLessThanMinTransactionException.class, () -> {
            account.deposit(input);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "10001",
            "100000",
            "1000000",
    })
    void depositFailMax(double input) {
        Assertions.assertThrows(IllegalCashIsMoreThanMaxTransactionException.class, () -> {
            account.deposit(input);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "0.99",
            "0",
            "-1",
    })
    void cashIsLessThanMinTransactionPass(double input) {
        assertTrue(account.cashIsLessThanMinTransaction(input));
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "10",
            "100",
            "1000",
    })
    void cashIsLessThanMinTransactionFail(double input) {
        assertFalse(account.cashIsLessThanMinTransaction(input));
    }

    @ParameterizedTest
    @CsvSource({
            "10001",
            "100000",
            "1000000",
    })
    void cashIsMoreThanMaxTransactionPass(double input) {
        assertTrue(account.cashIsMoreThanMaxTransaction(input));
    }

    @ParameterizedTest
    @CsvSource({
            "10000",
            "9999",
            "1000",
            "100",
            "10",
            "1",
            "0",
            "-1",
            "-100",
            "-1000",
            "-10000",
    })
    void cashIsMoreThanMaxTransactionFail(double input) {
        assertFalse(account.cashIsMoreThanMaxTransaction(input));
    }

    @org.junit.jupiter.api.Test
    void transactionActionDeposit() {
        String[] action = new String[]{"deposited", "to"};
        String result = account.transactionAction(initialCash, action, accountNr);
        String expected = "You " + action[0] + " " + initialCash + " " + action[1] + " account " + accountNr;
        assertEquals(expected, result);
    }

    @org.junit.jupiter.api.Test
    void transactionActionWithdraw() {
        String[] action = new String[]{"deposited", "to"};
        String result = account.transactionAction(initialCash, action, accountNr);
        String expected = "You " + action[0] + " " + initialCash + " " + action[1] + " account " + accountNr;
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "1,99",
            "100,0",
            "10000,100",
    })
    void withdrawPass(double input, double output) {
        if (input == 10000) {
            account.deposit(10000);
        }
        account.withdraw(input);
        double result = account.getBalance();
        assertEquals(output, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "-100",
            "0.9",
            "-0.1",
    })
    void withdrawFailMin(double input) {
        Assertions.assertThrows(IllegalCashIsLessThanMinTransactionException.class, () -> {
            account.withdraw(input);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "10001",
    })
    void withdrawFailMax(double input) {
        Assertions.assertThrows(IllegalCashIsMoreThanMaxTransactionException.class, () -> {
            account.withdraw(input);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "100.1",
            "101",
            "10000",
    })
    void cashIsMoreThanBalancePass(double input) {
        assertTrue(account.cashIsMoreThanBalance(input));
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "0.99",
            "1",
    })
    void cashIsMoreThanBalanceFail(double input) {
        assertFalse(account.cashIsMoreThanBalance(input));
    }

    @org.junit.jupiter.api.Test
    void getBalance() {
        assertEquals(100, account.getBalance());
    }

    @org.junit.jupiter.api.Test
    void getAccountNr() {
        assertEquals(1234, account.getAccountNr());
    }
}