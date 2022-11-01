package com.smilehelo.community.relationship.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * project: community
 * className：EchoController
 * description:
 * @author: HeLO
 * version: V1.0
 * createDate: 2022-11-01 16:43
 *
 * </pre>
 **/
@RequestMapping("/")
@RestController
public class EchoController {

    @RequestMapping("/")
    public String echo() {
        return "Hello world!";
    }

}
