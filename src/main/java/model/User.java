package model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Для тестов логина
 */
@Data
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;
}
