package com.imooc.web.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    public interface UserSimpleView {}
    public interface UserDetailView extends  UserSimpleView {}

    private String Username;
    private String password;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
