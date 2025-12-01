package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<Data> extends Container { // Must extend Container

  // Remove
  boolean Remove(Data dat);

  // RemoveAll
  default boolean RemoveAll(TraversableContainer<Data> con) {
    Box<Boolean> all = new Box<>(true);
    if (con != null)
      con.TraverseForward(dat -> {
        all.Set(all.Get() && Remove(dat));
        return false;
      });
    return all.Get();
  }

  // RemoveSome
  default boolean RemoveSome(TraversableContainer<Data> con) {
    Box<Boolean> some = new Box<>(false);
    if (con != null)
      con.TraverseForward(dat -> {
        some.Set(some.Get() || Remove(dat));
        return false;
      });
    return some.Get();
  }

}
