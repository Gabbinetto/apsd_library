package zapsdtest.testframework.containers.base;

import apsd.interfaces.containers.base.SortedIterableContainer;

import static org.junit.jupiter.api.Assertions.*;

public interface SortedIterableContainerTest<Data extends Comparable<? super Data>, Con extends SortedIterableContainer<Data>> extends IterableContainerTest<Data, Con> {

  default void TestSortedOrder() {
    BeginTest("SortedOrder");
    var iterator = ThisContainer().FIterator();
    Data previous = null;
    while (iterator.IsValid()) {
      Data current = iterator.GetCurrent();
      if (current != null) {
        if (previous != null) {
          assertTrue(previous.compareTo(current) <= 0,
          "Elements should be in sorted order: " + previous + " <= " + current);
        }
        previous = current;
      }
      iterator.Next();
    }
    EndTest();
  }

}
