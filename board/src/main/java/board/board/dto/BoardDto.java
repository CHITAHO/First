package board.board.dto;

import java.time.LocalDateTime;
import lombok.Data;
import java.util.List;
@Data
public class BoardDto {
	private List<BoardFileDto> fileList;
    private int boardIdx;
    private String title;
    private String contents;
    private int hitCnt;
    private String creatorId;
    private LocalDateTime createdDatetime;
    private String updaterId;
    private LocalDateTime updatedDatetime;
}