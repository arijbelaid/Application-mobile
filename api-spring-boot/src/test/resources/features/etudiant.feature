Feature: Calcul de l'âge d'un étudiant

  Scenario: Étudiant né il y a exactement 22 ans
    Given un étudiant avec la date de naissance "2004-04-07"
    When on calcule son âge
    Then l'âge retourné doit être 22

  Scenario: Étudiant né il y a 25 ans et 6 mois
    Given un étudiant avec la date de naissance "2000-10-07"
    When on calcule son âge
    Then l'âge retourné doit être 25

  Scenario: Étudiant avec date de naissance nulle
    Given un étudiant sans date de naissance
    When on calcule son âge
    Then l'âge retourné doit être 0