package com.example.seminar322;

import com.example.seminar322.Domain.Person;
import com.example.seminar322.Repository.IRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {
    public ListView<Person> listPersons;
    public TextField txtId;
    public TextField txtNume;
    public Button btnAdd;

    private IRepository<Person> repo;

    ObservableList<Person> personList = FXCollections.observableList(new ArrayList<Person>());

    public void loadPersons() {

        // sablonul de proiectare factory method https://refactoring.guru/design-patterns/factory-method
        // sablonul de proiectare wrapper https://refactoring.guru/design-patterns/adapter
//        var personList = FXCollections.observableList(new ArrayList<Person>());

        // aici setam lista ca si sursa de date pentru componenta grafica
        // componenta grafica se aboneaza la modificarile din lista "personList"
        // sablonul de proiectare observer https://refactoring.guru/design-patterns/observer
        listPersons.setItems(personList);


        for (var p : repo) {
            // lista de persoane notifica componenta grafica ca si-a schimbat continutul
            personList.add(p);
        }
    }

    public void setRepository(IRepository<Person> repo) {
        this.repo = repo;
    }

    public void btnAddPressed(ActionEvent actionEvent) {
        // TODO Error handling
        var personId = Integer.parseInt(txtId.getText());
        var personName = txtNume.getText();

        Person p = new Person(personId, personName);
        try {
            repo.add(p);
            personList.add(p);
        } catch (RepositoryException e) {
            Alert hopa = new Alert(Alert.AlertType.ERROR, e.getMessage());
            hopa.show();
        }


    }


    public void btnRemovePressed(ActionEvent actionEvent) {
        var personId = Integer.parseInt(txtId.getText());
        var personName = txtNume.getText();

        Person p = new Person(personId, personName);

        try {
            repo.remove(p.getId());
            personList.removeIf(person -> person.getId() == p.getId());
        } catch (RepositoryException e) {
            Alert hopa = new Alert(Alert.AlertType.ERROR, e.getMessage());
            hopa.show();
        }
    }

    public void btnUpdatePressed(ActionEvent actionEvent) {
        try {
            int personId = Integer.parseInt(txtId.getText()); // Get the ID from the text field.
            String newName = txtNume.getText(); // Get the new name from the text field.

            // Create a new Person object with the updated details.
            Person updatedPerson = new Person(personId, newName);

            // Update the person in the repository.
            repo.update(personId, updatedPerson);

            // Update the person in the observable list.
            for (Person p : personList) {
                if (p.getId() == personId) {
                    // Update the existing person's details.
                    p.setName(newName); // Assuming you have a setName method in your Person class.
                    break; // Exit the loop once updated.
                }
            }

            // Optionally, you can provide feedback to the user.
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person updated successfully!");
            alert.show();

        } catch (NumberFormatException e) {
            // Handle the case where the ID cannot be parsed to an integer.
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid ID format!");
            alert.show();
        }
    }
}