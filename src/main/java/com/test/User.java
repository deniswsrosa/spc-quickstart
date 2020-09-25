package com.test;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.index.CompositeQueryIndex;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;

@ToString
@Data
@Document
@TypeAlias("user")
@CompositeQueryIndex(fields = {"id", "username", "email"})
public class User {

    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
    private List<Preference> preferences;
    private Address address;
    private String _class;
    private int credits;
    private List<Submission> submissions;
}


//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getTagLine() {
//        return tagLine;
//    }
//
//    public void setTagLine(String tagLine) {
//        this.tagLine = tagLine;
//    }
//}
