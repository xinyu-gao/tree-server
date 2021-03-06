package com.suda.tree.controller;

import com.suda.tree.dao.CityCoordinateRepository;
import com.suda.tree.dao.CityTreeCountRepository;
import com.suda.tree.dao.TreeInfoRepository;
import com.suda.tree.dto.City;
import com.suda.tree.dto.result.HttpResult;
import com.suda.tree.entity.mysql.CityCoordinate;
import com.suda.tree.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private TreePicService treePicService;

    @Autowired
    private TreeInfoRepository treeInfoRepository;

    @Autowired
    private TreeInfoService treeInfoService;

    @Autowired
    private CityCoordinateRepository cityCoordinateRepository;

    @Autowired
    private ExistedProvinceAndCityService existedProvinceAndCityService;

    @Autowired
    private CityTreeCountService cityTreeCountService;

    @Autowired
    private CityTreeCountRepository cityTreeCountRepository;

    @PostMapping("/1")
    public HttpResult pic(){
        return HttpResult.success(existedProvinceAndCityService.saveExistedProvinceAndCityStatistic());
    }

    @PostMapping("")
    public HttpResult test(){
        return HttpResult.success(cityTreeCountRepository.findCityAndCoordinate());
    }

    @GetMapping("/ws")
    public void test2() throws IOException, EncodeException {
        WebSocketService.sendInfoForAll("111");
    }

    @ApiOperation("存数据用，调用baidu map api 的数据，不用调用了")
    @PostMapping("/city")
    public HttpResult save(@RequestBody City o){
        List<CityCoordinate> lists = new ArrayList<>();
        for(City.Location i : o.getMunicipalities()) {
            String[] g = i.getG().split("\\|");
            lists.add(new CityCoordinate(
                    i.getN(),
                    Double.parseDouble(g[0].split(",")[0]),
                    Double.parseDouble(g[0].split(",")[1])
            ));
        }
        for(City.Location i : o.getOther()) {
            String[] g = i.getG().split("\\|");
            lists.add(new CityCoordinate(
                    i.getN(),
                    Double.parseDouble(g[0].split(",")[0]),
                    Double.parseDouble(g[0].split(",")[1])
            ));
        }
        for(City.CityLocation i : o.getProvinces()) {
            for(City.Location j : i.getCities()) {
                String[] g = j.getG().split("\\|");
                lists.add(new CityCoordinate(
                        j.getN(),
                        Double.parseDouble(g[0].split(",")[0]),
                        Double.parseDouble(g[0].split(",")[1])
                ));
            }
        }
        cityCoordinateRepository.saveAll(lists);
        return HttpResult.success(lists);
    }
}
