package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.Collection;
import apsd.interfaces.traits.Predicate;

import zapsdtest.testframework.containers.base.ClearableContainerTest;
import zapsdtest.testframework.containers.base.InsertableContainerTest;
import zapsdtest.testframework.containers.base.RemovableContainerTest;
import zapsdtest.testframework.containers.base.IterableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface CollectionTest<Data, Con extends Collection<Data>> extends ClearableContainerTest<Con>, InsertableContainerTest<Data, Con>, RemovableContainerTest<Data, Con>, IterableContainerTest<Data, Con> {

  default void TestFilter(Predicate<Data> predicate) {
    BeginTest("Filter");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Filter(predicate);
    assertTrue(ThisContainer().Size().ToLong() <= initialSize,
    "Size should not increase after Filter");
    EndTest();
  }

}
