package code.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * guava demo
 *
 * @author wangwenjie
 * @date 2020-11-22
 */
public class GuavaDemo {
    //test bus 总线
    static EventBus bus = new EventBus();

    static {
        bus.register(new GuavaDemo());
    }

    public static void main(String[] args) {
        bus.post(new AEvent("张麻子"));
    }

    @Subscribe
    public void handle(AEvent event){
        System.out.println(event.getName());
    }

    @Data
    @AllArgsConstructor
    public static class AEvent{
        private String name;
    }
}
