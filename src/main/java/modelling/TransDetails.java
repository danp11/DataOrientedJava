package modelling;

import java.time.LocalDate;
import java.time.LocalTime;

sealed public interface TransDetails {


record POS(String paidTo,
           String city,
           LocalDate date,
           LocalTime time)
        implements TransDetails {
}

record NEFT(String referenceNo,
            String accountFrom,
            String accountTo,
            String desc)
        implements TransDetails {
}

record ATMCash(String ATMCode,
               String transCode,
               LocalDate date,
               LocalTime time,
               String city)
        implements TransDetails {
}

record FD(String AccountNumber)
        implements TransDetails {
}

}