package org.example.framework.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.framework.managers.InitManager;


public class Hooks {

    @Before
    public void before() {
        InitManager.initFramework();
    }


    @After
    public void after() {
        InitManager.quitFramework();
    }
}
