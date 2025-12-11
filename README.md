# Classi
### 1. Le Utility (I mattoni fondamentali)

Questi file non dipendono dagli altri container, ma sono usati ovunque.

* **`classes/utilities/Box.java`**
    * **Scopo:** È un contenitore mutabile per un singolo valore. In Java i parametri sono passati per valore; `Box` serve per simulare il passaggio per riferimento (puntatori) o per scambiare nodi nelle liste linkate.
    * **Funzioni chiave:**
        * `Get()` / `Set()`: Legge o sovrascrive il valore interno.
    * **Connessione:** Usato intensivamente in `LLChainBase` per manipolare i puntatori `next` dei nodi.

* **`classes/utilities/Natural.java` & `MutableNatural.java`**
    * **Scopo:** Rappresentano numeri interi non negativi ($\ge 0$). Servono a garantire che indici e dimensioni non siano mai negativi.
    * **Funzioni chiave:**
        * `Increment()` / `Decrement()`: Aumentano o diminuiscono il valore controllando overflow/underflow.
    * **Connessione:** Usati come tipo di ritorno per `Size()` e come parametri per `GetAt(Natural pos)`.

---

### 2. I Vettori (Array e Gestione Memoria)

Qui c'è la gestione della memoria contigua (array).

* **`classes/containers/sequences/abstractbases/VectorBase.java`**
    * **Cosa fa:** È la classe base per *tutti* i vettori. Gestisce l'array grezzo `Data[]`.
    * **Funzioni chiave:**
        * `ArrayAlloc(Natural)`: Crea l'array di oggetti.
        * `SubSequence(...)`: Crea un nuovo vettore copiando una porzione dell'array esistente.
        * `GetAt(pos)`: Accesso diretto all'array (`arr[pos]`).
        * `Iterators`: Implementa iteratori che usano un indice numerico semplice per scorrere l'array.

* **`classes/containers/sequences/abstractbases/LinearVectorBase.java`**
    * **Cosa fa:** Implementa la logica "lineare". L'indice logico 0 corrisponde all'indice fisico 0 dell'array.
    * **Funzioni chiave:**
        * `Realloc()`: Quando l'array cambia dimensione, usa `System.arraycopy` per copiare i dati dal vecchio al nuovo array, partendo da 0.

* **`classes/containers/sequences/abstractbases/CircularVectorBase.java`**
    * **Cosa fa:** Implementa la logica "circolare" (buffer circolare). Ha un puntatore `start` che indica dove inizia logicamente il vettore.
    * **Funzioni chiave:**
        * `GetAt(pos)`: Calcola l'indice reale con la formula: `(start + pos) % capacity`.
        * `ShiftRight/Left`: Queste sono complesse. Invece di spostare tutti gli elementi fisicamente, a volte basta spostare l'indice `start` (rotazione), rendendo le operazioni in testa molto veloci.

* **`classes/containers/sequences/abstractbases/DynLinearVectorBase.java`** & **`DynCircularVectorBase.java`**
    * **Cosa fa:** Aggiunge la capacità di ridimensionarsi (Dinamic).
    * **Funzioni chiave:**
        * `Expand(n)`: Chiama `Grow()` (che raddoppia la capacità se serve) e aggiorna la variabile `size`.
        * `Reduce(n)`: Chiama `Shrink()` (che dimezza la capacità se troppo vuoto).

* **`classes/containers/sequences/Vector.java`, `DynVector.java`, ecc.**
    * **Cosa fa:** Sono classi "concrete". Non aggiungono logica, servono solo per istanziare gli oggetti (es. `new Vector()`). Ereditano tutto dalle classi `...Base` sopra.

---

### 3. Le Linked List (Catene di Nodi)

Qui la logica è basata su puntatori, non indici.

* **`classes/containers/collections/concretecollections/bases/LLNode.java`**
    * **Cosa fa:** Il singolo nodo della lista. Contiene il dato `Data` e un `Box<LLNode>` che punta al prossimo.
    * **Connessione:** Usato da tutte le classi `LL...`.

* **`classes/containers/collections/concretecollections/bases/LLChainBase.java`**
    * **Cosa fa:** Il motore delle liste linkate. Gestisce `headref` (testa) e `tailref` (coda).
    * **Funzioni chiave:**
        * `Remove(Data)`: Scorre la lista. Usa due riferimenti (corrente e precedente tramite Box) per "scollegare" il nodo trovato.
        * `SubSequence`: Crea una nuova lista clonando i nodi uno per uno.
        * `AtNRemove(index)`: Scorre fino all'indice $N$, rimuove il nodo e ricuce la lista.
    * **Nota:** Gli iteratori qui sono più complessi perché devono gestire il fatto che un nodo potrebbe essere rimosso durante l'iterazione (usano i `Box`).

