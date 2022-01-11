package de.united.aztube.backend.Controller;

import de.united.aztube.backend.CodeGenerator;
import de.united.aztube.backend.Model.RegisterRequest;
import de.united.aztube.backend.Model.RegisterResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class YTDLController {

    @GetMapping(path = "/dosomething")
    public @ResponseBody
    String doSomething() {
        System.out.println("Hello from Spring backend");
        return "Hello Frontend";
    }

    @PostMapping(path = "/register")
    public @ResponseBody
    RegisterResponse register(@RequestBody RegisterRequest request) {
        RegisterResponse response = new RegisterResponse(true, "", UUID.randomUUID());
        return response;
    }

}
