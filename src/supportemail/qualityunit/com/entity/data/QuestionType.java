package supportemail.qualityunit.com.entity.data;

public class QuestionType {

    private int id;
    private Category category;

    public QuestionType(int id, Category category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuestionType{" +
                "id=" + id +
                ", category=" + category +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public class Category {

        private int id;
        private SubCategory subCategory;

        public Category(int id, SubCategory subCategory) {
            this.id = id;
            this.subCategory = subCategory;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public SubCategory getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(SubCategory subCategory) {
            this.subCategory = subCategory;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", subCategory=" + subCategory +
                    '}';
        }

        private class SubCategory {

            private int id;

            public SubCategory(int id) {
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
                return "SubCategory{" +
                        "id=" + id +
                        '}';
            }

        }

    }

}
