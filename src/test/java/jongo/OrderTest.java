package jongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * Tests for {@link OrderDao}.
 * @author tobias.trelle
 */
public class OrderTest {
	
	/** Unit under test. */
	private MongoCollection orders;
	
	@Before public void setUp() throws UnknownHostException {
		MongoClient mc = new MongoClient();
		DB db = mc.getDB("odm_jongo");

		db.getCollection("order").drop();
		
		// Jongo API entry point
		Jongo jongo = new Jongo(db);
		orders = jongo.getCollection("order");
	}
	
	@Test
	public void should_find_by_items_quantity() {
		// given
		Order order = new Order("Tobias Trelle, gold customer");
		List<Item> items = new ArrayList<Item>();
		items.add( new Item(1, 47.11, "Item #1") );
		items.add( new Item(2, 42.0, "Item #2") );
		order.setItems(items);
		orders.save(order);
		
		// when
		Iterable<Order> result = orders.find("{\"items.quantity\": #}", 2).as(Order.class);
		
		// then
		Assert.assertNotNull( result );
		Assert.assertNotNull( result.iterator() );
		order = result.iterator().next();
		Assert.assertNotNull( order );
	}
	
}
