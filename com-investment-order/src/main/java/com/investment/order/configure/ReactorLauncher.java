package com.investment.order.configure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
//import reactor.bus.EventBus;
//
//import static reactor.bus.selector.Selectors.$;

@Configuration
public class ReactorLauncher implements CommandLineRunner {
//    @Autowired
//    private EventBus eventBus;
//
//    @Autowired
//    private PayBusReactorHandler receiver;

    @Override
    public void run(String... args) throws Exception {
//        eventBus.on($("success"), receiver);
    }

}
