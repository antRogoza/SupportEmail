package supportemail.qualityunit.com.entity.data;

public class Service {

    private int id;
    private Variant variant;

    public Service(int id, Variant variant) {
        this.id = id;
        this.variant = variant;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    private static class Variant {

        private int id;

        public Variant(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }

}
