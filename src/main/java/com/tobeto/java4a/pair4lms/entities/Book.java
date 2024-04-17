package com.tobeto.java4a.pair4lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="available_quantity")
    private int availableQuantity;

    @Column(name="inventory_quantity")
    private int inventoryQuantity;

    @OneToMany(mappedBy = "book")
    private List<Borrowing> borrowings;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
