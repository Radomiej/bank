package pl.studia.bank.exception;

public class BankAccountException extends Exception {
    public BankAccountException(){}

    public BankAccountException(String message){
        super(message);
    }
}
