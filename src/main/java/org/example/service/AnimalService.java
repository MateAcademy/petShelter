package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.Executor;
import org.example.model.Animal;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AnimalService {
    private ObjectMapper mapper;
    private AnimalMenuService menuService;
    private File vault = new File("src/main/resources/vault.json");
    private List<Animal> animals;

    public AnimalService(AnimalMenuService animalMenuService) {
        this.menuService = animalMenuService;
        this.mapper = new JsonMapper();
        CollectionType animals = mapper.getTypeFactory()
                .constructCollectionType(List.class, Animal.class);

        try {
            if (!vault.exists()) vault.createNewFile();

            this.animals = mapper.readValue(vault, animals);

        } catch (IOException e) {
            System.err.println("cannot get animal data");
            e.printStackTrace();
            System.exit(1);
        }

    }

    public Executor addAnimal() {
        return () -> this.animals.add(menuService.leave());
    }


    public Executor removeAnimal() {
        return () -> {
            String name = menuService.take();
            animals.removeIf( animal -> Objects
                    .equals(name, animal.getName()));
        };
    }

    public Executor listAnimal() {
        return () ->  this.animals.forEach(System.out::println);
    }

    public Executor save() {
        return () -> {

            try {
                mapper.writeValue(vault, animals);
            } catch (IOException e) {
                System.err.println("cannot save animals");
                e.printStackTrace();
            }
        };
    }

    public Executor exit(){
        return save();
    }
}
