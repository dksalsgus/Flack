package com.faslow.flack.controller;

import com.faslow.flack.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;
}
