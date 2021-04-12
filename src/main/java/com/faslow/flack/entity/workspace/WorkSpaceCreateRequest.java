package com.faslow.flack.entity.workspace;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkSpaceCreateRequest {

    @ApiModelProperty(value = "워크 스페이스 이름")
    private String workspaceName;

    @ApiModelProperty(value = "채널 번호")
    private int channelNo;

    @ApiModelProperty(value = "워크스페이스 권한")
    private WorkSpace.enWorkSpaceRole workspaceRole;

}
