# boom

Crash the JVM!

## Exercise

Goal: Crash the JVM and learn about error handling when things go boom.

### Steps:
1. Open `src/fastleannative/boom.clj` and change the `-main` function so that it crashes the JVM. The process should stop abruptly and create a file that starts with `hs_err_*`.
2. Inspect the `hs_err_*` file created
3. Change the `-main` function so that it spawns and new thread and crashes another thread besides the main thread.
4. Find the stack trace for the crash
5. Is there any easy way to tell which thread was the problematic thread?


