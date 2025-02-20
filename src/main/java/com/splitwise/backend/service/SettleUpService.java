package com.splitwise.backend.service;

import com.splitwise.backend.model.Expense;
import com.splitwise.backend.model.Group;
import com.splitwise.backend.model.Transaction;
import com.splitwise.backend.repository.ExpenseRepository;
import com.splitwise.backend.repository.GroupRepository;
import com.splitwise.backend.strategy.BasicSettleUpStrategy;
import com.splitwise.backend.strategy.SettleUpStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;
    private SettleUpStrategyContext settleUpStrategyContext;

    @Autowired
    public SettleUpService(GroupRepository groupRepository, ExpenseRepository expenseRepository, SettleUpStrategyContext settleUpStrategyContext) {
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.settleUpStrategyContext = settleUpStrategyContext;
    }
    public List<Transaction> settleUp(long groupId) {
        //1. check if group exists
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if(groupOptional.isEmpty()) {
            throw new RuntimeException("Group not found");
        }
        //2. fetch all expenses of the group
        List<Expense> expenses = expenseRepository.findAllByGroupId(groupId);
        //3. pass expense to the algorithm and get the transactions
        settleUpStrategyContext.setSettleUpStrategy(new BasicSettleUpStrategy());
        //4. return transactions
        return settleUpStrategyContext.executeSettleUpGroupStrategy(expenses);
    }
}
