package fun.ciallo.blog.common.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    public Page(IPage<T> iPage) {
        this.records = iPage.getRecords();
        this.current = iPage.getCurrent();
        this.size = iPage.getSize();
        this.total = iPage.getTotal();
        this.pages = iPage.getPages();
    }
    private List<T> records;
    private Long current;
    private Long size;
    private Long total;
    private Long pages;
}
