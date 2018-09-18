package scrabble;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrabbleController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/scrabble")
    public Scrabble scrabble(@RequestParam(value="list", defaultValue="") String list) {
        return new Scrabble(counter.incrementAndGet(),
                String.format(template, list));
    }
}