* **`classes/containers/collections/concretecollections/LLList.java`**
    * **Cosa fa:** Una lista non ordinata.
    * **Funzioni chiave:**
        * `InsertAt`, `InsertFirst`, `InsertLast`: Creano un nuovo nodo e aggiustano i puntatori `next` per inserirlo nella posizione giusta. Costo O(N) per `InsertAt`, O(1) per First/Last.

* **`classes/containers/collections/concretecollections/LLSortedChain.java`**
    * **Cosa fa:** Una lista che mantiene i dati ordinati.
    * **Funzioni chiave:**
        * `PredFind(Data)`: È il cuore della classe. Scorre la lista per trovare il nodo *immediatamente precedente* a dove dovrebbe stare il dato (cioè trova il primo nodo che è minore del dato).
        * `Insert(Data)`: Usa `PredFind` per trovare il punto giusto, poi inserisce lì. Non puoi scegliere la posizione ("InsertAt" non esiste qui), decide l'ordine.

---

### 4. Liste basate su Vettori (Adapter Pattern)

Queste classi fanno comportare un Vettore come se fosse una Lista (Chain).

* **`classes/containers/collections/concretecollections/bases/VChainBase.java`**
    * **Cosa fa:** Contiene al suo interno un `DynVector<Data> vec`.
    * **Connessione:** Quando chiami un metodo della lista (es. `GetAt`), lui lo delega al vettore interno (`vec.GetAt`).
    * **Funzioni chiave:**
        * `Filter`: Implementa la rimozione "lazy" o per compattamento usando `vec.Reduce`.

* **`classes/containers/collections/concretecollections/VSortedChain.java`**
    * **Cosa fa:** Lista ordinata ma salvata su array.
    * **Funzioni chiave:**
        * `Insert(Data)`: Deve mantenere l'ordine. Chiama `SearchPredecessor` (che nel vettore è una **Ricerca Binaria**, molto veloce O(log N)), ottiene l'indice, e poi chiama `vec.InsertAt` (che fa lo shift degli elementi).

---

### 5. Wrappers (Set, Stack, Queue)

Queste classi avvolgono altre strutture per limitarne o specializzarne il comportamento.

* **`classes/containers/deqs/WQueue.java`**
    * **Cosa fa:** Coda FIFO.
    * **Connessione:** Contiene una `List` (di solito `VList`).
    * **Funzioni chiave:**
        * `Enqueue`: Chiama `internalList.InsertLast()`.
        * `Dequeue`: Chiama `internalList.RemoveFirst()`.

* **`classes/containers/deqs/WStack.java`**
    * **Cosa fa:** Pila LIFO.
    * **Connessione:** Contiene una `List`.
    * **Funzioni chiave:**
        * `Push`: Chiama `internalList.InsertFirst()` (o InsertLast, dipende dall'implementazione scelta, qui usa `Insert`).
        * `Pop`: Chiama `internalList.RemoveFirst()`.

* **`classes/containers/collections/abstractcollections/bases/WSetBase.java`**
    * **Cosa fa:** Base per gli Insiemi (niente duplicati).
    * **Connessione:** Contiene una `Chain` (lista).
    * **Funzioni chiave:**
        * Tutti i metodi (Insert, Remove, Search) sono delegati alla `chn` interna.

* **`classes/containers/collections/abstractcollections/WSet.java`** & **`WOrderedSet.java`**
    * **Cosa fa:** Implementazioni concrete.
    * **Funzioni chiave:**
        * `Insert(Data)`: Qui c'è la logica dell'insieme. Chiama `chn.InsertIfAbsent(data)`. Se il dato c'è già, non lo inserisce (garantisce l'unicità).
        * `WOrderedSet` usa internamente una `SortedChain` (`VSortedChain` nel costruttore di default) per garantire che, oltre a essere unici, i dati siano ordinati.

### Riassunto per i tuoi amici: Come connettere tutto?

1.  **Se devi implementare una funzione di `Stack`:** Non scrivere cicli `for`. Usa i metodi della `List` interna (`InsertFirst`, `RemoveFirst`).
2.  **Se devi implementare una funzione di `DynVector`:** Non gestire l'array manualmente se non serve. Usa `Grow()` e `Shrink()` ereditati, e implementa solo come si muovono gli indici.
3.  **Se devi implementare `SortedChain`:** Non preoccuparti di *come* sono salvati i dati, preoccupati di trovare la posizione giusta (`PredFind` o Ricerca Binaria) e poi lascia fare l'inserimento alla struttura sottostante (Link o Vettore).

