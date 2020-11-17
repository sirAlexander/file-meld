package com.shikanga.domain.user.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public abstract class UserCsvMixin {

    @JsonProperty("User ID")
    abstract Integer getUserId();

    @JsonProperty("First Name")
    abstract String getFirstName();

    @JsonProperty("Last Name")
    abstract String getLastName();

    @JsonProperty("Username")
    abstract String getUserName();

    @JsonProperty("User Type")
    abstract String getUserType();

    @JsonProperty("Last Login Time")
    abstract LocalDateTime getLastLoginTime();
}
