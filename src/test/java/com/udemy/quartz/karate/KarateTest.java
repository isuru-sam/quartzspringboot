package com.udemy.quartz.karate;
import com.intuit.karate.junit5.Karate;

public class KarateTest {
	@Karate.Test
    Karate testHello() {
        return Karate.run("hello").relativeTo(getClass());
    }
}
