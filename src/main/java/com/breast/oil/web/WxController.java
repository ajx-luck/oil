package com.breast.oil.web;

import com.breast.oil.utils.SignUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WxController {

    @ResponseBody
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public ResponseEntity deletewx(ModelMap map, HttpServletRequest request) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && SignUtil.checkSignature(signature, timestamp, nonce)) {
            return new ResponseEntity(echostr, HttpStatus.OK);
        }
        return new ResponseEntity("", HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String fx8(ModelMap map, HttpServletRequest request) {
        return "test";
    }


}
