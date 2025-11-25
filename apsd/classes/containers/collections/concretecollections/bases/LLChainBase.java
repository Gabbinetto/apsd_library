package apsd.classes.containers.collections.concretecollections.bases;

// import apsd.classes.containers.sequences.Vector;
// import apsd.classes.utilities.Box;
// import apsd.classes.utilities.MutableNatural;
// import apsd.classes.utilities.Natural;
// import apsd.interfaces.containers.base.TraversableContainer;
// import apsd.interfaces.containers.collections.Chain;
// import apsd.interfaces.containers.iterators.BackwardIterator;
// import apsd.interfaces.containers.iterators.ForwardIterator;
// import apsd.interfaces.containers.iterators.MutableBackwardIterator;
// import apsd.interfaces.containers.iterators.MutableForwardIterator;
// import apsd.interfaces.containers.sequences.Sequence;
// import apsd.interfaces.traits.Predicate;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> { // Must implement Chain

  // protected final MutableNatural size = new MutableNatural();
  // protected final Box<LLNode<Data>> headref = new Box<>();
  // protected final Box<LLNode<Data>> tailref = new Box<>();

  // LLChainBase

  // public LLChainBase(TraversableContainer<Data> con) {
  //   size.Assign(con.Size());
  //   final Box<Boolean> first = new Box<>(true);
  //   con.TraverseForward(dat -> {
  //     LLNode<Data> node = new LLNode<>(dat);
  //     if (first.Get()) {
  //       headref.Set(node);
  //       first.Set(false);
  //     } else {
  //       tailref.Get().SetNext(node);
  //     }
  //     tailref.Set(node);
  //     return false;
  //   });
  // }

  // NewChain

  /* ************************************************************************ */
  /* Specific member functions from LLChainBase                               */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  // @Override
  // public boolean Remove(Data dat) {
  //   if (dat == null) return false;
  //   final Box<LLNode<Data>> prd = new Box<>();
  //   return FRefIterator().ForEachForward(cur -> {
  //     LLNode<Data> node = cur.Get();
  //     if (node.Get().equals(dat)) {
  //       cur.Set(node.GetNext().Get());
  //       if (tailref.Get() == node) { tailref.Set(prd.Get()); }
  //       size.Decrement();
  //       return true;
  //     }
  //     prd.Set(node);
  //     return false;
  //   });
  // }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  // ...

}
