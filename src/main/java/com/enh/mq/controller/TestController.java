package com.enh.mq.controller;

import com.enh.mq.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * 描    述：
 *
 * 创 建 者： liudh
 * 创建时间： 2017/5/18 14:03
 * 创建描述：
 *
 */
@Controller
public class TestController {

    @Autowired
    private Producer producer;
    @Value("#{appConfig['mq.queue']}")
    private String queueId;

    /**
     * @Description: 消息队列
     * @Author:
     * @CreateTime:
     */
    @ResponseBody
    @RequestMapping("/sendQueue")
    public String testQueue() {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", "hello rabbitmq");
            producer.sendQueue(queueId + "_exchange", queueId + "_patt", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "发送完毕";
    }

}
