package co.com.ias.reactive_test.components;

import co.com.ias.reactive_test.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class OtherUserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler handler){
        return RouterFunctions.route()
                .path("users", builder -> {
                    builder.GET("", accept(MediaType.APPLICATION_JSON), handler::listUsers);
                    builder.GET("{id}", accept(MediaType.APPLICATION_JSON), handler::getUser);
                    builder.POST("", accept(MediaType.APPLICATION_JSON), handler::createUser);
                })
                .build();
    }
}
