<div align="center">

# **Java Arena**
Un jeu de combat tactique au tour par tour jouable en console, développé en Java.  
Le joueur incarne un dresseur qui constitue une équipe de monstres élémentaires afin d’affronter des monstres sauvages.

<img width="433" height="262" alt="image" src="https://github.com/user-attachments/assets/df0fd6cd-5d8c-4082-8f5e-4478f4805d81" />

</div>

## **Structure du projet**

```
Java_Arena/
│
├── .gitignore
├── README.md
│
├── Main.java
│
├── battle/
│   └── Battle.java
│
├── exceptions/
│   └── IllegalActionException.java
│
├── monsters/
│   ├── Monster.java
│   ├── FireMonster.java
│   ├── WaterMonster.java
│   └── PlantMonster.java
│
├── player/
│   ├── Player.java
│   └── Inventory.java
│
└── utils/
    ├── Generator.java
    └── Save.java
```

## **Prérequis**
- Java JDK 17 ou supérieur installé  
- Un terminal (Windows, macOS ou Linux)

## **Installation**

Dans le terminal :

```bash
git clone https://github.com/Daxow/Java_Arena.git
cd Java_Arena
```

## **Compilation et Exécution**

```bash
javac Main.java
java Main
```

## **Utilisation**

**Au lancement du jeu :**
- Donner un nom à son équipe
- Choisir entre une nouvelle partie ou charger une sauvegarde existante

**Fonctionnalités principales :**
- Gestion d’une équipe de monstres élémentaires (Feu, Eau, Plante)
- Combats au tour par tour avec système de type Pierre-Feuille-Ciseaux
- Utilisation d’objets (potions, rappels, PokéBalls)
- Capture de monstres sauvages
- Boutique pour acheter des objets
- Sauvegarde et chargement de la progression
- Le jeu se joue entièrement via la console à l’aide de menus interactifs.
