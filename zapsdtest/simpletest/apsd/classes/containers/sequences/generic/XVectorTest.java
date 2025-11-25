package zapsdtest.simpletest.apsd.classes.containers.sequences.generic;

import apsd.interfaces.containers.sequences.Vector;
import zapsdtest.testframework.containers.ContainerBaseTest;
import zapsdtest.testframework.containers.sequences.VectorTest;

abstract public class XVectorTest<Data> extends ContainerBaseTest<Vector<Data>> implements VectorTest<Data, Vector<Data>> {}
