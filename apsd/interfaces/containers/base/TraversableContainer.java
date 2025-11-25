package apsd.interfaces.containers.base;

// import apsd.classes.utilities.Box;
// import apsd.classes.utilities.MutableNatural;
// import apsd.classes.utilities.Natural;
// import apsd.interfaces.traits.Accumulator;
// import apsd.interfaces.traits.Predicate;

/** Interface: MembershipContainer con supporto all'attraversamento. */
public interface TraversableContainer<Data> { // Must extend MembershipContainer

  // TraverseForward
  // TraverseBackward

  // default <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {
  //   final Box<Acc> acc = new Box<>(ini);
  //   if (fun != null) TraverseForward(dat -> { acc.Set(fun.Apply(dat, acc.Get())); return false; });
  //   return acc.Get();
  // }

  // FoldBackward

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  // ...

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  // ...

}
