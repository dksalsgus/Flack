package com.faslow.flack.entity.dto.chat;

import com.faslow.flack.entity.channel.Channel;
import com.faslow.flack.entity.chat.Chat;
import com.faslow.flack.entity.profile.Profile;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@AllArgsConstructor
public class ChatDto {

    @ApiModelProperty(value = "채팅 메세지")
    private String chatMsg;

    @ApiModelProperty(value = "보내는 사람")
    private Profile profile;

    @ApiModelProperty(value = "채팅 보낸 시간")
    private String createAt;

    @ApiModelProperty(value = "채팅 속한 채널")
    private Channel channel;

    public ChatDto(Chat chat) {
        BeanUtils.copyProperties(chat, this);
    }
}
