package FXPROJECT.CHECKPASS.domain.common.initdata;


import FXPROJECT.CHECKPASS.domain.entity.attendance.StartSemesterDays;
import FXPROJECT.CHECKPASS.domain.entity.building.Buildings;
import FXPROJECT.CHECKPASS.domain.entity.college.Colleges;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.enums.BuildingsEnum;
import FXPROJECT.CHECKPASS.domain.enums.CollegesEnum;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.repository.building.JpaBuildingRepository;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaCollegesRepository;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectureweek.JpaLectureWeekRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitData {

    private final JpaCollegesRepository jpaCollegesRepository;
    private final JpaDepartmentRepository jpaDepartmentRepository;
    private final JpaBuildingRepository jpaBuildingRepository;
    private final JpaLectureWeekRepository jpaLectureWeekRepository;

    @PostConstruct
    private void initData(){

        CollegesEnum[] collegesEnums = CollegesEnum.values();

        for (CollegesEnum college : collegesEnums) {
            log.info("college : {}", college.getCollege());
            Colleges colleges = new Colleges();
            colleges.setCollege(college.getCollege());
            jpaCollegesRepository.save(colleges);
        }

        DepartmentsEnum[] departmentsEnums = DepartmentsEnum.values();

        for (DepartmentsEnum department : departmentsEnums) {
            log.info("department : {}", department.getDepartment());
            Colleges colleges = new Colleges();
            Optional<Colleges> findCollege = jpaCollegesRepository.findById(department.getCollegeCode());
            log.info("findCollege : {}", findCollege.get().getCollege());
            Departments departments = new Departments();
            departments.setDepartment(department.getDepartment());
            departments.setColleges(findCollege.get());
            jpaDepartmentRepository.save(departments);
        }

        BuildingsEnum[] buildingsEnums = BuildingsEnum.values();

        for (BuildingsEnum building : buildingsEnums) {
            log.info("Building : {}", building.getBuilding());
            Buildings buildings = new Buildings();
            buildings.setBuildingCode(building.getBuildingCode());
            buildings.setBuildingName(building.getBuilding());
            jpaBuildingRepository.save(buildings);
        }

        StartSemesterDays oneSemesterDay = new StartSemesterDays(1, "2024-03-04");
        StartSemesterDays twoSemesterDay = new StartSemesterDays(2, "2024-09-02");
        jpaLectureWeekRepository.save(oneSemesterDay);
        jpaLectureWeekRepository.save(twoSemesterDay);

    }

}
