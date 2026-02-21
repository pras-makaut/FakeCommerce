package com.example.FakeCommerce.Schema;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends BaseSchema {

    @Column(nullable = false)
    private String name;
}
