import java.sql.Date;

public class ResultEntity {
    private int id;
    private int level;
    private int time;
    private Date data;

    public ResultEntity() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "id=" + id +
                ", level=" + level +
                ", time=" + time +
                ", data=" + data +
                '}';
    }
}
