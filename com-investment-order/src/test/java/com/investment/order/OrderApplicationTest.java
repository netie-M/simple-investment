package com.investment.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import reactor.bus.Event;
//import reactor.bus.EventBus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTest {
//    @Autowired
//    @Qualifier("spring_reactor")
//    private Reactor reactor;


//    @Autowired
//    private EventBus eventBus;
    @Test
    public void test(){
        
//        PayVo payVo = PayVo.builder().orderNo("anc").orderAmt(BigDecimal.ONE).build();
//        String json = JSON.toJSONString(payVo);
//        System.out.println(json);
//        reactor.notify("default",Event.wrap(json));
//        reactor.notify("success",Event.wrap(PayVo.builder().orderNo("anc2").orderAmt(BigDecimal.ZERO).payNo("dkekke").build()));
//        reactor.notify("success",Event.wrap(payVo));
//        eventBus.notify("success", Event.wrap(payVo));
    }
}