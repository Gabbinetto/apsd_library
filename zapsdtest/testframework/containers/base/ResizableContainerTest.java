package zapsdtest.testframework.containers.base;

import apsd.interfaces.containers.base.ReallocableContainer;
import apsd.interfaces.containers.base.ResizableContainer;
import apsd.classes.utilities.Natural;

import static org.junit.jupiter.api.Assertions.*;

public interface ResizableContainerTest<Con extends ResizableContainer> extends ReallocableContainerTest<Con> {

  default void TestExpand() {
    BeginTest("Expand");
    long initialSize = ThisContainer().Size().ToLong();
    long initialCapacity = ThisContainer().Size().ToLong();
    ThisContainer().Expand();
    assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
    "Size should increase by 1 after Expand");
    if (initialSize < initialCapacity) {
      assertEquals(initialCapacity, ThisContainer().Size().ToLong(),
      "Capacity should not change after Expand with sufficient space");
    } else {
      assertTrue(initialCapacity < ThisContainer().Size().ToLong(),
      "Capacity needs to expand after Expand without sufficient space");
    }
    EndTest();
  }

  default void TestExpandWithNumber(Natural number) {
    BeginTest("ExpandWithNumber");
    long initialSize = ThisContainer().Size().ToLong();
    long initialCapacity = ThisContainer().Size().ToLong();
    ThisContainer().Expand(number);
    assertEquals(initialSize + number.ToLong(), ThisContainer().Size().ToLong(),
    "Size should increase by " + number + " after Expand");
    if (initialSize + number.ToLong() < initialCapacity) {
      assertEquals(initialCapacity, ThisContainer().Size().ToLong(),
      "Capacity should not change after Expand with sufficient space");
    } else {
      assertTrue(initialCapacity < ThisContainer().Size().ToLong(),
      "Capacity needs to expand after Expand without sufficient space");
    }
    EndTest();
  }

  default void TestReduce() {
    BeginTest("Reduce");
    long initialSize = ThisContainer().Size().ToLong();
    long initialCapacity = ThisContainer().Size().ToLong();
    ThisContainer().Reduce();
    assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
    "Size should decrease by 1 after Reduce");
    if (ResizableContainer.THRESHOLD_FACTOR * ReallocableContainer.SHRINK_FACTOR * initialSize > initialCapacity) {
      assertEquals(initialCapacity, ThisContainer().Size().ToLong(),
      "Capacity should not change after Reduce without much space");
    } else {
      assertTrue(initialCapacity < ThisContainer().Size().ToLong(),
      "Capacity needs to reduce after Reduce with a lot of space");
    }
    EndTest();
  }

  default void TestReduceWithNumber(Natural number) {
    BeginTest("ReduceWithNumber");
    long initialSize = ThisContainer().Size().ToLong();
    long initialCapacity = ThisContainer().Size().ToLong();
    ThisContainer().Reduce();
    assertEquals(initialSize - number.ToLong(), ThisContainer().Size().ToLong(),
    "Size should decrease by " + number + " after Reduce");
    if (ResizableContainer.THRESHOLD_FACTOR * ReallocableContainer.SHRINK_FACTOR * initialSize > initialCapacity) {
      assertEquals(initialCapacity, ThisContainer().Size().ToLong(),
      "Capacity should not change after Reduce without much space");
    } else {
      assertTrue(initialCapacity < ThisContainer().Size().ToLong(),
      "Capacity needs to reduce after Reduce with a lot of space");
    }
    EndTest();
  }

}
