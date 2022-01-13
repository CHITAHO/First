package project;

public class RoomUsing {
    private int roomNo;
    private int memberNo;
    private String startTime;
    private String endTime;
    private int price;

    public void setRoomNo(int roomNo){
        this.roomNo = roomNo;
    }
    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public void setPrice(int price){
        this.price = price;
    }

    public int getRoomNo(){
        return this.roomNo;
    }
    public int getMemberNo(){
        return this.memberNo;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public int getPrice(){
        return this.price;
    }
}
