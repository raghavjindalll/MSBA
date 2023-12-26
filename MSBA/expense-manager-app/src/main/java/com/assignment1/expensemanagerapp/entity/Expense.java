package com.assignment1.expensemanagerapp.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Represents an expense in the expense manager application.
 */
@Entity
@Table(name = "expenses")
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private Date date;

    private String color;

    private String url;

    private String remarks;
}
