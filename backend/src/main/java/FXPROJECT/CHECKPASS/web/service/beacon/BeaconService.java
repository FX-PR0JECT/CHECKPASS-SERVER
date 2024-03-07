package FXPROJECT.CHECKPASS.web.service.beacon;

import FXPROJECT.CHECKPASS.domain.common.exception.ExistingBeacon;
import FXPROJECT.CHECKPASS.domain.common.exception.NonExistentBeacon;
import FXPROJECT.CHECKPASS.domain.entity.beacon.Beacon;
import FXPROJECT.CHECKPASS.domain.entity.beacon.BeaconPK;
import FXPROJECT.CHECKPASS.domain.entity.building.Buildings;
import FXPROJECT.CHECKPASS.domain.repository.beacon.JpaBeaconRepository;
import FXPROJECT.CHECKPASS.domain.repository.QueryRepository;
import FXPROJECT.CHECKPASS.domain.repository.building.JpaBuildingRepository;
import FXPROJECT.CHECKPASS.web.common.utils.ResultFormUtils;
import FXPROJECT.CHECKPASS.web.form.requestForm.beacon.register.BeaconRegisterForm;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.BeaconInformation;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.ResultForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static FXPROJECT.CHECKPASS.domain.common.constant.CommonMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeaconService {

    private final JpaBeaconRepository jpaBeaconRepository;
    private final JpaBuildingRepository jpaBuildingRepository;

    /**
     * 비콘 등록
     * @param form 비콘 등록 Form
     * @return 등록된 비콘
     */
    @Transactional
    public Beacon registerBeacon(BeaconRegisterForm form){
        int major = form.getMajor();
        int minor = form.getMinor();
        Buildings building = jpaBuildingRepository.findByBuildingCode(major);
        BeaconPK beaconPK = new BeaconPK(building, minor);

        if (existsBeacon(beaconPK)) {
            throw new ExistingBeacon();
        }

        Beacon beacon = new Beacon(beaconPK);

        return jpaBeaconRepository.save(beacon);
    }

    /**
     * 비콘 DB에 저장되어 있는지 확인
     * @param beaconPK 비콘의 복합 PK(major + minor)
     * @return true: 존재함, false: 존재하지 않음
     */
    public boolean existsBeacon(BeaconPK beaconPK) {
        if (!jpaBeaconRepository.existsById(beaconPK)){
            return false;
        }

        return true;
    }

}
