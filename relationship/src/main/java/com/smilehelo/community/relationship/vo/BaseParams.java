package com.smilehelo.community.relationship.vo;

import lombok.Data;

/**
 * <pre>
 *
 * project: community
 * className：BaseParams
 * description: 基础请求参数
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-01 17:22
 *
 * </pre>
 **/
@Data
public class BaseParams {

    private String appId;

    private String userId;

    private Integer page;

    private Integer pageSize;


}
