package com.faslow.flack.entity.dto.channel;

import com.faslow.flack.entity.channel.Channel;
import com.faslow.flack.entity.workspace.WorkSpace;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
public class ChannelDto {

    private String channelName;

    private String channelInfo;

    private WorkSpace workSpace;

    public ChannelDto(Channel channel) {
        BeanUtils.copyProperties(channel, this);
    }
}
