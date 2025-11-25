package zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections.generic;

import org.junit.jupiter.api.*;

import apsd.classes.utilities.Natural;

abstract public class XListITest extends XListTest<Long> {

  @Override
  public void NewNonEmptyContainer() {
    AddTest(11);
    NewEmptyContainer();
    TestInsert(0L, true);
    TestInsertLast(4L);
    TestInsertFirst(5L);
    TestInsertLast(9L);
    TestInsertFirst(2L);
    TestInsertFirst(1L);
    TestInsert(0L, true);
    TestInsert(5L, true);
    TestInsertLast(1L);
    TestSize(9, false);
    TestPrintContent("");
  }

  @Nested
  @DisplayName("List Basics")
  public class ListBasics {

    @Test
    @DisplayName("Check starting from an Empty List")
    public void Empty() {
      AddTest(12);
      NewEmptyContainer();
      TestGetFirst(0L, true);
      TestGetLast(0L, true);
      TestSetFirst(0L, true);
      TestSetLast(0L, true);
      TestGetAt(Natural.Of(1), 0L, true);
      TestSetAt(0L, Natural.Of(2), true);
      TestExists(0L, false);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 0L);
      TestRemove(0L, false);
      TestRemoveFirst();
      TestRemoveLast();
    }

    @Test
    @DisplayName("Check starting from a NonEmpty List")
    public void NonEmpty() {
      AddTest(22);
      NewNonEmptyContainer();
      TestGetFirst(5L, false);
      TestGetLast(1L, false);
      TestSetFirst(2L, false);
      TestSetLast(6L, false);
      TestGetAt(Natural.Of(4), 5L, false);
      TestSetAt(3L, Natural.Of(4), false);
      TestExists(4L, true);
      TestPrintContent("");
      TestFoldForward((dat, acc) -> acc + dat, 0L, 27L);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 27L);
      TestRemoveFirst();
      TestFoldForward((dat, acc) -> acc + dat, 0L, 25L);
      TestRemoveLast();
      TestFoldForward((dat, acc) -> acc + dat, 0L, 19L);
      TestAtNRemove(Natural.Of(5), 4L, false);
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 15L);
      TestSize(6, false);
      TestInsertAt(5L, Natural.Of(6), false);
      TestPrintContent("");
      TestFoldBackward((dat, acc) -> acc + dat, 0L, 20L);
      TestClear();
      TestSize(0, false);
    }

  }

}
