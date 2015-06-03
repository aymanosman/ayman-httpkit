# Bench
`wrk -d30m -t3 -c180 --timeout 10s https://ayman-httpkit.herokuapp.com/load`
```
Running 10m test @ https://ayman-httpkit.herokuapp.com/load
  3 threads and 180 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     3.29s   242.46ms  11.11s    95.09%
    Req/Sec    17.89      2.52    19.00     88.77%
  32739 requests in 10.00m, 5.74MB read
  Socket errors: connect 0, read 0, write 0, timeout 12
Requests/sec:     54.54
Transfer/sec:      9.80KB
```

`wrk -d30m -t3 -c180 --timeout 10s https://doge-harmony.herokuapp.com/auth/load\?callback_url\=/send/choose`
```

```
