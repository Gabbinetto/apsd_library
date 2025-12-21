package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;
import apsd.classes.utilities.Natural;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data> {

  // Top
  Data Top();

  // Pop
  void Pop();

  // TopNPop
  default Data TopNPop() {
    Data dat = Top();
    Pop();
    return dat;
  }

  // SwapTop
  default void SwapTop(Data dat) {
    TopNSwap(dat);
  }

  // TopNSwap
  default Data TopNSwap(Data dat) {
    Data old = TopNPop();
    Push(dat);
    return old;
  }

  // Push
  void Push(Data dat);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer */
  /* ************************************************************************ */

  @Override
  default void Clear() {
    while (Size().compareTo(Natural.ZERO) > 0)
      Pop();
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer */
  /* ************************************************************************ */

  @Override
  default boolean Insert(Data dat) {
    Natural oldSize = Size();
    Push(dat);
    return oldSize.compareTo(Size()) < 0; // Se la dimensione cresce, dat è stato inserito con successo
  }

}
