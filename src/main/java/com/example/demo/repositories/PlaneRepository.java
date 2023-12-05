package src.main.java.com.example.demo.repositories;

import com.example.demo.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane,Long> {
}
