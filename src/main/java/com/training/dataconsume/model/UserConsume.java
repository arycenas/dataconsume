package com.training.dataconsume.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_consume")
public class UserConsume {

    private Long id;
    private String username;
    private String password;
    private String token;
    private String role;
}
