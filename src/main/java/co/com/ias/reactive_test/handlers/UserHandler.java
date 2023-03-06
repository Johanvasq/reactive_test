package co.com.ias.reactive_test.handlers;

import co.com.ias.reactive_test.model.User;
import co.com.ias.reactive_test.repository.IUserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private final IUserRepository repository;

    public UserHandler(IUserRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> listUsers(ServerRequest request){
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.findAll(), User.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest request){
        Mono<User> user = repository.findById(Integer.parseInt(request.pathVariable("id")));
        return ServerResponse
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(user, User.class)
              .switchIfEmpty(ServerResponse
                      .notFound().build());
    }

    public Mono<ServerResponse> createUser(ServerRequest request){
        Mono<User> user = request.bodyToMono(User.class);
        return ServerResponse
              .ok()
              .contentType(MediaType.APPLICATION_JSON)
              .body(user.flatMap(repository::save), User.class);
    }
}
