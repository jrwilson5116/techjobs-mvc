package org.launchcode.controllers;

import javafx.scene.control.RadioButton;
import org.launchcode.controllers.ListController;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }


    @RequestMapping(value = "/results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        model.addAttribute("columns", ListController.columnChoices);

        ArrayList results;

        if("all".equals(searchType))
            results = JobData.findByValue(searchTerm);
        else
            results = JobData.findByColumnAndValue(searchType,searchTerm);

        if(!results.isEmpty())
            model.addAttribute("jobs", results);

        return "search";
    }

}
