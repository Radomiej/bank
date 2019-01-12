package pl.studia.bank.service;

import org.springframework.stereotype.Service;
import pl.studia.bank.helper.BigDecimalFactory;
import pl.studia.bank.helper.BigDecimalOperation;
import pl.studia.bank.helper.Capitalization;
import pl.studia.bank.model.Interest;

import java.math.BigDecimal;

@Service
public class PercentageService {
    public BigDecimal computePaymentAmount(BigDecimal value, Interest interest){
        int chunkCount = interest.getChunk();
        BigDecimal percentage = BigDecimalOperation.INSTANCE.add(BigDecimalFactory.INSTANCE.produceFromDouble(interest.getValue()), BigDecimalFactory.INSTANCE.getOneValue());
        BigDecimal result = BigDecimalFactory.INSTANCE.copy(value);
        for(int i = 0; i < chunkCount; i++) {
            result = BigDecimalOperation.INSTANCE.multiply(result, percentage);
        }

        return result;
    }
}
