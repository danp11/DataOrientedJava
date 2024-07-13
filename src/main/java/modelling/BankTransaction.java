package modelling;

import java.time.LocalDate;
import java.util.Objects;

public record BankTransaction(LocalDate date,
                              TransDetails transDetails,
                              double debitAmt,
                              double creditAmt,
                              double accountBalance) {

    void process1() {
        switch (transDetails) {
            case TransDetails.POS pos -> print(pos.paidTo());
            case TransDetails.FD fd -> print(fd.AccountNumber());
            case TransDetails.ATMCash atmCash -> print(atmCash.city());
            case TransDetails.NEFT(
                    _,
                    var from,
                    var to,
                    _
            ) -> print(from.toUpperCase() + to.toUpperCase());
        }
    }

    void process2(BankTransaction transaction) {
        Objects.requireNonNull(transaction);
        switch (transaction.transDetails()) {
            case TransDetails.POS pos -> print(pos.paidTo());
            case TransDetails.FD fd -> print(fd.AccountNumber());
            case TransDetails.ATMCash atmCash -> print(atmCash.city());
            case TransDetails.NEFT(
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