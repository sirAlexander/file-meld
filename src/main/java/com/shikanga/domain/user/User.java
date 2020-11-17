package com.shikanga.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "user")
@JsonPropertyOrder({"userId", "firstName", "lastName", "userName", "userType", "lastLoginTime"})
public class User {

    @JacksonXmlProperty(localName = "userid")
    Integer userId;
    @JacksonXmlProperty(localName = "firstname")
    String firstName;
    @JacksonXmlProperty(localName = "surname")
    String lastName;
    @JacksonXmlProperty(localName = "username")
    String userName;
    @JacksonXmlProperty(localName = "type")
    String userType;
    @JacksonXmlProperty(localName = "lastlogintime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime lastLoginTime;


}
