package com.ruirua.sampleguideapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.ruirua.sampleguideapp.repositories.PinRepository;
import com.ruirua.sampleguideapp.repositories.UserRepository;

import java.util.concurrent.CountDownLatch;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private void makeLogin(UserRepository ur, String username, String password) throws InterruptedException {
        // CountDownLatch latch = ur.login(username,password);
        // latch.await();
    }
    @Test
    public void login() throws InterruptedException {
        // PinRepository p = new PinRepository()
        // UserRepository ur = new UserRepository();
        // makeLogin(ur,"abc","abc");
        // assertFalse(ur.isLogged());
        // makeLogin(ur,"premium_user","paiduser");
        // assertTrue(ur.isLogged());
        // ur.logout();
        // assertFalse(ur.isLogged());
        // makeLogin(ur,"standard_user","cheapuser");
        // assertTrue(ur.isLogged());
        // ur.logout();
        // assertFalse(ur.isLogged());


    }
}