Answer to Q5

Use of a decorator ensures clean addition of a new functionality on existing logic.

If the inventory were to create a command, execute them and dave it, it would violate many design principles, one of
them being Single Responsibility. Now your inventory is not just acting as an inventory but also as a command
orchestrator. Also, the changes are not easy to add on without interfering with the existing logic. (You essentially
change the behavior of the class)

If you choose to use a decorator, it ensures any new logic (creating,executing, and saving commands) is handled
separately and that inventory can still function independently, if you need inventory to be persisted all you need is a
decorator wrapped around your inventory. The decorator seamlessly handles the responsibility of persisting commands and
inventory to a file, while the core inventory can handle the responsibility of handling the inventory
