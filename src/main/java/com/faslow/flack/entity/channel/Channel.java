package com.faslow.flack.entity.channel;

import com.faslow.flack.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
public class Channel extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channelNo;

    @NotNull
    private String channelName;

    private String channelInfo;
}
