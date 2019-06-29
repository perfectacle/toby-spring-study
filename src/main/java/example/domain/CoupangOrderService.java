package example.domain;

import org.springframework.stereotype.Service;

@Service
public class CoupangOrderService implements OrderService {
    private OrderService otherService;

    // 스프링 4.3부터 생성자가 하나이면 @Autowired 어노테이션이 생략 가능하다.
    // 파라미터로 넘긴 변수 이름이 빈의 이름이 된다.
    public CoupangOrderService(OrderService timonOrderService) {
        this.otherService = timonOrderService;
    }

    public OrderService getOtherService() {
        return otherService;
    }
}
