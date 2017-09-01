# Spring DeferredResult Timeout Test

## GET

Demonstrates the timeout usage:

```
curl -v 'http://localhost:8080/timeout?waitTime=1500'
```

will create a HTTP/1.1 503 Service Unavailable

while

```
curl -v 'http://localhost:8080/timeout?waitTime=500'
```

will create a HTTP/1.1 200 OK

## POST

Demonstrates the timeout usage:

```
curl -v -H'Content-Type: application/json' -d '1500' 'http://localhost:8080/timeout'
```

will create a HTTP/1.1 503 Service Unavailable

while

```
curl -v -H'Content-Type: application/json' -d '500' 'http://localhost:8080/timeout'
```

will create a HTTP/1.1 200 OK
