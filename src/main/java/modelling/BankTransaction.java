package modelling;

import java.time.LocalDate;
import java.util.Objects;

public record BankTransaction(LocalDate date,
                              TransDetails transDetails,
                              double debitAmt,
                              double creditAmt,
                              double accountBalance) {

    void process(BankTransaction transaction) {
        Objects.requireNonNull(transaction);
        switch (transaction.transDetails()) {
            case POS pos -> print(pos.paidTo());
            case FD fd -> print(fd.AccountNumber());
            case ATMCash atmCash -> print(atmCash.city());
            case NEFT(
                    _,
                    var from,
                    var to,
                    _
            ) -> print(from.toUpperCase() + to.toUpperCase());
        }
    }

    private void print(String str) {
        System.out.println(str);
    }
}