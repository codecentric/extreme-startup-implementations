package de.britter.extremestartup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ExtremeStartupController {

    @RequestMapping("/")
    @ResponseBody
    public String handleRequest(@RequestParam("q") String q) {
        System.out.println(q);
        String result;

        result = Plus.plus(q);

        if (result == null) {
            result = Multiply.multiply(q);
        }


        if (result == null) {
            result = Largest.largest(q);
        }

        if (result == null && Pattern.compile(".*?what currency did Spain use before the Euro").matcher(q).matches()) {
            result = "Peseta";
        }
        if (result == null && Pattern.compile(".*?who is the Prime Minister of Great Britain").matcher(q).matches()) {
            result = "David Cameron";
        }
        if (result == null && Pattern.compile(".*?what colour is a banana").matcher(q).matches()) {
            result = "yellow";
        }
        if (result == null && Pattern.compile(".*?who played James Bond in the film Dr No").matcher(q).matches()) {
            result = "Sean Connery";
        }

        System.out.println("result: " + result);
        return result;
    }
}
