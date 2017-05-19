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
    @Value("#{appConfig['mq.queue1']}")
    private String queueId1;
    @Value("#{appConfig['mq.queue2']}")
    private String queueId2;
    @Value("#{appConfig['mq.queue3']}")
    private String queueId3;

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
            map.put("data", "hello rabbitmq ");
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("data", "hello rabbitmq liudh");
            producer.sendQueue(queueId + "_exchange", queueId + "_patt", map);
            producer.sendQueue(queueId + "_exchange", "liudh_patt", maps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "发送完毕";
    }

}
