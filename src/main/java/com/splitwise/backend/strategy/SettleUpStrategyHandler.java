package com.splitwise.backend.strategy;

import com.splitwise.backend.model.Expense;
import com.splitwise.backend.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
public class SettleUpStrategyHandler {
    private SettleUpStrategy settleUpStrategy;//holds the strategy object at RUNTIME

    public List<Transaction> executeSettleUpGroupStrategy(List<Expense> expenses) {
        return settleUpStrategy.settleUpGroup(expenses);
    }
}
