package co.com.ias.reactive_test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Getter
@ToString
@AllArgsConstructor
@Table("account")
public class User {

    @Id
    private Integer id;
    private String name;
    private String email;
}
