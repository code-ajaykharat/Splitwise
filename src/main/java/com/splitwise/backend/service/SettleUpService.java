package com.splitwise.backend.service;

import com.splitwise.backend.model.Expense;
import com.splitwise.backend.model.Group;
import com.splitwise.backend.model.Transaction;
import com.splitwise.backend.repository.ExpenseRepository;
import com.splitwise.backend.repository.GroupRepository;
import com.splitwise.backend.strategy.AdvanceSettleUpStrategy;
import com.splitwise.backend.strategy.SettleUpStrategyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;
    private SettleUpStrategyHandler settleUpStrategyHandler;

    @Autowired
    public SettleUpService(GroupRepository groupRepository, ExpenseRepository expenseRepository, SettleUpStrategyHandler settleUpStrategyHandler) {
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.settleUpStrategyHandler = settleUpStrategyHandler;
    }

    public List<Transaction> settleUpGroup(long groupId) {
        //1. check if group exists
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isEmpty()) {
            throw new RuntimeException("Group not found");
        }
        //2. set the strategy to be used at runtime
        settleUpStrategyHandler.setSettleUpStrategy(new AdvanceSettleUpStrategy());
        //3. fetch all expenses of the group
        List<Expense> expenses = expenseRepository.findAllByGroupId(groupId);
        //4. pass expense to the algorithm and get the transactions & return transactions
        return settleUpStrategyHandler.executeSettleUpGroupStrategy(expenses);
    }
}
