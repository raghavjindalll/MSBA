package com.assignment1.expensemanagerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment1.expensemanagerapp.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Additional query methods can be added here if needed
}
