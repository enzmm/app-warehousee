package uz.pdp.appwarehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.AttachmentContent;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.AttachmentDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.AttachmentContentRepository;
import uz.pdp.appwarehouse.repository.AttachmentRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        //attachmentni saqlash
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        //contentni saqlash
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("Fayl saqlandi", true, savedAttachment.getId());
    }

    @DeleteMapping(value = "/byAttachmentId/{attachmentId}")
    public Result deleteAttachment(@PathVariable Integer attachmentId){
        attachmentRepository.deleteById(attachmentId);
        return new Result("delete",true);
    }

    //UPDATE
    @PutMapping(value = "/attachment/{id}")
    public Result editAttachment(@PathVariable Integer id, @RequestBody AttachmentDto attachmentDto) {
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
        if (optionalAttachmentContent.isPresent()) {
            AttachmentContent attachmentContent = optionalAttachmentContent.get();
            attachmentContent.setBytes(attachmentDto.getBytes());

            Attachment attachment = attachmentContent.getAttachment();
            attachment.setName(attachmentDto.getName());
            attachment.setSize(attachmentDto.getSize());
            attachment.setContentType(attachmentDto.getContentType());
            attachmentRepository.save(attachment);

            attachmentContentRepository.save(attachmentContent);
            return new Result("Attachment edited",true);
        }
        return new Result("Attachment not found", false);
    }

    @GetMapping
    public List<Attachment> getAttachments()
    {
        return  attachmentRepository.findAll();
    }

}
