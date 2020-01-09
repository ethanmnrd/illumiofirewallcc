I decided to focus mainly on my implementation. Unfortunately, nearing the end of two hours, I had still not made any JUnit test cases. Had I given myself more time, I would have taken the samples in the PDF, plus a few others I came up with, and created a few assertions in a small JUnit test suite. As is, I tested my solution using the main method in the Firewall java class.

The use of the Range class is one interesting choice I made. I knew I had to handle the ranges of ports and IP addresses. One solution I thought of was to have two attributes for port, and two for ip_address within the Rule object. However, I think it was a smarter move to separate that out into a separate Range object altogether, avoiding any fat objects.

Also, I put a focus on having complete classes. With getters, a proper toString, and some javadoc to go along with each method. This way, it made it easier for me to go back and read my code. Hopefully, this helps you as well.

My algorithms were not the best implementations, by far. I had theorized using Java Streams API to make the search algorithm in accept_packet more time efficient (i.e. using map-filter-reduce on the array of rules), but I decided to get it working to begin. After that, I knew I would not have time to switch over to streams, so I left my algorithm the same.

As said above, the algorithms implemented were not in any way efficient. Given some more time, I would have attempted to work out better solutions, possibly taking advantage of constant-time solutions wherever I could. The algorithm to search the rules would be the primary focus for improvement because this is the algorithm likely to be executed most often.

At the end of this challenge's two hour mark, I was on a roll. Given even another hour, I honestly believe this implementation would have been much more flushed out and complete. Not to say this implementation is bad, but I see many areas of improvement. Overall, this was an extremely fun challenge, and I thank you for not taking the standard coding challenge approach.

My preferences for the three teams are as follows:
1. Platform Team
2. Data Team
3. Policy Team
