package zapsdtest.simpletest.apsd.classes.containers.deqs.generic;

import apsd.interfaces.containers.deqs.Queue;
import zapsdtest.testframework.containers.ContainerBaseTest;
import zapsdtest.testframework.containers.deqs.QueueTest;

abstract public class WQueueTest<Data> extends ContainerBaseTest<Queue<Data>> implements QueueTest<Data, Queue<Data>> {}
