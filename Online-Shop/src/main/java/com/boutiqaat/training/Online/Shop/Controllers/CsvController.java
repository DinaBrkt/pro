package com.boutiqaat.training.Online.Shop.Controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.boutiqaat.training.Online.Shop.Entities.User;
import com.boutiqaat.training.Online.Shop.Services.UserService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Controller
public class CsvController {

    private UserService userService;

    public CsvController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/export-users")
    public void exportCSV(HttpServletResponse response) throws Exception {

        //set file name and content type
        String filename = "users.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<User> writer = new StatefulBeanToCsvBuilder<User>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(userService.listUsers());
                 
    }
}