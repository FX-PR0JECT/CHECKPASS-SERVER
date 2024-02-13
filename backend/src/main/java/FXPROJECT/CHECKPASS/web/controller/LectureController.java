package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.InternalException;
import FXPROJECT.CHECKPASS.domain.common.exception.NoPermission;
import FXPROJECT.CHECKPASS.domain.common.exception.NonExistingLecture;
import FXPROJECT.CHECKPASS.domain.entity.lectures.Lecture;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.common.annotation.LoginUser;
import FXPROJECT.CHECKPASS.web.common.searchCondition.lectures.LectureSearchCondition;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.register.LectureRegisterForm;
import FXPROJECT.CHECKPASS.web.form.requestForm.lectures.update.LectureUpdateForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.LectureInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.SimpleLectureInformation;
import FXPROJECT.CHECKPASS.web.service.lectures.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;
    private final ConversionService conversionService;

    /**
     * 강의 등록
     * URL : /lectures
     * @param form 강의 등록 폼
     * @param bindingResult bindingResult 검증
     * @return 성공 : 등록이 완료 되었습니다. 실패 : Database에 이미 등록된 강의\n해결 방법 : 확인 후 재 요청
     */
    @PostMapping
    public ResultForm registerLecture(@LoginUser Users LoginUser, @RequestBody @Validated LectureRegisterForm form, BindingResult bindingResult){

        if (LoginUser.getUserJob() == Job.STUDENTS){
            throw new NoPermission();
        }

        if (!isEqualDepartment(LoginUser, form)){
            throw new NoPermission();
        }

        Lecture lecture = conversionService.convert(form, Lecture.class);

        Lecture savedLecture = lectureService.registerLecture(lecture);

        if (savedLecture == null){
            throw new InternalException();
        }

        return ResultFormUtils.getSuccessResultForm(COMPLETE_REGISTER.getDescription());
    }

    private static boolean isEqualDepartment(Users LoginUser, LectureRegisterForm form) {

        String userDepartment = LoginUser.getDepartments().getDepartment();
        String LectureDepartment = form.getDepartments().getDepartment();

        return userDepartment.equals(LectureDepartment);
    }

    /**
     * 강의 간략 정보 조회
     * URL : /lectures/simple/{lectureCode}
     * @param lectureCode 강의 코드
     * @return SimpleLectureInformation 객체 속성들
     */
    @GetMapping("/simple/{lectureCode}")
    public ResultForm showLectureSimpleInformation(@PathVariable("lectureCode") Long lectureCode){

        Lecture target = lectureService.getLecture(lectureCode);

        SimpleLectureInformation simpleLectureInformation = conversionService.convert(target, SimpleLectureInformation.class);

        return ResultFormUtils.getSuccessResultForm(simpleLectureInformation);

    }

    /**
     * 강의 정보 조회
     * URL : /lectures/{lectureCode}
     * @param lectureCode 강의 코드
     * @return LectureInformation 객체 속성들
     */
    @GetMapping("/{lectureCode}")
    public ResultForm showLectureInformation(@PathVariable("lectureCode") Long lectureCode){

        Lecture target = lectureService.getLecture(lectureCode);

        LectureInformation lectureInformation = conversionService.convert(target, LectureInformation.class);

        return ResultFormUtils.getSuccessResultForm(lectureInformation);

    }

    /**
     * 강의 목록 조회 (조건)
     * URL : /lectures
     * @param condition 강의 검색 조건
     * @return 조건에 따른 강의 목록
     */
    @GetMapping
    public ResultForm getLectureList(@RequestBody LectureSearchCondition condition){
        return ResultFormUtils.getSuccessResultForm(lectureService.getLectureList(condition));
    }


    /**
     * 강의 수정
     * URL : /lectures/{lectureCode}
     * @param lectureCode 강의 코드
     * @param form 수정 폼
     * @param loggedInUser 로그인 유저
     * @return 수정된 강의 객체
     */
    @PatchMapping("/{lectureCode}")
    public ResultForm editLectureInformation(@PathVariable("lectureCode") Long lectureCode, @RequestBody LectureUpdateForm form, @LoginUser Users loggedInUser) {

        if (!lectureService.existsLecture(lectureCode)){
            throw new NonExistingLecture();
        }

        Lecture target = lectureService.getLecture(lectureCode);

        if (!isEqaulDepartment(loggedInUser, target)) {
            throw new NoPermission();
        }

        if (!isMine(loggedInUser, target)){
            throw new NoPermission();
        }

        lectureService.editLectureInformation(target, form);

        return ResultFormUtils.getSuccessResultForm(COMPLETE_UPDATE.getDescription());
    }

    private static boolean isMine(Users loggedInUser, Lecture target) {
        return loggedInUser.getUserId().equals(target.getProfessor().getUserId());
    }

    private static boolean isEqaulDepartment(Users loggedInUser, Lecture target) {
        String loginUserDepartment = loggedInUser.getDepartments().getDepartment();
        String lectureRegistDepartment = target.getDepartments().getDepartment();
        return loginUserDepartment.equals(lectureRegistDepartment);
    }


    /**
     * 강의 삭제
     * URL : /lectures/{lectureCode}
     * @param lectureCode 강의 코드
     * @return 성공 : 삭제가 완료 되었습니다. 실패 : 삭제 실패
     */
    @DeleteMapping("/{lectureCode}")
    public ResultForm deleteLecture(@PathVariable("lectureCode") Long lectureCode){
        return lectureService.deleteLecture(lectureCode);
    }

}