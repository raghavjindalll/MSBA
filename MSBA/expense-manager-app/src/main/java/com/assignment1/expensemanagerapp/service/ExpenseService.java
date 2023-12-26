package com.assignment1.expensemanagerapp.service;

import org.springframework.stereotype.Service;

import com.assignment1.expensemanagerapp.entity.Expense;
import com.assignment1.expensemanagerapp.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the service layer for managing expenses.
 * It provides methods for retrieving, creating, updating, and deleting
 * expenses.
 */
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    /**
     * Constructs a new ExpenseService with the specified ExpenseRepository.
     * 
     * @param expenseRepository the repository used for accessing expense data
     */
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * Retrieves all expenses.
     * 
     * @return a list of all expenses
     */
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    /**
     * Retrieves an expense by its ID.
     * 
     * @param id the ID of the expense to retrieve
     * @return an Optional containing the expense, or an empty Optional if not found
     */
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    /**
     * Creates a new expense.
     * 
     * @param expense the expense to create
     * @return the created expense
     */
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    /**
     * Updates an existing expense.
     * 
     * @param id             the ID of the expense to update
     * @param updatedExpense the updated expense data
     * @return the updated expense
     * @throws IllegalArgumentException if the expense with the specified ID is not
     *                                  found
     */
    public Expense updateExpense(Long id, Expense updatedExpense) {
        if (expenseRepository.existsById(id)) {
            updatedExpense.setId(id);
            return expenseRepository.save(updatedExpense);
        } else {
            throw new IllegalArgumentException("Expense with ID " + id + " not found");
        }
    }

    /**
     * Deletes an expense by its ID.
     * 
     * @param id the ID of the expense to delete
     * @throws IllegalArgumentException if the expense with the specified ID is not
     *                                  found
     */
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id))
            throw new IllegalArgumentException("Expense with ID " + id + " not found");
        expenseRepository.deleteById(id);
    }
}
