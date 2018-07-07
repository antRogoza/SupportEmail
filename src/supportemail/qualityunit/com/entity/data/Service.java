package supportemail.qualityunit.com.entity.data;

public class Service {

    private int id;
    private Variant variation;

    public Service(int id, Variant variation) {
        this.id = id;
        this.variation = variation;
    }

    public Variant getVariation() {
        return variation;
    }

    public void setVariation(Variant variation) {
        this.variation = variation;
    }

    public static class Variant {

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

        @Override
        public String toString() {
            return "Variant{" +
                    "id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", variation=" + variation +
                '}';
    }
}