---

# INTERFACCE

### 1. I Tratti (Traits)
Queste sono le interfacce funzionali o di utilità più "piccole". Servono come blocchi base per le operazioni logiche.

* **`Predicate<Data>`**
    * **Scopo:** Rappresenta una condizione booleana.
    * **`Apply(Data dat)`:** Deve restituire `true` se il dato soddisfa la condizione, `false` altrimenti. Usato per filtri e ricerche.
* **`Accumulator<Data, Acc>`**
    * **Scopo:** Usato per operazioni di "folding" (riduzione), come sommare tutti gli elementi.
    * **`Apply(Data dat, Acc acc)`:** Prende il dato corrente e l'accumulatore parziale, e restituisce il nuovo valore dell'accumulatore.
* **`Reference<Data>` / `MutableReference<Data>`**
    * **Scopo:** Wrapper per un dato (utile per passare parametri per riferimento o gestire puntatori).
    * **`Get()` / `Set(Data dat)`:** Legge o scrive il dato incapsulato.

---

### 2. Gli Iteratori
Il motore per muoversi dentro le strutture dati.

* **`Iterator<Data>`**
    * **`IsValid()`:** Restituisce `true` se l'iteratore sta puntando a un dato valido (non è fuori dai bordi).
    * **`GetCurrent()`:** Restituisce l'elemento puntato attualmente.
    * **`Reset()`:** Riporta l'iteratore all'inizio.
* **`ForwardIterator` / `BackwardIterator`**
    * **`Next()` / `Prev()`:** Sposta il cursore avanti o indietro. I metodi `Next(n)` sono già implementati come default (chiamano `Next()` *n* volte), quindi chi implementa deve preoccuparsi solo del passo singolo.
* **`MutableIterator`**
    * **`SetCurrent(Data dat)`:** Permette di modificare il dato puntato dall'iteratore senza dover accedere alla struttura principale tramite indici.

---

### 3. La Gerarchia dei Container (Base)
Tutte le strutture dati estendono `Container`.

* **`Container`**
    * **`Size()`:** Deve restituire il numero di elementi presenti (tipo `Natural`).
    * **`IsEmpty()`:** (Default) Controlla se `Size()` è zero.
* **`TraversableContainer`**
    * **Connessione:** Estende `MembershipContainer`. È fondamentale perché definisce come visitare la struttura senza iteratori espliciti.
    * **`TraverseForward(Predicate<Data> p)`:** Deve scorrere tutti gli elementi e applicare il predicato `p`. Se `p` restituisce `true`, il ciclo si interrompe (utile per la ricerca).
    * **`FoldForward`:** (Default) Usa `TraverseForward` per accumulare un risultato (es. somma dei numeri).
* **`InsertableContainer` / `RemovableContainer`**
    * **`Insert(Data)`:** Aggiunge un elemento. Restituisce `true` se l'inserimento riesce.
    * **`Remove(Data)`:** Rimuove la prima occorrenza del dato.
* **`ReallocableContainer` / `ResizableContainer`**
    * **Scopo:** Per strutture basate su array (vettori).
    * **`Capacity()`:** Quanto spazio è allocato in memoria (diverso da `Size`).
    * **`Realloc(Natural newsize)`:** Deve ridimensionare l'array interno, copiando i dati vecchi.
    * **`Grow()` / `Shrink()`:** (Default) Aumentano o diminuiscono la capacità in base a `GROW_FACTOR` e `SHRINK_FACTOR`.

---

### 4. Sequenze e Vettori (Sequence & Vector)
Strutture dati indicizzabili (accesso tramite posizione).

* **`Sequence<Data>`**
    * **`GetAt(Natural pos)`:** (Default, ma lento) Recupera l'elemento alla posizione `pos` usando `TraverseForward`.
    * **Nota per l'implementazione:** Le classi concrete (come Array o Liste Linkate) dovrebbero fare *override* di `GetAt` per renderlo più veloce (O(1) per array, O(N) ottimizzato per liste).
    * **`Search(Data dat)`:** Trova l'indice di un dato.
* **`MutableSequence<Data>`**
    * **`SetAt(Data dat, Natural n)`:** Scrive un dato in una posizione specifica.
    * **`Swap(n1, n2)`:** Scambia due elementi.
