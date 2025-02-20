package com.splitwise.backend.strategy;

import com.splitwise.backend.model.Expense;
import com.splitwise.backend.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpStrategyContext {
    private SettleUpStrategy settleUpStrategy;//holds the strategy object

    public void setSettleUpStrategy(SettleUpStrategy settleUpStrategy) {
        this.settleUpStrategy = settleUpStrategy;
    }

    public SettleUpStrategy getSettleUpStrategy() {
        return settleUpStrategy;
    }

    public List<Transaction> executeSettleUpGroupStrategy(List<Expense> expenses) {
        return settleUpStrategy.settleUpGroup(expenses);
    }
}
