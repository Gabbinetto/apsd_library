package zapsdtest.testframework.containers.base;

import apsd.interfaces.containers.base.InsertableContainer;
import apsd.interfaces.containers.base.TraversableContainer;

import static org.junit.jupiter.api.Assertions.*;

public interface InsertableContainerTest<Data, Con extends InsertableContainer<Data>> extends ContainerTest<Con> {

  default void TestInsert(Data element, boolean shouldWork) {
    BeginTest("Insert");
    long initialSize = ThisContainer().Size().ToLong();
    boolean result = ThisContainer().Insert(element);
    if (shouldWork) {
      assertTrue(result, "Insert should return true for " + element);
      assertEquals(initialSize + 1, ThisContainer().Size().ToLong(),
      "Size should increase by 1 after Insert");
    } else {
      assertFalse(result, "Insert should return false for " + element);
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not change after failed Insert");
    }
    EndTest();
  }

  default void TestInsertAll(TraversableContainer<Data> otherContainer, boolean shouldWork) {
    BeginTest("InsertAll");
    long initialSize = ThisContainer().Size().ToLong();
    boolean result = ThisContainer().InsertAll(otherContainer);
    if (shouldWork) {
      assertTrue(result, "InsertAll should return true");
      assertTrue(ThisContainer().Size().ToLong() > initialSize,
      "Size should not decrease after InsertAll");
    } else {
      assertFalse(result, "InsertAll should return false");
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not change after failed InsertAll");
    }
    EndTest();
  }

  default void TestInsertSome(TraversableContainer<Data> otherContainer, boolean shouldWork) {
    BeginTest("InsertSome");
    long initialSize = ThisContainer().Size().ToLong();
    boolean result = ThisContainer().InsertSome(otherContainer);
    if (shouldWork) {
      assertTrue(result, "InsertSome should return true");
      assertTrue(ThisContainer().Size().ToLong() > initialSize,
      "Size should not decrease after InsertSome");
    } else {
      assertFalse(result, "InsertSome should return false");
      assertEquals(initialSize, ThisContainer().Size().ToLong(),
      "Size should not change after failed InsertSome");
    }
    EndTest();
  }

}
