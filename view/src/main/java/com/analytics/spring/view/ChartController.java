package com.analytics.spring.view;

import com.analytics.spring.model.dto.CountDealsByTool;
import com.analytics.spring.model.dto.PAndLByDay;
import com.analytics.spring.service.GraphicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by Alexandr Velikiy on 06.05.2015.
 */

@Controller
@RequestMapping("/charts")
public class ChartController {

    @Autowired
    private GraphicService graphicService;

    @RequestMapping(value = "/countDealsByTool")
    public String  getCountByToolsChart(Model model){

        List<CountDealsByTool> chartData = graphicService.getCountDealsByTool();
        model.addAttribute("data",chartData);
        return "chartCountDealsByTool.page";
    }

    @RequestMapping(value = "/getChartDataCountDealsByTool", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody List<CountDealsByTool> getChartData(){

        List<CountDealsByTool> chartData = graphicService.getCountDealsByTool();
        return chartData;
    }


    @RequestMapping(value = "/getChartDataPAndLByDay", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody List<PAndLByDay> getChartDataPAndLByDay(){

        List<PAndLByDay> chartData = graphicService.getPAndLByDay();
        return chartData;

    }

//    @RequestMapping(value = "/pAndLByDay")
//    public String  getPAndLByDayChart(){
//        return "chartPAndLByDay.page";
//    }

    @RequestMapping(value = "/pAndLByDay")
    public String  getPAndLByDayChart(Model model){

        List<PAndLByDay> chartData = graphicService.getPAndLByDay();
        model.addAttribute("data",chartData);
        model.addAttribute("tools", graphicService.getCountDealsByTool());
        return "chartPAndLByDay.page";
    }



    //todo
    @RequestMapping(value = "/getChartDataPAndLByDay/{tool}", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody List<PAndLByDay> getChartDataPAndLByTool(@PathVariable("tool") String tool){

        List<PAndLByDay> chartData = graphicService.getPAndLByTool(tool);

        return chartData;
    }



    @RequestMapping(value = "/{tool}")
    public String  getCurrencyPairChart(@PathVariable String tool){
        return "graph.page";
    }

}
