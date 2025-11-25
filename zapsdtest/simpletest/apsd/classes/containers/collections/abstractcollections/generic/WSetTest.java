package zapsdtest.simpletest.apsd.classes.containers.collections.abstractcollections.generic;

import apsd.interfaces.containers.collections.Set;
import zapsdtest.testframework.containers.ContainerBaseTest;
import zapsdtest.testframework.containers.collections.SetTest;

abstract public class WSetTest<Data> extends ContainerBaseTest<Set<Data>> implements SetTest<Data, Set<Data>> {}
