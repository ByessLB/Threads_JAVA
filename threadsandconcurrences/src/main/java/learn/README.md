# TP : Les Threads

Les threads en Java permettent d'exécuter pluseiurs tâches en parallèle dans un même programme. Dans cet exemple, nous allons voir comment utiliser des threads pour exécuter une tâche longue en arrière-plan tout en effectuant d'autres tâches dans le thread principal.

## Création de threads

Il existe 2 façons de créer des threads en Java : en étendant la classe `Thread` ou en implémentant l'interface `Runnable`.
Dans notre exemple, nous avons créé une classe `CustomThread` qui implémente l'interface `Runnable`. Cette interface ne contient qu'une seul méthode, `run()`, qui contient le code à exécuter dans le thread.

```java
public class CustomThread implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
```

Pour créer un thread à partir de cette classe, nous pouvons simplement créer un objet `Thread` et lui passer une instance de `CustomThread` dans le constructeur :

```java
Thread thread = new Thread(new CustomThread());
```

Puis nous pouvons démarrer le thread en appelant la méthode `start()` :

```java
thread.start();
```

## Utilisation d'un ExecutorService

Dans notre exemple, nous avons utilisé une autre approche pour créer des threas : l'utilisation d'un `ExecutorService`. Cette classe fait partir de l'API `java.util.concurrent` et permet de gérer un pool de threads.
Dans notre exemple, nous avons créé un `ExecutorService` avec un pool de 2 threads :

```java
ExecutorService executorService = Executors.newFixedThreadPool(2);
```

Nous pouvons ensuite soumettre une tâche à l'`ExecutorService` en utilisant la méthode `submit()`. Cette méthode prend un objet `Callable` en paramètre et renvoie un objet `Future` qui permet de récupérer le résultat de la tâche lorsqu'elle est terminée.
Dans notre exemple, nous avons créé une tâche qui exécute une longue opération (simulée avec la méthode `Thread.sleep()`) et renvoir le résultat 42 :

```java
Future<Integer> future = executorService.submit(() -> {
    System.out.println("Executing long tast...");
    Thread.sleep(4000); // 4s
    return 42;
});
```

Nous pouvons ensuite effectuer d'autres tâche dans le thread principal pendant que la tâche longue s'exécute en arrière-plan.
Dans notre exemple, nous avons simplement affiché un message dans la console :

```java
System.out.println("Doing othre stuff...");
```

Lorsque nous avons besoin du résultat de la tâche longue, nous pouvons appeler la méthode `get()` de l'objet `Future`. Cette méthode bloque l'exécution du thread principal jusqu'à ce que la tâche soit terminée et renvoie le résultat :

```java
int result = future.get();
System.out.println("Task terminated with relust : " + result);
```

Enfin, nous devons arrêter l'`ExecutorService` en appelant la méthode `shutdown()` :

```java
executorService.shutdown();
```

## Synchronisation de threads

Lorsque plusieurs threads accèdent à une ressource partagée, il est important de synchroniser l'accès à cette ressource pour éviter les conflits d'accès concurrents.
Dans notre exemple, nous avons créé une classe `Counter` qui contient un compteur partagé entre plusieurs threads. Cette classe contient une méthode `increment()` qui incrémente le compteur de 1 :

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

Notez que la méthode `increment()` est marquée avec le mot-clé `synchronized`. Cela signifie que seul un thread peut exécuter cette méthode à la fois. Si plusieurs threads appelent cette méthode en même temps, ils seront mis en file d'attente et exécutes séquentiellement.