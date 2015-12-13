package com.analytics.spring.view;

import com.analytics.spring.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GEO on 08.05.15.
 */
@Controller
@RequestMapping("/deal")
@SessionAttributes({"listData"})
public class DealController {

    @Autowired
    private DealService dealService;

    @RequestMapping("/search")
    public String searchFile(Model model) {
        return "deal.page";
    }

    @RequestMapping(value = "/send_file_path", method = RequestMethod.POST)
    public
    @ResponseBody
    List<List<String>>
    filePath(Model model, @RequestParam(value = "file") MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        List<List<String>> listData = dealService.preViewDataFromCSV(convFile);
        model.addAttribute("listData", listData);

        return listData;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody Integer
    fileUpload(@RequestParam("item") String item,
               @RequestParam("selectDateFormat") String selectDateFormat,
               @RequestParam("column") String column,
               @ModelAttribute("listData") List<List<String>> listData) {

        List<List<String>> listOfData=listData;

        List<String> items = null;
        List<Integer> columns = null;
        try {
            String[] stringItems=item.split(",");
            String[] stringColumn = column.split(",");
            items = new ArrayList<>();
            columns = new ArrayList<>();
            for (int i=0; i<stringColumn.length;i++) {
                //columns - array of Number of selected columns from viewed table
                columns.add(Integer.parseInt(stringColumn[i]));
                //items - array of Name of selected columns from viewed table
                items.add(stringItems[i]);
            }

            int addedRow= dealService.savePersedListToDB(items,columns,listOfData, selectDateFormat);

            return addedRow;
        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }
    }

}
