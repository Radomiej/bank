package pl.studia.bank.exception;

public class BankCreditException extends Exception {
    public BankCreditException(){}

    public BankCreditException(String message){
        super(message);
    }
}
