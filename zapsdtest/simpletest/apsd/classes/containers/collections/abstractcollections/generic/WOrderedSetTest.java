package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic;

import apsd.interfaces.containers.collections.OrderedSet;
import zapsdtest.testframework.containers.ContainerBaseTest;
import zapsdtest.testframework.containers.collections.OrderedSetTest;

abstract public class WOrderedSetTest<Data extends Comparable<? super Data>> extends ContainerBaseTest<OrderedSet<Data>> implements OrderedSetTest<Data, OrderedSet<Data>> {}
