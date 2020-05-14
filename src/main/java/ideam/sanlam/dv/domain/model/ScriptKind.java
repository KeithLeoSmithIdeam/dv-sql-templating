package ideam.sanlam.dv.domain.model;

public enum ScriptKind {

    CREATE {
        @Override
        public String resolveTemplate() {
            return "hive/create";
        }
    },

    INSERT {
        @Override
        public String resolveTemplate() {
            return "hive/create";
        }
    };

    public abstract String resolveTemplate();
}
