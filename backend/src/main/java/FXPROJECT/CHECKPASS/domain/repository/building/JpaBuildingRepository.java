package FXPROJECT.CHECKPASS.domain.repository.building;

import FXPROJECT.CHECKPASS.domain.entity.building.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaBuildingRepository extends JpaRepository<Buildings, Integer> {

    @Query(value = "select b.buildingName from Buildings b where b.buildingCode= :buildingCode")
    String findBuildingNameByBuildingCode(@Param("buildingCode") Integer buildingCode);

    Buildings findByBuildingCode(Integer buildingCode);
}
