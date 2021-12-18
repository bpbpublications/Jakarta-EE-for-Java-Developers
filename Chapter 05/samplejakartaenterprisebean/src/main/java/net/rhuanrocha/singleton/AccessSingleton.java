package net.rhuanrocha.singleton;

import javax.annotation.PostConstruct;
import javax.ejb.*;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AccessSingleton {

    private Integer count;

    @PostConstruct
    public void init(){
        this.count=0;
    }

    @Lock(LockType.WRITE)
    public void increment(){
        this.count++;
    }

    @Lock(LockType.READ)
    public Integer getCount(){
        return this.count;
    }
}
