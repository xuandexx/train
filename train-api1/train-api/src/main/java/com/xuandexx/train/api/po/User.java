package com.xuandexx.train.api.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;

    private String facebookId;

    private String witterId;

    private String password;

    private Long nickName;

}
