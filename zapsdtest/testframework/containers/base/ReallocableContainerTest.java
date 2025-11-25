package zapsdtest.testframework.containers.base;

import apsd.interfaces.containers.base.ReallocableContainer;
import apsd.classes.utilities.Natural;

import static org.junit.jupiter.api.Assertions.*;

public interface ReallocableContainerTest<Con extends ReallocableContainer> extends ClearableContainerTest<Con> {

  default void TestCapacity() {
    BeginTest("Capacity");
    long size = ThisContainer().Size().ToLong();
    Natural capacity = ThisContainer().Capacity();
    assertNotNull(capacity, "Capacity() should not return null");
    assertTrue(capacity.ToLong() >= size, "Capacity must be great then or equal to Size");
    EndTest();
  }

  default void TestRealloc(Natural newSize) {
    BeginTest("Realloc");
    ThisContainer().Realloc(newSize);
    assertTrue(ThisContainer().Size().ToLong() <= newSize.ToLong(),
    "Size should be consistent with new size");
    EndTest();
  }

  default void TestGrow() {
    BeginTest("Grow");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Grow();
    assertTrue(ThisContainer().Capacity().ToLong() >= initialSize * ReallocableContainer.GROW_FACTOR,
    "Capacity should be at least initial size plus one after Grow");
    EndTest();
  }

  default void TestGrowWithNumber(Natural number) {
    BeginTest("GrowWithNumber");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Grow(number);
    assertTrue(ThisContainer().Capacity().ToLong() >= (initialSize + number.ToLong()) * ReallocableContainer.GROW_FACTOR,
    "Capacity should be at least initial size plus " + number + " after Grow");
    EndTest();
  }

  default void TestShrink() {
    BeginTest("Shrink");
    long initialCapacity = ThisContainer().Capacity().ToLong();
    ThisContainer().Shrink();
    assertTrue(ThisContainer().Capacity().ToLong() <= initialCapacity / ReallocableContainer.SHRINK_FACTOR,
      "Capacity should not increase after Shrink");
    EndTest();
  }

}
