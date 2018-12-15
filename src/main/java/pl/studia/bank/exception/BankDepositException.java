package pl.studia.bank.exception;

public class BankDepositException extends Exception {
    public BankDepositException(){}

    public BankDepositException(String message){
        super(message);
    }
}
