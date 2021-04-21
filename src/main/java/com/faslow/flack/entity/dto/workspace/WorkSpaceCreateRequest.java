package com.faslow.flack.entity.dto.workspace;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkSpaceCreateRequest {

    @ApiModelProperty(value = "워크 스페이스 이름")
    private String workspaceName;

}
