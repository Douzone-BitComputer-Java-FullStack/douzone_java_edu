package edu.douzone.bitc.student;


import java.util.NoSuchElementException;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Student {
    private int studentNo;
    private String email;
    private int koreanScore;
    private int englishScore;
    private int mathScore;
    private int scienceScore;
    private int historyScore;
    private int totalScore;
    private String personInChargeCode;
    private AchievementEnum achievement;
    private String areaCode;

    public Student(Builder builder) {
        this.studentNo = builder.studentNo;
        this.email = builder.email;
        this.koreanScore = builder.koreanScore;
        this.englishScore = builder.englishScore;
        this.mathScore = builder.mathScore;
        this.scienceScore = builder.scienceScore;
        this.historyScore = builder.historyScore;
        this.totalScore = builder.totalScore;
        this.personInChargeCode = builder.personInChargeCode;
        this.achievement = builder.achievement;
        this.areaCode = builder.areaCode;
    }

    static class Builder {
        private int studentNo;
        private String email;
        private int koreanScore;
        private int englishScore;
        private int mathScore;
        private int scienceScore;
        private int historyScore;
        private int totalScore;
        private String personInChargeCode;
        private AchievementEnum achievement;
        private String areaCode;

        public Builder studentNum(int studentNo) {
            this.studentNo = studentNo;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder koreanScore(int koreanScore) {
            this.koreanScore = koreanScore;
            return this;
        }

        public Builder englishScore(int englishScore) {
            this.englishScore = englishScore;
            return this;
        }

        public Builder mathScore(int mathScore) {
            this.mathScore = mathScore;
            return this;
        }

        public Builder scienceScore(int scienceScore) {
            this.scienceScore = scienceScore;
            return this;
        }

        public Builder historyScore(int historyScore) {
            this.historyScore = historyScore;
            return this;
        }

        public Builder totalScore(int totalScore) {
            this.totalScore = totalScore;
            return this;
        }

        public Builder personInChargeCode(String personInChargeCode) {
            this.personInChargeCode = personInChargeCode;
            return this;
        }

        public Builder achievement(String key) {
            this.achievement = setAchievement(key);
            return this;
        }

        public Builder areaCode(String areaCode) {
            this.areaCode = areaCode;
            return this;
        }

        public Student build() {
            return new Student(this);
        }

        public AchievementEnum setAchievement(String key) {
            switch (key) {
                case "A" :
                    return AchievementEnum.A;
                case "B" :
                    return AchievementEnum.B;
                case "C" :
                    return AchievementEnum.C;
                default:
                    throw new NoSuchElementException();
            }
        }

    }

    @Override
    public String toString() {
        return "Student{" +
            "studentNum='" + studentNo + '\'' +
            ", email='" + email + '\'' +
            ", koreanScore=" + koreanScore +
            ", englishScore=" + englishScore +
            ", mathScore=" + mathScore +
            ", scienceScore=" + scienceScore +
            ", historyScore=" + historyScore +
            ", totalScore=" + totalScore +
            ", personInChargeCode='" + personInChargeCode + '\'' +
            ", achievement='" + achievement + '\'' +
            ", areaCode='" + areaCode + '\'' +
            '}';
    }

    public int getStudentNo() {
        return studentNo;
    }

    public String getEmail() {
        return email;
    }

    public int getKoreanScore() {
        return koreanScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public int getScienceScore() {
        return scienceScore;
    }

    public int getHistoryScore() {
        return historyScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getPersonInChargeCode() {
        return personInChargeCode;
    }

    public AchievementEnum getAchievement() {
        return achievement;
    }

    public String getAreaCode() {
        return areaCode;
    }


}
