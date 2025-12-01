package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container { // Must extend Container

  // Insert
  boolean Insert(Data dat);

  // InsertAll
  default boolean InsertAll(TraversableContainer<Data> con) {
    Box<Boolean> all = new Box<>(true);
    if (con != null)
      con.TraverseForward(dat -> {
        all.Set(all.Get() && Insert(dat));
        return false;
      });
    return all.Get();
  }

  // InsertSome
  default boolean InsertSome(TraversableContainer<Data> con) {
    Box<Boolean> some = new Box<>(false);
    if (con != null)
      con.TraverseForward(dat -> {
        some.Set(some.Get() || Insert(dat));
        return false;
      });
    return some.Get();
  }

}
