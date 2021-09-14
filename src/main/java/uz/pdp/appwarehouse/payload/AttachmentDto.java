package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class AttachmentDto {
    private String name;
    private long size;
    private String contentType;
    private byte[] bytes;
}
