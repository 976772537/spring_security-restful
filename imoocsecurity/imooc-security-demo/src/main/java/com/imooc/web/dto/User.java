package com.imooc.web.dto;

import com.imooc.web.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
//    public interface UserSimpleView {}
//    public interface UserDetailView extends  UserSimpleView {}
    private String id;

    @MyConstraint (message = "this is constraint by myself")
    private String Username;
    @ApiModelProperty(value = "生日")
    @NotBlank(message = "password not be empty")
    private String password;
    @Past(message = "birthday always be before")
    private Date birthday;

/*

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
*/
}
