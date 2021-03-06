package com.eqshen.webdemo;

import com.eqshen.webdemo.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Controller
public class HelloController {

    @Autowired
    private MessageProducer msgProducer;

    @Autowired
    private CallBackProducer callBackProducer;

    @Autowired
    private DirectProducer directProducer;

    @Autowired
    private TopicProducer topicProducer;

    @Autowired
    private DeadLetterProducer deadLetterProducer;

    @RequestMapping("/hello")
    @ResponseBody
    public Object hello(){
        HashMap<String,String> result=new HashMap<>(14);
        result.put("name","hendy");
        result.put("tel","123456789");
        return result;
    }

    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("username","hendy");
        return "/index";
    }

    @RequestMapping("/simple")
    @ResponseBody
    public String simpleQueue(){
        msgProducer.simpleSend("hello simple queue");
        return "ok";
    }

    @RequestMapping("/direct")
    @ResponseBody
    public String directQueue(){
        directProducer.directSend("hello direct msg");
        return "ok";
    }

    @RequestMapping("/direct_callback")
    @ResponseBody
    public String directCallback(){
        callBackProducer.send("hello call back");
        return "ok";
    }

    @RequestMapping("/topic")
    @ResponseBody
    public String topic(){
        topicProducer.send("hello topic ");
        return "ok";
    }

    @RequestMapping("/dead")
    @ResponseBody
    public String dead(){
        deadLetterProducer.send("this is a msg...");
        return "OK";
    }
}
