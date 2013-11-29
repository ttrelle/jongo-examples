package jongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

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
		assertNotNull( result );
		assertNotNull( result.iterator() );
		order = result.iterator().next();
		assertNotNull( order );
	}

	@Test
	public void should_query_only_items() {
		// given
		Order order = new Order("Tobias Trelle, gold customer");
		List<Item> items = new ArrayList<Item>();
		items.add( new Item(1, 47.11, "Item #1") );
		items.add( new Item(2, 42.0, "Item #2") );
		order.setItems(items);
		orders.save(order);
		
		// when
		Iterable<Order> result = orders.
				find("{custInfo: #}", "Tobias Trelle, gold customer").
				projection("{_id:0,items:1}"). 
				as(Order.class);
		
		// then
		assertNotNull( result );
		assertNotNull( result.iterator() );
		
		order = result.iterator().next();
		assertNotNull( order );
		assertNull(order.getId());
		
		items = order.getItems();
		assertNotNull(items);
		assertEquals(2, items.size());
		
		Item item = items.get(0);
		assertNotNull(item);
		assertNotNull(item.getDescription());
		assertNotNull(item.getPrice());
		assertNotNull(item.getQuantity());
	}
	
	
}
