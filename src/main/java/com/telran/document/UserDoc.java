package com.telran.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "username")
@Document(collection = "users")
public class UserDoc {
    @Id
    String username;
    String password;
    String firstName;
    String lastName;
    String phone;
    String photoUrl;
    List<String> roles;
}