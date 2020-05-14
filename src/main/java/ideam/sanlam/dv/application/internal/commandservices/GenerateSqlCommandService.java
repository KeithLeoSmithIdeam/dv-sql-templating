package ideam.sanlam.dv.application.internal.commandservices;

import com.mitchellbosecke.pebble.PebbleEngine;
import ideam.sanlam.dv.application.internal.excelreader.ExcelDataExtractor;
import ideam.sanlam.dv.application.internal.pipeline.GenerationPipeline;
import ideam.sanlam.dv.application.internal.pipeline.PipelineData;
import ideam.sanlam.dv.application.internal.pipeline.actions.StepExtractMetadata;
import ideam.sanlam.dv.application.internal.pipeline.actions.StepGenerateScripts;
import ideam.sanlam.dv.application.internal.pipeline.actions.StepPackageScripts;
import ideam.sanlam.dv.common.exception.NoFilesReceivedError;
import ideam.sanlam.dv.domain.model.commands.GenerateSqlCommand;
import ideam.sanlam.dv.infrastructure.config.ExcelConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class GenerateSqlCommandService {

    private static final Logger LOG = LoggerFactory.getLogger(GenerateSqlCommandService.class);
    private PebbleEngine pebbleEngine;
    private ExcelDataExtractor excelDataExtractor;

    @Autowired
    private ExcelConfigProperties props;

    public GenerateSqlCommandService(PebbleEngine pebbleEngine,ExcelDataExtractor excelDataExtractor) {
        this.pebbleEngine = pebbleEngine;
        this.excelDataExtractor = excelDataExtractor;
    }

    public byte[] generate(GenerateSqlCommand cmd) throws NoFilesReceivedError {
        PipelineData data = new PipelineData(
                cmd.getScriptKind(),
                cmd.getUploadfiles()
        );

        GenerationPipeline<PipelineData> pipeline = new GenerationPipeline<PipelineData>(data);
        pipeline.addStep(new StepExtractMetadata(excelDataExtractor,props));
        pipeline.addStep(new StepGenerateScripts(pebbleEngine));
        pipeline.addStep(new StepPackageScripts());
        pipeline.process();

        return data.getPackagedScripts();
    }
}
