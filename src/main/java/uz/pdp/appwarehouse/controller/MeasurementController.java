package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byMeasurementId/{measurementId}")
    public Result deleteMeasurement(@PathVariable Integer measurementId) {
        Result result = measurementService.deleteMeasurement(measurementId);
        return result;
    }

    //Update
    @PutMapping (value = "/byMeasurementId/{measurementId}")
    public Result editMeasurement(@PathVariable Integer measurementId, @RequestBody Measurement measurement) {
        Result result = measurementService.editMeasurement(measurementId, measurement);
        return result;
    }

    @GetMapping
    public List<Measurement> getMeasurements() {
        List<Measurement> measurementList = measurementService.getMeasurements();
        return measurementList;
    }

    //Get List, Get one, edit, delete
}
