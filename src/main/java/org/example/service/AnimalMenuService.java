package org.example.service;

import org.example.model.Animal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class AnimalMenuService {

    private final Scanner scanner;

    public AnimalMenuService(Scanner scanner) {
        this.scanner = scanner;
    }

    private final List<String> commands = Arrays.asList("leave", "take", "show");

    public void main(){
        System.out.println("welcome,\n -> " + String.join("\n -> ", commands));
    }

    public Animal leave(){
        Animal.AnimalBuilder builder = Animal.builder();
        System.out.println("Please enter the name: ");
        builder.name(scanner.next());
        System.out.println("age: ");
        builder.age(scanner.nextInt());
        System.out.println("type: ");
        builder.type(scanner.next());
        System.out.println("bread: ");
        builder.bread(scanner.next());
        return builder.build();
    }

    public String take(){
        System.out.println("type animal name to take it from the shelter: ");
        return scanner.next();
    }

    public void taskIsCompleted(){
        System.out.println("Successfully done!");
    }

}
