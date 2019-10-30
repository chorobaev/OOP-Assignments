public class Account {

    private int accountNumber;
    private double balance;

    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double credit) {
        balance += credit;
    }

    public void debit(double debit) {
        balance -= debit;
        if (balance < 0) {
            System.out.println("Amount withdrawn exceeds the current balance!");
        }
    }

    public void print() {
        double forPrintB = ((int) (balance * 100)) / 100.0;
        System.out.printf("A/C no: %d Balance=$%.2f", accountNumber, forPrintB);
    }
}
