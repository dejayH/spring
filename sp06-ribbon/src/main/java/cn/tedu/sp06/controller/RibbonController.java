package cn.tedu.sp06.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class RibbonController {

    @Autowired
    private RestTemplate rt;

    @HystrixCommand(fallbackMethod = "getItemsFB")
    @GetMapping("/item-service/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) {

        return rt.getForObject("http://item-service/{1}", JsonResult.class, orderId);
    }

    @HystrixCommand(fallbackMethod = "decreaseNumberFB")
    @PostMapping("/item-service/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        return rt.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
    }

    public JsonResult<List<Item>> getItemsFB(String orderId) {

        return JsonResult.err().msg("调用商品服务失败");
    }


    public JsonResult<?> decreaseNumberFB(List<Item> items) {

        return JsonResult.err().msg("调用商品服务失败");
    }

}
