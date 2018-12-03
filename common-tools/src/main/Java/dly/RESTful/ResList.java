package dly.RESTful;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResList<T> implements Serializable {
    private static final long serialVersionUID = -5941000018468065041L;

    // 分页信息，结果数据
    // 页大小
    private int pageRow = 0;
    // 起始页
    private int startPage = 0;
    // 总页数
    private long totalRow = 0;
    // 返回对象
    private List<T> list;

    public ResList() {
    }

    public ResList(List<T> list )
    {
        this.list = list;
        totalRow = (list == null) ? 0 : list.size();
    }

}
