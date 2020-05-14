package ideam.sanlam.dv.interfaces.rest.transform;

import ideam.sanlam.dv.domain.model.commands.GenerateSqlCommand;
import ideam.sanlam.dv.interfaces.rest.dto.FileInputResource;

public class GenerateSqlCommandDTOAssembler {

    /**
     * Static method within the Assembler
     * @param resource
     * @return GenerateSqlCommand Model
     */
    public static GenerateSqlCommand toCommandFromDTO(FileInputResource resource){

        return new GenerateSqlCommand(
                resource.getScriptKind(),
                resource.getUploadfiles()
        );
    }

}
