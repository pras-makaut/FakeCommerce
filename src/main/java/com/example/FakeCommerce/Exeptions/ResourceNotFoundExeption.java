package com.example.FakeCommerce.Exeptions;

public class ResourceNotFoundExeption extends RuntimeException{

    public ResourceNotFoundExeption(String message){
        super(message);
    }
}
