package com.splitwise.backend.strategy;

import com.splitwise.backend.model.Expense;
import com.splitwise.backend.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdvanceSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUpGroup(List<Expense> expenses) {
        return null;
    }
}
