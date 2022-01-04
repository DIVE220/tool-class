package com.fang.demo;

import com.fang.generalTools.utils.QRCodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Mr.Fang
 * @description 测试二维码
 * @date 2021/12/29
 */
@SpringBootTest
public class TestQRCodeUtil {

    @Test
    public void testOne() {
        String str = "";
        try {
            str = QRCodeUtil.encode("http://bifangze.cn/" , "E:\\1.jpg" , "E:\\" , "自制二维码" , true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("二维码名称：" + str);
    }
}
