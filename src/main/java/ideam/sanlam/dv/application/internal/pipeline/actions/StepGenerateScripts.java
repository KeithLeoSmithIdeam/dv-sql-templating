package ideam.sanlam.dv.application.internal.pipeline.actions;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import ideam.sanlam.dv.application.internal.pipeline.PipelineData;
import ideam.sanlam.dv.domain.model.ScriptFile;
import ideam.sanlam.dv.domain.model.TargetTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class StepGenerateScripts implements Pipe<PipelineData>
{
    private PebbleEngine pebbleEngine;
    private static final Logger LOG = LoggerFactory.getLogger(StepGenerateScripts.class);

    public StepGenerateScripts(PebbleEngine pebbleEngine) {
        this.pebbleEngine = pebbleEngine;
    }

    @Override
    public PipelineData process(PipelineData input) 
    {
        PebbleTemplate compiledTemplate = this.pebbleEngine.getTemplate(input.getScriptKind().resolveTemplate());

        for (TargetTable def : input.getTargetTableDefinitions()) {
            Writer writer = new StringWriter();
            try {

                Map<String, Object> context = new HashMap<>();
                context.put("tableName", def.getTableName());
                context.put("tableType", def.getTableType().toString());
                context.put("tableSchema", def.getTableDatabaseSchema());
                context.put("tableDesc", def.getTableDesc());
                context.put("tableComment", def.getTableComment());
                context.put("tableColumns", def.getTableColumns());
                context.put("ts", LocalDateTime.now().toString());
                context.put("author", "Keith Leo-Smith");
                context.put("br", "\r\n");

                compiledTemplate.evaluate(writer, context);

            } catch (IOException e) {
                LOG.error(e.getMessage());
                throw new RuntimeException(e);
            }
            input.addScript(new ScriptFile(def.getTableName()+ "-"+ java.util.UUID.randomUUID() + ".sql",writer));
            LOG.info(writer.toString());
        }
        return input;
    }
}