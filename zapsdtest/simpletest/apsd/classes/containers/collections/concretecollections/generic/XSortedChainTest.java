package zapsdtest.simpletest.apsd.classes.containers.collections.concretecollections.generic;

import apsd.interfaces.containers.collections.SortedChain;
import zapsdtest.testframework.containers.ContainerBaseTest;
import zapsdtest.testframework.containers.collections.SortedChainTest;

abstract public class XSortedChainTest<Data extends Comparable<? super Data>> extends ContainerBaseTest<SortedChain<Data>> implements SortedChainTest<Data, SortedChain<Data>> {}
