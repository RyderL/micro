package com.micro.console.controller;

import com.micro.common.util.EnumUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/enum")
public class EnumController {

    @RequestMapping(value = "/{e}")
    public Map<String, String> queryEnum(@PathVariable(value = "e") String e) {
        return EnumUtil.queryEnum(e);
    }
}
