package com.assignment1.expensemanagerapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assignment1.expensemanagerapp.entity.Expense;
import com.assignment1.expensemanagerapp.service.ExpenseService;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the ExpenseController which handles the REST API
 * endpoints for managing expenses.
 */
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    /**
     * Constructs a new ExpenseController with the specified ExpenseService.
     *
     * @param expenseService the ExpenseService used to perform expense-related
     *                       operations
     */
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /**
     * Retrieves all expenses.
     *
     * @return a list of all expenses
     */
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    /**
     * Retrieves an expense by its ID.
     *
     * @param id the ID of the expense to retrieve
     * @return the expense with the specified ID if found, otherwise returns a 404
     *         Not Found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        return expense.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Creates a new expense.
     *
     * @param expense the expense to create
     * @return the created expense with a 201 Created response
     */
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }

    /**
     * Updates an existing expense.
     *
     * @param id             the ID of the expense to update
     * @param updatedExpense the updated expense
     * @return the updated expense with a 200 OK response if successful, otherwise
     *         returns a 404 Not Found response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        try {
            Expense expense = expenseService.updateExpense(id, updatedExpense);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an expense by its ID.
     *
     * @param id the ID of the expense to delete
     * @return a message indicating the deletion status with a 202 Accepted response
     *         if successful, otherwise returns a 404 Not Found response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return new ResponseEntity<>("Expense with ID " + id + " has been deleted", HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Expense with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
