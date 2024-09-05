# Les Threads et Concurrence

Les threads et la concurrence sont des concepts importants en programmation, en particuler dans les applications qui nécessitent une exécution simultanée de plusieurs tâches.

## Threads

Un thread (un fil d'exécution en français) est une unité d'exécution au sein d'un programme. Un programme peut contenir plusieurs threads, chacun exécutant une tâche différente en parallèle avec les autres threads. Les threads paratagent le même espace mémoire, ce qui signifie qu'ils peuvent accéder aux mêmes variables et objets.

Les threads peuvent être de 2 façons en Java : en étendant la classe `Thread` fournit une implémentation par défaut de la méthode `run()`, qui est exécutée lorsque le thread est démaré. L'interface `Runnable` définit une seule méthode, `run()`, qui doit être implémentée par la class qui l'implémente.

## Concurrence

La concurrence se produit plusieurs threads accèdent simultanément à une même ressource partagée, telle qu'une variable ou un objet. Si l'accès à la ressource n'est pas correctement synchronisé, cela peut entraîner des problènes de cohérence des données, tels que des conditions de course (rece conditions) ou des blocages (deadblocks).

Pour éviter ces problèmes, il est important de synchroniser l'accès aux ressources partagées à l'aide de mécanismes tels que les verrous (locks) ou les moniteurs (monitors). En Java, le mot-clé `synchronized` peut être utilisé pour synchroniser l'accès à une méthode ou à un bloc de code. Lorsqu'un thread entre dans une section critique synchronisée, tous les autres threads qui tentent d'accéder à la même section critique sont bloqués jusqu'à ce que le premier thread ait terminé son exécution.

Il est important de noter que la synchronisation peut avoir un impact sur les performances, car elle peut entraîner des blocages et des retards dans l'exécution des threads. Il est donc important de trouver un équilibre entre la synchronisation et la concurrence pour maximiser l'efficacité de l'application.

## Exemple de concurrence

Voici un exemple de concurrence en Java :

```java
public class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

```java
public class Thread1 implements Runnable {
    private Counter counter;

    public Thread1(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}
```

```java
public class Thread2 implements Runnable {
    private Counter counter;

    public Thread2(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}
```

```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(new Thread1(counter));
        Thread t2 = new Thread(new Thread2(counter));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter = " + counter.getCount());
    }
}
```

Dans cet exemple, 2 threads (`Thread1` et `Thread2`) accèdent simultanément à un objet `Counter` partagé et incrémentent sa valeur de 10 000 fois chacun. Cependant, la méthode `increment()` n'est pas synchronisée, ce qui peut entraîner des conditions de course et des résultats incohérents. Pour éviter cela, nous pouvons ajouter le mot-clé `synchronized` à la méthode `increment()` :

```java
public synchronized void increment() {
    count++;
}
```

Cela garantit que seul un thread peut accéder à la méthode `increment()` à la fois, évitant les conditions de course et les résultats incohérents.