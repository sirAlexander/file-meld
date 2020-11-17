package com.shikanga.domain.user.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public abstract class UserJsonMixin {

    @JsonProperty("user_id")
    abstract Integer getUserId();

    @JsonProperty("first_name")
    abstract String getFirstName();

    @JsonProperty("last_name")
    abstract String getLastName();

    @JsonProperty("username")
    abstract String getUserName();

    @JsonProperty("user_type")
    abstract String getUserType();

    @JsonProperty("last_login_time")
    abstract LocalDateTime getLastLoginTime();
}
