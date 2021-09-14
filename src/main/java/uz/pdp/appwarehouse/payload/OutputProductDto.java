package uz.pdp.appwarehouse.payload;

import lombok.Data;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.entity.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
@Data
public class OutputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private Integer outputId;

    private Integer outputProductId;
}
