package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    //Post
    public Result addMeasurementService(Measurement measurmenet) {
        boolean existsByName = measurementRepository.existsByName(measurmenet.getName());
        if(existsByName) {
            Result result = new Result();
            result.setMessage("Bunday o'lchov birligi mavjud");
            result.setSuccess(false);
             //bu 3ta qator kod pastagi 1qator kod bilan bir xil........
            //return new Result("Bunday o'lchov birligi mavjud", false)
            return result;
        }
        measurementRepository.save(measurmenet);

        Result result = new Result();
        result.setMessage("Muaffaqiyatli saqlandi");
        result.setSuccess(true);
       // return new Result("Muaffaqiyatli saqlandi", true);
        return result;
    }

    @DeleteMapping(value = "/byMeasurementId/{measurementId}")
    public Result deleteMeasurement(@PathVariable Integer measurementId){
        measurementRepository.deleteById(measurementId);
        return new Result("delete",true);
    }

    @PutMapping("/byMeasurementId/{measurementId}")
    public Result editMeasurement(@PathVariable Integer measurementId , @RequestBody Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurement.getMeasurementId());
        if (optionalMeasurement.isPresent()) {
            Measurement measurement1 = optionalMeasurement.get();
            measurement1.setName(measurement.getName());
            measurementRepository.save(measurement1);
        }
        return new Result("edit qilindi", true);
    }

    @GetMapping
    public List<Measurement> getMeasurements()
    {
        return  measurementRepository.findAll();
    }
}
