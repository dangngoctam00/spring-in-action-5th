package tacocloud.data;


import org.springframework.data.repository.CrudRepository;
import tacocloud.domain.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByDeliveryCityEquals(String deliveryZip);
}
