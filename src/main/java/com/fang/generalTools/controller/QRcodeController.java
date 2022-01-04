package com.fang.generalTools.controller;

import com.fang.generalTools.pojo.Result;
import com.fang.generalTools.utils.QRCodeUtil;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;


/**
 * @author Mr.Fang
 * @description 二维码测试接口
 * @date 2022/1/4
 */
@RestController
@CrossOrigin
public class QRcodeController {

    @RequestMapping("/encode")
    public Result encode(HttpServletResponse response , String content , String logoPath ){
        try {
            OutputStream stream = response.getOutputStream();
            QRCodeUtil.encode(content , stream );
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().error(e.getMessage());
        }
        return new Result().success();
    }
}
