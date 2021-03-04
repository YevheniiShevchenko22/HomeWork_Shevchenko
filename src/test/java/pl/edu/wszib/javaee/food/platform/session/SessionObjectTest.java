package pl.edu.wszib.javaee.food.platform.session;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Yevhenii Shevchenko at 3/4/21
 * Project name: food.platform
 **/
class SessionObjectTest {

    @Test
    void getInfo() {
        SessionObject sessionObject = new SessionObject();
        String info = "INFO";

        Assert.assertNull(sessionObject.getInfo());
        sessionObject.setInfo(info);
        Assert.assertEquals(info, sessionObject.getInfo());
        Assert.assertNull(sessionObject.getInfo());

    }
}