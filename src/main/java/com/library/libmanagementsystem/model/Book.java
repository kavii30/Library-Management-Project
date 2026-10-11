package com.library.libmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn( nullable = false)
    private Genre genre;

    private String publisher;

    private LocalDate publishedDate;

    private String language;

    private Integer pages;

    private String description;

    @Column(nullable = false)
    private Integer totalCopies;

    private BigDecimal price;

    private String coverImageUrl;

    @Column(nullable = false)
    private Boolean active=true;

    @CreationTimestamp
    @Column(nullable=false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable=false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer availableCopies;

    @AssertTrue(message= "Available copies cannot exceed total copies")
    public Boolean isAvailableCopiesValid(){
        if(totalCopies==null|| availableCopies ==null){
            return true;
        }
        else{
            return availableCopies<=totalCopies;
        }
        //if (dto.getAvailableCopies() > dto.getTotalCopies()) {
        //    throw new BookException("Available copies cannot exceed total copies");
        //} we can use this also in service class if we do not want headache.
    }

}
