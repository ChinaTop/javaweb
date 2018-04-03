import com.javaweb.dao.DBUtil;
import org.junit.Test;

import java.sql.Connection;

public class PoolTest {
    @Test
    public void getConnectionFormDB(){
        Connection [] conns=new Connection[50];
        long start=System.currentTimeMillis();
        for(int i=0;i<conns.length;i++){
            conns[i]= DBUtil.openConnection();
            DBUtil.close(conns[i],null,null);
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
