package cn.tedu.sp09.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service",fallback = UserClientFB.class)
public interface UserClient {

    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable("userId") Integer userId);

    @GetMapping("/{userId}/score")
    JsonResult<?> addScore(@PathVariable("userId") Integer userId,@RequestParam Integer score);
}
