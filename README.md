# SeriesTracker Android

**SeriesTracker** est une application Android moderne développée en Kotlin qui permet de découvrir les séries TV les plus populaires du moment grâce à l'intégration de l'API EpisoDate.

---

## Fonctionnalités

* Récupération en temps réel des séries les plus populaires via l'API EpisoDate.
* Affichage d'une liste fluide avec les affiches des séries, le réseau de diffusion et le statut (En cours / Terminé).
* Chargement asynchrone et mise en cache des images.
* Gestion robuste des états de l'interface (Indicateur de chargement, affichage des données, gestion des erreurs réseau avec bouton de relance).
* Interface utilisateur 100% déclarative.

---

## Technologies et Architecture

L'application respecte les standards de développement recommandés par Google, en utilisant l'architecture **MVVM** (Model-View-ViewModel) couplée aux principes de la **Clean Architecture**.

* **Langage :** Kotlin
* **Interface Utilisateur :** Jetpack Compose (Material 3)
* **Injection de Dépendances :** Dagger-Hilt
* **Réseau & API :** Retrofit2, OkHttp3 (Logging Interceptor), Gson
* **Chargement d'images :** Coil
* **Asynchronisme :** Coroutines & Kotlin Flow (`StateFlow`)

---

## Architecture du projet

Le code est organisé par couches de responsabilités (Feature/Layer packaging) :

```text
SeriesTracker-Android/app/src/main/java/com/example/seriestracker/
│
├── data/           # Couche de données
│   ├── mapper/     # Conversion des DTOs en Modèles
│   ├── remote/     # DTOs et interface API Retrofit
│   └── repository/ # TvShowRepository (Gestion de la source de vérité)
│
├── di/             # Injection de dépendances
│   └── NetworkModule # Fournisseur Hilt pour Retrofit et OkHttp
│
├── domain/         # Couche métier
│   └── model/      # Modèles de données propres (TvShow)
│
└── ui/             # Couche de présentation
    ├── components/ # Composants Compose réutilisables (CarteSerie)
    ├── screens/    # Écrans entiers (EcranAccueil)
    ├── theme/      # Couleurs, typographie (Material Theme)
    └── viewmodel/  # PopularShowsViewModel et gestion de l'UiState
```
---

## Installation et Test

### Prérequis
* Android Studio (version récente supportant Jetpack Compose)
* SDK Android avec `minSdk` 24 (Android 7.0) et `targetSdk` 36

### Via le code source
1. Cloner le dépôt : 
```bash
git clone [https://github.com/Synergy-XVortex/SeriesTracker-Android.git](https://github.com/Synergy-XVortex/SeriesTracker-Android.git)
```
2. Ouvrir le projet dans Android Studio.
3. Laisser Gradle synchroniser les dépendances.
4. Lancer l'application sur un émulateur ou un appareil physique.

### Via l'APK
Un fichier exécutable de l'application est disponible directement dans ce dépôt pour une installation rapide sur un appareil Android :
* Naviguez dans le dossier `/apk` à la racine du projet.
* Téléchargez et installez le fichier `SeriesTracker-debug.apk`.

---

## Évolutions possibles

* Ajout d'une barre de recherche pour trouver une série spécifique.
* Sauvegarde des séries favorites dans une base de données locale (Room).
* Ajout d'un écran de détail pour afficher le résumé d'une série sélectionnée.
* Pagination dynamique (Infinite Scrolling) pour charger la suite de la liste lors du défilement.

---

## Contribution

Les contributions sont les bienvenues :

1. Fork du projet
2. Créer une branche (`feature/NouvelleFonctionnalite`)
3. Commit vos modifications
4. Push et ouvrir une Pull Request

---

## Auteurs

Projet développé par **Synergy-XVortex / Zeineb / TTeano**.
