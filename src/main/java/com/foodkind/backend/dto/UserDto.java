package com.foodkind.backend.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private Set<String> roles;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
