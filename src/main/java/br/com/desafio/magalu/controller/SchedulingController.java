package br.com.desafio.magalu.controller;

import br.com.desafio.magalu.dto.SchedulingDTO;
import br.com.desafio.magalu.service.SchedulingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scheduling/v1/")
@Tag(name = "scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("returnAll")
    public ResponseEntity<List<SchedulingDTO>> listAll() {
        List<SchedulingDTO> transactionDTOS = schedulingService.listAll();
        return ResponseEntity.ok(transactionDTOS);
    }

    @GetMapping("returnSchedulingEmail/{email}")
    public ResponseEntity<List<SchedulingDTO>> schedulingListEmail(@PathVariable(value = "email") String email) {
        List<SchedulingDTO> transactionDTOS = schedulingService.schedulingListEmail(email);
        return ResponseEntity.ok(transactionDTOS);
    }

    @GetMapping("returnSchedulingStatus/{status}")
    public ResponseEntity<List<SchedulingDTO>> schedulingListStatus(@PathVariable(value = "status") String status) {
        List<SchedulingDTO> transactionDTOS = schedulingService.schedulingListStatus(status);
        return ResponseEntity.ok(transactionDTOS);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateTransaction(@RequestBody SchedulingDTO schedulingDTO) {
        var identityUpdateScheduling = schedulingService.updateScheduling(schedulingDTO);
        return ResponseEntity.status(HttpStatus.OK).body(identityUpdateScheduling);
    }

    @PostMapping("insert")
    public ResponseEntity<?> insertTransaction(@RequestBody SchedulingDTO schedulingDTO) {
        var identityInsertScheduling = schedulingService.insertScheduling(schedulingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(identityInsertScheduling);
    }

}
