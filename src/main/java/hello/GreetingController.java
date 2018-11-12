package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class GreetingController {

    @Autowired
    private Environment env;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String version() {
	return new String("reading env variables 1");
    }

    @RequestMapping(value = "/env", method = RequestMethod.GET)
    public String env() {

	String s = "Client Id: " + env.getProperty("foursquare-client-id")
	+ " Client Secret: " + env.getProperty("foursquare-client-secret");
	return s;
    }

}
