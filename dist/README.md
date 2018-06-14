# 7 Familles *(SOLO)*
Il s'agit d'un jeu de 7 familles en solo avec 3 IA

### 1.Règles du jeu
Ce jeu se joue de 2 à 6 joueurs.
On commence par distribuer 6 cartes par personne. Le reste du jeu constitue la pioche.
Puis on désigne le joueur qui commencera *(cela peut être celui qui est à gauche de celui qui distribue)*
Ce joueur va regarder ses cartes et va demander à un autre joueur *(autre que lui)* une carte appartenant à l'une de ses familles. Il y a dès lors deux choix possibles:
 * Le joueur possède la carte
   * Le second joueur donne sa carte au premier
   * Le premier joueur vérifie s'il possède une famille *(6 cartes)*
     * S'il la possède, il la pose devant lui à la vue de tous
   * Puis le premier joueur rejoue
 * Le joueur ne possède pas la carte
   * Le premier joueur pioche une carte *(si la pioche en contient encore)*
     * Si la carte piochée est identique à celle demandée, alors il dit `Bonne pioche` puis il rejoue après avoir vérifié qu'il ne possède pas une famille. Car si c'est le cas il doit la poser devant lui
     * Sinon, il vérifie qu'il ne possède pas une famille. Car si c'est le cas il doit la poser devant lui. Puis c'est au tour du second joueur de jouer.

Et les tours s'effectuent ainsi de suite jusqu'à ce qu'il n'y ai plus de carte dans les mains des joueurs et dans la pioche.

***Attention:** Lorsqu'un joueur vient de terminer une famille et qu'il ne possède plus de carte et qu'il doit rejouer, celui-ci peut demander n'importe quelle carte du moment qu'elle ne figure pas dans une des familles réalisées et présente sur la table.*

### 2.L'application

Cette application comporte 3 IAs *(Intelligences Artificielles)* qui vont évoluer contre le joueur et contre elles. Elles s'appellent:
 * Diane
 * Marc
 * Aurore

Pour renommer le nom du joueur physique dans l'application, il suffit d'écrire dans un fichier texte appelé `name.txt` à coté de l'éxécutable le nom du joueur. Exemple:
```sh
Philipe
```
puis enregistrer le fichier.

Lorsque vous pouvez jouer et demander une carte, une pastille verte apparait en bas à droite, sinon elle est rouge.