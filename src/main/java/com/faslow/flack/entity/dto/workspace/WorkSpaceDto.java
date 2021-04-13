package com.faslow.flack.entity.dto.workspace;

import com.faslow.flack.entity.workspace.WorkSpace;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
public class WorkSpaceDto {

    @ApiModelProperty(value = "워크스페이스 이름")
    private String workspaceName;

    @ApiModelProperty(value = "채널")
    private int channelNo;

    @ApiModelProperty(value = "권한")
    private WorkSpace.enWorkSpaceRole workspaceRole;

    public WorkSpaceDto(WorkSpace workSpace) {
        BeanUtils.copyProperties(workSpace, this);
    }
}
