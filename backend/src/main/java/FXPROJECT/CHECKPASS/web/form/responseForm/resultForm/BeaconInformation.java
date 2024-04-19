package FXPROJECT.CHECKPASS.web.form.responseForm.resultForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BeaconInformation {

    private int major;
    private int minor;
    private String buildingName;

}
