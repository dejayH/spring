package cn.tedu.sp04.order.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        Order order = orderService.getOrder(orderId);
        return JsonResult.ok().data(order);
    }

    @GetMapping("/")
    public JsonResult<?> addOrder(){
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setItems(Arrays.asList(new Item[] {
                new Item(1,"aaa",2),
                new Item(2,"bbb",1),
                new Item(3,"ccc",3),
                new Item(4,"ddd",1),
                new Item(5,"eee",5),
        }));
        order.setUser(new User(8,null,null));
        orderService.addOrder(order);
        return JsonResult.ok().msg("添加订单成功");
    }
}
