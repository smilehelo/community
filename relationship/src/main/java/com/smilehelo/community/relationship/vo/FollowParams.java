package com.smilehelo.community.relationship.vo;

import lombok.Data;

/**
 * <pre>
 *
 * project: community
 * className：FollowParams
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-01 16:57
 *
 * </pre>
 **/
@Data
public class FollowParams extends BaseParams{

    private String targetUserId;
}
