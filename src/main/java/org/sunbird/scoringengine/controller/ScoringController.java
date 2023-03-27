
package org.sunbird.scoringengine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sunbird.scoringengine.models.EvaluatorModel;
import org.sunbird.scoringengine.models.Response;
import org.sunbird.scoringengine.service.ScoringEngineService;

@RestController
@RequestMapping("/v1")
public class ScoringController {

    @Autowired
    ScoringEngineService scoringEngineService;

    @PostMapping("/add")
    public ResponseEntity<Response> add(@RequestBody EvaluatorModel evaluatorModel, @RequestHeader String rootOrg,
                                        @RequestHeader String org) throws Exception {
        evaluatorModel.setRootOrg(rootOrg);
        evaluatorModel.setOrg(org);
        return new ResponseEntity<>(scoringEngineService.addV3(evaluatorModel), HttpStatus.OK);
    }

    @PostMapping("/fetch")
    public ResponseEntity<Response> search(@RequestBody EvaluatorModel evaluatorModel, @RequestHeader String rootOrg,
                                           @RequestHeader String org) throws Exception {
        evaluatorModel.setRootOrg(rootOrg);
        evaluatorModel.setOrg(org);
        return new ResponseEntity<>(scoringEngineService.searchV2(evaluatorModel), HttpStatus.OK);
    }

    @GetMapping("/getTemplate/{templateId}")
    public ResponseEntity<Response> getTemplateConfiguration(@RequestHeader String rootOrg,
                                           @RequestHeader String org, @PathVariable("templateId") String templateId) throws Exception {
        return new ResponseEntity<>(scoringEngineService.getTemplate(templateId, rootOrg, org), HttpStatus.OK);
    }

}
