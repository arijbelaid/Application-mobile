# Projet Étudiants - API REST Spring Boot + Application Mobile Flutter

## 📋 Description

Ce projet consiste à développer une API REST avec Spring Boot pour gérer une liste d'étudiants, conteneurisée avec Docker, et une application mobile Flutter qui consomme cette API.

## 🛠️ Technologies utilisées

- **Backend**: Spring Boot 4.0.5, Java 21, Spring Data JPA, PostgreSQL
- **Conteneurisation**: Docker, Docker Compose
- **Frontend Mobile**: Flutter, Dart
- **Base de données**: PostgreSQL 15
- 
## 🚀 Installation et exécution

### Prérequis

- Docker et Docker Compose
- Flutter SDK (pour l'application mobile)
- Git
  
# Construire et démarrer les conteneurs
docker compose up --build -d

# Vérifier que les conteneurs sont en cours d'exécution
docker ps

# Voir les logs
docker logs -f etudiants-api

### Démarrer l'API avec Docker
# Construire et démarrer les conteneurs
docker compose up --build -d

# Vérifier que les conteneurs sont en cours d'exécution
docker ps

# Voir les logs
docker logs -f etudiants-api

### 📸 Captures d’exécution

Application Flutter - Liste des étudiants

https://execution1.png

https://execution2.png

https://execution3.png

### Projet Étudiants - API REST Spring Boot + Application Mobile Flutter - Partie 2

### 📋 Description

Cette partie consiste à enrichir le projet Spring Boot de la Partie 1 en ajoutant de la logique métier testée, une interface web légère, une image Docker publiée, un déploiement Kubernetes, et une architecture microservice plus complète avec cache, gestion des erreurs, documentation et traçabilité Jira.

### 🛠️ Technologies utilisées

Backend: Spring Boot 4.0.5, Java 21, Spring Data JPA, PostgreSQL, Redis

Tests BDD: Cucumber, JUnit 5

Conteneurisation: Docker, Docker Compose, Docker Hub

Orchestration: Kubernetes (K3S)

Frontend Mobile: Flutter, Dart

Documentation: Swagger/OpenAPI

Gestion de projet: Jira Scrum

Base de données: PostgreSQL 15

### 🚀 Installation et exécution

### Construire et publier l'image Docker

### Construction de l'image Docker

https://construire_image.png

### Publication de l'image sur Docker Hub

https://pousser_image.png

### verification 

https://verification.png

### Déployer sur Kubernetes (K3S)

kubectl apply -f k8s/postgres-deployment.yaml
kubectl apply -f k8s/etudiant-deployment.yaml
kubectl port-forward service/etudiant-service 8083:8080
