package hello;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@Controller
public class GreetingController {

    @GetMapping("/timeout")
    public DeferredResult<ResponseEntity<?>> timeout(@RequestParam(name="waitTime") final long waitTime) {
        final DeferredResult<ResponseEntity<?>> responseEntityDeferredResult = new DeferredResult<>(1000L);
        CompletableFuture.runAsync(() -> {
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            responseEntityDeferredResult.setResult(ResponseEntity.ok().build());
        });
        return responseEntityDeferredResult;
    }

}