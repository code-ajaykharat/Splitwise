package com.splitwise.backend.strategy;

import com.splitwise.backend.model.Expense;
import com.splitwise.backend.model.Transaction;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUpGroup(List<Expense> expenses);
}
