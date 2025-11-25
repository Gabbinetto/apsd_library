package zapsdtest.testframework.containers.sequences;

import apsd.interfaces.containers.sequences.SortedSequence;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.base.SortedIterableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface SortedSequenceTest<Data extends Comparable<? super Data>, Con extends SortedSequence<Data>>
  extends SequenceTest<Data, Con>, SortedIterableContainerTest<Data, Con> {

  default void TestSearchInSortedSequence(Data element, boolean shouldWork, Natural expectedPosition) {
    BeginTest("SearchInSortedSequence");
    Natural position = ThisContainer().Search(element);
    if (shouldWork) {
      assertNotNull(position, "Search should find element in sorted sequence");
      if (expectedPosition != null) {
        assertEquals(expectedPosition.ToLong(), position.ToLong(),
        "Search should return correct position in sorted sequence");
      }
    } else {
      assertNull(position, "Search should not find non-existent element");
    }
    EndTest();
  }

}
