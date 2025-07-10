package com.example.backend.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class registerDTO implements Serializable {
    private String user;
    private String password;
    private String rol;
}
