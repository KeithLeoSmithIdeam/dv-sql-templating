package ideam.sanlam.dv.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TableColumn")
@Table(name = "table_column")
@AllArgsConstructor
public class TableColumn {

    @Id
    @GeneratedValue
    private Long id;

    @Getter @Setter
    private String columnName;

    @Getter @Setter
    private String columnDataType;

    @Getter @Setter
    private String columnSize;

    @Getter @Setter
    private String columnPrecision;

    @Getter @Setter
    private String columnIsKey;

    @Getter @Setter
    private String columnIsNullable;

    @Getter @Setter
    private String columnDefaultValue;
}
