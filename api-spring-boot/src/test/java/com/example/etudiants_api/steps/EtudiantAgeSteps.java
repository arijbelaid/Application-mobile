package com.example.etudiants_api.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.example.etudiants_api.entity.Etudiant;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EtudiantAgeSteps {

    private Etudiant etudiant;
    private int ageCalcule;

    @Given("un étudiant avec la date de naissance {string}")
    public void un_etudiant_avec_date_naissance(String dateStr) {
        LocalDate dateNaissance = LocalDate.parse(dateStr);
        etudiant = new Etudiant(null, "123456", "Test", dateNaissance);
    }

    @Given("un étudiant sans date de naissance")
    public void un_etudiant_sans_date_naissance() {
        etudiant = new Etudiant(null, "123456", "Test", null);
    }

    @When("on calcule son âge")
    public void on_calcule_son_age() {
        ageCalcule = etudiant.age();
    }

    @Then("l'âge retourné doit être {int}")
    public void l_age_retourne_doit_etre(int ageAttendu) {
        assertEquals(ageAttendu, ageCalcule);
    }
}