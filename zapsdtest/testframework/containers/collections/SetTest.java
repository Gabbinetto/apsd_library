package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.Set;

import static org.junit.jupiter.api.Assertions.*;

public interface SetTest<Data, Con extends Set<Data>> extends CollectionTest<Data, Con> {

  default void TestUnion(Set<Data> otherSet) {
    BeginTest("Union");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Union(otherSet);
    assertTrue(ThisContainer().Size().ToLong() >= initialSize,
    "Size should not decrease after Union");
    assertTrue(otherSet.FoldForward((dat, acc) -> ThisContainer().Exists(dat) && acc, true)  ,
    "The new set should contain all elements of the input set after Union");
    EndTest();
  }

  default void TestDifference(Set<Data> otherSet) {
    BeginTest("Difference");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Difference(otherSet);
    assertTrue(ThisContainer().Size().ToLong() <= initialSize,
    "Size should not increase after Difference");
    assertFalse(otherSet.FoldForward((dat, acc) -> ThisContainer().Exists(dat) || acc, false)  ,
    "The new set should not contain any element of the input set after Differerence");
    EndTest();
  }

  default void TestIntersection(Set<Data> otherSet) {
    BeginTest("Intersection");
    long initialSize = ThisContainer().Size().ToLong();
    ThisContainer().Intersection(otherSet);
    assertTrue(ThisContainer().Size().ToLong() <= initialSize,
    "Size should not increase after Intersection");
    assertTrue(ThisContainer().FoldForward((dat, acc) -> otherSet.Exists(dat) && acc, true)  ,
    "The input set should contain all elements of the new set after Intersection");
    EndTest();
  }

}
