package uz.pdp.appwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Currency extends AbsEntity {

    private Integer currencyId;
}

  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//1, 10, 13

    private String name;// Electronic, Telephone, Portotive technics

    private boolean active;*/

