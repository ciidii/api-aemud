# Gestion du Cycle de Vie des Membres

La gestion du cycle de vie d'un membre (nouveau, actif, ancien) est fondamentale pour une association. Cela permet une meilleure gestion administrative et ouvre la porte à des actions ciblées (relances, networking pour les anciens, intégration des nouveaux).

En tant qu'ingénieur logiciel, l'objectif est de modéliser cela de la manière la plus simple, robuste et automatisée possible.

## Le Principe Clé : Statut Dérivé vs. Statut Permanent

Il faut distinguer deux types de statuts :

1.  **Le statut d'activité (Actif, Inactif)** : C'est un état *dynamique* qui dépend du temps. Un membre est actif *maintenant*, mais ne le sera peut-être plus l'année prochaine. Il ne faut **surtout pas** stocker ce type de statut directement dans la table `Member` (par exemple, avec un champ `boolean estActif`), car cela obligerait à des mises à jour manuelles ou des scripts complexes et sources d'erreurs à chaque changement de mandat. Le statut doit être **calculé (ou "dérivé")** en temps réel.

2.  **Le statut de cycle de vie (Alumni, Suspendu)** : C'est un état *administratif* plus permanent, qui change rarement. Celui-ci **peut et doit** être stocké sur la fiche du membre pour tracer son parcours au sein de l'association.

---

### 1. Membre "Actif" et "Nouveau Adhérent" (Statuts Dérivés)

Ces deux statuts sont **dynamiques** et dépendent du contexte du `Mandat` en cours.

*   **Comment définir un membre "Actif" ?**
    Un membre est considéré comme "Actif" s'il possède une `Registration` pour le `Mandat` actuellement actif (`mandat.estActif = true`). C'est une simple requête à faire. On peut même affiner en disant qu'il doit avoir payé sa cotisation (`registration.statusPayment = true`).

*   **Comment définir un "Nouveau Adhérent" ?**
    C'est déjà dans notre modèle ! Un membre est un "Nouveau Adhérent" *pour un mandat donné* si son entité `Registration` pour ce mandat a le champ `registrationType` égal à `INITIAL`.

#### Avantage de cette approche :
*   **Zéro maintenance** : Le statut d'un membre (actif/inactif) se met à jour "tout seul" lorsque le mandat change. Pas de script à exécuter, pas d'oubli possible.
*   **Cohérence garantie** : Le statut est toujours le reflet exact des données d'inscription.

---

### 2. Membre "Ancien" (Alumni) et autres statuts permanents

C'est là qu'une modification de notre modèle `Member` devient très intéressante pour gérer le cycle de vie à long terme.

Nous pouvons ajouter un champ `statut` à l'entité `Member` elle-même. Ce champ ne gère pas l'activité annuelle, mais le cycle de vie global du membre.

#### Proposition : Ajouter un Enum `StatutMembre` à l'entité `Member`

```java
// Dans l'entité Member.java

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private StatutMembre statut = StatutMembre.ACTIF; // Valeur par défaut
```

Et l'énumération `StatutMembre.java` :

```java
public enum StatutMembre {
    ACTIF,      // Membre actuellement dans son cycle normal (peut être actif ou inactif selon les mandats)
    ALUMNI,     // Statut officiel d'ancien membre, accordé par l'administration
    SUSPENDU,   // Pour des raisons disciplinaires, par exemple
    HONORAIRE,  // Membre d'honneur
    RADIE       // A quitté l'association
}
```

#### Justification professionnelle :

*   **Séparation des préoccupations** :
    *   La **table `Registration`** répond à la question : "Ce membre participe-t-il au mandat actuel ?". C'est le **statut opérationnel**.
    *   Le **champ `Member.statut`** répond à la question : "Quel est le statut administratif de ce membre au sein de l'association ?". C'est le **statut de cycle de vie**.

*   **Puissance et flexibilité** :
    *   Vous pouvez facilement extraire une liste de tous vos `ALUMNI` pour une campagne de dons.
    *   Vous pouvez empêcher un membre `SUSPENDU` de se réinscrire.
    *   Un membre peut avoir le statut `ACTIF` mais être temporairement inactif (pas d'inscription au mandat en cours). Cela vous permet de le cibler avec une campagne de "Réinscrivez-vous !".

---

### Synthèse de la Solution

| Statut souhaité | Comment l'obtenir avec ce modèle ? |
| :--- | :--- |
| **Nouveau Adhérent** | Membre avec une `Registration` où `typeInscription = INITIAL` pour le mandat en cours. |
| **Membre Actif** | Membre avec une `Registration` pour le `Mandat` où `estActif = true`. |
| **Membre Inactif** | Membre qui n'a pas de `Registration` pour le `Mandat` en cours. |
| **Membre Ancien (Alumni)** | Membre dont le champ `statut` dans l'entité `Member` est `ALUMNI`. |
| **Membre Suspendu** | Membre dont le champ `statut` est `SUSPENDU`. |

Cette approche est robuste, évolutive et ne crée pas de dette technique. Elle distingue clairement ce qui est un état permanent de ce qui est un état temporaire et contextuel.
