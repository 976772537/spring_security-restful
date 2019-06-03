package com.imooc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryCondition {
    private String Username;
    private int age;
    private int ageTo;
    private String sex;
}
