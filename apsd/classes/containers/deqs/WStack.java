package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<Data> {

  protected final List<Data> lst;

  public WStack() {
    this.lst = new VList<Data>();
  }

  public WStack(List<Data> lst) {
    this.lst = lst;
  }

  public WStack(TraversableContainer<Data> con) {
    this.lst = new VList<Data>(con);
  }

  public WStack(List<Data> lst, TraversableContainer<Data> con) {
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
  /* Override specific member functions from Stack */
  /* ************************************************************************ */

  @Override
  public void Push(Data dat) {
    this.lst.InsertLast(dat);
  }

  @Override
  public void Pop() {
    if (IsEmpty()) {
      return;
    }
    this.lst.RemoveLast();
  }

  @Override
  public Data Top() {
    if (IsEmpty()) {
      return null;
    }
    return this.lst.GetLast();
  }

}
