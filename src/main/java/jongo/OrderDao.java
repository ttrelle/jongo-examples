package jongo;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.Mongo;

public class OrderDao {

	/** Jongo API entry point. */
	private MongoCollection collection;
	
	public OrderDao(Mongo mongo, String dbName, String collectionName) {
		collection = new Jongo( mongo.getDB(dbName) ).getCollection(collectionName);
	}
	
	public void save(Order order) {
		collection.save(order);
	}
	
	public Iterable<Order> findByItemsQuantity(int i) {
		return collection.find("{\"items.quantiy\": #}", i).as(Order.class);
	}

}
