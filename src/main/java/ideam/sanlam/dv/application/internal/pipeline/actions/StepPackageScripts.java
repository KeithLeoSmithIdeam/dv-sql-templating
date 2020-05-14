package ideam.sanlam.dv.application.internal.pipeline.actions;

import ideam.sanlam.dv.application.internal.pipeline.PipelineData;
import ideam.sanlam.dv.domain.model.ScriptFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class StepPackageScripts implements Pipe<PipelineData>
{
    private static final Logger LOG = LoggerFactory.getLogger(StepPackageScripts.class);

    @Override
    public PipelineData process(PipelineData input)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        try {
             for (ScriptFile script : input.getScripts()) {

                 ZipEntry entry = new ZipEntry(script.getScriptName()  );
                 byte[] byteArr = script.getScriptWriter().toString().getBytes();
                 entry.setSize(byteArr.length);
                 zos.putNextEntry(entry);
                 zos.write(byteArr);
                 zos.closeEntry();
             }
            zos.close();
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        input.setPackagedScripts(baos.toByteArray());
        return input;
    }
}