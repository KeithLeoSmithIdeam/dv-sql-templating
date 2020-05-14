package ideam.sanlam.dv.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.config.excel")
public class ExcelConfigProperties {

    @Getter @Setter
    private String metadataSheetName;

    @Getter @Setter
    private TableDefConfig tableDef;

    @Getter @Setter
    private TableColDefConfig tableColDef;

    public static class ExcelCell
    {
        @Getter @Setter
        private int row;
        @Getter @Setter
        private int col;
    }

    public static class TableDefConfig
    {
        @Getter @Setter
        private ExcelCell tableName;

        @Getter @Setter
        private ExcelCell tableType;

        @Getter @Setter
        private ExcelCell tableDisplayName;

        @Getter @Setter
        private ExcelCell tableDatabaseSchema;

        @Getter @Setter
        private ExcelCell tableDesc;

        @Getter @Setter
        private ExcelCell tableComment;
    }

    public static class TableColDefConfig
    {
        @Getter @Setter
        private int rowStart;

        @Getter @Setter
        private int colColumnName;

        @Getter @Setter
        private int colColumnDataType;

        @Getter @Setter
        private int colColumnSize;

        @Getter @Setter
        private int colColumnPrecision;

        @Getter @Setter
        private int colColumnIsKey;

        @Getter @Setter
        private int colColumnIsNullable;

        @Getter @Setter
        private int colColumnDefaultValue;
    }

}


