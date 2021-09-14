package uz.pdp.appwarehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appwarehouse.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbsEntity {

    @ManyToOne
    private Category parentCategory;// null, 1, 1
}

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//1, 10, 13

    private String name;// Electronic, Telephone, Portotive technics

    @ManyToOne
    private Category parentCategory;// null, 1, 1

    private boolean active;//true, true, true */

