package tacos.web.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Order;
import tacos.data.OrderRepository;
import tacos.messaging.OrderMessagingService;

@Slf4j
@RestController
@RequestMapping(path="/orders",
                produces="application/json")
@CrossOrigin(origins="*")
public class OrderApiController {

  private final OrderRepository repo;
  private final OrderMessagingService orderMessages;
  private final EmailOrderService emailOrderService;

  @Autowired
  public OrderApiController(OrderRepository repo,
                            OrderMessagingService orderMessages,
                            EmailOrderService emailOrderService) {
    this.repo = repo;
    this.orderMessages = orderMessages;
    this.emailOrderService = emailOrderService;
  }
  
  @GetMapping(produces="application/json")
  public Iterable<Order> allOrders() {
    return repo.findAll();
  }
  
  @PostMapping(consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Order postOrder(@RequestBody Order order) {
//    orderMessages.sendOrder(order);
    return repo.save(order);
  }

  @PostMapping(path="fromEmail", consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Order postOrderFromEmail(@RequestBody EmailOrder emailOrder) {
    Order order = emailOrderService.convertEmailOrderToDomainOrder(emailOrder);
    log.info("Email integration, postOrderFromEmail function receives: " + emailOrder);
//    orderMessages.sendOrder(order);
    return repo.save(order);
  }

  @PutMapping(path="/{orderId}", consumes="application/json")
  public Order putOrder(@PathVariable("orderId") Long id, @RequestBody Order order) {
    return repo.save(order);
  }

  @PatchMapping(path="/{orderId}", consumes="application/json")
  public Order patchOrder(@PathVariable("orderId") Long orderId,
                          @RequestBody Order patch) {
    
    Order order = repo.findById(orderId).get();
    if (patch.getDeliveryName() != null) {
      order.setDeliveryName(patch.getDeliveryName());
    }
    if (patch.getDeliveryStreet() != null) {
      order.setDeliveryStreet(patch.getDeliveryStreet());
    }
    if (patch.getDeliveryCity() != null) {
      order.setDeliveryCity(patch.getDeliveryCity());
    }
    if (patch.getDeliveryState() != null) {
      order.setDeliveryState(patch.getDeliveryState());
    }
    if (patch.getDeliveryZip() != null) {
      order.setDeliveryZip(patch.getDeliveryState());
    }
    if (patch.getCcNumber() != null) {
      order.setCcNumber(patch.getCcNumber());
    }
    if (patch.getCcExpiration() != null) {
      order.setCcExpiration(patch.getCcExpiration());
    }
    if (patch.getCcCVV() != null) {
      order.setCcCVV(patch.getCcCVV());
    }
    return repo.save(order);
  }
  
  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable("orderId") Long orderId) {
    try {
      repo.deleteById(orderId);
    } catch (EmptyResultDataAccessException e) {}
  }

}
