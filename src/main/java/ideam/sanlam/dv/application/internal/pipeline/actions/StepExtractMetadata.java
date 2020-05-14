package ideam.sanlam.dv.application.internal.pipeline.actions;

import ideam.sanlam.dv.application.internal.excelreader.ExcelDataExtractor;
import ideam.sanlam.dv.application.internal.pipeline.PipelineData;
import ideam.sanlam.dv.common.exception.NoFilesReceivedError;
import ideam.sanlam.dv.domain.model.TableColumn;
import ideam.sanlam.dv.domain.model.TargetTable;
import ideam.sanlam.dv.infrastructure.config.ExcelConfigProperties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StepExtractMetadata implements Pipe<PipelineData>
{
    private ExcelConfigProperties excelProps;
    private ExcelDataExtractor excelDataExtractor;

    public StepExtractMetadata(ExcelDataExtractor excelDataExtractor, ExcelConfigProperties excelProps) {
        this.excelProps = excelProps;
        this.excelDataExtractor = excelDataExtractor;
    }

    @Override
    public PipelineData process(PipelineData input) {


        if (input.getUploadfiles().length == 0) {
            throw new NoFilesReceivedError("No Excel files have been received for processing in the request");
        }
        else
        {
            for (MultipartFile file : input.getUploadfiles()) {
                try
                {
                    Workbook workbook = WorkbookFactory.create(convert(file));
                    Sheet sheet = workbook.getSheet(excelProps.getMetadataSheetName());

                    TargetTable tbl = excelDataExtractor.extractTableMetadata(sheet);
                    List<TableColumn> cols = excelDataExtractor.extractTableColumnMetadata(sheet);
                    tbl.setTableColumns(cols);

                    input.addTargetTable(tbl);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidFormatException e) {
                    e.printStackTrace();
                }

            }
            return input;
        }
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}