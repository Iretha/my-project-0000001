package mbd.creational.factorymethod;

import mbd.creational.factorymethod.data.IBObject;

/**
 * <b>Design Pattern Name: </b> Factory Method Pattern <b>OR</b> Virtual
 * Constructor Pattern<br>
 * <b>Summary: </b> Also known as Virtual Constructor, the Factory Method is
 * related to the idea on which libraries work: a library uses abstract classes
 * for defining and maintaining relations between objects. One type of
 * responsibility is creating such objects. The library knows when an object
 * needs to be created, but not what kind of object it should create, this being
 * specific to the application using the library. The Factory method works just
 * the same way: it defines an interface for creating an object, but leaves the
 * choice of its type to the subclasses, creation being deferred at run-time. <br>
 * <b>Main purposes: </b> <br>
 * <li>Defines an interface for creating objects, but let subclasses to decide
 * which class to instantiate <br> <li>Refers to the newly created object
 * through a common interface <br>
 * 
 * @author developer0024
 */
public abstract class AMyFactoryMethod {

	public String anOperation() {
		IBObject obj = factoryMethod();
		return obj.getName();
	}

	protected abstract IBObject factoryMethod();
}
