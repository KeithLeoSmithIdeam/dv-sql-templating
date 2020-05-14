package ideam.sanlam.dv.interfaces.rest.dto;

import ideam.sanlam.dv.domain.model.ScriptKind;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class FileInputResource {

    public FileInputResource(ScriptKind scriptKind, MultipartFile[] uploadfiles) {
        this.scriptKind = scriptKind;
        this.uploadfiles = uploadfiles;
    }

    @Getter
    @Setter
    ScriptKind scriptKind;

    @Getter
    @Setter
    MultipartFile[] uploadfiles;
}
