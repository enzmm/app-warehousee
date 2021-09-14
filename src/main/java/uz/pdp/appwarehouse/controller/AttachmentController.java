package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.AttachmentDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.AttachmentService;

import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        Result result = attachmentService.uploadFile(request);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byAttachmentId/{attachmentId}")
    public Result deleteAttachment(@PathVariable Integer attachmentId) {
        Result result = attachmentService.deleteAttachment(attachmentId);
        return result;
    }

    @PutMapping(value = "/university/{id}")
    public Result editAttachment(@PathVariable Integer attachmentId, @RequestBody AttachmentDto attachmentDto){
       Result result = attachmentService.editAttachment(attachmentId, attachmentDto);
       return result;
    }

    @GetMapping
    public List<Attachment> getAttachments() {
        List<Attachment> attachmentList = attachmentService.getAttachments();
        return attachmentList;
    }
}
