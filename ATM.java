
import java.util.Scanner;

class BankAccount {

    private String accountHolderName;
    private String accountNumber;
    private String accountPassword;
    private float accountBalance = 100000f;
    private int transactionCount = 0;
    private StringBuilder transactionHistory = new StringBuilder();

    public void registerAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Name - ");
        this.accountHolderName = sc.nextLine();
        System.out.print("\nEnter Your Account Number - ");
        this.accountNumber = sc.nextLine();
        System.out.print("\nEnter Your Password - ");
        this.accountPassword = sc.nextLine();
        while (accountPassword.length() < 6) {
            System.out.println("Enter a strong password (at least 6 characters)");
            this.accountPassword = sc.nextLine();
        }
        System.out.println("\nRegistration completed. Kindly login.");
    }

    public boolean loginAccount() {
        Scanner sc = new Scanner(System.in);
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.print("\nEnter Your Account Number - ");
            String inputAccountNumber = sc.nextLine();
            if (inputAccountNumber.equals(accountNumber)) {
                System.out.print("\nEnter Your Password - ");
                String inputPassword = sc.nextLine();
                if (inputPassword.equals(accountPassword)) {
                    System.out.print("\nLogin successful!!");
                    isLoggedIn = true;
                } else {
                    System.out.println("\nIncorrect Password");
                }
            } else {
                System.out.println("\nAccount Number not found");
            }
        }

        return isLoggedIn;
    }

    public void withdrawFunds() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the amount to withdraw - ");
        float withdrawalAmount = sc.nextFloat();

        if (accountBalance >= withdrawalAmount) {
            transactionCount++;
            accountBalance -= withdrawalAmount;
            System.out.println("\nWithdrawal Successful");
            String transaction = withdrawalAmount + " Rs Withdrawn\n";
            transactionHistory.append(transaction);
        } else {
            System.out.println("\nInsufficient Balance");
        }
    }

    public void depositFunds() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the amount to deposit - ");
        float depositAmount = sc.nextFloat();

        if (depositAmount <= 100000f) {
            transactionCount++;
            accountBalance += depositAmount;
            System.out.println("\nDeposit Successful");
            String transaction = depositAmount + " Rs deposited\n";
            transactionHistory.append(transaction);
        } else {
            System.out.println("\nSorry!!! Maximum deposit limit is 100,000.00");
        }
    }

    public void transferFunds() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Recipient's Name - ");
        String recipientName = sc.nextLine();
        System.out.print("Enter Recipient's Account Number - ");
        String recipientAccountNumber = sc.nextLine();
        System.out.print("Enter the amount to transfer - ");
        float transferAmount = sc.nextFloat();

        if (accountBalance >= transferAmount) {
            if (transferAmount <= 50000f) {
                transactionCount++;
                accountBalance -= transferAmount;
                System.out.println("\nSuccessfully Transferred to " + recipientName + " (Account No: " + recipientAccountNumber + ")");
                String transaction = transferAmount + " Rs transferred to " + recipientName + "\n";
                transactionHistory.append(transaction);
            } else {
                System.out.println("\nSorry!!! Maximum transfer limit is 50,000.00");
            }
        } else {
            System.out.println("\nInsufficient Balance");
        }
    }

    public void checkAccountBalance() {
        System.out.println("\nAccount Balance: " + accountBalance + " Rs");
    }

    public void viewTransactionHistory() {
        if (transactionCount == 0) {
            System.out.println("\nNo transaction history");
        } else {
            System.out.println("\nTransaction History:\n" + transactionHistory);
        }
    }

    public String getName() {
        return accountHolderName;
    }
}

public class ATM {
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input >= 1 && input <= limit) {
                    isValidInput = true;
                } else {
                    System.out.println("Choose a number between 1 and " + limit);
                }
            } catch (Exception e) {
                System.out.println("Enter only an integer value");
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n*********WELCOME ATM INTERFACE*********\n");
        System.out.println("1. Register \n2. Exit");
        System.out.print("Enter Your Choice - ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.registerAccount();

            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.print("Enter Your Choice - ");
                int ch = takeIntegerInput(2);

                if (ch == 1) {
                    if (bankAccount.loginAccount()) {
                        System.out.println("\n\n**********WELCOME BACK " + bankAccount.getName() + " **********\n");
                        boolean isFinished = false;

                        while (!isFinished) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            System.out.print("\nEnter Your Choice - ");
                            int c = takeIntegerInput(6);

                            switch (c) {
                                case 1:
                                    bankAccount.withdrawFunds();
                                    break;
                                case 2:
                                    bankAccount.depositFunds();
                                    break;
                                case 3:
                                    bankAccount.transferFunds();
                                    break;
                                case 4:
                                    bankAccount.checkAccountBalance();
                                    break;
                                case 5:
                                    bankAccount.viewTransactionHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}
