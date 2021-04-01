package cn.tedu.sp02.controller;


import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Value("${server.port}")
    private int port;

    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws InterruptedException {
        log.info("获取商品， orderId=" + orderId);
        // 随机延迟
        if (Math.random() < 0.9) { //90%概率执行延迟
            // 随机延迟时长 [0, 5) 秒
            int t = new Random().nextInt(5000);
            log.info("延迟： " + t);
            Thread.sleep(t);
        }
        List<Item> items = itemService.getItems(orderId);
        return JsonResult.ok().data(items).msg("port=" + port);
    }

    /**
     * @RequestBody 注解
     * 从客户端提交的“请求协议体”中，完整接收协议体数据（Json格式），转换成商品列表
     */
    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        itemService.decreaseNumber(items);
        return JsonResult.ok().msg("减少商品库存成功");
    }
}
