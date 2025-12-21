package apsd.interfaces.containers.deqs;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer, InsertableContainer<Data> {

  // Head
  Data Head();

  // Dequeue
  void Dequeue();

  // HeadNDequeue
  default Data HeadNDequeue() {
    Data head = Head();
    Dequeue();
    return head;
  }

  // Enqueue
  void Enqueue(Data dat);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer */
  /* ************************************************************************ */

  @Override
  default void Clear() {
    while (Size().compareTo(Natural.ZERO) > 0)
      Dequeue();
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data dat) {
    Natural oldSize = Size();
    Enqueue(dat);
    return oldSize.compareTo(Size()) < 0; // Se la dimensione cresce, dat è stato inserito con successo
  }

}
