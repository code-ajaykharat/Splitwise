package com.splitwise.backend.strategy;

import com.splitwise.backend.model.Expense;
import com.splitwise.backend.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUpGroup(List<Expense> expenses) {
        for(Expense expense: expenses) {
            System.out.println("Settling up expense: " + expense);
        }
        return null;
    }

}
