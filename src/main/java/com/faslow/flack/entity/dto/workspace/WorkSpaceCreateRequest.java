package com.faslow.flack.entity.dto.workspace;

import com.faslow.flack.entity.workspace.WorkSpace;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
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

    // 생성날짜
    @ApiModelProperty(value = "워크스페이스 권한")
    private LocalDateTime createAt;

    // 수정날짜
    @ApiModelProperty(value = "워크스페이스 권한")
    private LocalDateTime updateAt;

}
