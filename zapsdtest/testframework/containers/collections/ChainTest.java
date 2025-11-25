package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.Chain;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.sequences.RemovableAtSequenceTest;

import static org.junit.jupiter.api.Assertions.*;

public interface ChainTest<Data, Con extends Chain<Data>> extends SetTest<Data, Con>, RemovableAtSequenceTest<Data, Con> {

  default void TestInsertIfAbsent(Data element, boolean shouldWork) {
    BeginTest("InsertIfAbsent");
    boolean result = ThisContainer().InsertIfAbsent(element);
    if (shouldWork) {
      assertTrue(result, "InsertIfAbsent should return true for " + element);
    } else {
      assertFalse(result, "InsertIfAbsent should return false for " + element);
    }
    EndTest();
  }

  default void TestRemoveOccurrences(Data element) {
    BeginTest("RemoveOccurrences");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().RemoveOccurrences(element);
    assertTrue(ThisContainer().Size().ToLong() <= initialSize,
    "Size should not increase after RemoveOccurrences");
    EndTest();
  }

  default void TestSubChain(Natural from, Natural to, boolean edgeCase) {
    BeginTest("SubChain");
    Chain<Data> subChain = ThisContainer().SubChain(from, to);
    if (edgeCase) {
      assertNull(subChain, "SubChain should return null for invalid range");
    } else {
      assertNotNull(subChain, "SubChain should not return null");
      assertTrue(subChain.Size().ToLong() <= ThisContainer().Size().ToLong(),
      "SubChain should not be larger than original");
      assertEquals(subChain.Size().ToLong(), to.ToLong() - from.ToLong() + 1,
      "SubChain should not be as large as the required interval");
    }
    EndTest();
  }

}
