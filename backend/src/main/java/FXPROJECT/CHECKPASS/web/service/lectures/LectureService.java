package FXPROJECT.CHECKPASS.web.service.lectures;

import FXPROJECT.CHECKPASS.domain.common.exception.*;
import FXPROJECT.CHECKPASS.domain.dto.LectureTimeCode;
import FXPROJECT.CHECKPASS.domain.entity.attendance.AttendanceTokens;
import FXPROJECT.CHECKPASS.domain.entity.beacon.Beacon;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Professor;
import FXPROJECT.CHECKPASS.domain.entity.users.Students;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.attendance.JpaAttendanceTokenRepository;
import FXPROJECT.CHECKPASS.domain.repository.lectures.JpaLectureRepository;
import FXPROJECT.CHECKPASS.web.common.searchCondition.lectures.LectureSearchCondition;
import FXPROJECT.CHECKPASS.web.common.utils.LectureCodeUtils;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.common.utils.SemesterUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureTimeSource;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.update.LectureUpdateForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LectureInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.SimpleLectureInformation;
import FXPROJECT.CHECKPASS.web.service.beacon.BeaconService;
import FXPROJECT.CHECKPASS.web.service.users.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
    private final JpaAttendanceTokenRepository jpaAttendanceTokenRepository;
    private final UserService userService;
    private final BeaconService beaconService;
    private final QueryRepository queryRepository;
    private final ConversionService conversionService;
    private final LectureCodeUtils lectureCodeUtils;
    private final SemesterUtils semesterUtils;

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
            throw new NonExistentLecture();
        }

        return jpaLectureRepository.findByLectureCode(lectureCode);
    }

    /**
     * 해당 학기에 개설된 모든 강의 조회
     * @return 해당 학기에 개설된 모든 강의 목록
     */
    public List<LectureInformation> getLectureList(){

        String semester = semesterUtils.getSemester();
        int year = LocalDate.now().getYear();
        List<Lecture> lectureList = queryRepository.getLectureList(year, semester);

        List<LectureInformation> lectureInformationList = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            LectureInformation lectureInformation = conversionService.convert(lecture, LectureInformation.class);
            lectureInformationList.add(lectureInformation);
        }

        return lectureInformationList;
    }


    /**
     * 강의 목록 조회 (조건 검색)
     * @param condition 강의 검색 조건
     * @return 조건에 따른 강의 목록
     */
    public List<LectureInformation> getLectureList(LectureSearchCondition condition){

        String semester = semesterUtils.getSemester();
        int year = LocalDate.now().getYear();
        List<Lecture> lectureList =  queryRepository.getLectureList(condition, year, semester);

        List<LectureInformation> lectureInformationList = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            LectureInformation lectureInformation = conversionService.convert(lecture, LectureInformation.class);
            lectureInformationList.add(lectureInformation);
        }

        return lectureInformationList;
    }

    /**
     * 해당 교수가 개설한 강의 목록 찾기
     * @param professor 로그인한 교수
     * @return 해당 교수가 개설한 강의 목록
     */
    public List<SimpleLectureInformation> getLecturesByProfessor(Professor professor) {
        Long professorId = professor.getUserId();

        List<SimpleLectureInformation> simpleLectureInformationList = new ArrayList<>();

        List<Lecture> lectureList = queryRepository.getLectureListByProfessorId(professorId);

        for (Lecture lecture : lectureList) {
            SimpleLectureInformation simpleLectureInformation = conversionService.convert(lecture, SimpleLectureInformation.class);
            simpleLectureInformationList.add(simpleLectureInformation);
        }
        return simpleLectureInformationList;
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

        Lecture lecture = getLecture(lectureCode);

        deleteAttendanceToken(lecture);

        jpaLectureRepository.deleteLectureByLectureCode(lectureCode);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_DELETE.getDescription());
    }

    /**
     * 사용자가 수강한 강의 중 해당 비콘에 매칭되어있는 강의 정보 목록 조회
     * @param major 비콘의 major
     * @param minor 비콘의 minor
     * @param loggedInUser 로그인된 유저
     * @return 사용자가 수강한 강의 중 해당 비콘에 매칭되어있는 강의 정보 객체 목록
     */
    public List<LectureInformation> getLectureList(int major, int minor, Users loggedInUser) {
        List<Lecture> lectureList = queryRepository.getLectureList(major, minor);
        List<Lecture> enrollmentList = queryRepository.getEnrollmentList((Students) loggedInUser);
        int day = LocalDate.now().getDayOfWeek().getValue(); // 1(월) ~ 5(금)

        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime().truncatedTo(ChronoUnit.MINUTES);

        List<LectureInformation> lectureInformationList = new ArrayList<>();

        for (Lecture lecture : lectureList) {
            if (enrollmentList.contains(lecture)) {
                List<LectureTimeCode> timeCodeList = lecture.getLectureTimeCode();

                for (LectureTimeCode timeCode : timeCodeList) {
                    String lectureTimeCode = timeCode.getLectureTimeCode();
                    int lectureDay = Integer.parseInt(lectureTimeCode.substring(1, 2)) + 1;
                    int lectureHour = Integer.parseInt(lectureTimeCode.substring(3, 5));
                    int lectureMinute = Integer.parseInt(lectureTimeCode.substring(5, 7));

                    LocalTime startTime = calculateStartTime(lectureHour, lectureMinute);
                    LocalTime endTime = calculateEndTime(lectureHour, lectureMinute);

                    if (day == lectureDay && currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
                        LectureInformation lectureInformation = conversionService.convert(lecture, LectureInformation.class);
                        lectureInformationList.add(lectureInformation);
                        break;
                    }
                }
            }
        }
        return lectureInformationList;
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
            throw new NoSuchProfessor();
        }

        Users user = userService.getUser(form.getProfessorId());

        if (!isProfessor(user.getUserJob())){
            throw new NoSuchProfessor();
        }

        return lectureAllFieldUpdate(target, form);
    }

    private Lecture lectureAllFieldUpdate(Lecture target, LectureUpdateForm form) {

        LectureTimeSource lectureTimeSource = extractionLectureTimeSource(form);

        int major = form.getMajor();
        int minor = form.getMinor();
        Beacon beacon = beaconService.getBeacon(major, minor);

        target.setProfessor((Professor)userService.getUser(form.getProfessorId()));
        target.setLectureName(form.getLectureName());
        target.setLectureTimeCode(lectureCodeUtils.getLectureCode(lectureTimeSource));
        target.setBeacon(beacon);
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

        if(job != Job.PROFESSOR){
            return false;
        }
        return true;
    }

    private LocalTime calculateStartTime(int lectureHour, int lectureMinute) {
        return lectureMinute == 0 ? LocalTime.of(lectureHour - 1, 49) : LocalTime.of(lectureHour, 19);
    }

    private LocalTime calculateEndTime(int lectureHour, int lectureMinute) {
        return lectureMinute == 0 ? LocalTime.of(lectureHour, 31) : LocalTime.of(lectureHour + 1, 01);
    }

    private void deleteAttendanceToken(Lecture lecture) {
        if (jpaAttendanceTokenRepository.existsByLecture(lecture)){
            jpaAttendanceTokenRepository.deleteByLecture(lecture);
        }
    }
}