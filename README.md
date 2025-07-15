# Lab 5 – Application d'affichage d'images avec patrons de conception

## 📋 Description
Cette application permet de charger une image, d’en modifier la perspective (zoom & translation) via la souris ou un menu,  
de défaire les opérations (undo), et de sauvegarder/charger l’état sur disque.

---

## 🚀 Technologies & Librariés
- **Java SE 11+**  
- **Swing** pour l’interface graphique  
- **Java Serialization** pour la persistance  
- **Maven** (ou Gradle) pour la compilation et les dépendances  
- **JUnit 5** pour les tests unitaires  

---

## 🏗 Architecture & Patrons utilisés
| Patrons                  | Rôle / Composants principaux               |
|--------------------------|---------------------------------------------|
| **MVC**                  | `Model`, `View`, `Controller` séparés       |
| **Observer** (GoF)       | `Subject`, `Observer` pour notifications    |
| **Command** (GoF)        | `ZoomCommand`, `TranslateCommand`, `undo()`|
| **Singleton** (GoF)      | `CommandManager`                            |
| **Memento** / Snapshot   | (optionnel) redo via sauvegarde d’état      |
| **Factory**              | (optionnel) création de commandes           |
| **Mediator**             | (optionnel) gestion copier‑coller           |
| **Strategy**             | (optionnel) choix de paramètres à copier    |

---

## 📁 Structure du projet
lab5-image-viewer/
├─ src/
│  ├─ main/java/
│  │  ├─ model/           # ImageModel.java, Perspective.java, Subject/Observer
│  │  ├─ view/            # ThumbnailView.java, PerspectiveView.java
│  │  ├─ controller/      # MouseController.java, MenuController.java
│  │  ├─ command/         # Command.java, ZoomCommand.java, TranslateCommand.java
│  │  └─ util/            # SerializationUtil.java
│  └─ test/java/          # Tests JUnit pour modèle, commandes et sérialisation
├─ docs/                  # Diagrammes UML (classes, séquences) et rapport final
├─ pom.xml (ou build.gradle)
└─ README.md


## Suite avec taches par personne/ Installation & Exécution...........
