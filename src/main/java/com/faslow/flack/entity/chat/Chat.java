package com.faslow.flack.entity.chat;

import com.faslow.flack.entity.channel.Channel;
import com.faslow.flack.entity.profile.Profile;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatNo;

    @NotNull
    private String chatMsg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profileNo")
    private Profile profile;

    @CreationTimestamp
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channelNo")
    private Channel channel;

}
