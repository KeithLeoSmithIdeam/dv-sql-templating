package ideam.sanlam.dv.domain.model;

import ideam.sanlam.dv.domain.model.DvTableType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="target_table")
public class TargetTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String tableName;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private DvTableType tableType;

    @Getter
    @Setter
    private String tableDisplayName;

    @Getter
    @Setter
    private String tableDatabaseSchema;

    @Getter
    @Setter
    private String tableDesc;

    @Getter
    @Setter
    private String tableComment;

    @Getter
    @Setter
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TableColumn> tableColumns;

    public TargetTable(String tableName, DvTableType tableType, String tableDisplayName, String tableDatabaseSchema, String tableDesc, String tableComment, List<TableColumn> tableColumns) {
        this.tableName = tableName;
        this.tableType = tableType;
        this.tableDisplayName = tableDisplayName;
        this.tableDatabaseSchema = tableDatabaseSchema;
        this.tableDesc = tableDesc;
        this.tableComment = tableComment;
        this.tableColumns = tableColumns;
    }
}
