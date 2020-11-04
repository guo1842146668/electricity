package com.example.electricity.controller;

import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.service.IDictService;
import com.example.electricity.tool.DictEnum;
import com.example.electricity.tool.ReadFile;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/advertising")
public class AdvertisingController {
    @Resource
    private IDictService iDictService;

    @GetMapping("/list")
    public Result listAll()
    {
        List<Map<String,Object>> map = new ArrayList<>();
        List<Map<String, Object>> guide = iDictService.getGuide(DictEnum.CustomerType);
        String resource = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("/")).getPath();
        resource = resource.substring(0,resource.lastIndexOf("electricity"));
        String path=resource + guide.get(0).get("dictName");
        List<String> listFiles = ReadFile.getListFiles(path);
        for (int i = 0; i < listFiles.size(); i++) {
            Map<String,Object> map1 = new HashMap<>();
            map1.put("name",guide.get(0).get("dictName")+"//"+listFiles.get(i));
            map.add(map1);
        }
        return ResultUtil.seccess(map);
    }

}
