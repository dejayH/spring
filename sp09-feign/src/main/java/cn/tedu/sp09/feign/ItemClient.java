package cn.tedu.sp09.feign;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "item-service",fallback = ItemClientFB.class)
public interface ItemClient {

    @GetMapping("/{orderId}")
    JsonResult<List<Item>> getItems(@PathVariable("orderId") String orderId);

    @PostMapping("/decreaseNumber")
    JsonResult<?> decreaseNumber(@RequestBody List<Item> items);

}
