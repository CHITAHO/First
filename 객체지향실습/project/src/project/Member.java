package project;

public class Member {
    private int MEMBER_NO;
    private String MEMBER_NAME;
    private String MEMBER_EMAIL;
    private String MEMBER_TEL_NO;
    private String MEMBER_BIRTHDAY;
    private int MILEAGE;

    public int getMemberNo(){
        return this.MEMBER_NO;
    }
    public String getMemberName(){
        return this.MEMBER_NAME;
    }
    public String getMemberEmail(){
        return this.MEMBER_EMAIL;
    }
    public String getMemberTelNo(){
        return this.MEMBER_TEL_NO;
    }
    public String getMemberBirth(){
        return this.MEMBER_BIRTHDAY;
    }
    public int getMemberMileage(){
        return this.MILEAGE;
    }

    public void setMemberNo(int MEMBER_NO){
        this.MEMBER_NO = MEMBER_NO;
    }
    public void setMemberName(String MEMBER_NAME){
        this.MEMBER_NAME = MEMBER_NAME;
    }
    public void setMemberEmail(String MEMBER_EMAIL){
        this.MEMBER_EMAIL = MEMBER_EMAIL;
    }
    public void setMemberTelNo(String MEMBER_TEL_NO){
        this.MEMBER_TEL_NO = MEMBER_TEL_NO;
    }
    public void setMemberBirth(String MEMBER_BIRTHDAY){
        this.MEMBER_BIRTHDAY = MEMBER_BIRTHDAY;
    }
    public void setMemberMileage(int MILEAGE){
        this.MILEAGE = MILEAGE;
    }
}
