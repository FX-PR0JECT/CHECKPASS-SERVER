package FXPROJECT.CHECKPASS.web.controller;

import FXPROJECT.CHECKPASS.domain.common.exception.InternalException;
import FXPROJECT.CHECKPASS.domain.common.exception.NoPermission;
import FXPROJECT.CHECKPASS.domain.entity.beacon.Beacon;
import FXPROJECT.CHECKPASS.domain.entity.users.Users;
import FXPROJECT.CHECKPASS.domain.enums.Job;
import FXPROJECT.CHECKPASS.web.common.annotation.LoginUser;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.beacon.register.BeaconRegisterForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.BeaconInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import FXPROJECT.CHECKPASS.web.service.beacon.BeaconService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.COMPLETE_REGISTER;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/beacon")
public class BeaconController {

    private final BeaconService beaconService;

    /**
     * 비콘 등록
     * @param form 비콘 등록 정보가 있는 Parameter
     * @param loggedInUser 로그인 유저
     * @return 성공 : 등록이 완료 되었습니다. 실패 : Database에 이미 등록된 비콘입니다.\n" + "해결 방법 : 확인 후 재 요청
     */
    @PostMapping
    public ResultForm registerBeacon(BeaconRegisterForm form, @LoginUser Users loggedInUser) {
        if (loggedInUser.getUserJob() != Job.STAFF) {
            throw new NoPermission();
        }

        Beacon savedBeacon = beaconService.registerBeacon(form);

        if (savedBeacon == null){
            throw new InternalException();
        }

        return ResultFormUtils.getSuccessResultForm(COMPLETE_REGISTER.getDescription());
    }
}