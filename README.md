# READ ME BROUILLON
# Lab 5 – Application d'affichage d'images avec patrons de conception

##  📋  Description bref pour le moment de ce que j'ai compris 
Cette application permet de charger une image, d’en modifier la perspective (zoom & translation) via la souris ou un menu,  de défaire les opérations (undo), et de sauvegarder/charger l’état sur disque.

---

##  🚀   Technologies & Librariés
- **Java SE 11+**  
- **Swing** pour l’interface graphique  
- **Java Serialization** pour la persistance  
- **Maven** (ou Gradle) pour la compilation et les dépendances , mais faut en discuter
- **JUnit 5** pour les tests unitaires  

---

##  🏗  Architecture & Patrons utilisés
| Patrons                  | Rôle / Composants principaux               |
|--------------------------|---------------------------------------------|
| **MVC**                  | `Model`, `View`, `Controller` séparés       |
| **Observer** (GoF / ou Gurru )       | `Subject`, `Observer` pour notifications    |
| **Command** (GoF / Gurru )        | `ZoomCommand`, `TranslateCommand`, `undo()`|
| **Singleton** (GoF / Gurru)      | `CommandManager`                            |
| **Memento** / Snapshot   | (optionnel) redo via sauvegarde d’état      |
| **Factory**              | (optionnel mais on peut faire lui parce que j'ai un exemple MMI) création de commandes           |
| **Mediator**             | (optionnel) gestion copier‑coller           |
| **Strategy**             | (optionnel) choix de paramètres à copier    |

---

##  📁  Structure du projet - Exemple StackOverFlow

lab5-image-viewer/
├─ src/
│  ├─ main/java/
│  │  ├─ model/           # ImageModel.java, Perspective.java, Subject/Observer
│  │  ├─ view/            # ThumbnailView.java, PerspectiveView.java
│  │  ├─ controller/      # MouseController.java, MenuController.java
│  │  ├─ command/         # Command.java, ZoomCommand.java, TranslateCommand.java
│  │  └─ utile/            # SerializationUtil.java
│  └─ test/java/          # Tests JUnit pour modèle, commandes et sérialisation
├─ docs /                  # Diagrammes UML (classes, séquences) et rapport final
├─ pom.xml (ou build.gradle)
└─ README.md

├─
│  │
└─


## Suite avec taches par personne/ Installation & Exécution...........

changement test.