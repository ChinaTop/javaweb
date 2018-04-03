import com.javaweb.pojo.Car;

import java.util.Date;

public class CarDaoTest {
    public void add(){
        for(int i=0;i<=60;i++){
            Car car=new Car();
            car.setName("car"+i);
            car.setPrice(1000d+i);
            car.setCreateDate(new Date());
        }
    }
}
