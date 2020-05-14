package ideam.sanlam.dv.application.internal.pipeline;

import ideam.sanlam.dv.domain.model.ScriptFile;
import ideam.sanlam.dv.domain.model.ScriptKind;
import ideam.sanlam.dv.domain.model.TargetTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PipelineData {

    @Getter
    @Setter
    ScriptKind scriptKind;

    @Getter
    @Setter
    MultipartFile[] uploadfiles;

    public PipelineData(ScriptKind scriptKind, MultipartFile[] uploadfiles) {
        this.scriptKind = scriptKind;
        this.uploadfiles = uploadfiles;
        TargetTableDefinitions = new ArrayList<TargetTable>();
        scripts = new ArrayList<ScriptFile>();
    }

    @Getter
    @Setter
    List<TargetTable> TargetTableDefinitions;

    @Getter
    List<ScriptFile> scripts;

    @Getter
    @Setter
    byte[] packagedScripts;

    public void addTargetTable(TargetTable tbl)
    {
        TargetTableDefinitions.add(tbl);
    }

    public void addScript(ScriptFile script)
    {
        scripts.add(script);
    }

}
