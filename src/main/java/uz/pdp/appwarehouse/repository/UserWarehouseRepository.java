package uz.pdp.appwarehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appwarehouse.entity.UserWarehouse;

public interface UserWarehouseRepository extends JpaRepository<UserWarehouse, Integer> {
}
