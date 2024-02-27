package example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;


@RestController
@RequestScope
@RequestMapping("/api/v1/auth/deneme")
@RequiredArgsConstructor
public class Deneme {


    @GetMapping()
    public ResponseEntity <String> register(

    ){
return ResponseEntity.ok("merhaba dünyalı");
    }
}
