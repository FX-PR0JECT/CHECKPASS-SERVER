package FXPROJECT.CHECKPASS.domain.common.converter;

import FXPROJECT.CHECKPASS.domain.entity.beacon.Beacon;
import FXPROJECT.CHECKPASS.domain.entity.beacon.BeaconPK;
import FXPROJECT.CHECKPASS.web.form.responseForm.resultForm.BeaconInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeaconToBeaconInformationConverter implements Converter<Beacon, BeaconInformation> {

    @Override
    public BeaconInformation convert(Beacon beacon) {

        BeaconPK beaconPK = beacon.getBeaconPK();
        BeaconInformation beaconInformation = new BeaconInformation().builder()
                .major(beaconPK.getMajor())
                .minor(beaconPK.getMinor())
                .build();

        return beaconInformation;
    }
}
