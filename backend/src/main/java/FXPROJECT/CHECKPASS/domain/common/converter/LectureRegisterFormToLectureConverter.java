package FXPROJECT.CHECKPASS.domain.common.converter;

import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.web.common.utils.LectureCodeUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureRegisterForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureTimeSource;
import FXPROJECT.CHECKPASS.web.service.users.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class LectureRegisterFormToLectureConverter implements Converter<LectureRegisterForm, Lecture> {

    private final UserService userService;
    private final JpaDepartmentRepository jpaDepartmentRepository;

    @Override
    public Lecture convert(LectureRegisterForm form) {

        LectureTimeSource lectureTimeSource = new LectureTimeSource().builder()
                .lectureTimes(form.getLectureTimes())
                .lectureDays(form.getLectureDays())
                .lectureStartTime(form.getLectureStartTime())
                .build();

        LectureCodeUtils lectureCodeUtils = new LectureCodeUtils();

        Optional<Departments> departments = getDepartments(DepartmentsEnum.valueOf(form.getDepartments().getDepartment()));

        if (departments.isEmpty()){
            log.info("departments Error");
        }

        Lecture lecture = new Lecture().builder()
                .lectureCode(form.getLectureCode())
                .professor((Professor) userService.getUser(form.getProfessorId()))
                .lectureName(form.getLectureName())
                .lectureRoom(form.getLectureRoom())
                .lectureGrade(form.getLectureGrade())
                .lectureKind(String.valueOf(form.getLectureKind()))
                .lectureGrades(form.getLectureGrades())
                .lectureFull(form.getLectureFull())
                .lectureCount(form.getLectureCount())
                .dayOrNight(form.getDayOrNight())
                .departments(departments.get())
                .lectureTimeCode(lectureCodeUtils.getLectureCode(lectureTimeSource))
                .build();

        return lecture;
    }

    private Optional<Departments> getDepartments(DepartmentsEnum departmentName) {
        Optional<Departments> byDepartment = jpaDepartmentRepository.findByDepartment(departmentName.getDepartment());
        return byDepartment;
    }
}
