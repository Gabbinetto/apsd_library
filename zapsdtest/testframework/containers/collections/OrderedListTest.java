package zapsdtest.testframework.containers.collections;

import apsd.interfaces.containers.collections.OrderedList;

public interface OrderedListTest<Data extends Comparable<? super Data>, Con extends OrderedList<Data>> extends ListTest<Data, Con>, OrderedChainTest<Data, Con> {}
