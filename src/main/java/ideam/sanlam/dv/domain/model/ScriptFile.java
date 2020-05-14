package ideam.sanlam.dv.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Writer;

@AllArgsConstructor
public class ScriptFile {

    @Getter
    @Setter
    private String scriptName;
    @Getter
    @Setter
    private Writer scriptWriter;

}
