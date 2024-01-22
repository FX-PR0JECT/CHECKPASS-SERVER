package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.common.exception.UnauthenticatedUser;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureRegisterForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.update.LectureUpdateForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.users.UserService;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;

@Slf4j
@Service
@AllArgsConstructor
public class LectureService {

    private final JpaDepartmentRepository jpaDepartmentRepository;
    private final JpaLectureRepository jpaLectureRepository;
    private final UserService userService;

    /**
     * 강의 등록
     * @param lecture 등록할 Lecture 객체
     * @return true: 등록 성공, false: 등록 실패
     */
    @Transactional
    public boolean registerLecture(Lecture lecture){

        if(existsLecture(lecture.getLectureCode())){
            return false;
        }

        jpaLectureRepository.save(lecture);

        return true;
    }

    /**
     * 강의 수정
     * @param target 수정할 강의 객체
     * @param param 강의 정보 수정 Parameter
     */
    @Transactional
    public void editLectureInformation(Lecture target, LectureUpdateForm param){

        Lecture revisedLecture = updateLecture(target, param);

        jpaLectureRepository.save(revisedLecture);

    }

    /**
     * 강의 삭제
     * @param lectureCode 강의 코드
     * @return 삭제 성공 시: 성공 ResultForm, 삭제 실패 시: 실패 ResultForm
     */
    @Transactional
    public ResultForm deleteLecture(Long lectureCode){
        if(!existsLecture(lectureCode)){
            return ResultFormUtils.getFailResultForm(NON_EXISTING_LECTURE.getCode(), NON_EXISTING_LECTURE.getTitle(), FAIL_DELETE.getDescription(), null);
        }
        jpaLectureRepository.deleteLectureByLectureCode(lectureCode);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_DELETE.getDescription());
    }


    /**
     * 강의 코드를 이용하여 등록된 강의인지 확인
     * @param lectureCode 강의 코드
     * @return true: 존재함, false: 존재하지 않음
     */
    public Boolean existsLecture(Long lectureCode) {
        return jpaLectureRepository.existsByLectureCode(lectureCode);
    }

    public Lecture transferToLecture(LectureRegisterForm form) {
        Optional<Departments> departments = getDepartments(form.getDepartments());

        if (departments.isEmpty()){
            log.info("departments Error");
        }

        Lecture lecture = new Lecture().builder()
                .lectureCode(form.getLectureCode())
                .professor((Professor)userService.getUser(form.getProfessorId()))
                .lectureName(form.getLectureName())
                .lectureGrade(form.getLectureGrade())
                .lectureTimes(form.getLectureTimes())
                .lectureRoom(form.getLectureRoom())
                .lectureGrades(form.getLectureGrades())
                .lectureKind(form.getLectureKind())
                .lectureFull(form.getLectureFull())
                .dayOrNight(form.getDayOrNight())
                .departments(departments.get())
                .build();
        return lecture;
    }

    public Lecture updateLecture(Lecture target, LectureUpdateForm form) {

        if (!userService.existsUser(form.getProfessorId())){
            throw new UnauthenticatedUser();
        }

        target.setProfessor((Professor)userService.getUser(form.getProfessorId()));
        target.setLectureName(form.getLectureName());
        target.setLectureTimes(form.getLectureTimes());
        target.setLectureRoom(form.getLectureRoom());
        target.setLectureGrade(form.getLectureGrade());
        target.setLectureKind(form.getLectureKind());
        target.setLectureGrades(form.getLectureGrades());
        target.setLectureFull(form.getLectureFull());
        target.setDayOrNight(form.getDayOrNight());

        return target;
    }

    private Optional<Departments> getDepartments(DepartmentsEnum departmentName) {
        Optional<Departments> byDepartment = jpaDepartmentRepository.findByDepartment(departmentName.getDepartment());
        return byDepartment;
    }

    public Lecture getLecture(Long lectureCode){
        return jpaLectureRepository.findByLectureCode(lectureCode);
    }
}