package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Queue;

/** Object: Wrapper queue implementation. */
public class WQueue<Data> implements Queue<Data> { // Must implement Queue

  protected final List<Data> lst;

  public WQueue() {
    this.lst = new VList<Data>();
  }

  public WQueue(List<Data> lst) {
    this.lst = lst;
  }

  public WQueue(TraversableContainer<Data> con) {
    this.lst = new VList<Data>(con);
  }

  public WQueue(List<Data> lst, TraversableContainer<Data> con) {
    this.lst = lst;
    con.TraverseForward(
        (dat) -> {
          this.lst.Insert(dat);
          return false;
        });
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container */
  /* ************************************************************************ */

  @Override
  public Natural Size() {
    return this.lst.Size();
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer */
  /* ************************************************************************ */

  @Override
  public void Clear() {
    this.lst.Clear();
  }

  /* ************************************************************************ */
  /* Override specific member functions from Queue */
  /* ************************************************************************ */

  @Override
  public void Enqueue(Data dat) {
    this.lst.InsertLast(dat);
  }

  @Override
  public void Dequeue() {
    this.lst.RemoveFirst();
  }

  @Override
  public Data Head() {
    return this.lst.GetFirst();
  }

  // TODO: Reimplementare HeadNDequeue in qualche modo

}
