package cn.tedu.sp01.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
lombok 插件和 lombok 依赖的版本需要匹配，
如果不匹配，代码无法生成

如果代码不能生成，可以升级lombok插件，或调整lombok依赖的版本
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Integer id;
    private String name;
    private Integer number;


    // public static void main(String[] args) {
    //     Item item = new Item();
    //     item.setId(3);
    //     System.out.println(item.getId());
    // }
}
