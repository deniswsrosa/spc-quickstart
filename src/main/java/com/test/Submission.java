package com.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Submission {


    public static final String APPROVED = "APPROVED";
    public static final String UNDECIDED = "UNDECIDED";
    public static final String REJECTED = "REJECTED";

    private String id;
    private String userId;
    private String talkId;
    private String status;

    @CreatedDate
    private long createdDate;
}
