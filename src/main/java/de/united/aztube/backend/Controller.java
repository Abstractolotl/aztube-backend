package de.united.aztube.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/qr")
public class Controller {

    @GetMapping(path = "/generate")
    public CodeGenerator getCode() {
        CodeGenerator codeGenerator = new CodeGenerator();
        return codeGenerator;
    }
}