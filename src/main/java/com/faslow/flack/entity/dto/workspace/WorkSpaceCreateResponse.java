package com.faslow.flack.entity.dto.workspace;

import com.faslow.flack.entity.workspace.WorkSpace;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
public class WorkSpaceCreateResponse {

    @ApiModelProperty(value = "워크스페이스")
    private WorkSpace workSpace;
}
