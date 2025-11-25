package zapsdtest.testframework.containers.sequences;

import apsd.interfaces.containers.sequences.Sequence;
import apsd.classes.utilities.Natural;

import zapsdtest.testframework.containers.base.IterableContainerTest;

import static org.junit.jupiter.api.Assertions.*;

public interface SequenceTest<Data, Con extends Sequence<Data>>
  extends IterableContainerTest<Data, Con> {

  default void TestGetAt(Natural position, Data expectedElement, boolean edgeCase) {
    BeginTest("GetAt");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class, () -> ThisContainer().GetAt(position),
      "GetAt should throw IndexOutOfBoundsException for invalid position");
    } else {
      Data element = ThisContainer().GetAt(position);
      assertEquals(expectedElement, element,
      "GetAt should return " + expectedElement + " at position " + position);
    }
    EndTest();
  }

  default void TestGetFirst(Data expectedElement, boolean edgeCase) {
    BeginTest("GetFirst");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class, () -> ThisContainer().GetFirst(),
      "GetFirst should throw exception when sequence is empty");
    } else {
      Data first = ThisContainer().GetFirst();
      assertEquals(expectedElement, first, "GetFirst should return " + expectedElement);
    }
    EndTest();
  }

  default void TestGetLast(Data expectedElement, boolean edgeCase) {
    BeginTest("GetLast");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class, () -> ThisContainer().GetLast(),
      "GetLast should throw exception when sequence is empty");
    } else {
      Data last = ThisContainer().GetLast();
      assertEquals(expectedElement, last, "GetLast should return " + expectedElement);
    }
    EndTest();
  }

  default void TestSearch(Data element, Natural expectedPosition) {
    BeginTest("Search");
    Natural position = ThisContainer().Search(element);
    assertEquals(expectedPosition, position,
    "Search should return position " + expectedPosition + " for " + element);
    EndTest();
  }

  default void TestIsInBound(Natural position, boolean edgeCase) {
    BeginTest("IsInBound");
    boolean result = ThisContainer().IsInBound(position);
    if (edgeCase) {
      assertTrue(result, "IsInBound should return true for position " + position);
    } else {
      assertFalse(result, "IsInBound should return false for position " + position);
    }
    EndTest();
  }

  default void TestExcIfOutOfBound(Natural position, boolean edgeCase) {
    BeginTest("ExcIfOutOfBound");
    if (edgeCase) {
      assertThrows(IndexOutOfBoundsException.class,
      () -> ThisContainer().ExcIfOutOfBound(position),
      "ExcIfOutOfBound should throw exception for invalid position");
    } else {
      long index = ThisContainer().ExcIfOutOfBound(position);
      assertEquals(position.ToLong(), index,
      "ExcIfOutOfBound should return the index for valid position");
    }
    EndTest();
  }

  default void TestSubSequence(Natural from, Natural to, boolean edgeCase) {
    BeginTest("SubSequence");
    Sequence<Data> subSequence = ThisContainer().SubSequence(from, to);
    if (edgeCase) {
      assertNull(subSequence, "SubSequence should return null for invalid range");
    } else {
      assertNotNull(subSequence, "SubSequence should not return null");
      assertTrue(subSequence.Size().ToLong() <= ThisContainer().Size().ToLong(),
      "SubSequence should not be larger than original");
      for(Natural idx = Natural.ZERO; from.compareTo(to) <= 0; idx = idx.Increment(), from = from.Increment()) {
        assertEquals(subSequence.GetAt(idx), ThisContainer().GetAt(from),
        "Values at position " + idx + " and " + from + " should be equal");
      }
    }
    EndTest();
  }

}
