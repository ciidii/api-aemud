# AEMUD API - Application de Gestion d'Association

Ce projet est une application web complète conçue pour la gestion des membres, des contributions et des activités de l'association des étudiants et diplômés de l'Université de Dschang (AEMUD).

L'application est construite sur une architecture moderne avec un backend **Spring Boot** qui expose une API REST sécurisée, et un frontend **Angular** intégré dans le même livrable.

## ✨ Fonctionnalités Principales

- **Gestion des Membres** : Inscription, authentification, et gestion des profils des membres.
- **Suivi des Contributions** : Système de suivi des paiements mensuels des membres.
- **Sécurité** : Authentification basée sur les tokens JWT (JSON Web Tokens) avec Spring Security.
- **Gestion des Clubs et Commissions** : Organisation des différentes entités de l'association.
- **Notifications** : Système de notification pour les membres.
- **Déploiement Facilité** : Conteneurisation avec Docker pour un déploiement simple et reproductible.

## 🛠️ Stack Technique

| Domaine | Technologies |
| :--- | :--- |
| **Backend** | Java, Spring Boot, Spring Security (JWT), Spring Data JPA (Hibernate) |
| **Frontend** | Angular (servi via `static/`) |
| **Base de données** | PostgreSQL (ou autre SGBDR), **Flyway** pour les migrations |
| **Build & Dépendances** | **Maven** |
| **DevOps** | **Docker**, Docker Compose |
| **Qualité de Code** | **Qodana** (analyse statique) |

## 🚀 Démarrage Rapide

Pour lancer le projet en local, assurez-vous d'avoir les prérequis installés, puis suivez ces étapes.

### Prérequis

- Java Development Kit (JDK) 17 ou supérieur
- Apache Maven
- Docker et Docker Compose (recommandé)

### Configuration

1.  **Cloner le dépôt :**
    ```bash
    git clone [URL_DU_DEPOT]
    cd aemud-api
    ```

2.  **Configurer les variables d'environnement :**
    Le projet utilise des fichiers `.env` pour gérer la configuration. Créez un fichier `.env.dev` à la racine du projet en vous basant sur les fichiers existants.
    ```properties
    # Exemple de variables pour .env.dev
    DB_URL=jdbc:postgresql://localhost:5432/aemud_db
    DB_USER=votre_user
    DB_PASSWORD=votre_mot_de_passe
    JWT_SECRET=votre_cle_secrete_pour_jwt
    ```

### Lancement

#### Option 1 : Via le script de démarrage (Windows)

Exécutez le script fourni pour lancer l'application en mode développement.
```bash
start-dev.bat
```

#### Option 2 : Via Maven

Vous pouvez également lancer l'application directement avec Maven.
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Option 3 : Via Docker (Recommandé)

Utilisez Docker Compose pour construire et lancer l'application et sa base de données.
```bash
docker-compose -f Docker-compose.yaml up --build
```

Une fois l'application lancée, l'API sera accessible sur `http://localhost:8080`.

## 🏛️ Structure du Projet

```
.
├── Dockerfile              # Fichier pour construire l'image Docker de l'application
├── Docker-compose.yaml     # Orchestration du conteneur de l'app et de la BDD
├── pom.xml                 # Fichier de configuration Maven
├── src/
│   ├── main/
│   │   ├── java/           # Code source Java (Spring Boot)
│   │   │   └── org/aemudapi
│   │   │       ├── activity
│   │   │       ├── club
│   │   │       ├── contribution
│   │   │       ├── member
│   │   │       └── security    # Logique d'authentification JWT
│   │   └── resources/
│   │       ├── db/migration/ # Scripts de migration Flyway (SQL)
│   │       ├── static/       # Fichiers du frontend Angular
│   │       └── application.yaml # Configuration Spring Boot
│   └── test/                 # Tests unitaires et d'intégration
└── ...
```

---
*Ce projet a été développé dans le but de mettre en pratique des compétences en développement backend avec Java/Spring Boot et de comprendre les enjeux d'une application full-stack.*