* **`Vector<Data>`**
    * Questa è una delle interfacce più complesse.
    * **`ShiftRight(pos, num)`:** (Default) Sposta gli elementi a destra per creare un "buco" (per inserimenti). L'algoritmo è già fornito: copia dall'ultimo elemento all'indietro per non sovrascrivere i dati.
    * **`ShiftLeft(pos, num)`:** (Default) Sposta gli elementi a sinistra per coprire un "buco" (per rimozioni).
    * **Cosa fare:** Chi implementa una classe `ArrayVector` deve solo fornire `Size()`, `Realloc()`, `GetAt()` e `SetAt()`. Le funzioni di shift funzioneranno automaticamente.

---

### 5. Collezioni (Collections)
Strutture dati di alto livello.

* **`Collection<Data>`**
    * Unisce `Insertable`, `Removable`, `Iterable` e `Clearable`.
    * **`Filter(Predicate p)`:** (Default) Rimuove tutti gli elementi che *non* soddisfano il predicato. Modifica la collezione sul posto ("in-place").
* **`Set<Data>`**
    * Rappresenta insiemi matematici (niente duplicati, ordine non garantito a meno che non sia `OrderedSet`).
    * **`Union`, `Intersection`, `Difference`:** Sono implementate di default usando `Traverse` e `Insert`/`Remove`.
* **`List<Data>`**
    * Combina `MutableSequence` e `Chain`. È una sequenza che può crescere e restringersi dinamicamente.
* **`OrderedSet` / `SortedSequence`**
    * Richiedono che il dato generico estenda `Comparable`.
    * **`Min()` / `Max()`:** Trovano minimo e massimo. Le implementazioni di default usano `FoldForward` per scansionare tutto. Una struttura ordinata concreta (come un albero BST) dovrebbe fare override per efficienza.
    * **`Search(Data)` in `SortedSequence`:** Usa la **Ricerca Binaria** (già implementata nel default method). Questo funziona solo se l'accesso posizionale è veloce.

---

### 6. Stack e Queue (Pile e Code)
Strutture con politiche di accesso limitate.

* **`Stack<Data>`**
    * LIFO (Last In, First Out).
    * **`Push(Data)`:** Inserisce in cima.
    * **`Pop()`:** Rimuove dalla cima.
    * **`Top()`:** Legge la cima senza rimuovere.
* **`Queue<Data>`**
    * FIFO (First In, First Out).
    * **`Enqueue(Data)`:** Inserisce in coda.
    * **`Dequeue()`:** Rimuove dalla testa.
    * **`Head()`:** Legge la testa.

---

### Consigli per i tuoi amici su come "connettere" il tutto:

1.  **Non reinventare la ruota:** Moltissimi metodi (come `InsertAll`, `RemoveAll`, `Search`, `ShiftLeft`) hanno un'implementazione `default` nelle interfacce. Quando creano una classe concreta (es. `MyLinkedList`), devono implementare solo i metodi **abstract** (fondamentali) come `Insert`, `Size`, `GetAt`. Il resto "funzionerà da solo".
2.  **Catena di estensioni:**
    * Se devono fare una **Lista Linkata**, devono implementare `List`.
    * Se devono fare un **Vettore Dinamico**, devono implementare `DynVector` (che estende `Vector`).
3.  **Iteratori:** La parte più difficile è spesso creare l'iteratore interno alla classe. Una volta che `FIterator()` (Forward Iterator) funziona correttamente, metodi complessi come `TraverseForward` e `FoldForward` funzioneranno automaticamente perché si basano su di esso.

Vettore statico:
1) ShiftFirstLeft: 2 3 4 5 null
2) ShiftLastLeft: 1 2 3 4 null
3) ShiftLeft, Pos 1 di 2 posti: 1 4 5 null null
4) ShiftFirstRight: null 1 2 3 4
5) ShiftLastRight: 1 2 3 4 null
6) ShiftRight, Pos 1 di 2 posti:  1 null null 2 3

Vettore dinamico:
1) ShiftFirstLeft: 2 3 4 5
2) ShiftLastLeft: 1 2 3 4
3) ShiftLeft, Pos 1 di 1 posto: 1 3 4 5
4) ShiftFirstRight: null 1 2 3 4 5
5) ShiftLastRight: 1 2 3 4 null 5 <--- PROBLEMA col test (il professore deve ancora rispondere alla mia mail)
6) ShiftRight pos 1 di 2 posti: 1 null null 2 3 4 5