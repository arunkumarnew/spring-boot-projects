package com.example.demo.controller;

import com.example.demo.utility.RestTemplateHelper;
import com.example.demo.utility.RoutingDataParser;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.ResultVO;
import com.example.demo.vo.RouteData;
import com.example.demo.vo.UIResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class OrchestrationController {

    @Autowired
    private RoutingDataParser cParser;

    @Autowired
    private RestTemplateHelper restHelper;

    @GetMapping("/getData")
    public ResponseEntity<UIResponseBean> invokeAPIGet()  {
        RouteData route = cParser.parseJonData("GET-ALL-USERS-REQRES");

        ResultVO dataList = restHelper.getForEntity(ResultVO.class, route.getUrl());
        UIResponseBean responseBean = new UIResponseBean();
        responseBean.setOperation("TEST-GET");
        responseBean.setStatus("Success");
        responseBean.setResult(dataList);
        return new ResponseEntity<>(responseBean, OK);
    }
    @PostMapping("/postData")
    public ResponseEntity<UIResponseBean> invokeAPIPost(@RequestBody String jsonInput){
        RouteData route = cParser.parseJonData("GET-ALL-USERS-REQRES");
        UIResponseBean responseBean = new UIResponseBean();
        responseBean.setOperation("TEST-POST");
        responseBean.setStatus("Success");
        responseBean.setResult(route);
        return new ResponseEntity<>(responseBean, OK);
    }

}
