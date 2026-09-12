- Always when you are doing concurrency, use the thread.join() method else your required output wont works well
because thread finishes up the execution faster and does not wait for threads.

- You can create threads using
 -> Using Thread Class implementing runnable 
 -> Using lambda
 -> Using ExecutorService 