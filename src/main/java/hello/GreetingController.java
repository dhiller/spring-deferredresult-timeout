package hello;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@Controller
public class GreetingController {

    @GetMapping("/timeout")
    public DeferredResult<ResponseEntity<?>> timeout(@RequestParam(name="waitTime") final long waitTime) {
        return createDeferredResultWithTimeoutAndWaitTime(waitTime, null);
    }

    @PostMapping("/timeout")
    public DeferredResult<ResponseEntity<?>> timeoutPost(@RequestBody final long waitTime) {
        return createDeferredResultWithTimeoutAndWaitTime(waitTime, null);
    }

    private DeferredResult<ResponseEntity<?>> createDeferredResultWithTimeoutAndWaitTime(
            @RequestParam(name = "waitTime") long waitTime, Long timeoutAfter) {
        final DeferredResult<ResponseEntity<?>> responseEntityDeferredResult =
                timeoutAfter != null ? new DeferredResult<>(timeoutAfter) : new DeferredResult<>();
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