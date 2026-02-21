package com.example.FakeCommerce.Schema;


import jakarta.persistence.*;
import lombok.*;

@Data
@MappedSuperclass
public class BaseSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
