package com.example.coffee_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.coffee_service.model.Coffee;

@Service
public class CoffeeService {
    private final List<Coffee> coffeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public CoffeeService() {
        // ใส่ข้อมูลเริ่มต้น 2 รายการตามที่โจทย์กำหนด
        addCoffee(new Coffee(null, "Espresso", 45.0));
        addCoffee(new Coffee(null, "Latte", 55.0));
    }

    public List<Coffee> getAllCoffees() {
        return coffeeList;
    }

    public Optional<Coffee> getCoffeeById(Long id) {
        return coffeeList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Coffee addCoffee(Coffee coffee) {
        coffee.setId(idCounter.getAndIncrement());
        coffeeList.add(coffee);
        return coffee;
    }

    public Optional<Coffee> updateCoffee(Long id, Coffee updatedCoffee) {
        return getCoffeeById(id).map(existingCoffee -> {
            existingCoffee.setName(updatedCoffee.getName());
            existingCoffee.setPrice(updatedCoffee.getPrice());
            return existingCoffee;
        });
    }

    public boolean deleteCoffee(Long id) {
        return coffeeList.removeIf(c -> c.getId().equals(id));
    }
}