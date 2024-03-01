package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.common.exception.ExistingLecture;
import FXPROJECT.CHECKPASS.domain.common.exception.NoPermission;
import FXPROJECT.CHECKPASS.domain.common.exception.NonExistingLecture;
import FXPROJECT.CHECKPASS.domain.common.exception.UnauthenticatedUser;
import FXPROJECT.CHECKPASS.domain.entity.college.Departments;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.DepartmentsEnum;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.college.JpaDepartmentRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.common.searchCondition.lectures.LectureSearchCondition;
import FXPROJECT.CHECKPASS.web.common.utils.LectureCodeUtils;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureRegisterForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureTimeSource;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.update.LectureUpdateForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LectureInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.users.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;
import static FXPROJECT.CHECKPASS.domain.common.constant.ErrorCode.*;

@Slf4j
@Service
@AllArgsConstructor
public class LectureService {

    private final JpaLectureRepository jpaLectureRepository;
    private final UserService userService;
    private final QueryRepository jpaQueryUsersRepository;
    private final ConversionService conversionService;
    private final LectureCodeUtils lectureCodeUtils;

    /**
     * 강의 등록
     * @param lecture 등록할 Lecture 객체
     * @return true: 등록 성공, false: 등록 실패
     */
    @Transactional
    public Lecture registerLecture(Lecture lecture){

        if(existsLecture(lecture.getLectureCode())){
            throw new ExistingLecture();
        }

        return jpaLectureRepository.save(lecture);
    }


    /**
     * 강의 조회
     * @param lectureCode 강의 코드
     * @return 강의 객체
     */
    public Lecture getLecture(Long lectureCode){

        if(!existsLecture(lectureCode)){
            throw new NonExistingLecture();
        }

        return jpaLectureRepository.findByLectureCode(lectureCode);
    }


    /**
     * 강의 목록 조회 (조건 검색)
     * @param condition 강의 검색 조건
     * @return 조건에 따른 강의 목록
     */
    public List<LectureInformation> getLectureList(LectureSearchCondition condition){

        List<Lecture> lectureList =  jpaQueryUsersRepository.getLectureList(condition);

        List<LectureInformation> lectureInformationList = new ArrayList<>();

        for (Lecture lecture : lectureList) {

            LectureInformation lectureInformation = conversionService.convert(lecture, LectureInformation.class);

            lectureInformationList.add(lectureInformation);
        }

        return lectureInformationList;
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
            return ResultFormUtils.getFailResultForm(NON_EXISTING_LECTURE);
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


    public Lecture updateLecture(Lecture target, LectureUpdateForm form) {

        if (!userService.existsUser(form.getProfessorId())){
            throw new UnauthenticatedUser();
        }

        Users user = userService.getUser(form.getProfessorId());

        if (!isProfessor(user.getUserJob())){
            throw new NoPermission();
        }

        return lectureAllFieldUpdate(target, form);
    }

    private Lecture lectureAllFieldUpdate(Lecture target, LectureUpdateForm form) {

        LectureTimeSource lectureTimeSource = extractionLectureTimeSource(form);

        target.setProfessor((Professor)userService.getUser(form.getProfessorId()));
        target.setLectureName(form.getLectureName());
        target.setLectureTimeCode(lectureCodeUtils.getLectureCode(lectureTimeSource));
        target.setLectureRoom(form.getLectureRoom());
        target.setLectureGrade(form.getLectureGrade());
        target.setLectureKind(form.getLectureKind().getKind());
        target.setLectureGrades(form.getLectureGrades());
        target.setLectureFull(form.getLectureFull());
        target.setDayOrNight(form.getDayOrNight());

        return target;
    }

    private static LectureTimeSource extractionLectureTimeSource(LectureUpdateForm form) {
        LectureTimeSource lectureTimeSource = new LectureTimeSource().builder()
                .lectureTimes(form.getLectureTimes())
                .lectureDays(form.getLectureDays())
                .lectureStartTime(form.getLectureStartTime())
                .build();
        return lectureTimeSource;
    }

    private static Boolean isProfessor(Job job) {

        if(job == Job.STUDENTS){
            return false;
        }
        return true;
    }

}