
# Plan de Refactorisation : Remplacement de `Session` par `Mandat`

L'objectif de cette refactorisation est de remplacer l'entité `Session` par l'entité `Mandat` dans l'ensemble de l'application. Cette opération est critique et doit être menée avec précaution pour éviter toute régression.

## 1. Analyse d'Impact

La première étape consiste à identifier tous les points de l'application où l'entité `Session` est utilisée. Cela inclut :

*   Les services, contrôleurs, et repositories qui dépendent de `Session`.
*   Les requêtes de base de données (JPQL ou SQL natif) qui impliquent `Session`.
*   Les DTOs et les mappers qui utilisent `Session`.
*   Les tests unitaires et d'intégration qui reposent sur `Session`.

## 2. Création d'une Branche de Travail

Pour isoler cette refactorisation, il est impératif de créer une nouvelle branche `git`.

```bash
git checkout -b feature/refactor-session-to-mandat
```

## 3. Remplacement Progressif

Nous allons procéder au remplacement de manière progressive, module par module, pour minimiser les risques.

### 3.1. Mise à jour des Entités

*   Analyser les relations entre `Session` et les autres entités.
*   Modifier les entités qui ont une relation avec `Session` pour qu'elles pointent vers `Mandat`.
*   Supprimer l'entité `Session`.

### 3.2. Mise à jour des Repositories

*   Identifier tous les `SessionRepository` et leurs méthodes.
*   Remplacer les usages de `SessionRepository` par `MandatRepository`.
*   Adapter les méthodes de recherche pour qu'elles fonctionnent avec `Mandat`.

### 3.3. Mise à jour des Services

*   Modifier les services qui dépendent de `SessionService` ou `SessionRepository`.
*   Adapter la logique métier pour qu'elle utilise `Mandat` au lieu de `Session`.

### 3.4. Mise à jour des Contrôleurs

*   Modifier les points de terminaison de l'API qui exposent ou consomment des informations liées à `Session`.
*   Mettre à jour les DTOs pour qu'ils reflètent la structure de `Mandat`.

## 4. Mise à jour des Tests

*   Identifier tous les tests qui utilisent `Session`.
*   Mettre à jour les tests pour qu'ils fonctionnent avec `Mandat`.
*   Ajouter de nouveaux tests pour couvrir les nouvelles fonctionnalités liées à `Mandat`.

## 5. Validation et Nettoyage

*   Exécuter l'ensemble des tests (unitaires et d'intégration) pour s'assurer qu'aucune régression n'a été introduite.
*   Supprimer l'entité `Session`, son repository, son service, et ses DTOs.
*   Vérifier qu'il ne reste aucune référence à `Session` dans le code.

## 6. Pull Request

*   Une fois que toutes les étapes ont été complétées et validées, une Pull Request pourra être créée pour merger les changements dans la branche principale.
