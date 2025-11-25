package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.List;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.sequences.MutableSequenceTest;
import zapsdtest.testframework.containers.sequences.InsertableAtSequenceTest;

import static org.junit.jupiter.api.Assertions.*;

public interface ListTest<Data, Con extends List<Data>> extends ChainTest<Data, Con>, MutableSequenceTest<Data, Con>, InsertableAtSequenceTest<Data, Con> {

  default void TestSubList(Natural from, Natural to, boolean edgeCase) {
    BeginTest("SubList");
    List<Data> subList = ThisContainer().SubList(from, to);
    if (edgeCase) {
      assertNull(subList, "SubList should return null for invalid range");
    } else {
      assertNotNull(subList, "SubList should not return null");
      assertTrue(subList.Size().ToLong() <= ThisContainer().Size().ToLong(),
      "SubList should not be larger than original");
      assertEquals(subList.Size().ToLong(), to.ToLong() - from.ToLong() + 1,
      "SubList should not be as large as the required interval");
    }
    EndTest();
  }

}
