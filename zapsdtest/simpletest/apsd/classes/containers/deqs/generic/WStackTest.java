package zapsdtest.simpletest.apsd.classes.containers.deqs.generic;

import apsd.interfaces.containers.deqs.Stack;
import zapsdtest.testframework.containers.ContainerBaseTest;
import zapsdtest.testframework.containers.deqs.StackTest;

abstract public class WStackTest<Data> extends ContainerBaseTest<Stack<Data>> implements StackTest<Data, Stack<Data>> {}
