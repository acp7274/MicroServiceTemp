package JavaBrains.cdinfoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JavaBrains.cdinfoservice.domain.CD;

@RestController
@RequestMapping("/cds")
public class CDController {

    @RequestMapping("/{cdId}")
    public CD getCDInfo(@PathVariable("cdId") String cdId) { return new CD(cdId, "CD Test Title");}
}
