package org.example.Bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    private String chatId;
    private String name;
    private String userName;
    private UserStatus userStatus;
    private String phoneNumer;
}
