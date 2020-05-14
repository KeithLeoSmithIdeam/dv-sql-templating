package ideam.sanlam.dv.application.internal.excelreader;

import ideam.sanlam.dv.domain.model.TableColumn;
import ideam.sanlam.dv.domain.model.TargetTable;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public interface ExcelDataExtractor {

    public TargetTable extractTableMetadata(Sheet sheet);

    public List<TableColumn> extractTableColumnMetadata(Sheet sheet);
}
