package de.united.aztube.backend.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class YTDLController {

    @GetMapping(path = "/dosomething")
    public @ResponseBody
    String doSomething() {
        System.out.println("Hello from Spring backend");
        return "Hello Frontend";
    }

}
