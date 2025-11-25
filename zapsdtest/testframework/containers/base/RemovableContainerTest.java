package zapsdtest.testframework.containers.base;

import apsd.interfaces.containers.base.RemovableContainer;
import apsd.interfaces.containers.base.TraversableContainer;

import static org.junit.jupiter.api.Assertions.*;

public interface RemovableContainerTest<Data, Con extends RemovableContainer<Data>> extends ContainerTest<Con> {

  default void TestRemove(Data element, boolean shouldWork) {
    BeginTest("Remove");
    long initialSize = ThisContainer().Size().ToLong();
    boolean result = ThisContainer().Remove(element);
    if (shouldWork) {
      assertTrue(result, "Remove should return true for " + element);
      assertEquals(initialSize - 1, ThisContainer().Size().ToLong(),
      "Size should decrease by 1 after Remove");
    } else {
      assertFalse(result, "Remove should return false for " + element);
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not change after failed Remove");
    }
    EndTest();
  }

  default void TestRemoveAll(TraversableContainer<Data> otherContainer, boolean shouldWork) {
    BeginTest("RemoveAll");
    long initialSize = ThisContainer().Size().ToLong();
    boolean result = ThisContainer().RemoveAll(otherContainer);
    if (shouldWork) {
      assertTrue(result, "RemoveAll should return true");
      assertTrue(ThisContainer().Size().ToLong() < initialSize,
      "Size should not increase after RemoveAll");
    } else {
      assertFalse(result, "RemoveAll should return false");
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not change after failed RemoveAll");
    }
    EndTest();
  }

  default void TestRemoveSome(TraversableContainer<Data> otherContainer, boolean shouldWork) {
    BeginTest("RemoveSome");
    long initialSize = ThisContainer().Size().ToLong();
    boolean result = ThisContainer().RemoveSome(otherContainer);
    if (shouldWork) {
      assertTrue(result, "RemoveSome should return true");
      assertTrue(ThisContainer().Size().ToLong() < initialSize,
      "Size should not increase after RemoveSome");
    } else {
      assertFalse(result, "RemoveSome should return false");
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not change after failed RemoveSome");
    }
    EndTest();
  }

}
