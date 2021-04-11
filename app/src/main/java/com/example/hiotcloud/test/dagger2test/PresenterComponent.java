package com.example.hiotcloud.test.dagger2test;



import com.example.hiotcloud.test.mvptest.TestMVPActivity;

import dagger.Component;
import dagger.Module;

/**
 * 测试注入器
 */
@Component(modules = TestModule.class)
public interface PresenterComponent {

    void inject(TestMVPActivity testMVPActivity);
}
