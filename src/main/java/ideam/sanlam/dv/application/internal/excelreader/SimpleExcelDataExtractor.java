package ideam.sanlam.dv.application.internal.excelreader;

import ideam.sanlam.dv.domain.model.DvTableType;
import ideam.sanlam.dv.domain.model.TableColumn;
import ideam.sanlam.dv.domain.model.TargetTable;
import ideam.sanlam.dv.infrastructure.config.ExcelConfigProperties;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleExcelDataExtractor implements ExcelDataExtractor
{

    @Autowired
    private ExcelConfigProperties props;

    private  DataFormatter dataFormatter;

    public SimpleExcelDataExtractor()
    {
        this.dataFormatter = new DataFormatter();
    }

    @Override
    public TargetTable extractTableMetadata(Sheet sheet)
    {
        String tblName = dataFormatter.formatCellValue(sheet.getRow(props.getTableDef().getTableName().getRow()).getCell(props.getTableDef().getTableName().getCol(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
        String tbltableType = dataFormatter.formatCellValue(sheet.getRow(props.getTableDef().getTableType().getRow()).getCell(props.getTableDef().getTableType().getCol(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
        String tblDisplayName = dataFormatter.formatCellValue(sheet.getRow(props.getTableDef().getTableDisplayName().getRow()).getCell(props.getTableDef().getTableDisplayName().getCol(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
        String tblDatabaseSchema = dataFormatter.formatCellValue(sheet.getRow(props.getTableDef().getTableDatabaseSchema().getRow()).getCell(props.getTableDef().getTableDatabaseSchema().getCol(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
        String tblTableDesc = dataFormatter.formatCellValue(sheet.getRow(props.getTableDef().getTableDesc().getRow()).getCell(props.getTableDef().getTableDesc().getCol(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
        String tblTableComment = dataFormatter.formatCellValue(sheet.getRow(props.getTableDef().getTableComment().getRow()).getCell(props.getTableDef().getTableComment().getCol(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));

        TargetTable tbl = new TargetTable();
        tbl.setTableName(tblName);
        tbl.setTableDisplayName(tblDisplayName);
        tbl.setTableType(DvTableType.valueOf(tbltableType.toUpperCase()));
        tbl.setTableDatabaseSchema(tblDatabaseSchema);
        tbl.setTableDesc(tblTableDesc);
        tbl.setTableComment(tblTableComment);

        return tbl;
    }

    @Override
    public List<TableColumn> extractTableColumnMetadata(Sheet sheet)
    {
        List<TableColumn> cols = new ArrayList<TableColumn>();
        for (int rowNum = props.getTableColDef().getRowStart(); rowNum <= sheet.getLastRowNum(); rowNum++)
        {
            Row r = sheet.getRow(rowNum);
            String colName = dataFormatter.formatCellValue(sheet.getRow(rowNum).getCell(props.getTableColDef().getColColumnName(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
            String colDataType = dataFormatter.formatCellValue(sheet.getRow(rowNum).getCell(props.getTableColDef().getColColumnDataType(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
            String colSize = dataFormatter.formatCellValue(sheet.getRow(rowNum).getCell(props.getTableColDef().getColColumnSize(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
            String colPrecision = dataFormatter.formatCellValue(sheet.getRow(rowNum).getCell(props.getTableColDef().getColColumnPrecision(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
            String colIsKey = dataFormatter.formatCellValue(sheet.getRow(rowNum).getCell(props.getTableColDef().getColColumnIsKey(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
            String colIsNull = dataFormatter.formatCellValue(sheet.getRow(rowNum).getCell(props.getTableColDef().getColColumnIsNullable(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
            String ColDefaultValue = dataFormatter.formatCellValue(sheet.getRow(rowNum).getCell(props.getTableColDef().getColColumnDefaultValue(),Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));

            cols.add(
                    new TableColumn(0L,colName,colDataType,colSize,colPrecision,colIsKey,colIsNull,ColDefaultValue)
            );
        }
        return cols;
    }
}
