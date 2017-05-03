* 1000 thread 10 connecttion each

| Label Bytes | Samples | Average |  Min   |    Max     |   Std.Dev.     |  Error %  |  Throughput | Received KB/sec       | Sent KB/sec |Avg.|
| -------- | -------- | -------- |--------|--------|--------|--------|--------|--------|--------|--------|
HTTP Request	|10000	|      121	|  1 |	 3202 |  544.81	|  0.00%	   | 2271.69468	|   561.27	     |  503.59|	    253| 
TOTAL	      |  10000	    |  121	|  1	|     3202	|544.81	|  0.00%	|    2271.69468	   |561.27	    |   503.59	|    253|



* 1000 thread 10 connecttion each with cache

|Label Bytes |  Samples|	Average|Min|	 Max|   Std. Dev.| Error %	|Throughput	|Received KB/sec | Sent KB/sec |Avg.|
| -------- | -------- | -------- |--------|--------|--------|--------|--------|--------|--------|--------|
|HTTP Request|	10000	|      47	|  1 	| 3095	|315.83	|  0.00%	|    2360.71766|583.26	   |    523.32	|    253|
TOTAL	|        10000	 |     47	|  1	|     3095|	315.83	|  0.00%	|    2360.71766	|   583.26	|       523.32	|    253|

* 1000 thread 100 connecttion each

|Label Bytes |  Samples|	Average|Min|	 Max|   Std. Dev.| Error %	|Throughput	|Received KB/sec | Sent KB/sec |Avg.|
| -------- | -------- | -------- |--------|--------|--------|--------|--------|--------|--------|--------|
|HTTP Request|	100000	|109		|1	|24109|	456.37|	0.00%	|3956.16568	|977.53|	876.99|	253|
|TOTAL	|100000	|109		|1	|24109|	456.37|	0.00%	|3956.16568|	977.53|	876.99	|253|

* 1000 thread 100 connecttion each with cache

|Label Bytes |  Samples|	Average|Min|	 Max|   Std. Dev.| Error %	|Throughput	|Received KB/sec | Sent KB/sec |Avg.|
| -------- | -------- | -------- |--------|--------|--------|--------|--------|--------|--------|--------|
|HTTP Request	|100000	|112|	1|	21100	|432.27	|0.00%|	4623.20851|	1142.43	|1024.85|	253
|TOTAL|	100000	|112	|1	|21100|	432.27|	0.00%|	4623.20851|	1142.43|	1024.85	|253



