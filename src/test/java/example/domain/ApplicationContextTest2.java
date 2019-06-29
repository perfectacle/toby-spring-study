package example.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ComponentScanConfig.class})
class ApplicationContextTest2 {
    @Autowired
    private OrderService orderService;

//    @Autowired
//    private CoupangOrderService coupangOrderService;

    @Test
    void test() {
        assertNotNull(orderService);
//        assertNotNull(coupangOrderService.getOtherService());
    }
}