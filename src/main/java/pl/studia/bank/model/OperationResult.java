package pl.studia.bank.model;

import lombok.Data;

@Data
public class OperationResult<T> {
    boolean isSuccess;
    T data;


    //TODO ...
}
