package ideam.sanlam.dv.interfaces.rest.api;

import ideam.sanlam.dv.application.internal.commandservices.GenerateSqlCommandService;
import ideam.sanlam.dv.common.exception.FieldErrorMessage;
import ideam.sanlam.dv.domain.model.ScriptKind;
import ideam.sanlam.dv.domain.model.commands.GenerateSqlCommand;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Generate API")
@RestController
@RequestMapping(value="/v1/generate")
public class GenerateController {

    private static final Logger logger = LoggerFactory.getLogger(GenerateController.class);
    private GenerateSqlCommandService generateSqlCommandService;




    public GenerateController(GenerateSqlCommandService generateSqlCommandService)
    {
        this.generateSqlCommandService = generateSqlCommandService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(),e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }

    @PostMapping(path = "/", consumes = "multipart/form-data", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation(value = "Generate a new SQL script", nickname = "Generate Service", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success", response = String.class),
            @ApiResponse(code = 500, message = "fail")})
    //public ResponseEntity<String> generateScript(@Valid @RequestBody FileInputResource fileInputResource)
    public ResponseEntity generateScripts
            (
                @RequestPart(value = "files",required = true) @ApiParam(value="files", required=true) MultipartFile[] files,
                @RequestParam  ScriptKind scriptKind
            )
    {
        byte[] f = generateSqlCommandService.generate(new GenerateSqlCommand(scriptKind,files));

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + "SqlFilesZip" + ".zip")
                .contentLength(f.length)
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(new ByteArrayResource(f));


    }


}
