package uz.pdp.appwarehouse.payload;

import lombok.Data;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Supplier;
import uz.pdp.appwarehouse.entity.Warehouse;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
public class InputDto {
    private Timestamp date;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
    private Integer inputId;
}
