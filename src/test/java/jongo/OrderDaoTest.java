package jongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.Mongo;

/**
 * Tests for {@link OrderDao}.
 * @author tobias.trelle
 */
public class OrderDaoTest {

	/** Unit under test.*/
	private OrderDao dao;
	
	@Before public void setUp() throws UnknownHostException {
		dao = new OrderDao( new Mongo(), "odm_jongo", "order" );
	}
	
	@Test
	public void should_find_by_items_quantity() {
		// given
		Order order = new Order("Tobias Trelle, gold customer");
		List<Item> items = new ArrayList<Item>();
		items.add( new Item(1, 47.11, "Item #1") );
		items.add( new Item(2, 42.0, "Item #2") );
		order.setItems(items);
		dao.save(order);
		
		// when
		Iterable<Order> orders = dao.findByItemsQuantity(2);
		
		// then
		Assert.assertNotNull(orders);
		Assert.assertNotNull( orders.iterator() );
	}
	
}
