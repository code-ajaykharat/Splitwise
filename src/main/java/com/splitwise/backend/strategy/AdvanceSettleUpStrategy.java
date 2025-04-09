package com.splitwise.backend.strategy;

import com.splitwise.backend.model.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AdvanceSettleUpStrategy implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUpGroup(List<Expense> expenses) {
        //step-0: check if expenses are empty
        if (expenses == null || expenses.isEmpty()) {
            return Collections.emptyList();
        }
        //step-1: create a hashmap for USER & AMOUNT from all expenses
        HashMap<User, Double> userAmountMap = new HashMap<>();
        for(Expense expense: expenses){
            for(UserExpense ue: expense.getUserExpenses()){
                double signedAmount = ue.getUserExpenseType().equals(UserExpenseType.PAID_BY) ? ue.getAmount() : -ue.getAmount();
                userAmountMap.put(ue.getUser(), userAmountMap.getOrDefault(ue.getUser(), 0.0) + signedAmount);
            }
        }

        //step-2: create max & min heap for above expenses
        PriorityQueue<Map.Entry<User, Double>> positiveHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getValue(), a.getValue())
        );
        PriorityQueue<Map.Entry<User, Double>> negativeHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(a.getValue(), b.getValue())
        );

        //step-3: add all expenses to respective heaps
        for(Map.Entry<User, Double> entry: userAmountMap.entrySet()){
            if(entry.getValue() > 0){
                positiveHeap.offer(entry);
            } else if(entry.getValue() < 0){
                negativeHeap.offer(entry);
            }
        }

        //step-4: create transactions
        List<Transaction> transactions = new ArrayList<>();
        while(!positiveHeap.isEmpty() && !negativeHeap.isEmpty()){
            //extract top entry from both heaps
            Map.Entry<User, Double> positiveEntry = positiveHeap.poll();
            Map.Entry<User, Double> negativeEntry = negativeHeap.poll();

            //calculate settled amount
            double settledAmount = Math.min(positiveEntry.getValue(), -negativeEntry.getValue());

            //create transaction & add to transactions
            Transaction transaction = new Transaction();
            transaction.setUserFrom(negativeEntry.getKey());
            transaction.setUserTo(positiveEntry.getKey());
            transaction.setAmount(settledAmount);
            transactions.add(transaction);

            //update the heaps
            positiveEntry.setValue(positiveEntry.getValue() - settledAmount);
            negativeEntry.setValue(negativeEntry.getValue() + settledAmount);

            if(positiveEntry.getValue() > 0){
                positiveHeap.offer(positiveEntry);
            }
            if(negativeEntry.getValue() < 0){
                negativeHeap.offer(negativeEntry);
            }
        }
        return transactions;
    }
}
