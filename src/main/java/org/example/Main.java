package org.example;

import org.example.service.AnimalMenuService;
import org.example.service.AnimalService;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        AnimalMenuService animalMenuService = new AnimalMenuService(scanner);
        Map<String, Executor> orchestrator = init(animalMenuService);
        animalMenuService.main();
        String command;
        do {
            command = scanner.next();

            orchestrator
                    .getOrDefault(command, () -> System.out.println("Incorrect command"))
                    .execute();
            animalMenuService.taskIsCompleted();
        } while (!command.equals("exit"));

        scanner.close();
    }

    private static Map<String, Executor> init(AnimalMenuService animalMenuService) {
        AnimalService animalService = new AnimalService(animalMenuService);
        return Map.of(
                "leave", animalService.addAnimal(),
                "take",animalService.removeAnimal(),
                "show",animalService.listAnimal(),
                "save",animalService.save(),
                "exit", animalService.exit()
        );
    }
}