package co.com.ias.reactive_test.handlers;

import co.com.ias.reactive_test.components.OtherUserRouter;
import co.com.ias.reactive_test.model.User;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest(OtherUserRouter.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserHandlerTest {

    @MockBean
    private UserHandler handler;
    @Autowired
    private WebTestClient testClient;

    static User user1;
    static User user2;
    static User user3;
    @BeforeAll
    public static void beforeClass() throws Exception {
        user1 = User.builder().id(1).name("Pepe").email("pepe@example.com").build();
        user2 = User.builder().id(2).name("Jhon").email("jhon@example.com").build();
        user3 = User.builder().id(3).name("David").email("david@example.com").build();
    }

    @Test
    void listUsers() {

        List<User> userList = List.of(user1, user2, user3);

        Mono<ServerResponse> response = ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userList);

        when(handler.listUsers(any(ServerRequest.class))).thenReturn(response);

        /*Flux<User> rta = */
        testClient.get()
                .uri("/users")
                .exchange()
                .expectStatus()
                .isOk();
/*                .returnResult(User.class)
                .getResponseBody();*/

/*        StepVerifier.create(rta)
                .expectNext(user1)
                .expectNext(user2)
                .expectNext(user3)
                .verifyComplete();*/

    }

}