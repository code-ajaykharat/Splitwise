package com.splitwise.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends Base{
    private String name;
    private String description;
    private double amount;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Group group;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "expense")
    private List<UserExpense> userExpenses;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;
}
