# TP : Threads & Concurrence

Ecrivez un programme qui crée 2 threads, `Thread1` et `Thread2`, qui affichent chacun 10 fois un message différent. `Thread1` affiche le message "Jour" et `Thread2` affiche le message "Nuit". Les messages doivent être affichés alternativement, c'est-à-dire que le premier message affiché doit être "Jour", suivi de "Nuit", et ainsi de suite.


# Correction ExerciceThreads

Tout d'abord, nous aavons kune class `PrintFlag` qui encapsule une variable booléenne `shouldPrint`. Cette variable est utilisée pour indiquer quel thread doit imprimer son message. Nous avons des méthodes `isShouldPrint()` et `setShouldPrint(boolean)` pour accéder et modifier cette variable.

Ensuite, nous avons 2 classes `Thread1` et `Thread2` qui implémentent l'interface `Runnable`. Ces classes représentent les 2 threads qui vont s'exécuter en parallèle. Chaque thread à un constructeur qui prend un objet `monitor` et un objet `PrintFlag`. L'objet `monitor` est utilisé pour synchoniser l'accèsaux ressources partagées, tandis que l'objet `PrintFlag` est utilisé pour déterminer quel thread doit imprimer son message.

Dans la méthode `run()` de chaque thread, nous avons une boucle `for` qui s'exécute 10 fois. A chaque itération, le thread vérifie s'il doit imprimer son message en utilisant la méthode `isShouldPrint()` de l'objet `PrintFlag`. Si ce n'est pas le cas, le thread entre en attente en appelant la méthode `wait()` de l'objet `monitor`. Lorsque l'autre thread modifie la variable `shouldPrint` et appelle la méthode `notify()` de l'objet `monitor`, le thread en attente reprend son exécution et vérifie à nouveau s'il doit imprimer son message.

Lorsqu'un thread doit imprimer son message, il le fait en utilisant la méthode `println()` de la classe `System.out` puis il modifie la variable `shouldPrint` en utilisant la méthode `setShouldPrint(boolean)` de l'objet `PrintFlag`, et enfin il appelle la méthode `notify()` de l'objet `monitor` pour réveiller l'autre thread.

Enfin, nous avons la classe `ExerciceThreads` qui contient la méthode `main()`. Dans cette méthode, nous créons un objet `monitor` et un objet `PrintFlag`, puis nous créons 2 threads en utilisant les constructeurs des classes `Thread1` et `Thread2`. Nous démarrons les 2 threads en appelant la méthode `start()` de chaque objet `thread`, puis nous attendons qu'ils terminent leur exécution en appelant la méthode `join()` de chaque objet `thread`.

En résumé, ce programme utilise 2 threads pour imprimer alternativement les messages "Jour" et "Nuit" 10 fois chacun. Les threads sont synchronisés à l'aide d'un objet `monitor` et d'une variable `shouldPrint` partagée. Chaque thread vérifie s'il doit imprimer son message en utilisant la variable `shouldPrint`, et s'il ne doit pas le faire, il entre en attente jusqu'à ce que l'autre thread modifie la variable `shouldPrint` et le réveille.