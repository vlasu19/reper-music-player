package com.xxnan.reper;

import com.xxnan.reper.common.constant.PathConstant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReperMusicServerApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testPath(){
        System.out.println(PathConstant.AVATOR_IMAGES_PATH);
    }
}
