package com.investment.order.event;

import org.springframework.stereotype.Component;
//import reactor.core.Reactor;
//import reactor.spring.annotation.ReplyTo;
//import reactor.spring.annotation.Selector;
//import reactor.core.Reactor;
//import reactor.spring.annotation.ReplyTo;
//import reactor.spring.annotation.Selector;

@Component
public class PaySpringReactorHandler {
//    @Autowired
//    @Qualifier("spring_reactor")
//    public Reactor spring_reactor;
//
//    @Selector(value = "success",reactor = "spring_reactor")
//    @ReplyTo(value = "failed")
//    public PayVo paySuccessHandler(PayVo payVo){
//        System.err.println("on success");
//        System.err.println(payVo.getOrderNo());
//        System.err.println(JSON.toJSONString(payVo));//TODO 不起效
//        payVo.setPayNo("123");
//        return payVo;
//    }
//    @Selector(value = "failed",reactor = "spring_reactor")
////@Selector(value="hello",reactor="@rootReactor")
//    public void payFailedHandler(PayVo vo){
//        System.err.println("on failed");
//        System.err.println(vo.getPayNo());
//    }
//
//    @Selector(value = "default",reactor = "spring_reactor")
//    public void payDefaultHandler(String json){
//        System.err.println(json);
//
//        System.err.println(JSON.parseObject(json).get("orderNo"));
//        System.err.println(json);
//    }
}
